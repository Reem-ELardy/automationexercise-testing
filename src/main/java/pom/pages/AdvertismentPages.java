package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import utils.Framework.DriverFactory;
import utils.Framework.ElementActions;

public class AdvertismentPages {

    private static By close = By.id("dismiss-button");
    private static By dismiss = By.id("dismiss-button");
    private static By adDiv = By.id("ad_position_box");

    public static void closeAdsIfAny() {
        WebDriver driver = DriverFactory.getDriver();
        try {
            // Use try/catch to avoid Timeout if element not present
            if (ElementActions.isElementPresent(close) || ElementActions.isElementPresent(dismiss)) {
                try {
                    ElementActions.Click(close);
                } catch (NoSuchElementException e) {
                    ElementActions.Click(dismiss);
                }
                driver.switchTo().defaultContent(); // return to main content
                System.out.println("Ad closed successfully.");
            }
        } catch (Exception e) {
            System.out.println("No ad to close or error occurred: " + e.getMessage());
        }
    }
}
