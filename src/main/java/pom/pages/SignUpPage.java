package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.Framework.DriverFactory;
import utils.Framework.ElementActions;


public class SignUpPage {
    //Variables

    //Locators
    private By enterAccountInformationTitle = By.xpath("(//div//h2)[1]");
    private By titleMrRadioButton = By.id("uniform-id_gender1");
    private By titleMrsRadioButton = By.id("uniform-id_gender2");
    private By passwordField = By.id("password");
    private By DayOfBirthDropDown = By.id("days");
    private By MonthOfBirthDropDown = By.id("months");
    private By YearOfBirthDropDown = By.id("years");
    private By newsletterCheckBox = By.id("newsletter");
    private By specialOffersCheckBox = By.id("optin");
    private By firstNameField = By.id("first_name");
    private By lastNameField = By.id("last_name");
    private By companyField = By.id("company");
    private By addressField = By.id("address1");
    private By address2Field = By.id("address2");
    private By countryDropDow = By.id("country");
    private By stateField = By.id("state");
    private By cityField = By.id("city");
    private By zipCodeField = By.id("zipcode");
    private By mobileNumberField = By.id("mobile_number");
    private By createAccountButton = By.xpath("//button[@data-qa='create-account']");


    //Constructor

    //Actions
    @Step("Fill Account Info")
    public SignUpPage fillAccountInformation(String title, String password, String day, String month, String year) {
        AdvertismentPages.closeAdsIfAny();
        if (title.equals("Mr")) {
            ElementActions.Click(titleMrRadioButton);
        } else if (title.equals("Mrs")) {
            ElementActions.Click(titleMrsRadioButton);
        }
        ElementActions.Type(passwordField, password);
        ElementActions.SelectByValue(YearOfBirthDropDown, year);
        ElementActions.SelectByValue(DayOfBirthDropDown, day);
        ElementActions.SelectByValue(MonthOfBirthDropDown, month);

        ElementActions.Click(newsletterCheckBox);
        ElementActions.Click(specialOffersCheckBox);
        return this;
    }

    @Step("Fill Address Personal Info")
    public SignUpPage fillAddressPersonalInformation(String firstName, String lastName, String company, String mobileNumber) {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Type(firstNameField, firstName);
        ElementActions.Type(lastNameField, lastName);
        ElementActions.Type(companyField, company);
        ElementActions.Type(mobileNumberField, mobileNumber);
        return this;
    }

    @Step("Fill Address Location Info")
    public SignUpPage fillAddressLocationInformation(String address, String address2, String country, String state, String city, String zipCode) {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Type(addressField, address);
        ElementActions.Type(address2Field, address2);
        ElementActions.SelectByValue(countryDropDow, country);
        ElementActions.SelectByValue(countryDropDow, country);
        ElementActions.Type(stateField, state);
        ElementActions.Type(cityField, city);
        ElementActions.Type(zipCodeField, zipCode);
        return this;
    }

    @Step("Click On Create Account Button")
    public void clickOnCreateAccountButton() {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(createAccountButton);
    }

    //Validation
    @Step("Assert On Enter Account Information Form Title")
    public SignUpPage assertOnEnterAccountInformationTitle(String title) {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertEquals(ElementActions.getText(enterAccountInformationTitle), title);
        return this;
    }
}
