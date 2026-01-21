package pom.tests.UiTesting;

import io.qameta.allure.*;
import org.testng.annotations.*;
import pom.pages.HomePage;
import utils.Framework.DriverFactory;
import utils.Framework.JsonFileReader;
import utils.Framework.TestNgListener;

@Feature("UI Scroll")
@Listeners({TestNgListener.class})
public class UIScrollTest {
    JsonFileReader testDataManager;

    @Test
    @Description("Verify scrolling down and using the scroll-up arrow returns the user to the top of the page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Scroll using scroll-up arrow")
    @TmsLink("AT-36")
    public void TC25_ScrollUpArrowAndDownTest() {
        new HomePage()
                .scrollToBottom()
                .verifySubscriptionSections(testDataManager.getData("SubscriptionTitleText"))
                .clickScrollUpButton()
                .verifySliderText(testDataManager.getData("SliderText"));
    }

    @Test
    @Description("Verify scrolling down and scrolling up manually returns the user to the top of the page")
    @Severity(SeverityLevel.NORMAL)
    @Story("Scroll using page header")
    @TmsLink("AT-37")
    public void TC26_ScrollUpAndDownTest() {
        new HomePage()
                .scrollToBottom()
                .verifySubscriptionSections(testDataManager.getData("SubscriptionTitleText"))
                .scrollToHeader()
                .verifySliderText(testDataManager.getData("SliderText"));
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
        testDataManager = new JsonFileReader("UITestingData/ScrollUIData.json");
    }

    @AfterClass(description = "Close the browser after the test suite")
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
