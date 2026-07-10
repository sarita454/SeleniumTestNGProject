pipeline {
    // Use agent any to allow pipeline to run on any available agent
    // including Windows agents without needing a specific label
    agent any
    
    environment {
        // Define directory paths using Windows format
        SCREENSHOT_DIR = "build\\screenshots"
        LOG_DIR        = "build\\logs"
        REPORT_DIR     = "build\\reports"
    }

    parameters {
        // Allows you to pass specific Cucumber tags (e.g., @Smoke, @Regression, or not @WIP)
        string(name: 'CUCUMBER_TAGS', defaultValue: '@Smoke', description: 'Enter Cucumber tags to run')
    }
    
    stages {
        stage('Checkout') {
            steps {
                script {
                    // Repository is already checked out by Multibranch Pipeline SCM
                    echo 'Repository already checked out by Multibranch Pipeline SCM'
                    echo "Current commit: ${env.GIT_COMMIT}"
                    echo "Current branch: ${env.GIT_BRANCH}"
                }
            }
        }

        stage('Create Directories') {
            steps {
                echo 'Creating required directories...'
                // 'if not exist' prevents errors if the folders already exist
                bat """
                    if not exist "${SCREENSHOT_DIR}" mkdir "${SCREENSHOT_DIR}"
                    if not exist "${LOG_DIR}" mkdir "${LOG_DIR}"
                    if not exist "${REPORT_DIR}" mkdir "${REPORT_DIR}"
                """
            }
        }

        stage('Build') {
            steps {
                echo 'Compiling and building the project...'
                // Use Maven wrapper (mvnw.bat) for Windows if available, otherwise fall back to mvn
                bat '''
                    if exist mvnw.bat (
                        call mvnw.bat clean compile
                    ) else (
                        mvn clean compile
                    )
                '''
            }
        }

        stage('Test') {
            steps {
                echo 'Running automated tests...'
                // Use Maven wrapper (mvnw.bat) for Windows if available, otherwise fall back to mvn
                bat '''
                    if exist mvnw.bat (
                        call mvnw.bat test -Dcucumber.filter.tags="''' + params.CUCUMBER_TAGS + '''" -Dmaven.test.failure.ignore=true
                    ) else (
                        mvn test -Dcucumber.filter.tags="''' + params.CUCUMBER_TAGS + '''" -Dmaven.test.failure.ignore=true
                    )
                '''
            }
        }

        stage('Publish Reports') {
            steps {
                echo 'Generating test and coverage reports...'
                // Example using Windows curl to send build status
                // Escaped double quotes (\\\") are required for Windows cmd JSON payloads
                bat """
                    curl -X POST "https://example.com" ^
                    -H "Content-Type: application/json" ^
                    -d "{\\\"buildNumber\\\":\\\"${BUILD_NUMBER}\\\"}"
                """
            }
        }
    }
        
    post {
        always {
            // archiveArtifacts requires a workspace (hudson.FilePath). 
            // Guard with a check for WORKSPACE to prevent MissingContextVariableException.
            script {
                if (env.WORKSPACE) {
                    echo 'Archiving test artifacts...'
                    // Native Jenkins step to save your screenshots, logs, and reports
                    archiveArtifacts artifacts: 'build/**/*', allowEmptyArchive: true
                } else {
                    echo 'No workspace available - skipping archiveArtifacts'
                }
            }
        }
        success {
            script {
                if (env.WORKSPACE) {
                    echo 'Pipeline completed successfully. Notifying team...'
                    // Optional: send webhook notification (wrapped in workspace check)
                    bat 'curl -X POST "https://example.com" -d "status=success"'
                }
            }
        }
        failure {
            script {
                if (env.WORKSPACE) {
                    echo 'Pipeline failed. Checking logs...'
                    // Optional: send webhook notification (wrapped in workspace check)
                    bat 'curl -X POST "https://example.com" -d "status=failed"'
                }
            }
        }
    }
}