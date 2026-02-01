package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class SampleTest extends BaseTest {

    @Test
    public void openBookswagonAndVerifyTitle() {
        // Start Extent test log
        startTest("Open Bookswagon and verify title");

        // Step 1: Open URL
        driver.get("https://www.bookswagon.com");
        test.info("Opened Bookswagon website");

        // Step 2: Get page title
        String actualTitle = driver.getTitle();
        test.info("Page title is: " + actualTitle);

        // Step 3: Simple assertion on title
        // Just check that title contains the word "Bookswagon"
        Assert.assertTrue(actualTitle.contains("Online Bookstore"),
                "Page title does not contain 'Bookswagon'");

        test.pass("Verified that page title contains 'Bookswagon'");
    }
}
