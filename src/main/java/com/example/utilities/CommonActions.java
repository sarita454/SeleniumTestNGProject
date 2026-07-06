package com.example.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import java.util.List;

public class CommonActions {

    // Default timeout constants
    private static final int DEFAULT_TIMEOUT = 10;
    private static final int SHORT_TIMEOUT = 5;

    /**
     * Click on an element using WebElement
     */
    public static void clickElement(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            LoggerUtility.info("Clicked on element successfully");
        } catch (Exception e) {
            LoggerUtility.error("Failed to click element: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Click on an element using By locator
     */
    public static void clickElement(WebDriver driver, By locator) {
        try {
            WebElement element = driver.findElement(locator);
            clickElement(driver, element);
        } catch (Exception e) {
            LoggerUtility.error("Failed to click element with locator: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Click element using JavaScript (for stubborn elements)
     */
    public static void clickElementJS(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            LoggerUtility.info("Clicked on element using JavaScript");
        } catch (Exception e) {
            LoggerUtility.error("Failed to click element using JS: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Type text into a text field
     */
    public static void typeText(WebDriver driver, WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
            LoggerUtility.info("Typed text: '" + text + "' into element");
        } catch (Exception e) {
            LoggerUtility.error("Failed to type text: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Type text into a field using By locator
     */
    public static void typeText(WebDriver driver, By locator, String text) {
        try {
            WebElement element = driver.findElement(locator);
            typeText(driver, element, text);
        } catch (Exception e) {
            LoggerUtility.error("Failed to type text using locator: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Select dropdown by visible text
     */
    public static void selectDropdownByVisibleText(WebDriver driver, WebElement dropdown, String visibleText) {
        try {
            Select select = new Select(dropdown);
            select.selectByVisibleText(visibleText);
            LoggerUtility.info("Selected dropdown option by visible text: '" + visibleText + "'");
        } catch (Exception e) {
            LoggerUtility.error("Failed to select dropdown by visible text: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Select dropdown by visible text using By locator
     */
    public static void selectDropdownByVisibleText(WebDriver driver, By locator, String visibleText) {
        try {
            WebElement dropdown = driver.findElement(locator);
            selectDropdownByVisibleText(driver, dropdown, visibleText);
        } catch (Exception e) {
            LoggerUtility.error("Failed to select dropdown by visible text using locator: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Select dropdown by value attribute
     */
    public static void selectDropdownByValue(WebDriver driver, WebElement dropdown, String value) {
        try {
            Select select = new Select(dropdown);
            select.selectByValue(value);
            LoggerUtility.info("Selected dropdown option by value: '" + value + "'");
        } catch (Exception e) {
            LoggerUtility.error("Failed to select dropdown by value: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Select dropdown by value attribute using By locator
     */
    public static void selectDropdownByValue(WebDriver driver, By locator, String value) {
        try {
            WebElement dropdown = driver.findElement(locator);
            selectDropdownByValue(driver, dropdown, value);
        } catch (Exception e) {
            LoggerUtility.error("Failed to select dropdown by value using locator: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Select dropdown by index
     */
    public static void selectDropdownByIndex(WebDriver driver, WebElement dropdown, int index) {
        try {
            Select select = new Select(dropdown);
            select.selectByIndex(index);
            LoggerUtility.info("Selected dropdown option by index: " + index);
        } catch (Exception e) {
            LoggerUtility.error("Failed to select dropdown by index: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Select dropdown by index using By locator
     */
    public static void selectDropdownByIndex(WebDriver driver, By locator, int index) {
        try {
            WebElement dropdown = driver.findElement(locator);
            selectDropdownByIndex(driver, dropdown, index);
        } catch (Exception e) {
            LoggerUtility.error("Failed to select dropdown by index using locator: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Get all options from dropdown
     */
    public static List<WebElement> getDropdownOptions(WebDriver driver, WebElement dropdown) {
        try {
            Select select = new Select(dropdown);
            List<WebElement> options = select.getOptions();
            LoggerUtility.info("Retrieved all dropdown options, count: " + options.size());
            return options;
        } catch (Exception e) {
            LoggerUtility.error("Failed to get dropdown options: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Get selected option from dropdown
     */
    public static String getSelectedDropdownOption(WebDriver driver, WebElement dropdown) {
        try {
            Select select = new Select(dropdown);
            WebElement selectedOption = select.getFirstSelectedOption();
            String selectedText = selectedOption.getText();
            LoggerUtility.info("Selected dropdown option: '" + selectedText + "'");
            return selectedText;
        } catch (Exception e) {
            LoggerUtility.error("Failed to get selected dropdown option: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Check if element is displayed
     */
    public static boolean isElementDisplayed(WebDriver driver, WebElement element) {
        try {
            boolean isDisplayed = element.isDisplayed();
            LoggerUtility.info("Element is displayed: " + isDisplayed);
            return isDisplayed;
        } catch (NoSuchElementException e) {
            LoggerUtility.warn("Element not found or not displayed");
            return false;
        }
    }

    /**
     * Check if element is displayed using By locator
     */
    public static boolean isElementDisplayed(WebDriver driver, By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return isElementDisplayed(driver, element);
        } catch (NoSuchElementException e) {
            LoggerUtility.warn("Element not found with locator: " + locator);
            return false;
        }
    }

    /**
     * Check if element is enabled
     */
    public static boolean isElementEnabled(WebDriver driver, WebElement element) {
        try {
            boolean isEnabled = element.isEnabled();
            LoggerUtility.info("Element is enabled: " + isEnabled);
            return isEnabled;
        } catch (Exception e) {
            LoggerUtility.error("Failed to check if element is enabled: " + e.getMessage(), e);
            return false;
        }
    }

    /**
     * Check if element is selected
     */
    public static boolean isElementSelected(WebDriver driver, WebElement element) {
        try {
            boolean isSelected = element.isSelected();
            LoggerUtility.info("Element is selected: " + isSelected);
            return isSelected;
        } catch (Exception e) {
            LoggerUtility.error("Failed to check if element is selected: " + e.getMessage(), e);
            return false;
        }
    }

    /**
     * Wait for element to be visible
     */
    public static void waitForElementVisibility(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            wait.until(ExpectedConditions.visibilityOf(element));
            LoggerUtility.info("Element is visible");
        } catch (TimeoutException e) {
            LoggerUtility.error("Element not visible within timeout: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Wait for element to be visible using By locator
     */
    public static void waitForElementVisibility(WebDriver driver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            LoggerUtility.info("Element is visible");
        } catch (TimeoutException e) {
            LoggerUtility.error("Element not visible within timeout");
            throw e;
        }
    }

    /**
     * Wait for element to be clickable
     */
    public static void waitForElementClickability(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            LoggerUtility.info("Element is clickable");
        } catch (TimeoutException e) {
            LoggerUtility.error("Element not clickable within timeout");
            throw e;
        }
    }

    /**
     * Get text from element
     */
    public static String getText(WebDriver driver, WebElement element) {
        try {
            String text = element.getText();
            LoggerUtility.info("Retrieved text: '" + text + "'");
            return text;
        } catch (Exception e) {
            LoggerUtility.error("Failed to get text from element: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Get text from element using By locator
     */
    public static String getText(WebDriver driver, By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return getText(driver, element);
        } catch (Exception e) {
            LoggerUtility.error("Failed to get text using locator: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Get attribute value from element
     */
    public static String getAttribute(WebDriver driver, WebElement element, String attribute) {
        try {
            String value = element.getAttribute(attribute);
            LoggerUtility.info("Retrieved attribute '" + attribute + "': '" + value + "'");
            return value;
        } catch (Exception e) {
            LoggerUtility.error("Failed to get attribute: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Get attribute value from element using By locator
     */
    public static String getAttribute(WebDriver driver, By locator, String attribute) {
        try {
            WebElement element = driver.findElement(locator);
            return getAttribute(driver, element, attribute);
        } catch (Exception e) {
            LoggerUtility.error("Failed to get attribute using locator: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Clear a text field
     */
    public static void clearField(WebDriver driver, WebElement element) {
        try {
            element.clear();
            LoggerUtility.info("Cleared field");
        } catch (Exception e) {
            LoggerUtility.error("Failed to clear field: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Submit a form
     */
    public static void submitForm(WebDriver driver, WebElement element) {
        try {
            element.submit();
            LoggerUtility.info("Form submitted");
        } catch (Exception e) {
            LoggerUtility.error("Failed to submit form: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Scroll to element
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            LoggerUtility.info("Scrolled to element");
        } catch (Exception e) {
            LoggerUtility.error("Failed to scroll to element: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Highlight element
     */
    public static void highlightElement(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.border='3px solid red';", element);
            LoggerUtility.info("Element highlighted");
        } catch (Exception e) {
            LoggerUtility.warn("Failed to highlight element: " + e.getMessage());
        }
    }

    /**
     * Press keyboard key
     */
    public static void pressKey(WebDriver driver, WebElement element, Keys key) {
        try {
            element.sendKeys(key);
            LoggerUtility.info("Pressed key: " + key);
        } catch (Exception e) {
            LoggerUtility.error("Failed to press key: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Press Enter key
     */
    public static void pressEnter(WebDriver driver, WebElement element) {
        try {
            element.sendKeys(Keys.ENTER);
            LoggerUtility.info("Pressed Enter key");
        } catch (Exception e) {
            LoggerUtility.error("Failed to press Enter key: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Press Tab key
     */
    public static void pressTab(WebDriver driver, WebElement element) {
        try {
            element.sendKeys(Keys.TAB);
            LoggerUtility.info("Pressed Tab key");
        } catch (Exception e) {
            LoggerUtility.error("Failed to press Tab key: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Check if element exists
     */
    public static boolean elementExists(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            LoggerUtility.info("Element exists");
            return true;
        } catch (NoSuchElementException e) {
            LoggerUtility.info("Element does not exist");
            return false;
        }
    }

    /**
     * Wait for URL to contain text
     */
    public static void waitForUrlContains(WebDriver driver, String urlPart) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            wait.until(ExpectedConditions.urlContains(urlPart));
            LoggerUtility.info("URL contains: '" + urlPart + "'");
        } catch (TimeoutException e) {
            LoggerUtility.error("URL does not contain expected text within timeout");
            throw e;
        }
    }

    /**
     * Get current page title
     */
    public static String getPageTitle(WebDriver driver) {
        try {
            String title = driver.getTitle();
            LoggerUtility.info("Page title: '" + title + "'");
            return title;
        } catch (Exception e) {
            LoggerUtility.error("Failed to get page title: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Get current page URL
     */
    public static String getCurrentUrl(WebDriver driver) {
        try {
            String url = driver.getCurrentUrl();
            LoggerUtility.info("Current URL: '" + url + "'");
            return url;
        } catch (Exception e) {
            LoggerUtility.error("Failed to get current URL: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Switch to frame by WebElement
     */
    public static void switchToFrame(WebDriver driver, WebElement frameElement) {
        try {
            driver.switchTo().frame(frameElement);
            LoggerUtility.info("Switched to frame");
        } catch (Exception e) {
            LoggerUtility.error("Failed to switch to frame: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Switch to frame by index
     */
    public static void switchToFrame(WebDriver driver, int frameIndex) {
        try {
            driver.switchTo().frame(frameIndex);
            LoggerUtility.info("Switched to frame index: " + frameIndex);
        } catch (Exception e) {
            LoggerUtility.error("Failed to switch to frame by index: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Switch back to main content
     */
    public static void switchToMainContent(WebDriver driver) {
        try {
            driver.switchTo().defaultContent();
            LoggerUtility.info("Switched to main content");
        } catch (Exception e) {
            LoggerUtility.error("Failed to switch to main content: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Accept alert
     */
    public static void acceptAlert(WebDriver driver) {
        try {
            driver.switchTo().alert().accept();
            LoggerUtility.info("Alert accepted");
        } catch (Exception e) {
            LoggerUtility.error("Failed to accept alert: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Dismiss alert
     */
    public static void dismissAlert(WebDriver driver) {
        try {
            driver.switchTo().alert().dismiss();
            LoggerUtility.info("Alert dismissed");
        } catch (Exception e) {
            LoggerUtility.error("Failed to dismiss alert: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Get alert text
     */
    public static String getAlertText(WebDriver driver) {
        try {
            String alertText = driver.switchTo().alert().getText();
            LoggerUtility.info("Alert text: '" + alertText + "'");
            return alertText;
        } catch (Exception e) {
            LoggerUtility.error("Failed to get alert text: " + e.getMessage(), e);
            throw e;
        }
    }

    // ==================== ACTIONS CLASS METHODS ====================

    /**
     * Get Actions instance for a WebDriver
     */
    private static Actions getActionsInstance(WebDriver driver) {
        return new Actions(driver);
    }

    /**
     * Double click on a web element
     */
    public static void doubleClick(WebDriver driver, WebElement element) {
        try {
            Actions actions = getActionsInstance(driver);
            actions.doubleClick(element).perform();
            LoggerUtility.info("Double clicked on element: " + element.getText());
        } catch (Exception e) {
            LoggerUtility.error("Failed to double click element: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Right click (Context click) on a web element
     */
    public static void rightClick(WebDriver driver, WebElement element) {
        try {
            Actions actions = getActionsInstance(driver);
            actions.contextClick(element).perform();
            LoggerUtility.info("Right clicked on element: " + element.getText());
        } catch (Exception e) {
            LoggerUtility.error("Failed to right click element: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Hover mouse over a web element
     */
    public static void hoverOver(WebDriver driver, WebElement element) {
        try {
            Actions actions = getActionsInstance(driver);
            actions.moveToElement(element).perform();
            LoggerUtility.info("Hovered over element: " + element.getText());
        } catch (Exception e) {
            LoggerUtility.error("Failed to hover over element: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Drag and drop element from source to target
     */
    public static void dragAndDrop(WebDriver driver, WebElement source, WebElement target) {
        try {
            Actions actions = getActionsInstance(driver);
            actions.dragAndDrop(source, target).perform();
            LoggerUtility.info("Dragged element from source to target");
        } catch (Exception e) {
            LoggerUtility.error("Failed to perform drag and drop: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Type text into a web element using Actions
     */
    public static void typeTextUsingActions(WebDriver driver, WebElement element, String text) {
        try {
            Actions actions = getActionsInstance(driver);
            actions.click(element).sendKeys(text).perform();
            LoggerUtility.info("Typed text: " + text + " into element using Actions");
        } catch (Exception e) {
            LoggerUtility.error("Failed to type text using Actions: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Send keys to an element (for special keys like ENTER, TAB, etc.)
     */
    public static void sendKeysToElement(WebDriver driver, WebElement element, Keys key) {
        try {
            Actions actions = getActionsInstance(driver);
            actions.click(element).sendKeys(key).perform();
            LoggerUtility.info("Sent key: " + key + " to element");
        } catch (Exception e) {
            LoggerUtility.error("Failed to send key: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Send multiple keys to an element
     */
    public static void sendKeysToElement(WebDriver driver, WebElement element, String keys) {
        try {
            Actions actions = getActionsInstance(driver);
            actions.click(element).sendKeys(keys).perform();
            LoggerUtility.info("Sent keys to element using Actions");
        } catch (Exception e) {
            LoggerUtility.error("Failed to send keys using Actions: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Move to element and click
     */
    public static void moveAndClick(WebDriver driver, WebElement element) {
        try {
            Actions actions = getActionsInstance(driver);
            actions.moveToElement(element).click().perform();
            LoggerUtility.info("Moved to element and clicked");
        } catch (Exception e) {
            LoggerUtility.error("Failed to move and click: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Scroll to element and click
     */
    public static void scrollAndClick(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            try {
                Thread.sleep(500); // brief pause for scroll animation
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                LoggerUtility.warn("Sleep interrupted: " + ie.getMessage());
            }
            element.click();
            LoggerUtility.info("Scrolled to element and clicked");
        } catch (Exception e) {
            LoggerUtility.error("Failed to scroll and click: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Scroll by offset using Actions
     */
    public static void scrollByOffset(WebDriver driver, int x, int y) {
        try {
            Actions actions = getActionsInstance(driver);
            actions.scrollByAmount(x, y).perform();
            LoggerUtility.info("Scrolled by offset: X=" + x + ", Y=" + y);
        } catch (Exception e) {
            LoggerUtility.error("Failed to scroll by offset: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Hold down shift and click (for multiple selection)
     */
    public static void shiftClick(WebDriver driver, WebElement element) {
        try {
            Actions actions = getActionsInstance(driver);
            actions.keyDown(Keys.SHIFT).click(element).keyUp(Keys.SHIFT).perform();
            LoggerUtility.info("Shift+Clicked on element");
        } catch (Exception e) {
            LoggerUtility.error("Failed to shift click: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Hold down control and click (for multiple selection on Windows/Linux)
     */
    public static void controlClick(WebDriver driver, WebElement element) {
        try {
            Actions actions = getActionsInstance(driver);
            actions.keyDown(Keys.CONTROL).click(element).keyUp(Keys.CONTROL).perform();
            LoggerUtility.info("Ctrl+Clicked on element");
        } catch (Exception e) {
            LoggerUtility.error("Failed to control click: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Hold down command and click (for Mac)
     */
    public static void commandClick(WebDriver driver, WebElement element) {
        try {
            Actions actions = getActionsInstance(driver);
            actions.keyDown(Keys.COMMAND).click(element).keyUp(Keys.COMMAND).perform();
            LoggerUtility.info("Command+Clicked on element");
        } catch (Exception e) {
            LoggerUtility.error("Failed to command click: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Wait for element to be invisible
     */
    public static void waitForElementInvisibility(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
            wait.until(ExpectedConditions.invisibilityOf(element));
            LoggerUtility.info("Element is now invisible");
        } catch (Exception e) {
            LoggerUtility.error("Failed to wait for element invisibility: " + e.getMessage(), e);
            throw e;
        }
    }

}
