package utils.Framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    static public WebElement explicitWaitToBeVisibile(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(Long.parseLong(System.getProperty("waitTime"))));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    static public WebElement explicitWaitToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(Long.parseLong(System.getProperty("waitTime"))));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    static public void explicitWaitToBeInvisibile(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(Long.parseLong(System.getProperty("waitTime"))));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static WebElement waitForDropDownELementsToBeVisible(By locator) {
        Select select = new Select(Waits.explicitWaitToBeVisibile(locator));

        WebDriverWait wait = new WebDriverWait(
                DriverFactory.getDriver(),
                Duration.ofSeconds(Integer.parseInt(System.getProperty("waitTimeOutSeconds")))
        );

        wait.until(driver -> select.getOptions().size() > 1);
        return Waits.explicitWaitToBeVisibile(locator);
    }

}
