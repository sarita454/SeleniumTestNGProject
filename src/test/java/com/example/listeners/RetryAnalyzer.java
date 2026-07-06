package com.example.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import com.example.utilities.LoggerUtility;

/**
 * RetryAnalyzer implementation to retry failed tests
 * Retries failed tests for a configurable number of times
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    // Maximum number of retries
    private static final int MAX_RETRY_COUNT = 2;
    
    // Current retry count
    private int retryCount = 0;

    /**
     * Determines if a test should be retried based on failure
     * @param result The test result
     * @return true if test should be retried, false otherwise
     */
    @Override
    public boolean retry(ITestResult result) {
        // Check if test failed and retry count hasn't exceeded max
        if (!result.isSuccess() && retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            LoggerUtility.info("Retrying test: " + result.getName() + " | Attempt: " + (retryCount + 1));
            return true; // Retry the test
        }
        
        // Reset retry count for next test
        retryCount = 0;
        return false; // Don't retry
    }

    /**
     * Get the maximum retry count
     */
    public static int getMaxRetryCount() {
        return MAX_RETRY_COUNT;
    }

}
