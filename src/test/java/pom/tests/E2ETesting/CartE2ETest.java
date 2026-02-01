package pom.tests.E2ETesting;

import io.qameta.allure.*;
import org.testng.annotations.*;
import pom.pages.*;
import utils.Framework.DriverFactory;
import utils.Framework.JsonFileReader;
import utils.Framework.TestNgListener;
import utils.HelperClasses.APIFunctions;
import utils.HelperClasses.UserFunctions;

@Listeners({TestNgListener.class})
@Feature("Cart")
public class CartE2ETest {
    JsonFileReader testDataManager;

    @Test
    @Description("Adds first two products to cart, verifies number of products, prices, and total.")
    @Story("Cart Management")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("AT-23")
    public void TC12_AddProductToCartTest() {
        new Dashboard()
                .clickProductsButton();
        new ProductsPage()
                .FirstProductAddTOCart();
        new AddedToCartAllert()
                .clickOnContinueShopping();
        new ProductsPage()
                .SecondProductAddTOCart();
        new AddedToCartAllert()
                .clickOnViewCart();
        new CartPage()
                .verifyNumberOfProductsInCart(Integer.parseInt(testDataManager.getData("NumberOfProductsAdded")))
                .verifyTheirPricesQuantityAndTotalPrice();
    }

    @Test
    @Story("Cart Management")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("AT-24")
    @Description("Verify that product quantity can be changed in cart and the correct quantity is reflected.")
    public void TC13_VerifyProductQuantityInCart() {
        new Dashboard()
                .clickProductsButton();

        new ProductsPage()
                .clickFirstProductView();

        new ProductDetailsPage()
                .verifyProductDataIsVisible()
                .ChangeQuantity(testDataManager.getData("Quantity"))
                .addToCart();
        new AddedToCartAllert()
                .clickOnViewCart();

        new CartPage()
                .verifyProductQuantity(testDataManager.getData("Quantity"));

    }

    @Test
    @Story("Cart Management")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("AT-28")
    @Description("Remove a product from the cart and verify that it is removed successfully.")
    public void TC17_RemoveProductFromCartTest() {
        new Dashboard()
                .clickProductsButton();

        new ProductsPage()
                .FirstProductAddTOCart();
        new AddedToCartAllert()
                .clickOnContinueShopping();
        new ProductsPage()
                .SecondProductAddTOCart();
        new AddedToCartAllert()
                .clickOnContinueShopping();

        new Dashboard()
                .clickCartButton();

        new CartPage()
                .verifyNumberOfProductsInCart(Integer.parseInt(testDataManager.getData("NumberOfProductsAdded")))
                .removeFirstProduct()
                .verifyFirstProductIsDeleted();
    }

    @Test
    @Story("Cart Management")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("AT-31")
    @Description("Search products, add all searched products to cart, verify cart contents before and after login.")
    public void TC20_SearchAddProductsVerifyCartTest() {
        new Dashboard()
                .clickProductsButton();

        new ProductsPage()
                .assertOnProductsPageTitle(testDataManager.getData("ProductsPageTitle"))
                .searchProduct(testDataManager.getData("searchText"))
                .assertOnSearchTitle(testDataManager.getData("SearchedProductTitle"))
                .verifyProductsShownRelatedToSearch(testDataManager.getData("searchText"))
                .addAllSearchedProductsToCart();

        new Dashboard()
                .clickCartButton();

        new CartPage()
                .verifyProductsAreAdded()
                .verifyProductsInCartRelatedToSearch(testDataManager.getData("searchText"));

        new Dashboard()
                .clickSignupLoginButton();

        new SignUpLoginPage()
                .login(testDataManager.getData("userData.email"), testDataManager.getData("userData.password"));
        new Dashboard()
                .assertOnLoggedInAs(testDataManager.getData("userData.username"))
                .clickCartButton();
        new CartPage()
                .verifyProductsAreAdded()
                .verifyProductsInCartRelatedToSearch(testDataManager.getData("searchText"));
    }

    @Test
    @TmsLink("AT-33")
    public void TC22_AddToCartFromRecommendedItemsTest() {
        new HomePage()
                .goToRecommendationSection()
                .assertOnRecomSectionTitle(testDataManager.getData("RecommendationSectionTitle"))
                .clickFirstRecommProductInCart();

        new AddedToCartAllert()
                .clickOnViewCart();

        new CartPage()
                .verifyNumberOfProductsInCart(Integer.parseInt(testDataManager.getData("NumberOfRecommendedItemsAdded")));
    }

    @BeforeMethod(description = "Navigate to Home Page before each test")
    public void beforeMethod() {
        new HomePage()
                .navigateToHomePage()
                .assertHomePageVisible();
    }

    @AfterMethod(description = "Empty cart after each test")
    public void afterMethod() {
        UserFunctions.MakeTheCartEmpty();
    }

    @BeforeClass(description = "Initialize WebDriver, load test data, and create a new user")
    public void setUp() {
        DriverFactory.initiateDriver();
        testDataManager = new JsonFileReader("E2ETestingData/CartTestData.json");
        APIFunctions.createUser(testDataManager.getTestDataMap("RegisterFirstUserData"));
    }

    @AfterClass(description = "Quit WebDriver after all tests and delete Created User")
    public void tearDown() {
        DriverFactory.quitDriver();
        APIFunctions.deleteUser(testDataManager.getTestDataMap("DeleteAfterCreation"));
    }

}
