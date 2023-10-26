package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;
import testBase.BaseTestClass;

import java.io.IOException;

public class ExtentReportUtility implements ITestListener, ISuiteListener {
    public static ExtentSparkReporter extentSparkReporter;
    public static ExtentReports extentReport;
    public static ExtentTest extentTest;
    Logger logger = LogManager.getLogger(this.getClass());



    @Override
    public void onStart(ISuite suite) {
        extentSparkReporter = new ExtentSparkReporter("reports/extent-report.html");
        extentSparkReporter.config().setDocumentTitle("henry document");
        extentSparkReporter.config().setReportName("Test report");
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentReport = new ExtentReports();
        extentReport.attachReporter(extentSparkReporter);
        extentReport.setSystemInfo("Tester Name", "Jake");
    }

//    @Override
//    public void onTestStart(ITestResult result) {
//    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest = extentReport.createTest("Test case: " + result.getName() + " Test: " + result.getTestClass().getName());
        extentTest.log(Status.PASS,"Test case PASSED: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest = extentReport.createTest(result.getName());
        extentTest.log(Status.SKIP, "Test case SKIPPED: " + result.getSkipCausedBy());
    }


    @Override
    public void onTestFailure(ITestResult result) {
        extentTest = extentReport.createTest("Test case: " + result.getName() + " Test: " + result.getTestClass().getName());
        extentTest.log(Status.FAIL, "Test case FAILED: " + result.getThrowable());

        try {
            Object testClass = result.getInstance();
            var driver = ((BaseTestClass) testClass).getWebDriver();
            logger.info("test class: " + driver);
            String imagePath = ((BaseTestClass) testClass).captureScreen(result.getName());
            if (imagePath.contains(String.valueOf('/') )) {
                imagePath = imagePath.replace('/', '\\');
                imagePath = "\\" + imagePath;
            }

            extentTest.addScreenCaptureFromPath(imagePath);
        } catch(IOException e) {
            System.out.println("error io: screenshot can't be printed");
        }
    }


    @Override
    public void onFinish(ITestContext context) {

    }

    @Override
    public void onFinish(ISuite suite) {
        extentReport.flush();
    }
}
