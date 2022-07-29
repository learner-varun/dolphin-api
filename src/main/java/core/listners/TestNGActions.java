package core.listners;

import core.configs.ConfigLoader;
import core.enums.LogLevel;
import core.logger.LoggerFactory;
import core.report.ExtendReport;
import core.utils.StringUtilities;
import core.utils.TimeUtilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static core.configs.TestConfigs.*;


public class TestNGActions implements ITestListener {
    LoggerFactory loggerFactory = new LoggerFactory(this.getClass());
    ExtendReport extendReport = new ExtendReport();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        extendReport.addTestToReport(iTestResult.getMethod().getMethodName(), iTestResult);
        ExtendReport.logInfoMessage("************** Test is started at : " + TimeUtilities.getCurrentTime() + " **************");
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
        String configFile = System.getProperty("ConfigFile");
        if (!StringUtilities.isNull(configFile)) {
            ConfigLoader.loadConfig(TEST_RESOURCE_FOLDER + "/" + configFile);
            loggerFactory.logMessage(LogLevel.INFO, "The configuration is loading from : " + configFile);
        } else {
            ConfigLoader.loadConfig(TEST_RESOURCE_FOLDER + "/" + DEAFULT_CONFIG_JSON_FILE);
            loggerFactory.logMessage(LogLevel.INFO, "The configuration is loading from : " + DEAFULT_CONFIG_JSON_FILE);
        }
        extendReport.startReport();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extendReport.endReport();
    }

    public void logMessageToReport(String message) {
        extendReport.logInfoMessage(message);
    }
}
