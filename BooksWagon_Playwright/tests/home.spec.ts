import { test, expect } from '@playwright/test';
import { HomePage } from '../pages/HomePage';

test.describe('HomePage Tests', () => {
  test('search book', async ({ page }) => {
    const homePage = new HomePage(page);
    await homePage.openSite();
    await homePage.searchBook('Harry Potter');
    
    await expect(page.locator('h1')).toBeVisible();  // Any visible element
  });

  test('go to login', async ({ page }) => {
    const homePage = new HomePage(page);
    await homePage.openSite();
    await homePage.goToLoginFromMyAccount();
    
    await expect(page.locator('#ctl00_phBody_SignIn_txtEmail')).toBeVisible();
  });
});
