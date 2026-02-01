package base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class TestListener extends BaseTest implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        // Get test name
        String testName = result.getName();

        // Take screenshot
        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, testName);

        // Log to Extent report
        if (extent != null) {
            // Create a node in case 'test' is null for some reason
            if (test == null) {
                test = extent.createTest(testName);
            }
            test.log(Status.FAIL, "Test Failed: " + testName);
            test.log(Status.FAIL, result.getThrowable());

            try {
                test.addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Optional: log pass/skip/start if you like
    @Override
    public void onTestSuccess(ITestResult result) {
        // you can also capture screenshot on success if needed:
        // String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getName());
        // test.addScreenCaptureFromPath(screenshotPath);
    }

    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
