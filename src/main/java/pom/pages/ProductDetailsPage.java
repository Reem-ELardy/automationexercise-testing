package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.Framework.DriverFactory;
import utils.Framework.ElementActions;

public class ProductDetailsPage {
    //Variables

    //Locators
    private By reviewSectionTitle = By.cssSelector("a[href='#reviews']");
    private By reviewNameField = By.id("name");
    private By reviewEmailField = By.id("email");
    private By reviewMessageField = By.id("review");
    private By submitReviewButton = By.id("button-review");
    private By reviewSuccessMessage = By.cssSelector("div[id ='review-section'] span");
    private By productName = By.xpath("//div[@class = 'product-information']//h2");
    private By productCategoty = By.xpath("//div[@class = 'product-information']//p[contains(text(),'Category')]");
    private By productAvailability = By.xpath("//div[@class = 'product-information']//b[text()='Availability:']/parent::p");
    private By productCondition = By.xpath("//div[@class = 'product-information']//b[text()='Condition:']/parent::p");
    private By productBrand = By.xpath("//div[@class = 'product-information']//b[text()='Brand:']/parent::p");
    private By productPrice = By.xpath("//div[@class = 'product-information']/span/span");
    private By addToCartButton = By.cssSelector(".btn.btn-default.cart");

    //Constructor

    //Actions
    @Step("Submit review with Name, Email, Message")
    public ProductDetailsPage submitReview(String name, String email, String reviewMessage) {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Type(reviewNameField, name);
        ElementActions.Type(reviewEmailField, email);
        ElementActions.Type(reviewMessageField, reviewMessage);
        ElementActions.Click(submitReviewButton);
        return this;
    }

    //Validation
    @Step("Verify review section title contains")
    public ProductDetailsPage assertOnReviewSectionTitle(String title) {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(reviewSectionTitle).toLowerCase().contains(title));
        return this;
    }

    @Step("Verify review success message contains")
    public ProductDetailsPage assertOnReviewSuccessMessage(String successMessage) {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(reviewSuccessMessage).contains(successMessage));
        return this;
    }

    @Step("Verify user is on Product Details Page")
    public ProductDetailsPage verifyUserInProductDetailsPage() {
        String actualUrl = DriverFactory.getDriver().getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("/product_details"));
        return this;
    }

    @Step("Verify all product data is visible")
    public ProductDetailsPage verifyProductDataIsVisible(){
        AdvertismentPages.closeAdsIfAny();
        Assert.assertFalse(ElementActions.getText(productName).isEmpty());
        Assert.assertFalse(ElementActions.getText(productCategoty).isEmpty());
        Assert.assertFalse(ElementActions.getText(productPrice).isEmpty());
        Assert.assertFalse(ElementActions.getText(productAvailability).isEmpty());
        Assert.assertFalse(ElementActions.getText(productCondition).isEmpty());
        Assert.assertFalse(ElementActions.getText(productBrand).isEmpty());
        return this;
    }

}
