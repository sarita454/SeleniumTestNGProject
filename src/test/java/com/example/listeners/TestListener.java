package com.example.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import com.example.utilities.LoggerUtility;
import com.example.utilities.ScreenshotUtility;
import org.openqa.selenium.WebDriver;
import com.example.base.BaseClass;

/**
 * TestListener implementation for test lifecycle management
 * Logs test events and captures screenshots on failure
 */
public class TestListener implements ITestListener {

    /**
     * Invoked when test starts
     */
    @Override
    public void onTestStart(ITestResult result) {
        LoggerUtility.info("===== Test: " + result.getName() + " STARTED =====");
    }

    /**
     * Invoked when test passes
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        LoggerUtility.info("===== Test: " + result.getName() + " PASSED =====");
    }

    /**
     * Invoked when test fails
     */
    @Override
    public void onTestFailure(ITestResult result) {
        LoggerUtility.error("===== Test: " + result.getName() + " FAILED =====");
        LoggerUtility.error("Failure Reason: " + result.getThrowable().getMessage());
        
        try {
            WebDriver driver = BaseClass.getDriver();
            if (driver != null) {
                String screenshotPath = ScreenshotUtility.captureScreenshot(driver, result.getName());
                LoggerUtility.error("Screenshot saved at: " + screenshotPath);
            }
        } catch (Exception e) {
            LoggerUtility.warn("Failed to capture screenshot on test failure: " + e.getMessage());
        }
    }

    /**
     * Invoked when test is skipped
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        LoggerUtility.warn("===== Test: " + result.getName() + " SKIPPED =====");
        LoggerUtility.warn("Skip Reason: " + result.getThrowable() != null ? result.getThrowable().getMessage() : "Not specified");
    }

    /**
     * Invoked when test fails but within success percentage
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LoggerUtility.warn("===== Test: " + result.getName() + " FAILED BUT WITHIN SUCCESS PERCENTAGE =====");
    }

    /**
     * Invoked before test class is instantiated
     */
    @Override
    public void onStart(org.testng.ITestContext context) {
        LoggerUtility.info("===== Test Suite: " + context.getName() + " STARTED =====");
    }

    /**
     * Invoked after all tests have run
     */
    @Override
    public void onFinish(org.testng.ITestContext context) {
        LoggerUtility.info("===== Test Suite: " + context.getName() + " FINISHED =====");
        LoggerUtility.info("Total Tests Run: " + context.getAllTestMethods().length);
        LoggerUtility.info("Passed: " + context.getPassedTests().size());
        LoggerUtility.info("Failed: " + context.getFailedTests().size());
        LoggerUtility.info("Skipped: " + context.getSkippedTests().size());
    }

}
