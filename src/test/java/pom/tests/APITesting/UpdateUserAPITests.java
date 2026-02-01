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

@Feature("Update User API")
@Listeners({TestNgListener.class})
public class UpdateUserAPITests {
    JsonFileReader apiTestDataManager;

    @Test
    @TmsLink("AT-59")
    @Description("Verify that PUT /updateAccount with valid data returns 200 and 'User updated!' message")
    @Severity(SeverityLevel.CRITICAL)
    public void API14_PutToUpdateUserAccountWithValidCredentials() {
        Response response = APICallsUtils.putRequest(APIEndpoints.UPDATE_ACCOUNT, apiTestDataManager.getTestDataMap("UpdateUserValidData"));
        ResponseValidator.validateResponseCode(response, 200);
        ResponseValidator.validateMessage(response, apiTestDataManager.getData("MessageData.SuccessUpdate"));
    }

    @Test
    @TmsLink("AT-68")
    @Description("Verify that PUT /updateAccount with invalid user credentials returns 400 and 'Account not found!'")
    @Severity(SeverityLevel.CRITICAL)
    public void API15_PutToUpdateUserAccountWithInvalidCredentials() {
        Response response = APICallsUtils.putRequest(APIEndpoints.UPDATE_ACCOUNT, apiTestDataManager.getTestDataMap("UpdateUserInvalidData"));
        ResponseValidator.validateResponseCode(response, 404);
        ResponseValidator.validateMessage(response, apiTestDataManager.getData("MessageData.FailUpdateNotFound"));
    }

    @BeforeClass(description = "SetUp json file reader, Create User Account")
    public void setUp() {
        apiTestDataManager = new JsonFileReader("APITestingData/UpdateUserTestData.json");
        APICallsUtils.postRequest(APIEndpoints.CREATE_ACCOUNT, apiTestDataManager.getTestDataMap("RegisterFirstUserData"));
    }

    @AfterClass(description = "Delete Created user Account")
    public void tearDown() {
        APICallsUtils.deleteRequest(APIEndpoints.DELETE_ACCOUNT, apiTestDataManager.getTestDataMap("DeleteAfterCreation"));
    }
}
