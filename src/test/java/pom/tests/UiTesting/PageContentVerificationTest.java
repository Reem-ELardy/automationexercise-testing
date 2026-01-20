package pom.tests.UiTesting;

import io.qameta.allure.*;
import org.testng.annotations.*;
import pom.pages.APITestingPage;
import pom.pages.Dashboard;
import pom.pages.HomePage;
import pom.pages.TestCasesPage;
import utils.Framework.DriverFactory;
import utils.Framework.JsonFileReader;
import utils.Framework.TestNgListener;

@Feature("Page Content Verification")
public class PageContentVerificationTest {
    JsonFileReader testDataManager;

    @Test
    @Description("Check that user is navigated to Test Cases page and title is correct")
    @Severity(SeverityLevel.NORMAL)
    @Story("Page Navigation Validation")
    @TmsLink("AT-18")
    public void TC7_TestCasePageVisibleTest() {
        new Dashboard()
                .clickTestCasesButton();

        new TestCasesPage()
                .isUserInTestCasesPage()
                .assertOnTestCasesPageTitle(testDataManager.getData("TestCasesPageTitle"));
    }


    @Test
    @Description("Check that user is navigated to API Testing page and title is correct")
    @Severity(SeverityLevel.NORMAL)
    @Story("Page Navigation Validation")
    @TmsLink("AT-40")
    public void TC28_APITestingVisibleText() {
        new Dashboard()
                .clickApiTestingButton();

        new APITestingPage()
                .isUserInAPITestingPage()
                .assertOnAPIPageTitle(testDataManager.getData("ApiPageTitle"));

    }

    @Test
    @Description("Check that the Video Tutorials button opens the AutomationExercise YouTube page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigation Validation")
    @TmsLink("AT-41")
    public void TC29_VerifyVideoButtonWorking() {
        new Dashboard()
                .clickVideoTutorialsButton()
                .isNavigatedToYouTube();

    }

    @BeforeMethod(description = "Navigate to Home Page before each test")
    public void beforeMethod() {
        new HomePage()
                .navigateToHomePage()
                .assertHomePageVisible();
    }

    @BeforeClass(description = "Initialize WebDriver and load page content test data")
    public void setUp() {
        DriverFactory.initiateDriver();
        testDataManager = new JsonFileReader("UITestingData/PageContentData.json");
    }

    @AfterClass(description = "Quit WebDriver after all tests")
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
