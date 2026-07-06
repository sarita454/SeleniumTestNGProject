package com.example.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import com.example.utilities.LoggerUtility;

/**
 * AnnotationTransformer implementation to modify test annotations at runtime
 * Can be used to add retry logic, change enabled status, modify timeout, etc.
 */
public class AnnotationTransformer implements IAnnotationTransformer {

    /**
     * Transform test annotations at runtime
     * @param annotation The test annotation
     * @param testClass The test class
     * @param testConstructor The test constructor
     * @param testMethod The test method
     */
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        // Apply RetryAnalyzer to all test methods
        if (testMethod != null) {
            annotation.setRetryAnalyzer(RetryAnalyzer.class);
            
            // Set timeout to 60 seconds (60000 milliseconds) if not already set
            if (annotation.getTimeOut() == 0) {
                annotation.setTimeOut(60000);
                LoggerUtility.info("Set timeout to 60 seconds for: " + testMethod.getName());
            }
            
            LoggerUtility.debug("Applied AnnotationTransformer to method: " + testMethod.getName());
        }
    }

}
