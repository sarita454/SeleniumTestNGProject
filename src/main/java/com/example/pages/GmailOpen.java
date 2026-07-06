package com.example.pages;

import com.example.utilities.*;

import com.example.base.BaseClass;
import com.example.utilities.LoggerUtility;
import com.example.utilities.ScreenshotUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailOpen {

	// WebDriver reference
		private WebDriver driver;

		@FindBy(xpath= "//div[@class= \"gb_6 gb_7\"][1]/a")
		private WebElement gmailLink;
		
		// Page locators using @FindBy annotation
		@FindBy(xpath= "//a[@aria-label=\"Open the Google Gmail page\"]")
		private WebElement gmailLogo;
		
		public GmailOpen(WebDriver driver) {
			this.driver = driver;
			// Initialize page elements using PageFactory
			PageFactory.initElements(driver, this);
		}
		
		public void clickedOnGmailLink() throws InterruptedException {
			Thread.sleep(3000);
			ScreenshotUtility.captureScreenshot(driver, "GmailLinkClicked");
			gmailLink.click();
			LoggerUtility.info("Clicked on Gmail link");
		}
		public void verifyGmailLogoDisplayed() {
			ScreenshotUtility.captureScreenshot(driver, "GmailLogoDisplayed");
			gmailLogo.isDisplayed();
			LoggerUtility.info("Verified Gmail logo is displayed");
		}

		
	

}
