package utils.Framework;

import org.openqa.selenium.WebDriver;
import org.testng.*;
import utils.HelperClasses.AllureHelperFunction;

import java.io.File;

public class TestNgListener implements ISuiteListener, ITestListener, IInvokedMethodListener, IExecutionListener {

    //Before/After Full Execution
    @Override
    public void onExecutionStart() {
        System.out.println("=============================================== BEGIN ====================================");
        System.out.println("We are starting our execution from here");
        PropertiesReader.loadProperties();
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("Execution is finished ... Thank you !.. ");
        System.out.println("=============================================== END ====================================");
        try {
            // Generate report
            String[] generateCmd = {"cmd.exe", "/c", "mvn allure:report"};
            Process genProcess = new ProcessBuilder(generateCmd)
                    .inheritIO()
                    .start();
            genProcess.waitFor();
            System.out.println("Allure report generated successfully!");

            String reportPath = System.getProperty("user.dir") + "\\target\\allure-report";

            // Serve report (optional)
            File htmlFile = new File(reportPath + "\\index.html");
            if (htmlFile.exists()) {
                java.awt.Desktop.getDesktop().browse(htmlFile.toURI());
                System.out.println("Allure report opened in browser!");
            }
            System.out.println("Allure report is being served at http://localhost:####/ (Press Ctrl+C to stop)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Before/After Suite
    @Override
    public void onStart(ISuite suite) {
        System.out.println("===============================================START SUITE====================================");
        System.out.println("[SUITE START] " + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("[SUITE FINISH] " + suite.getName());
        System.out.println("===============================================END SUITE====================================");

    }

    //Before/After Methods
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            if (ITestResult.FAILURE == testResult.getStatus()) {
                AllureHelperFunction.takeScreenshot(testResult);
            }
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            if (ITestResult.FAILURE == testResult.getStatus()) {
                AllureHelperFunction.takeScreenshot(testResult);
            }
        }
    }

    //Test Case Listeners
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("[INFO] Starting test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[PASS] Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[FAIL] Test failed: " + result.getName());
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            AllureHelperFunction.takeScreenshot(result);
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
