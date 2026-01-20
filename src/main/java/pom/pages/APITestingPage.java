package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.Framework.DriverFactory;
import utils.Framework.ElementActions;

public class APITestingPage {
    //Variables

    //Locators
    private By ApiListTitle = By.xpath("//section//h2");

    //Constructor

    //Actions

    //Validation
    @Step("Verify On API Testing Page Title")
    public APITestingPage assertOnAPIPageTitle(String title){
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(ApiListTitle).toLowerCase().contains(title));
        return this;
    }

    @Step("Assert that user is in API Testing Page")
    public APITestingPage isUserInAPITestingPage() {
        AdvertismentPages.closeAdsIfAny();
        String actualUrl = DriverFactory.getDriver().getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("/api_list"));
        return this;
    }
}
