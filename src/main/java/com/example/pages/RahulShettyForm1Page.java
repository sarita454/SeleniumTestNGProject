package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class RahulShettyForm1Page {
	// WebDriver reference
	private WebDriver driver;

	// Page locators using @FindBy annotation
	@FindBy(xpath= "//input[@value=\"radio2\"]")
	private WebElement radioButton2 ;
	
	
	
	
	public RahulShettyForm1Page(WebDriver driver) {
		this.driver = driver;
		// Initialize page elements using PageFactory
		PageFactory.initElements(driver, this);
	}
	
	public void userIsOnRahulshettyPracticeWebsite() {
		
		String expectedTitle=driver.getTitle();
		
		Assert.assertEquals(expectedTitle, "Practice Page");
		
	}
	
	public void userSelectsRadioButton() {
		radioButton2.click();
	}
	
	public void userShouldSeeRadioButtonIsSelected() {
		Assert.assertTrue(radioButton2.isSelected(), "Radio button 2 is not selected");
	}
}
