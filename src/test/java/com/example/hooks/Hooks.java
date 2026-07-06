package com.example.hooks;

import com.example.base.BaseClass;
import com.example.utilities.LoggerUtility;
import com.example.utilities.ScreenshotUtility;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

	/**
	 * Initialize WebDriver before each scenario
	 */
	@Before
	public void beforeScenario(Scenario scenario) {
		// Initialize driver and navigate to base URL
		WebDriver driver = BaseClass.getDriver();
		String baseUrl = BaseClass.getBaseUrl();
		driver.navigate().to(baseUrl);
		LoggerUtility.info("===== Scenario: " + scenario.getName() + " started =====");
		LoggerUtility.info("Browser initialized and navigated to: " + baseUrl);
	}

	/**
	 * Teardown: quit driver and capture screenshot on failure
	 */
	@After
	public void afterScenario(Scenario scenario) {
		// Capture screenshot if scenario failed
		if (scenario.isFailed()) {
			try {
				WebDriver driver = BaseClass.getDriver();
				String screenshotPath = ScreenshotUtility.captureScreenshot(driver, scenario.getName());
				
				// Also attach to Cucumber report
				if (driver instanceof TakesScreenshot) {
					TakesScreenshot ts = (TakesScreenshot) driver;
					byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
					scenario.attach(screenshot, "image/png", "Failed_Scenario_" + scenario.getName());
				}
				
				LoggerUtility.error("Screenshot captured for failed scenario: " + scenario.getName() + " at: " + screenshotPath);
			} catch (Exception e) {
				LoggerUtility.error("Failed to capture screenshot: " + e.getMessage(), e);
			}
		}

		// Quit driver after scenario
		BaseClass.quitDriver();
		LoggerUtility.info("===== Scenario: " + scenario.getName() + " completed. Browser closed =====");
	}
}
