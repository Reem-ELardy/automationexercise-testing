package utils.Framework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

import java.util.Map;

public class APICallsUtils {
    public static String postRequestAndExtract(String url, String body, String extractedData) {
        return RestAssured
        .given()
                .contentType("application/x-www-form-urlencoded")
                .body(body)
                .log().all()
        .when()
                .post(url)
        .then()
                .log().body().extract().body().jsonPath().get(extractedData).toString();
    }

    public static Response postRequestWithBody(String url, String body) {
        return RestAssured
        .given()
                .contentType("application/x-www-form-urlencoded")
                .body(body)
                .log().all()
        .when()
                .post(url)
        .then()
                .log().body().extract().response();
    }

    public static Response postRequestWithFormParam(String url, Map<String, Object> formParams) {
        return RestAssured
        .given()
                .contentType("application/x-www-form-urlencoded")
                .formParams(formParams)
                .log().all()
        .when()
                .post(System.getProperty("apiUrl") + url)
        .then()
                .statusCode(anyOf(is(200), is(201)))
        .and()
                .log().body().extract().response();
    }

    public static String postRequestWithPlaceholders(String url, String body, java.util.Map<String, String> placeholders) {
        if (placeholders != null) {
            for (String key : placeholders.keySet()) {
                body = body.replace("{" + key + "}", placeholders.get(key));
            }
        }

        return RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded")
                .body(body)
                .log().all()
                .when()
                .post(url)
                .then()
                .log().body()
                .extract().asString();
    }

}
