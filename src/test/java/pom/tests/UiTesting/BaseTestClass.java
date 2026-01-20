package pom.tests.UiTesting;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pom.pages.HomePage;
import utils.Framework.DriverFactory;
import utils.Framework.JsonFileReader;
import utils.HelperFunctions.UserFunctions;

public class BaseTestClass {
    JsonFileReader testDataManager;

    @BeforeSuite
    public void registerUserOnce() {
        DriverFactory.initiateDriver();
        testDataManager = new JsonFileReader("UITestingData/userRegisterForAccountMangement.json");
        new HomePage()
                .navigateToHomePage();
        UserFunctions.SignUpAndLogout(testDataManager, "userTestDataNew");
        DriverFactory.quitDriver();
    }

//    @AfterSuite
//    public void teardown() {
//        new HomePage()
//                .navigateToHomePage();
//
//
//    }
}
