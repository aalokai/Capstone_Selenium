package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;
import reporting.ExtentManager;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
    public void setUp() {
        // Initialize Extent report once
        if (extent == null) {
            extent = ExtentManager.getInstance();
        }

        // Setup ChromeDriver automatically
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Just an info log for every test start
        System.out.println("Browser launched");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed");
        }
        if (extent != null) {
            extent.flush(); // writes everything to the HTML file
            System.out.println("Extent report flushed");
        }
    }

    // Helper to start a test log in Extent from your test classes
    protected void startTest(String testName) {
        test = extent.createTest(testName);
        test.log(Status.INFO, "Starting test: " + testName);
    }
}
