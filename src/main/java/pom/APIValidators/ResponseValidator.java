package pom.APIValidators;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseValidator {

    @Step("Validate the response code")
    public static void validateResponseCode(Response response, int expectedCode) {
        Assert.assertEquals(response.jsonPath().getInt("responseCode"), expectedCode);
    }

    @Step("Validate the response message")
    public static void validateMessage(Response response, String expectedMessage) {
        Assert.assertEquals(response.jsonPath().getString("message"), expectedMessage);
    }
}
