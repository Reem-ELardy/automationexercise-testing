package pom.tests.APITesting;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pom.APIValidators.ResponseValidator;
import utils.Framework.APICallsUtils;
import utils.Framework.JsonFileReader;
import utils.Framework.TestNgListener;
import utils.HelperClasses.APIEndpoints;

@Feature("Delete User API")
@Listeners({TestNgListener.class})
public class DeleteUserAPITests {
    JsonFileReader apiTestDataManager;

    @Test
    @TmsLink("AT-61")
    @Description("Verify that DELETE /deleteAccount with valid email and password returns 200 and 'Account deleted!'")
    @Severity(SeverityLevel.CRITICAL)
    public void API18_DeleteUserAccountWithValidCredentials() {
        Response response = APICallsUtils.deleteRequest(APIEndpoints.DELETE_ACCOUNT, apiTestDataManager.getTestDataMap("UserData"));
        ResponseValidator.validateResponseCode(response, 200);
        ResponseValidator.validateMessage(response, apiTestDataManager.getData("MessageData.SuccessDelete"));
    }

    @Test
    @TmsLink("AT-69")
    @Description("Verify that DELETE /deleteAccount with invalid email or password returns 404 and 'Account not found!'")
    @Severity(SeverityLevel.CRITICAL)
    public void API19_DeleteUserAccountWithInvalidCredentials() {
        Response response = APICallsUtils.deleteRequest(APIEndpoints.DELETE_ACCOUNT, apiTestDataManager.getTestDataMap("UserDataInvalidPassword"));
        ResponseValidator.validateResponseCode(response, 404);
        ResponseValidator.validateMessage(response, apiTestDataManager.getData("MessageData.FailDeleteNotFound"));
    }

    @Test
    @TmsLink("AT-70")
    @Description("Verify that DELETE /deleteAccount without providing the email parameter returns 400 and a validation error message")
    @Severity(SeverityLevel.CRITICAL)
    public void API20_DeleteUserAccountWithMissingEmail() {
        Response response = APICallsUtils.deleteRequest(APIEndpoints.DELETE_ACCOUNT, apiTestDataManager.getTestDataMap("UserDataWithMissingEmail"));
        ResponseValidator.validateResponseCode(response, 400);
        ResponseValidator.validateMessage(response, apiTestDataManager.getData("MessageData.FailDeleteMissingData"));
    }

    @BeforeClass(description = "SetUp json file reader, Create User Account")
    public void setUp() {
        apiTestDataManager = new JsonFileReader("APITestingData/DeleteUserTestData.json");
        APICallsUtils.postRequest(APIEndpoints.CREATE_ACCOUNT, apiTestDataManager.getTestDataMap("RegisterFirstUserData"));
    }
}
