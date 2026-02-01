import { test, expect } from '@playwright/test';
import { HomePage } from '../pages/HomePage';
import { LoginPage } from '../pages/LoginPage';
import { BookDetailPage } from '../pages/BookDetailPage';

test('Flow: Login → Search → Buy Now', async ({ page }) => {
  const homePage = new HomePage(page);
  await homePage.openSite();
  await page.waitForTimeout(1000);
  
  await homePage.goToLoginFromMyAccount();
  await page.waitForTimeout(2000);
  
  const loginPage = new LoginPage(page);
  await loginPage.loginWithYourAccount();
  await page.waitForTimeout(2000);
  
  await homePage.openSite();
  await page.waitForTimeout(2000);
  
  await homePage.searchBook('Harry Potter');
  await page.waitForTimeout(2000);
  
  await page.locator('a[href*="/book/"]').first().click();
  await page.waitForTimeout(2000);
  
  const bookDetail = new BookDetailPage(page);
  const bookTitle = await bookDetail.getBookTitle();
  console.log('Buying now:', bookTitle);
  
  await bookDetail.buyNow();  // BUY NOW!
  await page.waitForTimeout(2000);
  
  // Verify checkout page
  await expect(page.locator('.checkout, #address')).toBeVisible();
  console.log('✅ Buy Now → Checkout!');
  await page.waitForTimeout(3000);
});
