package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.Framework.DriverFactory;
import utils.Framework.ElementActions;

public class AccountCreatedPage {
    //Variables
    private WebDriver driver;

    //Locators
    private By accountCreatedTitle = By.xpath("(//div//h2)[1]");
    private By continueButton = By.xpath("//a[@data-qa = 'continue-button']");

    //Constructor
    public AccountCreatedPage() {
        this.driver = DriverFactory.getDriver();
    }

    //Actions
    @Step("Click on Continue Button")
    public void clickContinueButton() {
        ElementActions.Click(continueButton);
    }

    //Validation
    @Step("Assert on Account Created Page Title")
    public AccountCreatedPage assertAccountCreatedPageTitle(String title) {
        Assert.assertEquals(ElementActions.getText(accountCreatedTitle), title);
        return this;
    }
}
