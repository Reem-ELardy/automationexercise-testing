package utils.Framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;


public class ElementActions {

    static public List<WebElement> getElements(By locator) {
        Waits.explicitWaitToBeVisibile(locator);
        return DriverFactory.getDriver().findElements(locator);
    }

    static public WebElement getElement(By locator) {
        Waits.explicitWaitToBeVisibile(locator);
        return DriverFactory.getDriver().findElement(locator);
    }

    static public void Type(By elementLocator, String input) {
        if (!getElement(elementLocator).getAttribute("value").isBlank()) {
            getElement(elementLocator).clear();
        }
        try {
            getElement(elementLocator).sendKeys(input);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    static public void Click(By elementLocator) {
        Waits.explicitWaitToBeClickable(elementLocator);
        getElement(elementLocator).click();
    }

    public static void clickElementFromList(By locator, String visibleText) {
        List<WebElement> elements = getElements(locator);
        for (WebElement el : elements) {
            if (el.getText().trim().toLowerCase().contains(visibleText)) {
                el.click();
                return;
            }
        }
        Assert.fail("Element with text '" + visibleText + "' not found for locator: " + locator);
    }


    static public void SelectByValue(By elementLocator, String value) {
        Select selectOption = new Select(getElement(elementLocator));
        try {
            selectOption.selectByValue(value);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    static public void SelectByIndex(By elementLocator, int Index) {
        Select selectOption = new Select(getElement(elementLocator));
        try {
            selectOption.selectByIndex(Index);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    static public void SelectByConatinsText(By elementLocator, String text) {
        Select selectOption = new Select(getElement(elementLocator));
        try {
            selectOption.selectByContainsVisibleText(text);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    static public void selectDropdownByVisibleText(By elementLocator, String text) {
        Select selectOption = new Select(getElement(elementLocator));
        try {
            selectOption.selectByVisibleText(text);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    static public String getText(By elementLocator) {
        try {
            return getElement(elementLocator).getText();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return "";
    }

    static public String getValueAttribute(By elementLocator) {
        try {
            return getElement(elementLocator).getAttribute("value");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return "";
    }

    public static void scrollToElement(By elementLocator) {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.scrollToElement(getElement(elementLocator)).perform();
    }

    public static void HoverOnElement(By elementLocator) {
        Actions actions = new Actions(DriverFactory.getDriver());
        actions.moveToElement(getElement(elementLocator)).perform();
    }

    public static void checkCheckbox(By elementLocator) {
        WebElement checkbox = getElement(elementLocator);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public static boolean isELementEnabled(By elementLocator) {
        return getElement(elementLocator).isEnabled();
    }

    public static boolean isELementDisplayed(By elementLocator) {
        return getElement(elementLocator).isDisplayed();
    }

    public static boolean isElementPresent(By locator) {
        try {
            return DriverFactory.getDriver().findElements(locator).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }


}
