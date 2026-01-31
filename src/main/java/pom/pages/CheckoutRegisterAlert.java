package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.Framework.ElementActions;

public class CheckoutRegisterAlert {
    //Locators
    private By registerLoginButton = By.cssSelector("#checkoutModal a");
    private By continueInCart = By.className("close-checkout-modal");

    //Actions
    @Step("Click the 'Register / Login' button on checkout modal")
    public CheckoutRegisterAlert clickRegisterLoginButton(){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(registerLoginButton);
        return this;
    }

    @Step("Click 'Continue in Cart' button on checkout modal")
    public CheckoutRegisterAlert clickContinueInCart(){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(continueInCart);
        return this;
    }
}
