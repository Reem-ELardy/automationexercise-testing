package pom.tests.APITesting;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pom.APIValidators.ResponseValidator;
import utils.Framework.APICallsUtils;
import utils.Framework.JsonFileReader;
import utils.Framework.TestNgListener;
import utils.HelperClasses.APIEndpoints;

@Feature("Login User API")
@Listeners({TestNgListener.class})
public class LoginUserAPITests {
    JsonFileReader apiTestDataManager;

    @Test
    @TmsLink("AT-54")
    @Description("Verify that POST /verifyLogin with valid email and password returns 200 and 'User exists!' message")
    @Severity(SeverityLevel.CRITICAL)
    public void API10_PostToVerifyLoginWithValidDetails() {
        Response response = APICallsUtils.postRequest(APIEndpoints.VERIFY_LOGIN, apiTestDataManager.getTestDataMap("LoginWithValidData"));
        ResponseValidator.validateResponseCode(response, 200);
        ResponseValidator.validateMessage(response, apiTestDataManager.getData("MessageData.SuccessLogin"));
    }

    @Test
    @TmsLink("AT-57")
    @Description("Verify that POST /verifyLogin with invalid credentials returns 404 'User not found!'")
    @Severity(SeverityLevel.CRITICAL)
    public void API11_PostToVerifyLoginWithInvalidDetails() {
        Response response = APICallsUtils.postRequest(APIEndpoints.VERIFY_LOGIN, apiTestDataManager.getTestDataMap("LoginWithInvalidData"));
        ResponseValidator.validateResponseCode(response, 404);
        ResponseValidator.validateMessage(response, apiTestDataManager.getData("MessageData.FailLoginNotFound"));
    }

    @Test
    @TmsLink("AT-55")
    @Description("Verify that POST /verifyLogin without email parameter returns 400 Bad Request")
    @Severity(SeverityLevel.CRITICAL)
    public void API12_PostToVerifyLoginWithoutEmailParameter() {
        Response response = APICallsUtils.postRequest(APIEndpoints.VERIFY_LOGIN, apiTestDataManager.getTestDataMap("LoginWithMissingEmail"));
        ResponseValidator.validateResponseCode(response, 400);
        ResponseValidator.validateMessage(response, apiTestDataManager.getData("MessageData.FailLoginMissingData"));
    }

    @Test
    @TmsLink("AT-56")
    @Description("Verify that DELETE /verifyLogin returns 405 Method Not Allowed")
    @Severity(SeverityLevel.NORMAL)
    public void API13_DeleteToVerifyLogin() {
        Response response = APICallsUtils.deleteRequest(APIEndpoints.VERIFY_LOGIN);
        ResponseValidator.validateResponseCode(response, 405);
        ResponseValidator.validateMessage(response, apiTestDataManager.getData("MessageData.FailLoginWrongRequestMethod"));
    }

    @BeforeClass(description = "SetUp json file reader, Create User Account")
    public void setUp() {
        apiTestDataManager = new JsonFileReader("APITestingData/LoginUserTestData.json");
        APICallsUtils.postRequest(APIEndpoints.CREATE_ACCOUNT, apiTestDataManager.getTestDataMap("RegisterFirstUserData"));
    }

    @AfterClass(description = "Delete Created user Account")
    public void tearDown() {
        APICallsUtils.deleteRequest(APIEndpoints.DELETE_ACCOUNT, apiTestDataManager.getTestDataMap("DeleteAfterCreation"));
    }

}