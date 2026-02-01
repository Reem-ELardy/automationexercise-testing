package pom.tests.UiTesting;

import org.testng.annotations.BeforeSuite;
import pom.pages.HomePage;
import utils.Framework.DriverFactory;
import utils.Framework.JsonFileReader;
import utils.HelperClasses.UserFunctions;


public class BaseTestClass {
    JsonFileReader testDataManager;

    @BeforeSuite(description = "Register a test user once for all Account Management tests")
    public void registerUserOnce() {
        DriverFactory.initiateDriver();
        testDataManager = new JsonFileReader("UITestingData/userRegisterForAccountMangement.json");
        new HomePage()
                .navigateToHomePage();
        UserFunctions.SignUpAndLogout(testDataManager, "userTestDataNew");
        DriverFactory.quitDriver();
    }
}
