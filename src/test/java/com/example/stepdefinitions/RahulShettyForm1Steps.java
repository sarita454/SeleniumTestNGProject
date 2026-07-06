package com.example.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.example.base.BaseClass;
import com.example.pages.RahulShettyForm1Page;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class RahulShettyForm1Steps {
	
	private WebDriver driver;
    private RahulShettyForm1Page form1Page;
    private boolean opened = false;

    /**
     * Initialize WebDriver and LoginPage (called after @Before hook)
     */
    @Before
    public void initializePages() {
        driver = BaseClass.getDriver();
        form1Page = new RahulShettyForm1Page(driver);
    }


	@Given("user is on Rahulshetty Practice website")
	public void user_is_on_rahulshetty_practice_website() {
	    form1Page.userIsOnRahulshettyPracticeWebsite();
	    
	}
	@When("user selects radio button")
	public void user_selects_radio_button() {
	    
		form1Page.userSelectsRadioButton();
	}
	@Then("user should see radio button is selected")
	public void user_should_see_radio_button_is_selected() {
	    
		form1Page.userShouldSeeRadioButtonIsSelected();
	}
	
}
