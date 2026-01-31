package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.Framework.ElementActions;

import java.util.List;

public class CheckoutPage {
    //Locators
    private By addressDetailsHeadline = By.xpath("//div[@class='step-one'][1]/h2");
    private By reviewOrderHeadline = By.xpath("//div[@class='step-one'][2]/h2");
    private By DeliveryAddressSection = By.id("address_delivery");
    private By BillingAddressSection = By.id("address_invoice");
    private By commentSection = By.cssSelector("#ordermsg textarea");
    private By placeOrderButton = By.className("check_out");

    // Delivery Address Locators
    private By deliveryName = By.cssSelector("#address_delivery .address_firstname.address_lastname");
    private By deliveryStreet = By.cssSelector("#address_delivery .address_address1.address_address2");
    private By deliveryCityStatePostcode = By.cssSelector("#address_delivery .address_city.address_state_name.address_postcode");
    private By deliveryCountry = By.cssSelector("#address_delivery .address_country_name");
    private By deliveryPhone = By.cssSelector("#address_delivery .address_phone");

    // Billing Address Locators
    private By billingName = By.cssSelector("#address_invoice .address_firstname.address_lastname");
    private By billingStreet = By.cssSelector("#address_invoice .address_address1.address_address2");
    private By billingCityStatePostcode = By.cssSelector("#address_invoice .address_city.address_state_name.address_postcode");
    private By billingCountry = By.cssSelector("#address_invoice .address_country_name");
    private By billingPhone = By.cssSelector("#address_invoice .address_phone");
    private By checkoutProducts = By.cssSelector("tr[id^='product-']");


    //Actions
    @Step("Write a comment in the checkout page")
    public CheckoutPage writeComment(String Comment) {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Type(commentSection, Comment);
        return this;
    }

    @Step("Click the Place Order button")
    public CheckoutPage clickPlaceOrder(){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(placeOrderButton);
        return this;
    }


    //Validation
    @Step("Verify address details title is displayed")
    public CheckoutPage verifyAddressDetailsTitle(String title) {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(addressDetailsHeadline).toLowerCase().contains(title.toLowerCase()));
        return this;
    }

    @Step("Verify delivery addresses match the registration data")
    public CheckoutPage verifyDeliveryAddresses(String firstName, String lastName, String Company, String address, String address2, String country, String state, String city, String zipCode, String mobileNumber) {
        Assert.assertTrue(ElementActions.getText(deliveryName).contains(firstName) && ElementActions.getText(deliveryName).contains(lastName));
        List<String> addressLines = ElementActions.getTexts(deliveryStreet);
        Assert.assertTrue(addressLines.get(0).contains(Company));
        Assert.assertTrue(addressLines.get(1).contains(address));
        Assert.assertTrue(addressLines.get(2).contains(address2));
        Assert.assertTrue(ElementActions.getText(deliveryCityStatePostcode).contains(city) && ElementActions.getText(deliveryCityStatePostcode).contains(state) && ElementActions.getText(deliveryCityStatePostcode).contains(zipCode));
        Assert.assertTrue(ElementActions.getText(deliveryCountry).contains(country));
        Assert.assertTrue(ElementActions.getText(deliveryPhone).contains(mobileNumber));
        return this;
    }

    @Step("Verify billing addresses match the registration data")
    public CheckoutPage verifyBillingAddresses(String firstName, String lastName, String Company, String address, String address2, String country, String state, String city, String zipCode, String mobileNumber) {
        Assert.assertTrue(ElementActions.getText(billingName).contains(firstName) && ElementActions.getText(billingName).contains(lastName));
        List<String> addressLines = ElementActions.getTexts(billingStreet);
        Assert.assertTrue(addressLines.get(0).contains(Company));
        Assert.assertTrue(addressLines.get(1).contains(address));
        Assert.assertTrue(addressLines.get(2).contains(address2));
        Assert.assertTrue(ElementActions.getText(billingCityStatePostcode).contains(city) && ElementActions.getText(billingCityStatePostcode).contains(state) && ElementActions.getText(billingCityStatePostcode).contains(zipCode));
        Assert.assertTrue(ElementActions.getText(billingCountry).contains(country));
        Assert.assertTrue(ElementActions.getText(billingPhone).contains(mobileNumber));
        return this;
    }

    @Step("Verify order review title is displayed")
    public CheckoutPage verifyOrderReviewTitle(String title) {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(reviewOrderHeadline).toLowerCase().contains(title.toLowerCase()));
        return this;
    }

    @Step("Verify products are present in checkout")
    public CheckoutPage verifyProductsPresentInCheckout(){
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(!ElementActions.getElements(checkoutProducts).isEmpty());
        return this;
    }

    @Step("Assert that delivery and billing address sections are present")
    public CheckoutPage assertDeliveryAndBillingAddressSectionArePresent() {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.isElementPresent(DeliveryAddressSection);
        ElementActions.isElementPresent(BillingAddressSection);
        return this;
    }
}
