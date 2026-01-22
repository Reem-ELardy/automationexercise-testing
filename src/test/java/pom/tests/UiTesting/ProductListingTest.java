package pom.tests.UiTesting;

import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import org.testng.annotations.*;
import pom.pages.*;
import utils.Framework.DriverFactory;
import utils.Framework.JsonFileReader;
import utils.Framework.TestNgListener;

@Feature("Products Listing")
@Listeners({TestNgListener.class})
public class ProductListingTest {
    JsonFileReader testDataManager;

    @Test
    @TmsLink("AT-19")
    public void TC8_AllProductsDetailsTest() {
        new Dashboard()
                .clickProductsButton();

        new ProductsPage()
                .assertOnProductsPageTitle(testDataManager.getData("ProductsPageTitle"))
                .verifyProductsListing()
                .clickFirstProductView();

        new ProductDetailsPage()
                .verifyUserInProductDetailsPage()
                .verifyProductDataIsVisible();
    }

    @Test
    @TmsLink("AT-29")
    public void TC18_ViewCategoryAndFilterTest() {
        new Dashboard()
                .clickProductsButton();
        new ProductLeftSliderPage()
                .assertOnCategoryTitle(testDataManager.getData("categoryTitle"))
                .clickWomenCategory()
                .clickWomenSubCategory(testDataManager.getData("Categories.womenCat.subCategories.One"));
        new CategoryProductPage()
                .verifyUserInCategoryPage()
                .assertOnCategoryPageTitle(String.format(testDataManager.getData("CategoryPageTitle"), testDataManager.getData("Categories.womenCat.name"), testDataManager.getData("Categories.womenCat.subCategories.One")));

        new ProductLeftSliderPage()
                .clickMenCategory()
                .clickMenSubCategory(testDataManager.getData("Categories.menCat.subCategories.One"));

        new CategoryProductPage()
                .verifyUserInCategoryPage()
                .assertOnCategoryPageTitle(String.format(testDataManager.getData("CategoryPageTitle"), testDataManager.getData("Categories.menCat.name"), testDataManager.getData("Categories.menCat.subCategories.One")));

        new ProductLeftSliderPage()
                .clickKidsCategory()
                .clickKidsSubCategory(testDataManager.getData("Categories.kidsCat.subCategories.One"));

        new CategoryProductPage()
                .verifyUserInCategoryPage()
                .assertOnCategoryPageTitle(String.format(testDataManager.getData("CategoryPageTitle"), testDataManager.getData("Categories.kidsCat.name"), testDataManager.getData("Categories.kidsCat.subCategories.One")));
    }

    @Test
    @TmsLink("AT-30")
    public void TC19_ViewBrandAndFilterTest() {
        new Dashboard()
                .clickProductsButton();

        new ProductLeftSliderPage()
                .assertOnBrandsTitle(testDataManager.getData("BrandTitle"))
                .clickSpecificBrand(testDataManager.getData("Brands.One"));

        new BrandProductsPage()
                .verifyUserInBrandPage()
                .assertOnBrandPageTitle(String.format(testDataManager.getData("BrandPageTitle"), testDataManager.getData("Brands.One")))
                .verifyProductsListing();


        new ProductLeftSliderPage()
                .clickSpecificBrand(testDataManager.getData("Brands.Two"));

        new BrandProductsPage()
                .verifyUserInBrandPage()
                .assertOnBrandPageTitle(String.format(testDataManager.getData("BrandPageTitle"), testDataManager.getData("Brands.Two")))
                .verifyProductsListing();
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
        testDataManager = new JsonFileReader("UITestingData/ProductListingData.json");
    }

    @AfterClass(description = "Close the browser after the test suite")
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
