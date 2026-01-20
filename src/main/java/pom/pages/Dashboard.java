package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.Framework.DriverFactory;
import utils.Framework.ElementActions;

public class Dashboard {
    //Variables

    //Locators
    private By HomeButton = By.xpath("//a[@href='/']");
    private By productsButton = By.xpath("//a[@href='/products']");
    private By cartButton = By.xpath("//a[@href='/view_cart']");
    private By signupLoginButton = By.xpath("//a[@href='/login']");
    private By deleteAccountButton = By.xpath("//a[@href='/delete_account']");
    private By logOutButton = By.xpath("//a[@href='/logout']");
    private By testCasesButton = By.xpath("//a[@href='/test_cases']");
    private By apiTestingButton =  By.xpath("//a[@href='/api_list']");
    private By videoTutorialsButton = By.xpath("//a[contains(@href,'AutomationExercise')]");
    private By contactUsButton = By.xpath("//a[@href='/contact_us']");
    private By loggedInAsText = By.xpath("//i[@class = 'fa fa-user']/parent::a");

    //Constructor

    //Actions
    @Step("Click On Home")
    public Dashboard clickHomeButton() {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(HomeButton);
        return this;
    }

    @Step("Click On Product")
    public Dashboard clickProductsButton() {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(productsButton);
        return this;
    }

    @Step("Click On Cart")
    public Dashboard clickCartButton() {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(cartButton);
        return this;
    }

    @Step("Click On Signup Login")
    public Dashboard clickSignupLoginButton() {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(signupLoginButton);
        return this;
    }

    @Step("Click On Logout")
    public Dashboard clickLogOutButton() {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(logOutButton);
        return this;
    }

    @Step("Click On Delete Account")
    public Dashboard clickDeleteAccountButton() {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(deleteAccountButton);
        return this;
    }


    @Step("Click On Test Cases")
    public Dashboard clickTestCasesButton() {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(testCasesButton);
        return this;
    }

    @Step("Click On API Testing")
    public Dashboard clickApiTestingButton() {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(apiTestingButton);
        return this;
    }

    @Step("Click On VideoTutorials")
    public Dashboard clickVideoTutorialsButton() {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(videoTutorialsButton);
        return this;
    }

    @Step("Click On Contact Us")
    public Dashboard clickContactUsButton() {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(contactUsButton);
        return this;
    }

    //Validation
    @Step("Assert On Logged In As Username")
    public Dashboard assertOnLoggedInAs(String username) {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertEquals(ElementActions.getText(loggedInAsText), "Logged in as " + username);
        return this;
    }

    public Dashboard isNavigatedToYouTube() {
        AdvertismentPages.closeAdsIfAny();
        String ExpectedUrl = "https://www.youtube.com/";
        String actualUrl = DriverFactory.getDriver().getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(ExpectedUrl));
        return this;
    }
}
