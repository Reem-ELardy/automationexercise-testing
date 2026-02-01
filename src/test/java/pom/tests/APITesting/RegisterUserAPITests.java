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

@Feature("Register User API")
@Listeners({TestNgListener.class})
public class RegisterUserAPITests {
    JsonFileReader apiTestDataManager;

    @Test
    @TmsLink("AT-58")
    @Description("Verify that POST /createAccount with valid data returns 201 and 'User created!' message")
    @Severity(SeverityLevel.CRITICAL)
    public void API7_PostToCreateRegisterUserAccount() {
        Response response = APICallsUtils.postRequest(APIEndpoints.CREATE_ACCOUNT, apiTestDataManager.getTestDataMap("UserData"));
        ResponseValidator.validateResponseCode(response, 201);
        ResponseValidator.validateMessage(response, apiTestDataManager.getData("MessageData.SuccessRegister"));
    }

    @Test
    @TmsLink("AT-63")
    @Description("Verify that POST /createAccount with already registered email returns 400 and 'Email already exists!'")
    @Severity(SeverityLevel.CRITICAL)
    public void API8_PostToCreateRegisterExistingUserAccount() {
        Response response = APICallsUtils.postRequest(APIEndpoints.CREATE_ACCOUNT, apiTestDataManager.getTestDataMap("UserData"));
        ResponseValidator.validateResponseCode(response, 400);
        ResponseValidator.validateMessage(response, apiTestDataManager.getData("MessageData.FailRegisterAccountExist"));
    }

    @Test
    @TmsLink("AT-66")
    @Description("Verify that POST /createAccount with missing required fields returns 400 and missing field error response")
    @Severity(SeverityLevel.CRITICAL)
    public void API9_PostToCreateRegisterUserAccountWithMissingRequiredData() {
        Response response = APICallsUtils.postRequest(APIEndpoints.CREATE_ACCOUNT, apiTestDataManager.getTestDataMap("UserDataWithMissingName"));
        ResponseValidator.validateResponseCode(response, 400);
        ResponseValidator.validateMessage(response, apiTestDataManager.getData("MessageData.FailRegisterMissingData"));
    }

    @BeforeClass(description = "SetUp json file reader")
    public void setUp() {
        apiTestDataManager = new JsonFileReader("APITestingData/RegisterUserTestData.json");
    }

    @AfterClass(description = "Delete Created user Account")
    public void tearDown() {
        APICallsUtils.deleteRequest(APIEndpoints.DELETE_ACCOUNT, apiTestDataManager.getTestDataMap("DeleteAfterCreation"));
    }
}
