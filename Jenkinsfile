pipeline {
    // Use a generic agent so the pipeline doesn't fail if a 'windows' labeled node
    // is not present. Change to a specific label if your Jenkins has a Windows agent.
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
        // This pipeline checks out the main branch only.
        // Optional: credentials id configured in Jenkins for accessing the git repo
        string(name: 'GIT_CREDENTIALS_ID', defaultValue: 'sarita454', description: 'Optional: Jenkins credentialsId to use for git checkout')
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Prefer the built-in 'git' step - use credentials only when provided.
                    if (params.GIT_CREDENTIALS_ID?.trim()) {
                        echo "Checking out repository with credentials id: ${params.GIT_CREDENTIALS_ID}"
                        git branch: 'main', url: 'https://github.com/sarita454/SeleniumTestNGProject.git', credentialsId: params.GIT_CREDENTIALS_ID
                    } else {
                        echo 'Checking out repository without credentials'
                        git branch: 'main', url: 'https://github.com/sarita454/SeleniumTestNGProject.git'
                    }
                }
            }
        } // This brace was previously closing the entire 'stages' block by mistake!

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
                // Replace with your actual build command (e.g., mvn clean package, npm run build)
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running automated tests...'
                // Example of running tests and redirecting logs to your log directory
                bat "mvn test -Dcucumber.filter.tags=\"${params.CUCUMBER_TAGS}\" -Dmaven.test.failure.ignore=true"
            }
        }

        stage('Publish Reports') {
            steps {
                echo 'Generating test and coverage reports...'
                // Example using Windows curl to fetch a report template or notify a dashboard
                // Escaped double quotes (\\\") are required for Windows cmd JSON payloads
                bat """
                    curl -X POST "https://example.com" ^
                    -H "Content-Type: application/json" ^
                    -d "{\\\"buildNumber\\\":\\\"${BUILD_NUMBER}\\\"}"
                """
            }
        }
    } // The stages block now correctly closes here, encompassing all steps.
        
    post {
        always {
            // archiveArtifacts requires a workspace (hudson.FilePath). When the pipeline
            // was never allocated a node (e.g. no matching label), this step fails
            // with MissingContextVariableException. Guard with a check for WORKSPACE.
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
            echo 'Pipeline completed successfully. Notifying team...'
            // Example curl command to send a success webhook (e.g., to Teams or Slack)
            bat 'curl -X POST "https://example.com" -d "status=success"'
        }
        failure {
            echo 'Pipeline failed. Checking logs...'
            bat 'curl -X POST "https://example.com" -d "status=failed"'
        }
    }
}
