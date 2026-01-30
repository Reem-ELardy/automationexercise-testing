package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.Framework.ElementActions;

public class AddedToCartAllert {
    private By ContinueShoppingButton = By.cssSelector("#cartModal .btn-success");
    private By viewCartButton = By.cssSelector(".modal-body p a");

    @Step("Click on 'Continue Shopping' button in the Add to Cart alert")
    public AddedToCartAllert clickOnContinueShopping(){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(ContinueShoppingButton);
        return this;
    }

    @Step("Click on 'View Cart' button in the Add to Cart alert")
    public AddedToCartAllert clickOnViewCart(){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(viewCartButton);
        return this;
    }
}
