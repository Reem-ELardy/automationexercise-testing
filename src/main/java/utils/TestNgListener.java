package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.ByteArrayInputStream;
import java.io.File;

public class TestNgListener implements ISuiteListener, ITestListener, IInvokedMethodListener {

    @Override
    public void onStart(ISuite Suite){
        System.out.println("Hello from Giza Systems interns 2025" + "\n" + "We are starting our execution from here" );
        PropertiesReader.loadProperties();
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("Execution is finished ... Thank you !.. ");
        try {
            // Generate report
            String[] generateCmd = { "cmd.exe", "/c", "mvn allure:report" };
            Process genProcess = new ProcessBuilder(generateCmd)
                    .inheritIO()
                    .start();
            genProcess.waitFor();
            System.out.println("Allure report generated successfully!");

            String reportPath = System.getProperty("user.dir") + "\\target\\allure-report";

            // Serve report (optional)
            File htmlFile = new File(reportPath + "\\index.html");
            if(htmlFile.exists()) {
                java.awt.Desktop.getDesktop().browse(htmlFile.toURI());
                System.out.println("Allure report opened in browser!");
            }
            System.out.println("Allure report is being served at http://localhost:####/ (Press Ctrl+C to stop)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("[INFO] Starting test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[PASS] Test passed: " + result.getName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        WebDriver driver = DriverFactory.getDriver();
        if (ITestResult.FAILURE == testResult.getStatus() && driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(screenshot));
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[SKIP] Test skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("[WARN] Test failed but within success percentage: " + result.getName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        System.out.println("[TIMEOUT] Test failed due to timeout: " + result.getName());
        onTestFailure(result);
    }

}
