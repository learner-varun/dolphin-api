package core.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import core.utils.TimeUtilities;
import org.testng.ITestResult;

import static core.configs.TestConfigs.*;

public class ExtendReport {

    static ExtentSparkReporter sparkReporter;
    static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<ExtentTest>();
    private static ExtentReports extentReports;
    ExtentTest extentTest;

    public ExtendReport() {
    }

    public static synchronized boolean logInfoMessage(String message) {
        extentTestThreadLocal.get().info(message);
        return true;
    }

    public static synchronized boolean logInfoMessage(Markup message) {
        extentTestThreadLocal.get().info(message);
        return true;
    }

    public synchronized boolean startReport() {
        extentReports = new ExtentReports();
        SYSTEM_PROPERTIES.forEach((key, value) -> extentReports.setSystemInfo(key, value));
        sparkReporter = new ExtentSparkReporter(EXTENT_REPORT_FULL_PATH);
        extentReports.attachReporter(sparkReporter);

        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle(PROJECT_NAME + " : " + SUITE_NAME);
        sparkReporter.config().setReportName(ENVIRONMENT_NAME + " - " + SUITE_NAME);
        return true;
    }

    public synchronized boolean getResult(ITestResult result) {

        if (result.getStatus() == ITestResult.SUCCESS) {
            extentTestThreadLocal.get().pass("Test is <b><font color='green'>PASSED</font></b>");
        } else if (result.getStatus() == ITestResult.SKIP) {
            if (ADD_SKIPPED_CASES_TO_REPORT) {
                extentTestThreadLocal.get().skip("Test <b><font color='orange'>SKIPPED</font></b> due to : <br><pre>" + result.getThrowable().getMessage().replace("expected [true] but found [false]", "").trim().replaceAll("\n", "<br>") + "</pre>");
            } else {
                extentReports.removeTest(result.getMethod().getMethodName());
            }
        } else if (result.getStatus() == ITestResult.FAILURE) {
            extentTestThreadLocal.get().fail("Test <b><font color='red'> FAILED</font></b> due to : <br><pre>" + result.getThrowable().getMessage().replace("expected [true] but found [false]", "").trim().replaceAll("\n", "<br>") + "</pre>");
        }
        extentTestThreadLocal.get().info("************** Test is completed at : " + TimeUtilities.getCurrentTime() + " **************");
        extentTestThreadLocal.remove();
        return false;
    }

    public synchronized boolean addTestToReport(String testName, ITestResult iTestResult) {

        this.extentTest = extentReports.createTest(testName);
        for (String tagName : iTestResult.getMethod().getGroups()) {
            this.extentTest.assignCategory(tagName);
        }
        extentTestThreadLocal.set(this.extentTest);
        this.extentTest = null;
        return true;
    }

    public synchronized boolean endReport() {
        extentReports.flush();
        return true;
    }
}
