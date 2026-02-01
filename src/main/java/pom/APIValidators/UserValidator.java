package pom.APIValidators;

import io.restassured.response.Response;
import org.testng.Assert;
import utils.Framework.JsonFileReader;
import utils.HelperClasses.APIEndpoints;

import java.util.Map;

public class UserValidator {
    public static void validateUserExists(Response response) {
        Assert.assertFalse(response.jsonPath().getString("user").isEmpty());
        Assert.assertFalse(response.jsonPath().getString("user.id").isEmpty());
    }

    public static void validateUserDataInGetAgainstRegisteredData(Response response, JsonFileReader apiTestDataManager, String dataLocation) {
        Map<String, String> fieldMapping = APIEndpoints.REGISTER_TO_GET_USER_FIELD_MAPPING;

        for (Map.Entry<String, String> entry : fieldMapping.entrySet()) {
            Assert.assertEquals(response.jsonPath().get("user." + entry.getKey()), apiTestDataManager.getData(dataLocation +"."+entry.getValue()));
        }
    }
}
