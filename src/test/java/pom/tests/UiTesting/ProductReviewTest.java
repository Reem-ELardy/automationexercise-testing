package pom.tests.UiTesting;

import io.qameta.allure.*;
import org.testng.annotations.*;
import pom.pages.Dashboard;
import pom.pages.HomePage;
import pom.pages.ProductDetailsPage;
import pom.pages.ProductsPage;
import utils.Framework.DriverFactory;
import utils.Framework.JsonFileReader;
import utils.Framework.TestNgListener;

@Feature("Product Review")
@Listeners({TestNgListener.class})
public class ProductReviewTest {
    JsonFileReader testDataManager;

    @Test
    @Description("Verify that a user can add a review on a product successfully")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User adds review on a product")
    @TmsLink("AT-32")
    public void TC21_AddReviewOnProduct() {
        new Dashboard().clickProductsButton();

        new ProductsPage().assertOnProductsPageTitle(testDataManager.getData("ProductsPageTitle")).clickFirstProductView();

        new ProductDetailsPage().assertOnReviewSectionTitle(testDataManager.getData("ReviewSectionTitle")).submitReview(testDataManager.getData("ReviewData.name"), testDataManager.getData("ReviewData.email"), testDataManager.getData("ReviewData.review")).assertOnReviewSuccessMessage(testDataManager.getData("ReviewSuccessMessage"));
    }

    @BeforeMethod(description = "Navigate to Home page before each test case")
    public void beforeMethod() {
        new HomePage().navigateToHomePage().assertHomePageVisible();
    }


    @BeforeClass(description = "SetUp browser, json file reader")
    public void setUp() {
        DriverFactory.initiateDriver();
        testDataManager = new JsonFileReader("UITestingData/ProductReviewTestData.json");
    }

    @AfterClass(description = "Close the browser after the test suite")
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
