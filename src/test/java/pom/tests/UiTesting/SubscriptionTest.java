package pom.tests.UiTesting;

import io.qameta.allure.*;
import org.testng.annotations.*;
import pom.pages.CartPage;
import pom.pages.Dashboard;
import pom.pages.HomePage;
import utils.Framework.DriverFactory;
import utils.Framework.JsonFileReader;
import utils.Framework.TestNgListener;

@Feature("Subscription")
@Listeners({TestNgListener.class})
public class SubscriptionTest {
    JsonFileReader testDataManager;

    @Test
    @Description("Verify user can subscribe successfully from the Home page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Subscription from Home page")
    @TmsLink("AT-21")
    public void TC10_SubscriptionInHomeShownTest() {
        new HomePage()
                .scrollToBottom()
                .verifySubscriptionSections(testDataManager.getData("SubscriptionTitleText"))
                .SubscriptionProcess(testDataManager.getData("email"))
                .assertOnSubscriptionSucessMessage(testDataManager.getData("SucessMessage"));
    }

    @Test
    @Description("Verify user can subscribe successfully from the Cart page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Subscription from Cart page")
    @TmsLink("AT-22")
    public void TC11_SubscriptionInCartShownTest() {
        new Dashboard()
                .clickCartButton();

        new CartPage()
                .scrollToBottom()
                .verifySubscriptionSections(testDataManager.getData("SubscriptionTitleText"))
                .SubscriptionProcess(testDataManager.getData("email"))
                .assertOnSubscriptionSucessMessage(testDataManager.getData("SucessMessage"));
    }


    @BeforeMethod(description = "Navigate to Home page before each test case")
    public void beforeMethod() {
        new HomePage()
                .navigateToHomePage()
                .assertHomePageVisible();
    }


    @BeforeClass(description = "SetUp browser, json file reader")
    public void setUp() {
        DriverFactory.initiateDriver();
        testDataManager = new JsonFileReader("UITestingData/SubscriptionTestData.json");
    }

    @AfterClass(description = "Close the browser after the test suite")
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
