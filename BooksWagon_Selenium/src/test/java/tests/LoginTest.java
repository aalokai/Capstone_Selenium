package tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import base.ScreenshotUtil;
import base.TestListener;
import pages.AddressPage;
import pages.BookDetailPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PaymentPage;
import pages.ReviewOrderPage;
import pages.SearchResultsPage;

@Listeners(base.TestListener.class)  // Auto screenshot on failure
public class LoginTest extends BaseTest {

    @Test
    public void loginSearchFirstBookBuyNowAddressReviewPayment() {
        startTest("COMPLETE CAPSTONE: Login → Buy → Address → Review → CCAvenue");

        // 1. Home → My Account → Login
        HomePage home = new HomePage(driver);
        home.openSite();
        test.info("Opened Bookswagon homepage");

        home.goToLoginFromMyAccount();
        test.info("Navigated to login page");

        // 2. Login with your credentials
        LoginPage login = new LoginPage(driver);
        login.loginWithYourAccount();
        test.info("Submitted login credentials");

        // Screenshot after login
        test.addScreenCaptureFromPath(ScreenshotUtil.takeScreenshot(driver, "AfterLogin"));

        // 3. Wait for login to complete, then reload homepage
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        home.openSite();
        test.info("Returned to homepage after login");

        // 4. Search a book
        home.searchBook("Selenium");
        test.info("Searched for Selenium books");

        // Screenshot after search
        test.addScreenCaptureFromPath(ScreenshotUtil.takeScreenshot(driver, "AfterSearch"));

        // 5. Click first book (your Selenium book)
        SearchResultsPage results = new SearchResultsPage(driver);
        results.clickFirstBook();
        test.info("Clicked first book title (Selenium book)");

        // 6. On book detail page, click Buy Now
        BookDetailPage bookDetail = new BookDetailPage(driver);
        bookDetail.clickBuyNow();
        test.info("Clicked Buy Now button on book detail page");

        // Screenshot after Buy Now
        test.addScreenCaptureFromPath(ScreenshotUtil.takeScreenshot(driver, "AfterBuyNow"));

        // 7. Select saved address
        AddressPage address = new AddressPage(driver);
        address.selectSavedAddress();
        test.info("Selected saved address - Deliver to this Address");

        // Screenshot after address
        test.addScreenCaptureFromPath(ScreenshotUtil.takeScreenshot(driver, "AfterAddress"));

        // 8. Review Order → Save & Continue
        ReviewOrderPage reviewOrder = new ReviewOrderPage(driver);
        reviewOrder.clickSaveAndContinue();
        test.info("Clicked Save & Continue on Review Order");

        // Screenshot after review
        test.addScreenCaptureFromPath(ScreenshotUtil.takeScreenshot(driver, "AfterReviewOrder"));

        // 9. Payment Gateway → CCAvenue
        PaymentPage payment = new PaymentPage(driver);
        payment.selectCCAvenuePayment();
        test.info("Selected CCAvenue payment gateway");

        // Final success screenshot
        test.addScreenCaptureFromPath(ScreenshotUtil.takeScreenshot(driver, "PaymentGatewayReached"));

        test.pass("🎉 COMPLETE CAPSTONE FLOW: Reached CCAvenue Payment Gateway!");
    }
}
