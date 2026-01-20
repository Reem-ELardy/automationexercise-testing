package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.Framework.DriverFactory;
import utils.Framework.ElementActions;

public class HomePage {
    //Variables
    private String url = System.getProperty("baseUrl");

    //Locators
    private By slider = By.id("slider");

    //Constructor

    //Actions
    @Step("Navigate to Home Page")
    public HomePage navigateToHomePage() {
        DriverFactory.getDriver().navigate().to(url);
        return this;
    }

    //Validation
    @Step("Validate that Home Page is Visible")
    public void assertHomePageVisible() {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.isELementDisplayed(slider));
    }
}
