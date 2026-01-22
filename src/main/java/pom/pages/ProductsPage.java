package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.Framework.ElementActions;

public class ProductsPage {
    //Variables

    //Locators
    private By productsPageTitle = By.cssSelector(".title.text-center");
    private By FirstProductDiv = By.xpath("(//div[@class='col-sm-4']//div[@class='product-image-wrapper'])[1]");
    private By FirstProductHover = By.xpath("(//div[@class='col-sm-4']//div[@class='product-overlay'])[1]");
    private By viewFirstProductButtons = By.xpath("(//div[@class='col-sm-4']//div[@class='choose'])[1]");
    private By productsCards = By.className("product-image-wrapper");
    private By productListingContainer = By.className("features_items");

    //Constructor

    //Actions
    @Step("Click on the first product's 'View Product' button")
    public ProductsPage clickFirstProductView() {
        AdvertismentPages.closeAdsIfAny();
        AdvertismentPages.closeStickyAds();
        ElementActions.scrollToElement(viewFirstProductButtons);
        ElementActions.Click(viewFirstProductButtons);
        return this;
    }

    //Validation
    @Step("Verify products page title contains")
    public ProductsPage assertOnProductsPageTitle(String Title) {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(productsPageTitle).toLowerCase().contains(Title));
        return this;
    }

    public ProductsPage verifyProductsListing(){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.isELementDisplayed(productListingContainer);
        Assert.assertTrue(ElementActions.getElements(productsCards).size() > 1);
        return this;
    }

}
