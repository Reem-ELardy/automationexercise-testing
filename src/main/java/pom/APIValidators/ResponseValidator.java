package pom.APIValidators;

import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseValidator {
    public static void validateResponseCode(Response response, int expectedCode) {
        Assert.assertEquals(response.jsonPath().getInt("responseCode"), expectedCode);
    }

    public static void validateMessage(Response response, String expectedMessage) {
        Assert.assertEquals(response.jsonPath().getString("message"), expectedMessage);
    }
}
