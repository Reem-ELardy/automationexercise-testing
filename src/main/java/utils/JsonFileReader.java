package utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import org.testng.Assert;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class JsonFileReader {

    private static final String TEST_DATA_PATH = "src/test/resources/";
    private final String jsonFilePath;
    private final String jsonContent;

    public JsonFileReader(String jsonFileName) {
        this.jsonFilePath = TEST_DATA_PATH + jsonFileName;
        try {
            JsonObject jsonObject =
                    JsonParser.parseReader(new FileReader(jsonFilePath)).getAsJsonObject();
            this.jsonContent = jsonObject.toString();
        } catch (IOException e) {
            Assert.fail("Failed to read JSON file: " + jsonFilePath);
            throw new RuntimeException(e);
        }
    }

    public String getData(String jsonPath) {
        JsonElement current = JsonParser.parseString(jsonContent).getAsJsonObject();

        for (String key : jsonPath.split("\\.")) {
            if (!current.getAsJsonObject().has(key)) {
                Assert.fail("Key '" + key + "' not found in path: " + jsonPath);
            }
            current = current.getAsJsonObject().get(key);
        }
        return current.getAsString();
    }

    public Map<String, Object> getTestDataMap(String jsonPath) {
        try {
            return JsonPath.read(jsonContent, jsonPath);
        } catch (PathNotFoundException e) {
            Assert.fail("JsonPath not found: " + jsonPath);
            return null;
        }
    }
}
