package pom.tests.APITesting;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pom.APIValidators.BrandValidator;
import pom.APIValidators.ResponseValidator;
import utils.Framework.APICallsUtils;
import utils.Framework.JsonFileReader;
import utils.Framework.TestNgListener;
import utils.HelperClasses.APIEndpoints;

@Feature("Brands API")
@Listeners({TestNgListener.class})
public class BrandsAPITests {
    JsonFileReader apiTestDataManager;

    @Test
    @TmsLink("AT-50")
    @Description("Verify that GET /brandsList returns all brands with status code 200 and brands list")
    @Severity(SeverityLevel.CRITICAL)
    public void API3_GetAllBrandsList() {
        Response response = APICallsUtils.getRequest(APIEndpoints.BRANDS_LIST);
        ResponseValidator.validateResponseCode(response, 200);
        BrandValidator.validateBrandsList(response);
    }

    @Test
    @TmsLink("AT-51")
    @Description("Verify that PUT /brandsList returns 405 Method Not Allowed")
    @Severity(SeverityLevel.NORMAL)
    public void API4_PutToAllBrandsList() {
        Response response = APICallsUtils.putRequest(APIEndpoints.BRANDS_LIST);
        ResponseValidator.validateResponseCode(response, 405);
        ResponseValidator.validateMessage(response, apiTestDataManager.getData("MessageData.FailBrandsWrongRequestMethod"));
    }

    @BeforeClass(description = "SetUp json file reader")
    public void setUp() {
        apiTestDataManager = new JsonFileReader("APITestingData/BrandsAPITestData.json");
    }
}
