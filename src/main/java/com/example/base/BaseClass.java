package com.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Locale;
import java.util.Properties;

public class BaseClass {

	// shared WebDriver instance for tests/page objects
	public static WebDriver driver;
	public static Properties props = new Properties();

	// initialize properties and driver (idempotent)
	public static void initDriver() {
		if (driver != null) {
			return; // already initialized
		}

		// load properties from classpath (src/main/resources/config.properties)
		try (InputStream in = BaseClass.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (in != null) {
				props.load(in);
			} // else proceed with defaults
		} catch (IOException e) {
			throw new RuntimeException("Failed to load config.properties", e);
		}

		// determine browser: system property `browser` takes precedence, then properties file `browser`, default chrome
		String browser = System.getProperty("browser");
		if (browser == null || browser.isBlank()) {
			browser = props.getProperty("browser", "chrome");
		}
		browser = browser.toLowerCase(Locale.ROOT).trim();

		// optional headless flag
		String headlessProp = System.getProperty("headless");
		if (headlessProp == null) {
			headlessProp = props.getProperty("headless", "false");
		}
		boolean headless = Boolean.parseBoolean(headlessProp);

		switch (browser) {
			case "firefox":
			case "ff":
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions ffOptions = new FirefoxOptions();
				if (headless) ffOptions.addArguments("-headless");
				driver = new FirefoxDriver(ffOptions);
				break;
			case "chrome":
			default:
				// Try to use ChromeDriver from project location (Driver/chromedriver.exe)
				String chromeDriverPath = props.getProperty("chromedriver.path");
				if (chromeDriverPath != null && !chromeDriverPath.isBlank()) {
					// Resolve path relative to project root
					Path driverPath = Paths.get(System.getProperty("user.dir"), chromeDriverPath.trim());
					if (Files.exists(driverPath)) {
						System.setProperty("webdriver.chrome.driver", driverPath.toAbsolutePath().toString());
						System.out.println("Using ChromeDriver from: " + driverPath.toAbsolutePath());
					} else {
						System.out.println("ChromeDriver not found at: " + driverPath + ", falling back to WebDriverManager");
						WebDriverManager.chromedriver().forceDownload().setup();
					}
				} else {
					// Fall back to WebDriverManager if no path is configured
					WebDriverManager.chromedriver().forceDownload().setup();
				}
				
				ChromeOptions options = new ChromeOptions();
				// allow remote origins for newer ChromeDriver/Chrome combinations
				options.addArguments("--remote-allow-origins=*");
				if (headless) options.addArguments("--headless=new");
				// recommended options for reliability in CI
				options.addArguments("--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080");
				driver = new ChromeDriver(options);
				break;
		}

		// apply timeouts from properties or use defaults
		int implicitWait = 5;
		int pageLoadTimeout = 60;
		try {
			String iw = props.getProperty("implicit.wait");
			if (iw != null && !iw.isBlank()) implicitWait = Integer.parseInt(iw.trim());
		} catch (NumberFormatException ignored) {
		}
		try {
			String plt = props.getProperty("page.load.timeout");
			if (plt != null && !plt.isBlank()) pageLoadTimeout = Integer.parseInt(plt.trim());
		} catch (NumberFormatException ignored) {
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
		try {
			driver.manage().window().maximize();
		} catch (Exception ignored) {
			// some drivers in headless mode may not support maximize
		}
	}

	// Get the WebDriver instance (initialize if necessary)
	public static WebDriver getDriver() {
		if (driver == null) {
			initDriver();
		}
		return driver;
	}

	// Quit and cleanup the driver
	public static void quitDriver() {
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception ignored) {
			}
			driver = null;
		}
	}

	// Helper to get base URL from properties
	public static String getBaseUrl() {
		String sys = System.getProperty("base.url");
		if (sys != null && !sys.isBlank()) {
			return sys;
		}
		String prop = props.getProperty("base.url");
		if (prop != null && !prop.isBlank()) {
			return prop;
		}
		return "http://localhost";
	}

}

