package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.Framework.DriverFactory;
import utils.Framework.ElementActions;

public class PaymentPage {
    //Locators
    private By paymentPageTitle = By.cssSelector("h2.heading");
    private By nameOnCard = By.cssSelector("input[data-qa='name-on-card']");
    private By cardNumber = By.cssSelector("input[data-qa='card-number']");
    private By cvcNumebr = By.cssSelector("input[data-qa='cvc']");
    private By expiryMonth = By.cssSelector("input[data-qa='expiry-month']");
    private By expiryYear = By.cssSelector("input[data-qa='expiry-year']");
    private By PayAndConfirmOrderButton = By.id("submit");

    //Actions
    @Step("Fill payment card details")
    public PaymentPage addPaymentCard(String CardName, String CardNum, String CVC, String expMonth, String expYear){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Type(nameOnCard, CardName);
        ElementActions.Type(cardNumber, CardNum);
        ElementActions.Type(cvcNumebr, CVC);
        ElementActions.Type(expiryMonth, expMonth);
        ElementActions.Type(expiryYear, expYear);
        return this;
    }

    @Step("Click the 'Pay and Confirm Order' button")
    public PaymentPage clickPaymentButton(){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(PayAndConfirmOrderButton);
        return this;
    }

    //Validation
    @Step("Verify payment page title is displayed")
    public PaymentPage verifyPaymentPageTitle(String title) {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(paymentPageTitle).toLowerCase().contains(title.toLowerCase()));
        return this;
    }
}
