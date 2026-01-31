package utils.HelperFunctions;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import utils.Framework.DriverFactory;

import java.io.ByteArrayInputStream;

public class AllureHelperFunction {
    // ------------------ Helper ------------------
    public static void takeScreenshot(ITestResult result) {
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Failure Screenshot: " + result.getName(),
                        new ByteArrayInputStream(screenshot));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("[WARN] WebDriver is null, cannot take screenshot for: " + result.getName());
        }
    }
}
