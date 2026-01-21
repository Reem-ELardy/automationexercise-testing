package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.Framework.ElementActions;

public class CartPage {
    //Variables

    //Locators
    private By SubscriptionEmail = By.id("susbscribe_email");
    private By SubmitSubscriptionButton = By.id("subscribe");
    private By SuccessSubscriptionAllert = By.className("alert-success");
    private By footerOfPage = By.className("footer-bottom");
    private By SubscriptionSections = By.xpath("//footer[@id='footer']//h2");


    //Constructor

    //Actions
    @Step("Scroll to the bottom of the Cart Page")
    public CartPage scrollToBottom(){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.scrollToElement(footerOfPage);
        return this;
    }

    @Step("Subscribe using email")
    public CartPage SubscriptionProcess(String email){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Type(SubscriptionEmail, email);
        ElementActions.Click(SubmitSubscriptionButton);
        return this;
    }

    //Validation
    @Step("Verify subscription success message is displayed")
    public CartPage assertOnSubscriptionSucessMessage(String successMessage){
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(SuccessSubscriptionAllert).contains(successMessage));
        return this;
    }

    @Step("Verify Subscription section title is displayed as expected")
    public CartPage verifySubscriptionSections(String Title){
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(SubscriptionSections).toLowerCase().contains(Title));
        return this;
    }

}
