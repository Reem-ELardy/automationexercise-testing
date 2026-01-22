package pom.tests.UiTesting;

import io.qameta.allure.*;
import org.testng.annotations.*;
import pom.pages.Dashboard;
import pom.pages.HomePage;
import pom.pages.ProductsPage;
import utils.Framework.DriverFactory;
import utils.Framework.JsonFileReader;
import utils.Framework.TestNgListener;

@Feature("Product Search")
@Listeners({TestNgListener.class})
public class ProductSearchTest {
    JsonFileReader testDataManager;

    @Test
    @Description("User searches for a product and verifies that all displayed products are related to the search keyword")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Search for a product")
    @TmsLink("AT-20")
    public void TC9_ProductSearchTest() {
        new Dashboard()
                .clickProductsButton();

        new ProductsPage()
                .assertOnProductsPageTitle(testDataManager.getData("ProductsPageTitle"))
                .searchProduct(testDataManager.getData("searchText"))
                .assertOnSearchTitle(testDataManager.getData("SearchedProductTitle"))
                .verifyProductsShownRelatedToSearch(testDataManager.getData("searchText"));
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
        testDataManager = new JsonFileReader("UITestingData/ProductSearchTestData.json");
    }

    @AfterClass(description = "Close the browser after the test suite")
    public void tearDown() {
        DriverFactory.quitDriver();
    }

}
