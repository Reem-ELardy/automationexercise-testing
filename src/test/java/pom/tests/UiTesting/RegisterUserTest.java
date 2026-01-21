package pom.tests.UiTesting;

import io.qameta.allure.*;
import org.testng.annotations.*;
import utils.Framework.DriverFactory;
import utils.Framework.JsonFileReader;
import utils.Framework.TestNgListener;
import utils.HelperFunctions.UserFunctions;
import pom.pages.*;


@Feature("Register User")
@Listeners({TestNgListener.class})
public class RegisterUserTest extends BaseTestClass {
    JsonFileReader testDataManager;

    @Test
    @Description("Register a new User")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User Registration Validation")
    @TmsLink("AT-12")
    public void TC1_RegisterNewUser() {
        new HomePage()
                .assertHomePageVisible();
        new Dashboard()
                .clickSignupLoginButton();
        new SignUpLoginPage()
                .assertSignupFormTitle(testDataManager.getData("AssertionData.SignUpFormTitle"))
                .SignUp(testDataManager.getData("userTestData.username"), testDataManager.getData("userTestData.email"));
        new SignUpPage()
                .assertOnEnterAccountInformationTitle(testDataManager.getData("AssertionData.EnterAccountInfoTitle"))
                .fillAccountInformation(testDataManager.getData("userTestData.title"), testDataManager.getData("userTestData.password"), testDataManager.getData("userTestData.day"), testDataManager.getData("userTestData.month"), testDataManager.getData("userTestData.year"))
                .fillAddressPersonalInformation(testDataManager.getData("userTestData.firstName"), testDataManager.getData("userTestData.lastName"), testDataManager.getData("userTestData.company"), testDataManager.getData("userTestData.mobileNumber"))
                .fillAddressLocationInformation(testDataManager.getData("userTestData.address"), testDataManager.getData("userTestData.address2"), testDataManager.getData("userTestData.country"), testDataManager.getData("userTestData.state"), testDataManager.getData("userTestData.city"), testDataManager.getData("userTestData.zipCode"))
                .clickOnCreateAccountButton();
        new AccountCreatedPage()
                .assertAccountCreatedPageTitle(testDataManager.getData("AssertionData.AccountCreatedPageTitle"))
                .clickContinueButton();
        new Dashboard()
                .assertOnLoggedInAs(testDataManager.getData("userTestData.username"));
    }

    @Test
    @Description("Verify that attempting to register with an existing user shows an error")
    @Severity(SeverityLevel.NORMAL)
    @Story("User Registration Validation")
    @TmsLink("AT-16")
    public void TC5_RegisterExistingUser() {
        new HomePage()
                .assertHomePageVisible();
        new Dashboard()
                .clickSignupLoginButton();
        new SignUpLoginPage()
                .assertSignupFormTitle(testDataManager.getData("AssertionData.SignUpFormTitle"))
                .SignUp(testDataManager.getData("userTestData2.username"), testDataManager.getData("userTestData2.email"))
                .assertOnSignUpError(testDataManager.getData("AssertionData.SignUpFormError"));
    }

    @BeforeMethod(description = "Navigate to Home page before each test case")
    public void beforeMethod() {
        new HomePage()
                .navigateToHomePage();
    }

    @AfterMethod(description = "Delete User account if the user is logged In")
    public void afterMethod() {
        if (!new SignUpLoginPage().isUserInSignUpLoginPage()) {
            UserFunctions.deleteUserAccount();
        }
    }

    @BeforeClass(description = "SetUp browser, json file reader")
    public void setUp() {
        DriverFactory.initiateDriver();
        testDataManager = new JsonFileReader("UITestingData/registerTestData.json");
    }

    @AfterClass(description = "Close the browser after the test suite")
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
