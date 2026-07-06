package com.example.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtility {

    private static final String SCREENSHOT_DIR = System.getProperty("user.dir") + "/screenshots/";

    static {
        // Create screenshots directory if it doesn't exist
        File dir = new File(SCREENSHOT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Capture screenshot with timestamp
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            String destination = SCREENSHOT_DIR + fileName;
            
            File destinationFile = new File(destination);
            FileUtils.copyFile(source, destinationFile);
            
            LoggerUtility.info("Screenshot captured: " + destination);
            return destination;
        } catch (Exception e) {
            LoggerUtility.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Capture screenshot with custom name
     */
    public static String captureScreenshot(WebDriver driver) {
        return captureScreenshot(driver, "screenshot");
    }

    /**
     * Get screenshots directory path
     */
    public static String getScreenshotDir() {
        return SCREENSHOT_DIR;
    }
}
