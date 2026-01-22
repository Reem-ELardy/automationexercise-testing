package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.Framework.DriverFactory;
import utils.Framework.ElementActions;

public class BrandProductsPage {
    //Variables

    //Locators
    private By brandPageTitle = By.cssSelector(".title.text-center");
    private By productsCards = By.className("product-image-wrapper");
    private By productListingContainer = By.className("features_items");


    //Constructor

    //Actions


    //Validation
    @Step("Verify brand page title")
    public BrandProductsPage assertOnBrandPageTitle(String Title) {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(brandPageTitle).toLowerCase().contains(Title));
        return this;
    }

    @Step("Verify user is on Brand Page")
    public BrandProductsPage verifyUserInBrandPage() {
        String actualUrl = DriverFactory.getDriver().getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("/brand_products"));
        return this;
    }

    @Step("Verify brand products are displayed")
    public BrandProductsPage verifyProductsListing(){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.isELementDisplayed(productListingContainer);
        Assert.assertTrue(ElementActions.getElements(productsCards).size() > 1);
        return this;
    }
}
