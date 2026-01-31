package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.Framework.ElementActions;

import java.util.List;

public class CartPage {
    //Variables

    //Locators
    private By SubscriptionEmail = By.id("susbscribe_email");
    private By SubmitSubscriptionButton = By.id("subscribe");
    private By SuccessSubscriptionAllert = By.className("alert-success");
    private By footerOfPage = By.className("footer-bottom");
    private By SubscriptionSections = By.xpath("//footer[@id='footer']//h2");

    private By prodcutsList = By.cssSelector("tbody tr");
    private By ProductPrice = By.cssSelector(".cart_price p");
    private By ProductQuantity = By.cssSelector(".cart_quantity button");
    private By ProductName = By.cssSelector(".cart_description a");
    private By ProductTotalPrice = By.cssSelector(".cart_total_price");
    private By ProductDeleteButton = By.cssSelector(".cart_quantity_delete");
    private By FirstProductDeleteButton = By.xpath("//a[@data-product-id='1']");
    private By FirstProductInCart = By.id("product-1");
    private By checkoutButton = By.className("check_out");

    //Constructor

    //Getter Functions
    public List<String> getPrices(){
        AdvertismentPages.closeAdsIfAny();
        return ElementActions.getTexts(ProductPrice);
    }

    public List<String> getQuantities(){
        AdvertismentPages.closeAdsIfAny();
        return ElementActions.getTexts(ProductQuantity);
    }

    public List<String> getTotalPrices(){
        AdvertismentPages.closeAdsIfAny();
        return ElementActions.getTexts(ProductTotalPrice);
    }

    //Actions
    @Step("Scroll to the bottom of the Cart Page")
    public CartPage scrollToBottom(){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.scrollToElement(footerOfPage);
        return this;
    }

    @Step("Subscribe to the newsletter")
    public CartPage SubscriptionProcess(String email){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Type(SubscriptionEmail, email);
        ElementActions.Click(SubmitSubscriptionButton);
        return this;
    }

    @Step("Remove the first product from the cart")
    public CartPage removeFirstProduct(){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(FirstProductDeleteButton);
        return this;
    }

    @Step("Empty the cart completely")
    public CartPage MakeTheCartEmpty() {
        AdvertismentPages.closeAdsIfAny();

        while (true) {
            int currentCount = ElementActions.getElements(prodcutsList).size();
            ElementActions.clickAndWaitForCountDecrease(ProductDeleteButton, prodcutsList, currentCount, 1);
            if(currentCount == 1){
                break;
            }
        }

        return this;
    }

    @Step("Click On Proceed To Payment")
    public CartPage clickOnProceedToCheckout(){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(checkoutButton);
        return this;
    }

    //Validation
    @Step("Verify that subscription success message is displayed")
    public CartPage assertOnSubscriptionSucessMessage(String successMessage){
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(SuccessSubscriptionAllert).contains(successMessage));
        return this;
    }

    @Step("Verify subscription section is displayed")
    public CartPage verifySubscriptionSections(String Title){
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(SubscriptionSections).toLowerCase().contains(Title.toLowerCase()));
        return this;
    }

    @Step("Verify number of products in the cart")
    public CartPage verifyNumberOfProductsInCart(int numberOfProducts){
        AdvertismentPages.closeAdsIfAny();
        Assert.assertEquals(ElementActions.getElements(prodcutsList).size(), numberOfProducts);
        return this;
    }

    @Step("Verify that product prices, quantities, and total prices are correct")
    public CartPage verifyTheirPricesQuantityAndTotalPrice() {
        AdvertismentPages.closeAdsIfAny();

        List<String> prices = getPrices();
        List<String> quantities = getQuantities();
        List<String> totalPrices = getTotalPrices();

        for (int i = 0; i < prices.size(); i++) {
            int price = Integer.parseInt(prices.get(i).replaceAll("[^\\d]", ""));
            int quantity = Integer.parseInt(quantities.get(i).replaceAll("[^\\d]", ""));
            int expectedTotal = price * quantity;

            int actualTotal = Integer.parseInt(totalPrices.get(i).replaceAll("[^\\d]", ""));

            Assert.assertEquals(actualTotal, expectedTotal);
        }

        return this;
    }

    @Step("Verify product quantity in cart")
    public CartPage verifyProductQuantity(String Quantity){
        AdvertismentPages.closeAdsIfAny();
        Assert.assertEquals(ElementActions.getText(ProductQuantity), Quantity);
        return this;
    }

    @Step("Verify first product is deleted from cart")
    public CartPage verifyFirstProductIsDeleted(){
        AdvertismentPages.closeAdsIfAny();
        ElementActions.isElementInvisible(FirstProductInCart);
        return this;
    }

    @Step("Verify that products are present in the cart")
    public CartPage verifyProductsAreAdded(){
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(!ElementActions.getElements(prodcutsList).isEmpty());
        return this;
    }

    @Step("Verify all products in cart are related to search")
    public CartPage verifyProductsInCartRelatedToSearch(String searchText){
        AdvertismentPages.closeAdsIfAny();
        List<String> allProductNames = ElementActions.getTexts(ProductName);
        for(String name : allProductNames) {
            System.out.println(name);
            Assert.assertTrue(name.toLowerCase().contains(searchText.toLowerCase()));
        }
        return this;
    }
}
