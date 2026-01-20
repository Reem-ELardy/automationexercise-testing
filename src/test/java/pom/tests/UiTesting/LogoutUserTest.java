package pom.tests.UiTesting;

import io.qameta.allure.*;
import org.testng.annotations.*;
import pom.pages.Dashboard;
import pom.pages.HomePage;
import pom.pages.SignUpLoginPage;
import utils.Framework.DriverFactory;
import utils.Framework.JsonFileReader;
import utils.Framework.TestNgListener;

@Feature("Logout User")
@Listeners({TestNgListener.class})
public class LogoutUserTest extends BaseTestClass{
    JsonFileReader testDataManager;

    @Test
    @Description("Logs in with a registered user, verifies login, performs logout, and verifies that the login page is displayed again")
    @Severity(SeverityLevel.NORMAL)
    @Story("User Logout Functionality")
    @TmsLink("AT-15")
    public void TC4_LogoutUserTest() {
        new Dashboard()
                .clickSignupLoginButton();

        new SignUpLoginPage()
                .assertLoginFormTitle(testDataManager.getData("AssertionData.LoginFormTitle"))
                .login(testDataManager.getData("userTestData.email"), testDataManager.getData("userTestData.password"));

        new Dashboard()
                .assertOnLoggedInAs(testDataManager.getData("userTestData.username"))
                .clickLogOutButton();

        new SignUpLoginPage()
                .assertLoginFormTitle(testDataManager.getData("AssertionData.LoginFormTitle"));
    }

    @BeforeClass(description = "SetUp browser, json file reader and Navigate to home page before each test method")
    public void setUp() {
        DriverFactory.initiateDriver();
        testDataManager = new JsonFileReader("UITestingData/logOutTestData.json");
        new HomePage().navigateToHomePage();
    }

    @AfterClass(description = "Close the browser after the test suite")
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
