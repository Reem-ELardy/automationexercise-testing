package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.Framework.DriverFactory;
import utils.Framework.ElementActions;

public class HomePage {
    //Variables
    private String url = System.getProperty("baseUrl");

    //Locators
    private By slider = By.id("slider");
    private By footerOfPage = By.className("footer-bottom");
    private By headerOfPage = By.id("header");
    private By ScrollUpButton = By.id("scrollUp");
    private By SubscriptionSections = By.xpath("//footer[@id='footer']//h2");
    private By SliderText = By.xpath("//div[@id='slider-carousel']//div[@class='item active']//h2");
    private By SubscriptionEmail = By.id("susbscribe_email");
    private By SubmitSubscriptionButton = By.id("subscribe");
    private By SuccessSubscriptionAllert = By.className("alert-success");
    private By firstRecomAddToCart = By.cssSelector("#recommended-item-carousel .item.active .col-sm-4:first-child a");
    private By recommendationSection = By.id("recommended-item-carousel");
    private By recommendationSectionTItle = By.cssSelector(".recommended_items .title");
    //Constructor

    //Actions
    @Step("Navigate to Home Page")
    public HomePage navigateToHomePage() {
        DriverFactory.getDriver().navigate().to(url);
        return this;
    }

    @Step("Scroll to the bottom of the Home Page")
    public HomePage scrollToBottom(){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.scrollToElement(footerOfPage);
        return this;
    }

    @Step("Scroll to the top of the Home Page")
    public HomePage scrollToHeader(){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.scrollToElement(headerOfPage);
        return this;
    }

    @Step("Click on the Scroll Up button")
    public HomePage clickScrollUpButton(){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(ScrollUpButton);
        return this;
    }

    @Step("Subscribe using email")
    public HomePage SubscriptionProcess(String email){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Type(SubscriptionEmail, email);
        ElementActions.Click(SubmitSubscriptionButton);
        return this;
    }

    public HomePage goToRecommendationSection(){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.scrollToElement(recommendationSection);
        return this;
    }

    public HomePage clickFirstRecommProductInCart(){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(firstRecomAddToCart);
        return this;
    }


    //Validation
    @Step("Validate that Home Page is Visible")
    public void assertHomePageVisible() {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.isELementDisplayed(slider));
    }

    @Step("Verify Subscription section title is displayed as expected")
    public HomePage verifySubscriptionSections(String Title){
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(SubscriptionSections).toLowerCase().contains(Title));
        return this;
    }

    @Step("Verify slider text is displayed correctly on Home Page")
    public HomePage verifySliderText(String Text){
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(SliderText).contains(Text));
        return this;
    }

    @Step("Verify subscription success message is displayed")
    public HomePage assertOnSubscriptionSucessMessage(String successMessage){
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(SuccessSubscriptionAllert).contains(successMessage));
        return this;
    }

    @Step("Verify recommendation section title")
    public HomePage assertOnRecomSectionTitle(String Title){
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(recommendationSectionTItle).toLowerCase().contains(Title));
        return this;
    }
}
