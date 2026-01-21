package utils.HelperFunctions;

import pom.pages.*;
import utils.Framework.JsonFileReader;

public class UserFunctions {
    public static void SignUpAndLogout(JsonFileReader dataManager, String parentJsonPath) {
        new HomePage()
                .navigateToHomePage();
        new Dashboard()
                .clickSignupLoginButton();
        new SignUpLoginPage()
                .SignUp(dataManager.getData(parentJsonPath + ".username"), dataManager.getData(parentJsonPath + ".email"));
        new SignUpPage()
                .fillAccountInformation(dataManager.getData(parentJsonPath + ".title"), dataManager.getData(parentJsonPath + ".password"), dataManager.getData(parentJsonPath + ".day"), dataManager.getData(parentJsonPath + ".month"), dataManager.getData(parentJsonPath + ".year"))
                .fillAddressPersonalInformation(dataManager.getData(parentJsonPath + ".firstName"), dataManager.getData(parentJsonPath + ".lastName"), dataManager.getData(parentJsonPath + ".company"), dataManager.getData(parentJsonPath + ".mobileNumber"))
                .fillAddressLocationInformation(dataManager.getData(parentJsonPath + ".address"), dataManager.getData(parentJsonPath + ".address2"), dataManager.getData(parentJsonPath + ".country"), dataManager.getData(parentJsonPath + ".state"), dataManager.getData(parentJsonPath + ".city"), dataManager.getData(parentJsonPath + ".zipCode"))
                .clickOnCreateAccountButton();
        new AccountCreatedPage()
                .clickContinueButton();
        new Dashboard()
                .clickLogOutButton();
    }

    public static void SignUpUser(JsonFileReader dataManager, String parentJsonPath) {
        new HomePage()
                .navigateToHomePage();
        new Dashboard()
                .clickSignupLoginButton();
        new SignUpLoginPage()
                .SignUp(dataManager.getData(parentJsonPath + ".username"), dataManager.getData(parentJsonPath + ".email"));
        new SignUpPage()
                .fillAccountInformation(dataManager.getData(parentJsonPath + ".title"), dataManager.getData(parentJsonPath + ".password"), dataManager.getData(parentJsonPath + ".day"), dataManager.getData(parentJsonPath + ".month"), dataManager.getData(parentJsonPath + ".year"))
                .fillAddressPersonalInformation(dataManager.getData(parentJsonPath + ".firstName"), dataManager.getData(parentJsonPath + ".lastName"), dataManager.getData(parentJsonPath + ".company"), dataManager.getData(parentJsonPath + ".mobileNumber"))
                .fillAddressLocationInformation(dataManager.getData(parentJsonPath + ".address"), dataManager.getData(parentJsonPath + ".address2"), dataManager.getData(parentJsonPath + ".country"), dataManager.getData(parentJsonPath + ".state"), dataManager.getData(parentJsonPath + ".city"), dataManager.getData(parentJsonPath + ".zipCode"))
                .clickOnCreateAccountButton();
        new AccountCreatedPage()
                .clickContinueButton();
    }

    public static void SignInUser(JsonFileReader dataManager, String parentJsonPath) {
        new Dashboard()
                .clickSignupLoginButton();
        new SignUpLoginPage()
                .login(dataManager.getData(parentJsonPath + ".email"), dataManager.getData(parentJsonPath + ".password"));
    }

    public static void deleteUserAccount() {
        new Dashboard()
                .clickDeleteAccountButton();
        new AccountDeletedPage()
                .clickContinueButton();
    }
}
