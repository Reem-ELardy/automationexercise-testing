package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
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

}
