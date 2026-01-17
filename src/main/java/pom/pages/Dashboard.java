package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.Framework.DriverFactory;
import utils.Framework.ElementActions;

public class Dashboard {
    //Variables

    //Locators
    private By signupLoginButton = By.xpath("//a[@href=\"/login\"]");
    private By loggedInAsText = By.xpath("//i[@class = 'fa fa-user']/parent::a");
    private By deleteAccountButton = By.xpath("//a[@href=\"/delete_account\"]");
    private By logOutButton = By.xpath("//a[@href=\"/logout\"]");


    //Constructor

    //Actions
    @Step("Click On Signup Login")
    public void clickSignupLoginButton() {
        ElementActions.Click(signupLoginButton);
    }

    @Step("Click On Delete Account")
    public void clickDeleteAccountButton() {
        ElementActions.Click(deleteAccountButton);
    }

    @Step("Click On Logout")
    public Dashboard clickLogOutButton() {
        ElementActions.Click(logOutButton);
        return this;
    }

    //Validation
    @Step("Assert On Logged In As Username")
    public Dashboard assertOnLoggedInAs(String username) {
        Assert.assertEquals(ElementActions.getText(loggedInAsText), "Logged in as " + username);
        return this;
    }

    @Step
    public void verifyAUserIsLoggedIn() {
        ElementActions.isELementDisplayed(loggedInAsText);
    }
}
