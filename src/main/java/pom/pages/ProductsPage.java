package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.Framework.ElementActions;

import java.util.List;

public class ProductsPage {
    //Variables

    //Locators
    private By productsPageTitle = By.cssSelector(".title.text-center");
    private By FirstProductDiv = By.xpath("(//div[@class='col-sm-4']//div[@class='product-image-wrapper'])[1]");
    private By FirstProductHover = By.xpath("(//div[@class='col-sm-4']//div[@class='product-overlay'])[1]");
    private By viewFirstProductButtons = By.xpath("(//div[@class='col-sm-4']//div[@class='choose'])[1]");
    private By productsCards = By.className("product-image-wrapper");
    private By productListingContainer = By.className("features_items");
    private By searchBox = By.id("search_product");
    private By searchButton = By.id("submit_search");
    private By ProductsName = By.cssSelector(".productinfo  p");
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

    public ProductsPage searchProduct(String searchText) {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Type(searchBox, searchText);
        ElementActions.Click(searchButton);
        return this;
    }

    //Validation
    @Step("Verify products page title contains")
    public ProductsPage assertOnProductsPageTitle(String Title) {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(productsPageTitle).toLowerCase().contains(Title));
        return this;
    }

    @Step("Verify that product listing is visible and has more than one product")
    public ProductsPage verifyProductsListing(){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.isELementDisplayed(productListingContainer);
        Assert.assertTrue(ElementActions.getElements(productsCards).size() > 1);
        return this;
    }

    @Step("Verify products page title after search")
    public ProductsPage assertOnSearchTitle(String Title) {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(productsPageTitle).toLowerCase().contains(Title));
        return this;
    }

    @Step("Verify all products shown are related to search keyword")
    public  ProductsPage verifyProductsShownRelatedToSearch(String searchText){
        AdvertismentPages.closeAdsIfAny();
        List<String> allProductNames = ElementActions.getTexts(ProductsName);
        for(String name : allProductNames) {
           Assert.assertTrue(name.toLowerCase().contains(searchText));
        }
        return this;
    }

}
