package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.Framework.DriverFactory;
import utils.Framework.ElementActions;

public class TestCasesPage {
    //Variables

    //Locators
    private By TestCasesTitle = By.xpath("//section//h2");

    //Constructor

    //Actions

    //Validation
    @Step("Verify On Test Cases Page Title")
    public TestCasesPage assertOnTestCasesPageTitle(String title){
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(TestCasesTitle).toLowerCase().contains(title));
        return this;
    }

    @Step("Assert that user is in Test Cases Page")
    public TestCasesPage isUserInTestCasesPage() {
        AdvertismentPages.closeAdsIfAny();
        String actualUrl = DriverFactory.getDriver().getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("/test_cases"));
        return this;
    }
}
