package utils.Framework;

import org.openqa.selenium.NoAlertPresentException;

public class AlertHandler {
    public static void acceptAlert() {
        try {
            DriverFactory.getDriver().switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            // no alert, continue
        }
    }

    public static void closeAlert() {
        try{
            DriverFactory.getDriver().switchTo().alert().dismiss();
        }catch (NoAlertPresentException e) {
            // no alert, continue
        }
    }

    public static void sendTextToAlert(String text) {
        DriverFactory.getDriver().switchTo().alert().sendKeys(text);
    }

    public static String getTextFromAlert() {
        return DriverFactory.getDriver().switchTo().alert().getText();
    }
}
