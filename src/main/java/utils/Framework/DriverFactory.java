package utils.Framework;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;

import java.util.Locale;

public class DriverFactory {
    static private WebDriver driver;

    @Step("Initialize Driver")
    static public WebDriver initiateDriver() {
        driver = initiateBrowser(System.getProperty("targetBrowser").toLowerCase(Locale.ROOT), Boolean.parseBoolean(System.getProperty("headless")), Boolean.parseBoolean(System.getProperty("maximizaWindow")));
        return driver;
    }

    static public WebDriver initiateBrowser(String BrowserName, boolean headless, boolean maximizaWindow) {
        switch (BrowserName) {
            case "firefox":
                if (headless) {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless=new");
                    driver = new FirefoxDriver(firefoxOptions);
                } else
                    driver = new FirefoxDriver();
                break;
            case "chrome":
                if (headless) {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless=new");
                    driver = new ChromeDriver(chromeOptions);
                } else
                    driver = new ChromeDriver();
                break;
            case "edge":
                if (headless) {
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--headless=new");
                    driver = new EdgeDriver(edgeOptions);
                } else
                    driver = new EdgeDriver();
                break;
            default:
                Assert.fail("Selected target browser [" + BrowserName + "] is not supported");
                break;
        }

        if (maximizaWindow) {
            driver.manage().window().maximize();
        }
        return driver;
    }

    static public WebDriver getDriver() {
        return driver;
    }

    static public void quitDriver() {
        driver.quit();
    }
}
