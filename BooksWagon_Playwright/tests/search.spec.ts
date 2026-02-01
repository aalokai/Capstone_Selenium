import { test, expect } from '@playwright/test';
import { HomePage } from '../pages/HomePage';
import { LoginPage } from '../pages/LoginPage';

test('Flow: Login → Homepage → Search (with pauses)', async ({ page }) => {
  // 1. Open + Login
  const homePage = new HomePage(page);
  await homePage.openSite();
  await page.waitForTimeout(1000);
  
  await homePage.goToLoginFromMyAccount();
  await page.waitForTimeout(2000);
  
  const loginPage = new LoginPage(page);
  await loginPage.loginWithYourAccount();
  await page.waitForTimeout(2000);
  
  // 2. Go to homepage (instead of reload)
  await homePage.openSite();  // Navigates to '/'
  await page.waitForTimeout(2000);
  
  // 3. Search (logged in)
  await homePage.searchBook('Harry Potter');
  await page.waitForTimeout(2000);
  
  // 4. Verify
  await expect(page.locator('body')).toContainText('Harry Potter');
  await expect(page.locator('.product, .book-item')).toBeVisible();
  
  console.log('✅ Homepage → Search complete!');
  await page.waitForTimeout(3000);
});
