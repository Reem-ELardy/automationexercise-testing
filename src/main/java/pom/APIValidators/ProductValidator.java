package pom.APIValidators;

import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class ProductValidator {
    public static void validateProductsList(Response response) {
        List<Map<String, Object>> products = response.jsonPath().getList("products");
        Assert.assertFalse(products.isEmpty());
        for (int i = 0; i < products.size(); i++) {
            validateSingleProduct(response, products.get(i), i);
        }
    }

    private static void validateSingleProduct(Response response, Map<String, Object> product, int index) {
        Assert.assertNotNull(product.get("id"));
        Assert.assertNotNull(product.get("name"));
        Assert.assertFalse(product.get("name").toString().isEmpty());
        Assert.assertNotNull(product.get("price"));
        Assert.assertNotNull(product.get("brand"));
        Assert.assertFalse(product.get("brand").toString().isEmpty());
        Assert.assertNotNull(product.get("category"));
        Assert.assertFalse(response.jsonPath().getString("products[" + index + "].category.usertype.usertype").isEmpty());
        Assert.assertFalse(response.jsonPath().getString("products[" + index + "].category.category").isEmpty());
    }

    public static void validateSearchMatch(Response response, String searchText) {
        List<Map<String, Object>> products = response.jsonPath().getList("products");
        for (Map<String, Object> product : products) {
            validateSingleSearchMatch(product, searchText);
        }
    }

    public static void validateSingleSearchMatch(Map<String, Object> product, String searchText) {
        String normalizedSearch = searchText.toLowerCase();
        String name = product.get("name").toString().toLowerCase().replaceAll("[^a-z0-9]", "");
        String brand = product.get("brand").toString().toLowerCase().replaceAll("[^a-z0-9]", "");
        Assert.assertTrue(name.contains(normalizedSearch) || brand.contains(normalizedSearch));
    }

}
