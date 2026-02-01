package pom.tests.APITesting;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
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
    public void API17_DeleteUserAccountWithValidCredentials() {
        Response response = APICallsUtils.deleteRequest(APIEndpoints.DELETE_ACCOUNT, apiTestDataManager.getTestDataMap("UserData"));
        Assert.assertEquals(response.jsonPath().getInt("responseCode"), 200);
        Assert.assertEquals(response.jsonPath().getString("message"), "Account deleted!");
    }

    @Test
    @TmsLink("AT-69")
    @Description("Verify that DELETE /deleteAccount with invalid email or password returns 404 and 'Account not found!'")
    @Severity(SeverityLevel.CRITICAL)
    public void API18_DeleteUserAccountWithInvalidCredentials() {
        Response response = APICallsUtils.deleteRequest(APIEndpoints.DELETE_ACCOUNT, apiTestDataManager.getTestDataMap("UserDataInvalidPassword"));
        Assert.assertEquals(response.jsonPath().getInt("responseCode"), 404);
        Assert.assertEquals(response.jsonPath().getString("message"), "Account not found!");
    }

    @Test
    @TmsLink("AT-70")
    @Description("Verify that DELETE /deleteAccount without providing the email parameter returns 400 and a validation error message")
    @Severity(SeverityLevel.CRITICAL)
    public void API19_DeleteUserAccountWithMissingEmail() {
        Response response = APICallsUtils.deleteRequest(APIEndpoints.DELETE_ACCOUNT, apiTestDataManager.getTestDataMap("UserDataWithMissingEmail"));
        Assert.assertEquals(response.jsonPath().getInt("responseCode"), 400);
        Assert.assertEquals(response.jsonPath().getString("message"), "Bad request, email parameter is missing in DELETE request.");
    }

    @BeforeClass(description = "SetUp json file reader, Create User Account")
    public void setUp() {
        apiTestDataManager = new JsonFileReader("APITestingData/DeleteUserTestData.json");
        APICallsUtils.postRequest(APIEndpoints.CREATE_ACCOUNT, apiTestDataManager.getTestDataMap("RegisterFirstUserData"));
    }
}
