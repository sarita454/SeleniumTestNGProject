package com.example.utilities;

import org.apache.log4j.Logger;

public class LoggerUtility {

    private static final Logger logger = Logger.getLogger(LoggerUtility.class);

    /**
     * Log info level messages
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * Log warning level messages
     */
    public static void warn(String message) {
        logger.warn(message);
    }

    /**
     * Log error level messages
     */
    public static void error(String message) {
        logger.error(message);
    }

    /**
     * Log error with exception
     */
    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    /**
     * Log debug level messages
     */
    public static void debug(String message) {
        logger.debug(message);
    }

    /**
     * Log fatal level messages
     */
    public static void fatal(String message) {
        logger.fatal(message);
    }

    /**
     * Log fatal with exception
     */
    public static void fatal(String message, Throwable throwable) {
        logger.fatal(message, throwable);
    }
}
