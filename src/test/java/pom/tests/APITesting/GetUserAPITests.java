package pom.tests.APITesting;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
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
        Assert.assertEquals(response.jsonPath().getInt("responseCode"), 200);
        Assert.assertFalse(response.jsonPath().getString("user").isEmpty());
        Assert.assertFalse(response.jsonPath().getString("user.id").isEmpty());

        Map<String, String> fieldMapping = APIEndpoints.REGISTER_TO_GET_USER_FIELD_MAPPING;
        for (Map.Entry<String, String> entry : fieldMapping.entrySet()) {
            Assert.assertEquals(response.jsonPath().get("user." + entry.getKey()), apiTestDataManager.getData("RegisterFirstUserData."+ entry.getValue()));
        }
    }

    @Test
    @TmsLink("AT-70")
    @Description("Verify that GET /getUserDetailByEmail with invalid email returns 404 and 'Account not found with this email, try another email!'")
    @Severity(SeverityLevel.CRITICAL)
    public void API17_GetUserAccountDetailByInvalidEmail() {
        Response response = APICallsUtils.getRequest(APIEndpoints.GET_USER_BY_EMAIL, apiTestDataManager.getTestDataMap("GetUserInvalidData"));
        Assert.assertEquals(response.jsonPath().getInt("responseCode"), 404);
        Assert.assertEquals(response.jsonPath().getString("message"), apiTestDataManager.getData("MessageData.FailUpdateNotFound"));
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
