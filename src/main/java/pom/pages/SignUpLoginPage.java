package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.Framework.DriverFactory;
import utils.Framework.ElementActions;

public class SignUpLoginPage {
    //Variables
    private WebDriver driver;

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
    public SignUpLoginPage() {
        this.driver = DriverFactory.getDriver();
    }

    //Actions
    @Step("Login User Steps")
    public SignUpLoginPage login(String loginEmail, String loginPassword) {
        ElementActions.Type(loginEmailField, loginEmail);
        ElementActions.Type(loginPasswordField, loginPassword);
        ElementActions.Click(loginButton);
        return this;
    }

    @Step("Sign Up User Steps")
    public SignUpLoginPage SignUp(String username, String signUpEmail) {
        ElementActions.Type(usernameSignUpField, username);
        ElementActions.Type(signUpEmailField, signUpEmail);
        ElementActions.Click(signUpButton);
        return this;
    }

    //Validation
    @Step("Assert On Login Form Title")
    public SignUpLoginPage assertLoginFormTitle(String title) {
        Assert.assertEquals(ElementActions.getText(LoginFormTitle), title);
        return this;
    }

    @Step("Assert on Login Warning")
    public void assertOnInvalidLoginWarning(String warningText) {
        Assert.assertEquals(ElementActions.getText(loginWarningText), warningText);
    }

    @Step("Assert On SignUp Form Title")
    public SignUpLoginPage assertSignupFormTitle(String title) {
        Assert.assertEquals(driver.findElement(SignUpFormTitle).getText(), title);
        return this;
    }

    @Step("Assert on SignUp Error")
    public void assertOnSignUpError(String warningText) {
        Assert.assertEquals(ElementActions.getText(signUpErrorMessage), warningText);
    }
}
