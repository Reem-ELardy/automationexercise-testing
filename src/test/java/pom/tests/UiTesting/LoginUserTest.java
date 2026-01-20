package pom.tests.UiTesting;

import io.qameta.allure.*;
import org.testng.annotations.*;
import pom.pages.*;
import utils.Framework.DriverFactory;
import utils.Framework.JsonFileReader;
import utils.Framework.TestNgListener;
import utils.HelperFunctions.UserFunctions;

@Feature("User Login")
@Listeners({TestNgListener.class})
public class LoginUserTest extends BaseTestClass{
    JsonFileReader loginTestDataManager;

    @Test(description = "login With Valid Credentials")
    @Description("Login in user with a registered user email and correct password")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Valid Login")
    @TmsLink("AT-13")
    public void TC2_loginWithValidCredentials() {
        new HomePage()
                .assertHomePageVisible();
        new Dashboard()
                .clickSignupLoginButton();
        new SignUpLoginPage()
                .assertLoginFormTitle(loginTestDataManager.getData("AssertionData.LoginFormTitle"))
                .login(loginTestDataManager.getData("UserData.validUserData.email"), loginTestDataManager.getData("UserData.validUserData.password"));
        new Dashboard()
                .assertOnLoggedInAs(loginTestDataManager.getData("UserData.validUserData.username"))
                .clickLogOutButton();
    }

    @Test(description = "login With Invalid Credentials")
    @Description("Login in user with a user that is not registered")
    @Severity(SeverityLevel.NORMAL)
    @Story("Invalid Login Warning")
    @TmsLink("AT-14")
    public void TC3_loginWithInvalidCredentials() {
        new HomePage()
                .assertHomePageVisible();
        new Dashboard()
                .clickSignupLoginButton();
        new SignUpLoginPage()
                .assertLoginFormTitle(loginTestDataManager.getData("AssertionData.LoginFormTitle"))
                .login(loginTestDataManager.getData("UserData.invalidUserData.email"), loginTestDataManager.getData("UserData.invalidUserData.password"))
                .assertOnInvalidLoginWarning(loginTestDataManager.getData("AssertionData.LoginWarning"));
    }

    @BeforeMethod(description = "Navigate to home page before each test method")
    public void beforeMethod() {
        new HomePage().navigateToHomePage();
    }

    @BeforeClass(description = "SetUp browser, json file reader")
    public void setUp() {
        DriverFactory.initiateDriver();
        loginTestDataManager = new JsonFileReader("UITestingData/loginTestData.json");
    }

    @AfterClass(description = "Close the browser after the tests")
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
