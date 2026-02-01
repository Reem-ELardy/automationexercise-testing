package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.Framework.AlertHandler;
import utils.Framework.ElementActions;

import java.nio.file.Paths;

public class ContactUsPage {
    //Variables

    //Locators
    private By contactUsPageTitle = By.xpath("//div[@class='bg']//h2");
    private By contactUsFormTitle = By.xpath("//div[@class='contact-form']/h2");
    private By nameField = By.xpath("//input[@name='name']");
    private By emailField = By.xpath("//input[@name='email']");
    private By subjectField = By.xpath("//input[@name='subject']");
    private By messageField = By.id("message");
    private By uploadFileField = By.xpath("//input[@name='upload_file']");
    private By submitButton = By.xpath("//input[@name='submit']");
    private By successMessage = By.xpath("//div[@class='status alert alert-success']");
    private By HomeButton = By.xpath("//a[@class='btn btn-success']");

    //Constructor
    public static String getFullPath(String relativePath){
        return Paths.get(relativePath).toAbsolutePath().toString();
    }

    public static String getFileName(String relativePath){
        return Paths.get(relativePath).getFileName().toString(); // ContactUsSample.txt
    }

    //Actions
    @Step("Submit Contact Us form with name: {name}, email: {email}, subject: {subject}")
    public ContactUsPage SubmitFeedback(String name, String email, String subject, String message, String filePath) {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Type(nameField, name);
        ElementActions.Type(emailField, email);
        ElementActions.Type(subjectField, subject);
        ElementActions.Type(messageField, message);
        String path = getFullPath(filePath);
        ElementActions.Type(uploadFileField, path);
        ElementActions.Click(submitButton);
        return this;
    }

    @Step("Accept confirmation alert after submitting Contact Us form")
    public ContactUsPage acceptFeedbackAlert(){
        AlertHandler.acceptAlert();
        return this;
    }

    @Step("Dismiss confirmation alert after submitting Contact Us form")
    public ContactUsPage dismissFeedbackAlert(){
        AlertHandler.closeAlert();
        return this;
    }

    @Step("Click Home button from Contact Us page")
    public ContactUsPage clickHomeButton(){
        ElementActions.Click(HomeButton);
        return this;
    }

    //Validation
    @Step("Verify Contact Us page title is: {title}")
    public ContactUsPage assertOnContactUsPageTitle(String title) {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(contactUsPageTitle).toLowerCase().contains(title));
        return this;
    }

    @Step("Verify Contact Us form title is: {title}")
    public ContactUsPage assertOnContactUsFormTitle(String title) {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(contactUsFormTitle).toLowerCase().contains(title));
        return this;
    }

    @Step("Verify success message is displayed: {message}")
    public ContactUsPage assertOnSuccessMessage(String message) {
        Assert.assertTrue(ElementActions.getText(successMessage).contains(message));
        return this;
    }

    @Step("Verify entered Contact Us data is not cleared after dismissing alert")
    public ContactUsPage VerifyDataNotCleared(String name, String email, String subject, String message, String filePath) {
        Assert.assertTrue(ElementActions.getValueAttribute(nameField).contains(name));
        Assert.assertTrue(ElementActions.getValueAttribute(emailField).contains(email));
        Assert.assertTrue(ElementActions.getValueAttribute(subjectField).contains(subject));
        Assert.assertTrue(ElementActions.getValueAttribute(messageField).contains(message));
        Assert.assertTrue(ElementActions.getValueAttribute(uploadFileField).contains(getFileName(filePath)));
        return this;
    }
}
