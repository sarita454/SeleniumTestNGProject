package com.example.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ActionsUtility {

    /**
     * Get Actions instance for a WebDriver
     */
    private static Actions getActionsInstance(WebDriver driver) {
        return new Actions(driver);
    }

    /**
     * Click on a web element
     */
    public static void click(WebDriver driver, WebElement element) {
        try {
            Actions actions = getActionsInstance(driver);
            actions.click(element).perform();
            LoggerUtility.info("Clicked on element: " + element.getText());
        } catch (Exception e) {
            LoggerUtility.error("Failed to click element: " + e.getMessage(), e);
            throw e;
        }
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
    public static void typeText(WebDriver driver, WebElement element, String text) {
        try {
            Actions actions = getActionsInstance(driver);
            actions.click(element).sendKeys(text).perform();
            LoggerUtility.info("Typed text: " + text + " into element");
        } catch (Exception e) {
            LoggerUtility.error("Failed to type text: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Send keys to an element (for special keys like ENTER, TAB, etc.)
     */
    public static void sendKeys(WebDriver driver, WebElement element, Keys key) {
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
    public static void sendKeys(WebDriver driver, WebElement element, String keys) {
        try {
            Actions actions = getActionsInstance(driver);
            actions.click(element).sendKeys(keys).perform();
            LoggerUtility.info("Sent keys to element");
        } catch (Exception e) {
            LoggerUtility.error("Failed to send keys: " + e.getMessage(), e);
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
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", element);
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
     * Scroll by offset
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
     * Scroll to element
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        try {
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", element);
            LoggerUtility.info("Scrolled to element");
        } catch (Exception e) {
            LoggerUtility.error("Failed to scroll to element: " + e.getMessage(), e);
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
     * Wait for element to be clickable and click
     */
    public static void waitAndClick(WebDriver driver, WebElement element, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            LoggerUtility.info("Waited and clicked on element");
        } catch (Exception e) {
            LoggerUtility.error("Failed to wait and click: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Wait for element to be visible
     */
    public static void waitForElementToBeVisible(WebDriver driver, WebElement element, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.visibilityOf(element));
            LoggerUtility.info("Element is visible");
        } catch (Exception e) {
            LoggerUtility.error("Failed to wait for element visibility: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Wait for element to be invisible
     */
    public static void waitForElementToBeInvisible(WebDriver driver, WebElement element, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.invisibilityOf(element));
            LoggerUtility.info("Element is now invisible");
        } catch (Exception e) {
            LoggerUtility.error("Failed to wait for element invisibility: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Execute JavaScript to click element (for hidden or stubborn elements)
     */
    public static void jsClick(WebDriver driver, WebElement element) {
        try {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            LoggerUtility.info("JavaScript clicked on element");
        } catch (Exception e) {
            LoggerUtility.error("Failed to JS click: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Highlight element using JavaScript
     */
    public static void highlightElement(WebDriver driver, WebElement element) {
        try {
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].style.border='3px solid red';", element);
            LoggerUtility.info("Element highlighted");
        } catch (Exception e) {
            LoggerUtility.error("Failed to highlight element: " + e.getMessage(), e);
        }
    }

}
