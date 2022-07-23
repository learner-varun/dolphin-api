package core.listners;

import com.aventstack.extentreports.ExtentTest;
import core.base.APIBase;
import core.report.ExtendReport;
import core.utils.TimeUtilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;

import static core.configs.TestConfigs.EXECUTION_START_TIME;
import static examples.constants.APIConfigs.BASE_URL;


public class TestNGActions implements ITestListener {
    ExtendReport extendReport = new ExtendReport();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        extendReport.addTestToReport(iTestResult.getMethod().getMethodName(),iTestResult);
        ExtendReport.logInfoMessage("************** Test is started at : "+TimeUtilities.getCurrentTime()+" **************");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        extendReport.getResult(iTestResult);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        extendReport.getResult(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        extendReport.getResult(iTestResult);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        EXECUTION_START_TIME = TimeUtilities.getCurrentTime();
        extendReport.startReport();
        APIBase.setBaseURL(BASE_URL);
        Map<String,String> headers = new HashMap();
        headers.put("Content-Type","application/json");
        APIBase.addGlobalHeaders(headers);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extendReport.endReport();
    }
    public void logMessageToReport(String message)
    {
        extendReport.logInfoMessage(message);
    }
}
