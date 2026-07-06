package com.example.stepdefinitions;

import com.example.base.BaseClass;
import com.example.pages.GmailOpen;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SampleSteps {

    private WebDriver driver;
    private GmailOpen gmailOpen;
    private boolean opened = false;

    /**
     * Initialize WebDriver and LoginPage (called after @Before hook)
     */
    @Before
    public void initializePages() {
        driver = BaseClass.getDriver();
        gmailOpen = new GmailOpen(driver);
    }

    @Given("I click on the gmail link")
    public void i_click_on_the_gmail_link() throws InterruptedException {
    	
        gmailOpen.clickedOnGmailLink();
    }

    @Then("I verify gmail logo is displayed")
    public void i_verify_gmail_logo_is_displayed() {
        gmailOpen.verifyGmailLogoDisplayed();
    }

    
}
