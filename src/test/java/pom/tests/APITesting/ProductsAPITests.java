package pom.tests.APITesting;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pom.APIValidators.ProductValidator;
import pom.APIValidators.ResponseValidator;
import utils.Framework.APICallsUtils;
import utils.Framework.JsonFileReader;
import utils.Framework.TestNgListener;
import utils.HelperClasses.APIEndpoints;


@Feature("Products API")
@Listeners({TestNgListener.class})
public class ProductsAPITests {
    JsonFileReader apiTestDataManager;

    @Test
    @TmsLink("AT-48")
    @Description("Verify that GET /productsList returns all products with status code 200 and proper JSON structure")
    @Severity(SeverityLevel.CRITICAL)
    public void API1_GetAllProductsList() {
        Response response = APICallsUtils.getRequest(APIEndpoints.PRODUCTS_LIST);
        ResponseValidator.validateResponseCode(response, 200);
        ProductValidator.validateProductsList(response);
    }

    @Test
    @TmsLink("AT-49")
    @Description("Verify that POST /productsList returns 405 Method Not Allowed")
    @Severity(SeverityLevel.NORMAL)
    public void API2_PostToAllProductsList() {
        Response response = APICallsUtils.postRequest(APIEndpoints.PRODUCTS_LIST);
        ResponseValidator.validateResponseCode(response, 405);
        ResponseValidator.validateMessage(response, apiTestDataManager.getData("MessageData.FailProductsWrongRequestMethod"));
    }

    @Test
    @TmsLink("AT-52")
    @Description("Verify that POST /searchProduct with valid 'search_product' parameter returns relevant products")
    @Severity(SeverityLevel.CRITICAL)
    public void API5_PostToSearchProduct() {
        Response response = APICallsUtils.postRequest(APIEndpoints.SEARCH_PRODUCT, apiTestDataManager.getTestDataMap("Search"));
        ResponseValidator.validateResponseCode(response, 200);
        ProductValidator.validateProductsList(response);
        ProductValidator.validateSearchMatch(response, apiTestDataManager.getData("Search.search_product"));
    }

    @Test
    @TmsLink("AT-53")
    @Description("Verify that POST /searchProduct without 'search_product' parameter returns 400 Bad Request")
    @Severity(SeverityLevel.CRITICAL)
    public void API6_PostToSearchProductWithoutParameter() {
        Response response = APICallsUtils.postRequest(APIEndpoints.SEARCH_PRODUCT);
        ResponseValidator.validateResponseCode(response, 400);
        ResponseValidator.validateMessage(response, apiTestDataManager.getData("MessageData.FailSearchForMissingSearchText"));
    }

    @BeforeClass(description = "SetUp json file reader")
    public void setUp() {
        apiTestDataManager = new JsonFileReader("APITestingData/ProductsAPITestData.json");
    }
}
