package pom.APIValidators;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class BrandValidator {
    @Step("Validate the list of brands in the response")
    public static void validateBrandsList(Response response) {
        List<Map<String, Object>> brands = response.jsonPath().getList("brands");
        Assert.assertFalse(brands.isEmpty());

        for (Map<String, Object> brand : brands) {
            Assert.assertNotNull(brand.get("id"));
            Assert.assertFalse(brand.get("brand").toString().isEmpty());
        }
    }
}
