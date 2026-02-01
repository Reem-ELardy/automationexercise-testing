package utils.HelperClasses;

import java.util.Map;

public class APIEndpoints {

    // Product APIs
    public static final String PRODUCTS_LIST = "/productsList";
    public static final String BRANDS_LIST = "/brandsList";
    public static final String SEARCH_PRODUCT = "/searchProduct";

    // Auth APIs
    public static final String VERIFY_LOGIN = "/verifyLogin";

    // User Account APIs
    public static final String CREATE_ACCOUNT = "/createAccount";
    public static final String UPDATE_ACCOUNT = "/updateAccount";
    public static final String DELETE_ACCOUNT = "/deleteAccount";
    public static final String GET_USER_BY_EMAIL = "/getUserDetailByEmail";

    public static final Map<String, String> REGISTER_TO_GET_USER_FIELD_MAPPING = Map.ofEntries(
            Map.entry("name", "name"),
            Map.entry("email", "email"),
            Map.entry("title", "title"),
            Map.entry("birth_day", "birth_date"),
            Map.entry("birth_month", "birth_month"),
            Map.entry("birth_year", "birth_year"),
            Map.entry("first_name", "firstname"),
            Map.entry("last_name", "lastname"),
            Map.entry("company", "company"),
            Map.entry("address1", "address1"),
            Map.entry("address2", "address2"),
            Map.entry("country", "country"),
            Map.entry("state", "state"),
            Map.entry("city", "city"),
            Map.entry("zipcode", "zipcode")
    );
}
