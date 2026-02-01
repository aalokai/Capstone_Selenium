import { test, expect } from '@playwright/test';
import { HomePage } from '../pages/HomePage';
import { LoginPage } from '../pages/LoginPage';

test.describe('Login Tests', () => {
  test('login from My Account menu', async ({ page }) => {
    // Navigate using HomePage
    const homePage = new HomePage(page);
    await homePage.openSite();
    await homePage.goToLoginFromMyAccount();
    
    // Login
    const loginPage = new LoginPage(page);
    await loginPage.loginWithYourAccount();
    
    // Verify success (update with your dashboard element)
    await expect(page.locator('#ctl00_lblUser')).toContainText('Aalok Tiwari');
    // OR await expect(page.locator('.dashboard')).toBeVisible();
  });
});
