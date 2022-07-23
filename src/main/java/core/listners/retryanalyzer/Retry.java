package core.listners.retryanalyzer;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static core.configs.TestConfigs.MAXIMUM_RETRY_COUNT;


public class Retry implements IRetryAnalyzer {
    private int retryCount = 0;

    public boolean retry(ITestResult result) {
        int maxRetryCount = MAXIMUM_RETRY_COUNT;
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retry #" + retryCount + " for test: " + result.getMethod().getMethodName() + ", on thread: " + Thread.currentThread().getName());
            return true;
        }
        return false;
    }
}