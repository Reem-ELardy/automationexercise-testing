package utils.Framework;

public class AlertHandler {
    public static void acceptAlert() {
        DriverFactory.getDriver().switchTo().alert().accept();
    }

    public static void closeAlert() {
        DriverFactory.getDriver().switchTo().alert().dismiss();
    }

    public static void sendTextToAlert(String text) {
        DriverFactory.getDriver().switchTo().alert().sendKeys(text);
    }

    public static String getTextFromAlert() {
        return DriverFactory.getDriver().switchTo().alert().getText();
    }
}
