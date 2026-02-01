package utils.HelperClasses;

import utils.Framework.APICallsUtils;
import utils.Framework.JsonFileReader;

import java.util.Map;

public class APIFunctions {
    public static void DeleteUserWithRandomEmail(String createdEmail, Map<String, Object> testDataMap) {
        testDataMap.put("email", createdEmail);
        APICallsUtils.deleteRequest(APIEndpoints.DELETE_ACCOUNT, testDataMap);
    }

    public static void createUser(Map<String, Object> testDataMap) {
        APICallsUtils.postRequest(APIEndpoints.CREATE_ACCOUNT, testDataMap);
    }

    public static void deleteUser(Map<String, Object> testDataMap) {
        APICallsUtils.deleteRequest(APIEndpoints.DELETE_ACCOUNT, testDataMap);
    }
}
