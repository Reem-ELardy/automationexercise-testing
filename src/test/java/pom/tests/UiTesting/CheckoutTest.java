package pom.tests.UiTesting;

import io.qameta.allure.*;
import org.testng.annotations.*;
import pom.pages.*;
import utils.Framework.DriverFactory;
import utils.Framework.JsonFileReader;
import utils.Framework.TestNgListener;
import utils.HelperFunctions.UserFunctions;

@Listeners({TestNgListener.class})
@Feature("Checkout")
public class CheckoutTest {
    JsonFileReader testDataManager;

    @Test
    @TmsLink("AT-25")
    @Description("Registers a user during checkout and verifies checkout workflow including product addition, address verification, and payment.")
    @Story("Cart Management")
    @Severity(SeverityLevel.CRITICAL)
    public void TC14_RegisterWhileCheckout() {
        new Dashboard()
                .clickProductsButton();
        new ProductsPage()
                .FirstProductAddTOCart();
        new AddedToCartAllert()
                .clickOnContinueShopping();
        new Dashboard()
                .clickCartButton();
        new CartPage()
                .clickOnProceedToCheckout();
        new CheckoutRegisterAlert()
                .clickRegisterLoginButton();
        new SignUpLoginPage()
                .SignUp(testDataManager.getData("RegisterTestData.username"), testDataManager.getData("RegisterTestData.username") + System.currentTimeMillis() + "@example.com");
        new SignUpPage()
                .fillAccountInformation(testDataManager.getData("RegisterTestData.title"), testDataManager.getData("RegisterTestData.password"), testDataManager.getData("RegisterTestData.day"), testDataManager.getData("RegisterTestData.month"), testDataManager.getData("RegisterTestData.year"))
                .fillAddressPersonalInformation(testDataManager.getData("RegisterTestData.firstName"), testDataManager.getData("RegisterTestData.lastName"), testDataManager.getData("RegisterTestData.company"), testDataManager.getData("RegisterTestData.mobileNumber"))
                .fillAddressLocationInformation(testDataManager.getData("RegisterTestData.address"), testDataManager.getData("RegisterTestData.address2"), testDataManager.getData("RegisterTestData.country"), testDataManager.getData("RegisterTestData.state"), testDataManager.getData("RegisterTestData.city"), testDataManager.getData("RegisterTestData.zipCode"))
                .clickOnCreateAccountButton();
        new AccountCreatedPage()
                .assertAccountCreatedPageTitle(testDataManager.getData("AccountCreatedPageTitle"))
                .clickContinueButton();
        new Dashboard()
                .assertOnLoggedInAs(testDataManager.getData("RegisterTestData.username"));
        new Dashboard()
                .clickCartButton();
        new CartPage()
                .clickOnProceedToCheckout();
        new CheckoutPage()
                .assertDeliveryAndBillingAddressSectionArePresent()
                .verifyAddressDetailsTitle(testDataManager.getData("CheckoutPage.AddressDetailsTitle"))
                .verifyOrderReviewTitle(testDataManager.getData("CheckoutPage.InvoiceDetailsTitle"))
                .verifyProductsPresentInCheckout()
                .writeComment(testDataManager.getData("CheckoutPage.CommentMessage"))
                .clickPlaceOrder();
        new PaymentPage()
                .verifyPaymentPageTitle(testDataManager.getData("PaymentPage.Title"))
                .addPaymentCard(testDataManager.getData("CardData.Name"), testDataManager.getData("CardData.CardNumber"), testDataManager.getData("CardData.CVC"), testDataManager.getData("CardData.Month"),testDataManager.getData("CardData.Year"))
                .clickPaymentButton();
        new PaymentDonePage()
                .verifyPageTitle(testDataManager.getData("PaymentDonePage.Title"))
                .verifyCongratsMessage(testDataManager.getData("PaymentDonePage.CongratsMessage"))
                .clickContinueButton();
    }

    @Test
    @TmsLink("AT-26")
    @Description("Registers a user before checkout and verifies the entire checkout process including product addition, addresses, payment, and confirmation.")
    @Story("Cart Management")
    @Severity(SeverityLevel.CRITICAL)
    public void TC15_RegisterBeforeCheckout() {
        new Dashboard()
                .clickSignupLoginButton();
        new SignUpLoginPage()
                .SignUp(testDataManager.getData("RegisterTestData.username"), testDataManager.getData("RegisterTestData.username") + System.currentTimeMillis() + "@example.com");
        new SignUpPage()
                .fillAccountInformation(testDataManager.getData("RegisterTestData.title"), testDataManager.getData("RegisterTestData.password"), testDataManager.getData("RegisterTestData.day"), testDataManager.getData("RegisterTestData.month"), testDataManager.getData("RegisterTestData.year"))
                .fillAddressPersonalInformation(testDataManager.getData("RegisterTestData.firstName"), testDataManager.getData("RegisterTestData.lastName"), testDataManager.getData("RegisterTestData.company"), testDataManager.getData("RegisterTestData.mobileNumber"))
                .fillAddressLocationInformation(testDataManager.getData("RegisterTestData.address"), testDataManager.getData("RegisterTestData.address2"), testDataManager.getData("RegisterTestData.country"), testDataManager.getData("RegisterTestData.state"), testDataManager.getData("RegisterTestData.city"), testDataManager.getData("RegisterTestData.zipCode"))
                .clickOnCreateAccountButton();
        new AccountCreatedPage()
                .assertAccountCreatedPageTitle(testDataManager.getData("AccountCreatedPageTitle"))
                .clickContinueButton();
        new Dashboard()
                .assertOnLoggedInAs(testDataManager.getData("RegisterTestData.username"));
        new ProductsPage()
                .FirstProductAddTOCart();
        new AddedToCartAllert()
                .clickOnContinueShopping();
        new Dashboard()
                .clickCartButton();
        new CartPage()
                .clickOnProceedToCheckout();
        new CheckoutPage()
                .assertDeliveryAndBillingAddressSectionArePresent()
                .verifyAddressDetailsTitle(testDataManager.getData("CheckoutPage.AddressDetailsTitle"))
                .verifyOrderReviewTitle(testDataManager.getData("CheckoutPage.InvoiceDetailsTitle"))
                .verifyProductsPresentInCheckout()
                .writeComment(testDataManager.getData("CheckoutPage.CommentMessage"))
                .clickPlaceOrder();
        new PaymentPage()
                .verifyPaymentPageTitle(testDataManager.getData("PaymentPage.Title"))
                .addPaymentCard(testDataManager.getData("CardData.Name"), testDataManager.getData("CardData.CardNumber"), testDataManager.getData("CardData.CVC"), testDataManager.getData("CardData.Month"),testDataManager.getData("CardData.Year"))
                .clickPaymentButton();
        new PaymentDonePage()
                .verifyPageTitle(testDataManager.getData("PaymentDonePage.Title"))
                .verifyCongratsMessage(testDataManager.getData("PaymentDonePage.CongratsMessage"))
                .clickContinueButton();
    }

    @Test
    @TmsLink("AT-27")
    @Description("Logs in an existing user before checkout and verifies the complete checkout process including product selection, payment, and order confirmation.")
    @Story("Cart Management")
    @Severity(SeverityLevel.CRITICAL)
    public void TC16_LoginBeforeCheckout() {
        new Dashboard()
                .clickSignupLoginButton();
        new SignUpLoginPage()
                .login(testDataManager.getData("TC16_userTestData.email"), testDataManager.getData("TC16_userTestData.password"));
        new Dashboard()
                .assertOnLoggedInAs(testDataManager.getData("TC16_userTestData.username"));
        new ProductsPage()
                .FirstProductAddTOCart();
        new AddedToCartAllert()
                .clickOnContinueShopping();
        new Dashboard()
                .clickCartButton();
        new CartPage()
                .clickOnProceedToCheckout();
        new CheckoutPage()
                .assertDeliveryAndBillingAddressSectionArePresent()
                .verifyAddressDetailsTitle(testDataManager.getData("CheckoutPage.AddressDetailsTitle"))
                .verifyOrderReviewTitle(testDataManager.getData("CheckoutPage.InvoiceDetailsTitle"))
                .verifyProductsPresentInCheckout()
                .writeComment(testDataManager.getData("CheckoutPage.CommentMessage"))
                .clickPlaceOrder();
        new PaymentPage()
                .verifyPaymentPageTitle(testDataManager.getData("PaymentPage.Title"))
                .addPaymentCard(testDataManager.getData("CardData.Name"), testDataManager.getData("CardData.CardNumber"), testDataManager.getData("CardData.CVC"), testDataManager.getData("CardData.Month"),testDataManager.getData("CardData.Year"))
                .clickPaymentButton();
        new PaymentDonePage()
                .verifyPageTitle(testDataManager.getData("PaymentDonePage.Title"))
                .verifyCongratsMessage(testDataManager.getData("PaymentDonePage.CongratsMessage"))
                .clickContinueButton();
    }

    @Test
    @TmsLink("AT-34")
    @Description("Verifies delivery and billing address details in the checkout page match the registered user's information.")
    @Story("Cart Management")
    @Severity(SeverityLevel.CRITICAL)
    public void TC23_VerifyAddressDetailsInCheckoutPage() {
        new Dashboard()
                .clickSignupLoginButton();
        new SignUpLoginPage()
                .SignUp(testDataManager.getData("RegisterTestData.username"), testDataManager.getData("RegisterTestData.username") + System.currentTimeMillis() + "@example.com");
        new SignUpPage()
                .fillAccountInformation(testDataManager.getData("RegisterTestData.title"), testDataManager.getData("RegisterTestData.password"), testDataManager.getData("RegisterTestData.day"), testDataManager.getData("RegisterTestData.month"), testDataManager.getData("RegisterTestData.year"))
                .fillAddressPersonalInformation(testDataManager.getData("RegisterTestData.firstName"), testDataManager.getData("RegisterTestData.lastName"), testDataManager.getData("RegisterTestData.company"), testDataManager.getData("RegisterTestData.mobileNumber"))
                .fillAddressLocationInformation(testDataManager.getData("RegisterTestData.address"), testDataManager.getData("RegisterTestData.address2"), testDataManager.getData("RegisterTestData.country"), testDataManager.getData("RegisterTestData.state"), testDataManager.getData("RegisterTestData.city"), testDataManager.getData("RegisterTestData.zipCode"))
                .clickOnCreateAccountButton();
        new AccountCreatedPage()
                .assertAccountCreatedPageTitle(testDataManager.getData("AccountCreatedPageTitle"))
                .clickContinueButton();
        new Dashboard()
                .assertOnLoggedInAs(testDataManager.getData("RegisterTestData.username"));
        new ProductsPage()
                .FirstProductAddTOCart();
        new AddedToCartAllert()
                .clickOnContinueShopping();
        new Dashboard()
                .clickCartButton();
        new CartPage()
                .clickOnProceedToCheckout();
        new CheckoutPage()
                .assertDeliveryAndBillingAddressSectionArePresent()
                .verifyAddressDetailsTitle(testDataManager.getData("CheckoutPage.AddressDetailsTitle"))
                .verifyOrderReviewTitle(testDataManager.getData("CheckoutPage.InvoiceDetailsTitle"))
                .verifyProductsPresentInCheckout()
                .verifyDeliveryAddresses(testDataManager.getData("RegisterTestData.firstName"), testDataManager.getData("RegisterTestData.lastName"), testDataManager.getData("RegisterTestData.company"), testDataManager.getData("RegisterTestData.address"), testDataManager.getData("RegisterTestData.address2"), testDataManager.getData("RegisterTestData.country"), testDataManager.getData("RegisterTestData.state"), testDataManager.getData("RegisterTestData.city"), testDataManager.getData("RegisterTestData.zipCode"), testDataManager.getData("RegisterTestData.mobileNumber"))
                .verifyBillingAddresses(testDataManager.getData("RegisterTestData.firstName"), testDataManager.getData("RegisterTestData.lastName"), testDataManager.getData("RegisterTestData.company"), testDataManager.getData("RegisterTestData.address"), testDataManager.getData("RegisterTestData.address2"), testDataManager.getData("RegisterTestData.country"), testDataManager.getData("RegisterTestData.state"), testDataManager.getData("RegisterTestData.city"), testDataManager.getData("RegisterTestData.zipCode"), testDataManager.getData("RegisterTestData.mobileNumber"));
    }

    @Test
    @TmsLink("AT-35")
    @Description("Downloads invoice after checkout and verifies the invoice file exists in the downloads folder.")
    @Story("Cart Management")
    @Severity(SeverityLevel.CRITICAL)
    public void TC24_DownloadInvoiceAfterCheckout() {
        new Dashboard()
                .clickProductsButton();
        new ProductsPage()
                .FirstProductAddTOCart();
        new AddedToCartAllert()
                .clickOnContinueShopping();
        new Dashboard()
                .clickCartButton();
        new CartPage()
                .clickOnProceedToCheckout();
        new CheckoutRegisterAlert()
                .clickRegisterLoginButton();
        new SignUpLoginPage()
                .SignUp(testDataManager.getData("RegisterTestData.username"), testDataManager.getData("RegisterTestData.username") + System.currentTimeMillis() + "@example.com");
        new SignUpPage()
                .fillAccountInformation(testDataManager.getData("RegisterTestData.title"), testDataManager.getData("RegisterTestData.password"), testDataManager.getData("RegisterTestData.day"), testDataManager.getData("RegisterTestData.month"), testDataManager.getData("RegisterTestData.year"))
                .fillAddressPersonalInformation(testDataManager.getData("RegisterTestData.firstName"), testDataManager.getData("RegisterTestData.lastName"), testDataManager.getData("RegisterTestData.company"), testDataManager.getData("RegisterTestData.mobileNumber"))
                .fillAddressLocationInformation(testDataManager.getData("RegisterTestData.address"), testDataManager.getData("RegisterTestData.address2"), testDataManager.getData("RegisterTestData.country"), testDataManager.getData("RegisterTestData.state"), testDataManager.getData("RegisterTestData.city"), testDataManager.getData("RegisterTestData.zipCode"))
                .clickOnCreateAccountButton();
        new AccountCreatedPage()
                .assertAccountCreatedPageTitle(testDataManager.getData("AccountCreatedPageTitle"))
                .clickContinueButton();
        new Dashboard()
                .assertOnLoggedInAs(testDataManager.getData("RegisterTestData.username"));
        new Dashboard()
                .clickCartButton();
        new CartPage()
                .clickOnProceedToCheckout();
        new CheckoutPage()
                .assertDeliveryAndBillingAddressSectionArePresent()
                .verifyAddressDetailsTitle(testDataManager.getData("CheckoutPage.AddressDetailsTitle"))
                .verifyOrderReviewTitle(testDataManager.getData("CheckoutPage.InvoiceDetailsTitle"))
                .verifyProductsPresentInCheckout()
                .writeComment(testDataManager.getData("CheckoutPage.CommentMessage"))
                .clickPlaceOrder();
        new PaymentPage()
                .verifyPaymentPageTitle(testDataManager.getData("PaymentPage.Title"))
                .addPaymentCard(testDataManager.getData("CardData.Name"), testDataManager.getData("CardData.CardNumber"), testDataManager.getData("CardData.CVC"), testDataManager.getData("CardData.Month"),testDataManager.getData("CardData.Year"))
                .clickPaymentButton();
        new PaymentDonePage()
                .verifyPageTitle(testDataManager.getData("PaymentDonePage.Title"))
                .verifyCongratsMessage(testDataManager.getData("PaymentDonePage.CongratsMessage"))
                .clickDownloadInvoiceButton()
                .verifyFileIsDownloaded();
    }

    @BeforeMethod(description = "Navigate to Home Page before each test")
    public void beforeMethod() {
        new HomePage()
                .navigateToHomePage()
                .assertHomePageVisible();
    }

    @AfterMethod(description = "Delete Account Created After Each Test")
    public void afterMethod() {
        UserFunctions.deleteUserAccount();
    }

    @BeforeClass(description = "Initialize WebDriver, load test data, and create a new user")
    public void setUp() {
        DriverFactory.initiateDriver();
        testDataManager = new JsonFileReader("UITestingData/CheckoutTestData.json");
        UserFunctions.SignUpAndLogout(testDataManager, "TC16_userTestData");
    }

    @AfterClass(description = "Quit WebDriver after all tests")
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
