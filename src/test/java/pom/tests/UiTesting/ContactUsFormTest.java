package pom.tests.UiTesting;

import io.qameta.allure.*;
import org.testng.annotations.*;
import pom.pages.ContactUsPage;
import pom.pages.Dashboard;
import pom.pages.HomePage;
import utils.Framework.DriverFactory;
import utils.Framework.JsonFileReader;
import utils.Framework.TestNgListener;

@Feature("Contact Us Form")
@Listeners({TestNgListener.class})
public class ContactUsFormTest {
    JsonFileReader testDataManager;

    @Test(description = "Verify successful submission of the Contact Us form with valid data")
    @Description("Fill in the Contact Us form with valid details, upload a file, submit the form, accept the confirmation alert, and verify the success message is displayed")
    @Severity(SeverityLevel.NORMAL)
    @Story("Contact Us Form Submission")
    @TmsLink("AT-17")
    public void TC6_SubmitContactUsFormSuccessfully() {
        new Dashboard()
                .clickContactUsButton();

        new ContactUsPage()
                .assertOnContactUsPageTitle(testDataManager.getData("ContactUsPageTitle"))
                .assertOnContactUsFormTitle(testDataManager.getData("ContactUsFormTitle"))
                .SubmitFeedback(testDataManager.getData("ContactUsFormData.name"), testDataManager.getData("ContactUsFormData.email"), testDataManager.getData("ContactUsFormData.subject"), testDataManager.getData("ContactUsFormData.message"), testDataManager.getData("ContactUsFormData.UploadedFilePath"))
                .acceptFeedbackAlert()
                .assertOnSuccessMessage(testDataManager.getData("ConactUsSuccessMessage"))
                .clickHomeButton();

        new HomePage()
                .assertHomePageVisible();
    }


    @Test(description = "Verify Contact Us form is not submitted when confirmation alert is dismissed")
    @Description("Fill in the Contact Us form with valid details, attempt to submit the form, dismiss the confirmation alert, and verify that no success message is displayed and data remains")
    @Severity(SeverityLevel.NORMAL)
    @Story("Contact Us Form Submission")
    @TmsLink("AT-43")
    public void TC30_CancelContactUsSubmissionFromAlertTest() {
        new Dashboard()
                .clickContactUsButton();

        new ContactUsPage()
                .assertOnContactUsPageTitle(testDataManager.getData("ContactUsPageTitle"))
                .assertOnContactUsFormTitle(testDataManager.getData("ContactUsFormTitle"))
                .SubmitFeedback(testDataManager.getData("ContactUsFormData.name"), testDataManager.getData("ContactUsFormData.email"), testDataManager.getData("ContactUsFormData.subject"), testDataManager.getData("ContactUsFormData.message"), testDataManager.getData("ContactUsFormData.UploadedFilePath"))
                .dismissFeedbackAlert()
                .VerifyDataNotCleared(testDataManager.getData("ContactUsFormData.name"), testDataManager.getData("ContactUsFormData.email"), testDataManager.getData("ContactUsFormData.subject"), testDataManager.getData("ContactUsFormData.message"), testDataManager.getData("ContactUsFormData.UploadedFilePath"));
    }

    @BeforeMethod(description = "Navigate to Home Page before each test")
    public void beforeMethod() {
        new HomePage()
                .navigateToHomePage()
                .assertHomePageVisible();
    }

    @BeforeClass(description = "Initialize WebDriver and load page content test data")
    public void setUp() {
        DriverFactory.initiateDriver();
        testDataManager = new JsonFileReader("UITestingData/ContactUsData.json");
    }

    @AfterClass(description = "Quit WebDriver after all tests")
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
