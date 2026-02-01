package pom.tests.APITesting;

import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pom.APIValidators.ResponseValidator;
import pom.APIValidators.UserValidator;
import utils.Framework.APICallsUtils;
import utils.Framework.JsonFileReader;
import utils.Framework.TestNgListener;
import io.restassured.response.Response;
import utils.HelperClasses.APIEndpoints;

import java.util.Map;

@Feature("Get User API")
@Listeners({TestNgListener.class})
public class GetUserAPITests {
    JsonFileReader apiTestDataManager;

    @Test
    @TmsLink("AT-60")
    @Description("Verify that GET /getUserDetailByEmail with valid email returns user details and status code 200")
    @Severity(SeverityLevel.CRITICAL)
    public void API16_GetUserAccountDetailByEmail() {
        Response response = APICallsUtils.getRequest(APIEndpoints.GET_USER_BY_EMAIL, apiTestDataManager.getTestDataMap("GetUserData"));
        ResponseValidator.validateResponseCode(response, 200);
        UserValidator.validateUserExists(response);
        UserValidator.validateUserDataInGetAgainstRegisteredData(response, apiTestDataManager, "RegisterFirstUserData");
    }

    @Test
    @TmsLink("AT-70")
    @Description("Verify that GET /getUserDetailByEmail with invalid email returns 404 and 'Account not found with this email, try another email!'")
    @Severity(SeverityLevel.CRITICAL)
    public void API17_GetUserAccountDetailByInvalidEmail() {
        Response response = APICallsUtils.getRequest(APIEndpoints.GET_USER_BY_EMAIL, apiTestDataManager.getTestDataMap("GetUserInvalidData"));
        ResponseValidator.validateResponseCode(response, 404);
    }

    @BeforeClass(description = "SetUp json file reader, Create User Account")
    public void setUp() {
        apiTestDataManager = new JsonFileReader("APITestingData/GetUserTestData.json");
        APICallsUtils.postRequest(APIEndpoints.CREATE_ACCOUNT, apiTestDataManager.getTestDataMap("RegisterFirstUserData"));
    }

    @AfterClass(description = "Delete Created user Account")
    public void tearDown() {
        APICallsUtils.deleteRequest(APIEndpoints.DELETE_ACCOUNT, apiTestDataManager.getTestDataMap("DeleteAfterCreation"));
    }
}
