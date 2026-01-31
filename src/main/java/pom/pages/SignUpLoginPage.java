package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.Framework.DriverFactory;
import utils.Framework.ElementActions;

import java.sql.SQLOutput;

public class SignUpLoginPage {
    //Variables

    //Locators
    private By loginEmailField = By.xpath("//input[@data-qa= 'login-email']");
    private By loginPasswordField = By.xpath("//input[@type= 'password']");
    private By loginButton = By.xpath("//button[@data-qa= 'login-button']");
    private By usernameSignUpField = By.xpath("//input[@data-qa= 'signup-name']");
    private By signUpEmailField = By.xpath("//input[@data-qa= 'signup-email']");
    private By signUpButton = By.xpath("//button[@data-qa= 'signup-button']");
    private By SignUpFormTitle = By.xpath("//div[@class = 'signup-form']//h2");
    private By LoginFormTitle = By.xpath("//div[@class = 'login-form']//h2");
    private By loginWarningText = By.xpath("//form[@action='/login']/p");
    private By signUpErrorMessage = By.xpath("//form[@action='/signup']/p");


    //Constructor

    //Actions
    @Step("Login User Steps")
    public SignUpLoginPage login(String loginEmail, String loginPassword) {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Type(loginEmailField, loginEmail);
        ElementActions.Type(loginPasswordField, loginPassword);
        ElementActions.Click(loginButton);
        return this;
    }

    @Step("Sign Up User Steps")
    public SignUpLoginPage SignUp(String username, String signUpEmail) {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Type(usernameSignUpField, username);
        ElementActions.Type(signUpEmailField, signUpEmail);
        ElementActions.Click(signUpButton);
        return this;
    }

    //Validation
    @Step("Assert that user is in SignUp/Login Page")
    public boolean isUserInSignUpLoginPage() {
        String actualUrl = DriverFactory.getDriver().getCurrentUrl();
        return actualUrl.contains("/login") || actualUrl.contains("/signup");
    }

    @Step("Assert On Login Form Title")
    public SignUpLoginPage assertLoginFormTitle(String title) {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.scrollToElement(LoginFormTitle);
        Assert.assertEquals(ElementActions.getText(LoginFormTitle), title);
        return this;
    }

    @Step("Assert on Login Warning")
    public void assertOnInvalidLoginWarning(String warningText) {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertEquals(ElementActions.getText(loginWarningText), warningText);
    }

    @Step("Assert On SignUp Form Title")
    public SignUpLoginPage assertSignupFormTitle(String title) {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.scrollToElement(SignUpFormTitle);
        Assert.assertEquals(ElementActions.getText(SignUpFormTitle), title);
        return this;
    }

    @Step("Assert on SignUp Error")
    public void assertOnSignUpError(String warningText) {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertEquals(ElementActions.getText(signUpErrorMessage), warningText);
    }
}
