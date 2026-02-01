package pom.tests.E2ETesting;

import io.qameta.allure.*;
import org.testng.annotations.*;
import pom.pages.*;
import utils.Framework.APICallsUtils;
import utils.Framework.DriverFactory;
import utils.Framework.JsonFileReader;
import utils.Framework.TestNgListener;
import utils.HelperClasses.APIEndpoints;
import utils.HelperClasses.APIFunctions;

@Feature("User Account Management")
@Listeners({TestNgListener.class})
public class AccountManagementE2ETest {
    JsonFileReader testDataManager;

    @Test
    @Description("Logs in, deletes the user account, and verifies login fails with an invalid login warning")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User Account Management")
    @TmsLink("AT-39")
    public void TC27_DeleteUserAccountTest() {
        new Dashboard()
                .clickSignupLoginButton();
        new SignUpLoginPage()
                .login(testDataManager.getData("userTestData.email"), testDataManager.getData("userTestData.password"));
        new Dashboard()
                .assertOnLoggedInAs(testDataManager.getData("userTestData.username"))
                .clickDeleteAccountButton();
        new AccountDeletedPage()
                .assertAccountDeletedPageTitle(testDataManager.getData("AssertionData.AccountDeletedPageTitle"))
                .clickContinueButton();
        new Dashboard()
                .clickSignupLoginButton();
        new SignUpLoginPage()
                .login(testDataManager.getData("userTestData.email"), testDataManager.getData("userTestData.password"))
                .assertOnInvalidLoginWarning(testDataManager.getData("AssertionData.LoginWarning"));
    }

    @BeforeClass(description = "SetUp browser, json file reader and Navigate to home page")
    public void setUp() {
        DriverFactory.initiateDriver();
        testDataManager = new JsonFileReader("E2ETestingData/AccountManagementData.json");
        APIFunctions.createUser(testDataManager.getTestDataMap("RegisterFirstUserData"));
        new HomePage()
                .navigateToHomePage();
    }

    @AfterClass(description = "Close the browser after the test suite")
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
