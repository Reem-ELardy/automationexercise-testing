package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.Framework.DriverFactory;
import utils.Framework.ElementActions;

public class AccountDeletedPage {
    //Variables
    private WebDriver driver;

    //Locators
    private By accountDeletedTitle = By.xpath("(//div//h2)[1]");
    private By continueButton = By.xpath("//a[@data-qa = 'continue-button']");

    //Constructor
    public AccountDeletedPage() {
        this.driver = DriverFactory.getDriver();
    }

    //Actions
    @Step("Click On Continue Button")
    public void clickContinueButton() {
        ElementActions.Click(continueButton);
    }

    //Validation
    @Step("Assert On Account Deleted Page Title")
    public AccountDeletedPage assertAccountDeletedPageTitle(String title) {
        Assert.assertEquals(ElementActions.getText(accountDeletedTitle), title);
        return this;
    }
}
