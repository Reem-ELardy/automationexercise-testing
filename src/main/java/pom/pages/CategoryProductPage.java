package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.Framework.DriverFactory;
import utils.Framework.ElementActions;

public class CategoryProductPage {
    //Variables

    //Locators
    private By categoryPageTitle = By.cssSelector(".title.text-center");

    //Constructor

    //Actions


    //Validation
    @Step("Verify products page title contains")
    public CategoryProductPage assertOnCategoryPageTitle(String category) {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(categoryPageTitle).toLowerCase().contains(category));
        return this;
    }

    public CategoryProductPage verifyUserInCategoryPage() {
        String actualUrl = DriverFactory.getDriver().getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("/category_products"));
        return this;
    }
}
