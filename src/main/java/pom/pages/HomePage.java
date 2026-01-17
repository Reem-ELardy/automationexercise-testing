package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.Framework.DriverFactory;

public class HomePage {
    //Variables
    private WebDriver driver;
    private String url = System.getProperty("baseUrl");

    //Locators
    private By slider = By.id("slider");

    //Constructor
    public HomePage() {
        this.driver = DriverFactory.getDriver();
    }

    //Actions
    @Step("Navigate to Home Page")
    public HomePage navigateToHomePage() {
        driver.navigate().to(url);
        return this;
    }

    //Validation
    @Step("Validate that Home Page is Visible")
    public void assertHomePageVisible() {
        Assert.assertTrue(driver.findElement(slider).isDisplayed());
    }
}
