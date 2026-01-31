package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.Framework.ElementActions;
import utils.Framework.FileUtils;

public class PaymentDonePage {
    //Locators
    private By pageTitle = By.cssSelector("h2 b");
    private By congratsMessage = By.cssSelector("#form p");
    private By DownloadInvoiceButton = By.className("check_out");
    private By ContinueButton = By.cssSelector("a[data-qa='continue-button']");

    //Actions
    @Step("Click the 'Download Invoice' button")
    public PaymentDonePage clickDownloadInvoiceButton() {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(DownloadInvoiceButton);
        return this;
    }

    @Step("Click the 'Continue' button")
    public PaymentDonePage clickContinueButton() {
        AdvertismentPages.closeAdsIfAny();
        ElementActions.Click(ContinueButton);
        return this;
    }

    //Validation
    @Step("Verify the payment done page title is displayed")
    public PaymentDonePage verifyPageTitle(String Title) {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(pageTitle).toLowerCase().contains(Title.toLowerCase()));
        return this;
    }

    @Step("Verify the congratulations message is displayed")
    public PaymentDonePage verifyCongratsMessage(String message) {
        AdvertismentPages.closeAdsIfAny();
        Assert.assertTrue(ElementActions.getText(congratsMessage).toLowerCase().contains(message.toLowerCase()));
        return this;
    }

    @Step("Verify the invoice file is downloaded successfully")
    public PaymentDonePage verifyFileIsDownloaded() {
        AdvertismentPages.closeAdsIfAny();
        String downloadPath = System.getProperty("user.home") + "/Downloads";
        Assert.assertTrue(FileUtils.waitForExactFile(downloadPath, "invoice.txt", 10).exists());
        return this;
    }

}
