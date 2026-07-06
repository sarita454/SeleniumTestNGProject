# Download and update the latest ChromeDriver
$driverPath = "C:\Users\Public\Downloads\SeleniumTestNG454Framework\SeleniumTestNGProject\Driver"
$zipPath = "$driverPath\chromedriver.zip"

# Latest ChromeDriver download URL (version 127+)
$downloadUrl = "https://edgedl.me/chrome-for-testing/127.0.6533.119/win64/chromedriver-win64.zip"

Write-Host "Downloading latest ChromeDriver..."
try {
    [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
    Invoke-WebRequest -Uri $downloadUrl -OutFile $zipPath -ErrorAction Stop
    Write-Host "Download successful!"
} catch {
    Write-Host "Error downloading: $_"
    exit 1
}

# Extract the zip file
Write-Host "Extracting ChromeDriver..."
Expand-Archive -Path $zipPath -DestinationPath "$driverPath\temp" -Force

# Move the executable to the root of the Driver folder
$extractedPath = "$driverPath\temp\chromedriver-win64\chromedriver.exe"
if (Test-Path $extractedPath) {
    Copy-Item -Path $extractedPath -Destination "$driverPath\chromedriver.exe" -Force
    Write-Host "ChromeDriver updated successfully!"
} else {
    Write-Host "Could not find extracted chromedriver.exe"
    exit 1
}

# Cleanup
Remove-Item -Path "$driverPath\temp" -Recurse -Force
Remove-Item -Path $zipPath -Force

Write-Host "ChromeDriver version has been updated to the latest!"
Write-Host "Location: $driverPath\chromedriver.exe"
