package utils.Framework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

import java.util.Map;

public class APICallsUtils {
    public static Response postRequest(String url, Map<String, Object> formParams) {
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

    public static Response deleteRequest(String url, Map<String, Object> formParams) {
        return RestAssured
        .given()
                .contentType("application/x-www-form-urlencoded")
                .formParams(formParams)
                .log().all()
        .when()
                .delete(System.getProperty("apiUrl") + url)
        .then()
                .statusCode(anyOf(is(200), is(201)))
        .and()
                .log().body().extract().response();
    }

    public static Response getRequest(String url, Map<String, Object> formParams) {
        return RestAssured
        .given()
                .contentType("application/x-www-form-urlencoded")
                .formParams(formParams)
                .log().all()
        .when()
                .get(System.getProperty("apiUrl") + url)
        .then()
                .statusCode(anyOf(is(200), is(201)))
        .and()
                .log().body().extract().response();
    }

    public static Response putRequest(String url, Map<String, Object> formParams) {
        return RestAssured
        .given()
                .contentType(System.getProperty("contentType"))
                .formParams(formParams)
                .log().all()
        .when()
                .put(System.getProperty("apiUrl") + url)
        .then()
                .statusCode(anyOf(is(200), is(201)))
        .and()
                .log().body().extract().response();
    }

    public static Response postRequest(String url) {
        return RestAssured
        .given()
                .contentType("application/x-www-form-urlencoded")
                .log().all()
        .when()
                .post(System.getProperty("apiUrl") + url)
        .then()
                .statusCode(anyOf(is(200), is(201)))
        .and()
                .log().body().extract().response();
    }

    public static Response deleteRequest(String url) {
        return RestAssured
        .given()
                .contentType("application/x-www-form-urlencoded")
                .log().all()
        .when()
                .delete(System.getProperty("apiUrl") + url)
        .then()
                .statusCode(anyOf(is(200), is(201)))
        .and()
                .log().body().extract().response();
    }

    public static Response getRequest(String url) {
        return RestAssured
        .given()
                .contentType("application/x-www-form-urlencoded")
                .log().all()
        .when()
                .get(System.getProperty("apiUrl") + url)
        .then()
                .statusCode(anyOf(is(200), is(201)))
        .and()
                .log().body().extract().response();
    }

    public static Response putRequest(String url) {
        return RestAssured
        .given()
                .contentType(System.getProperty("contentType"))
                .log().all()
        .when()
                .put(System.getProperty("apiUrl") + url)
        .then()
                .statusCode(anyOf(is(200), is(201)))
        .and()
                .log().body().extract().response();
    }

    public static String postRequest(String url, String body, java.util.Map<String, String> placeholders) {
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
                .post(System.getProperty("apiUrl") + url)
        .then()
                .statusCode(anyOf(is(200), is(201)))
        .and()
                .log().body()
                .extract().asString();
    }

    public static String postRequest(String url, String body, String extractedData) {
        return RestAssured
        .given()
                .contentType("application/x-www-form-urlencoded")
                .body(body)
                .log().all()
        .when()
                .post(System.getProperty("apiUrl") + url)
        .then()
                .statusCode(anyOf(is(200), is(201)))
        .and()
                .log().body().extract().body().jsonPath().get(extractedData).toString();
    }

    public static Response postRequest(String url, String body) {
        return RestAssured
        .given()
                .contentType("application/x-www-form-urlencoded")
                .body(body)
                .log().all()
        .when()
                .post(System.getProperty("apiUrl") + url)
        .then()
                .statusCode(anyOf(is(200), is(201)))
        .and()
                .log().body().extract().response();
    }

}
