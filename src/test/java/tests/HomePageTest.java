

package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;

public class HomePageTest extends BaseTest {

    @Test
    public void openHomeAndGoToLogin() {
        startTest("Open home page, go to My Account and click Login");

        HomePage home = new HomePage(driver);

        // Open site
        home.openSite();
        test.info("Opened Bookswagon home page");

        // Click My Account -> Login
        home.goToLoginFromMyAccount();
        test.info("Clicked My Account then Login");

        // Later you will add assertions for login page (URL, title, fields present)
    }
}
