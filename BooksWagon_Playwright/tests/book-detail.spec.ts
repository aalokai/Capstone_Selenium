import { test, expect } from '@playwright/test';
import { HomePage } from '../pages/HomePage';
import { LoginPage } from '../pages/LoginPage';
import { BookDetailPage } from '../pages/BookDetailPage';

test('Complete Flow: Login → Home → Search → Book Detail', async ({ page }) => {
  // 1. Open site
  const homePage = new HomePage(page);
  await homePage.openSite();
  await page.waitForTimeout(1000);
  
  // 2. Login flow
  await homePage.goToLoginFromMyAccount();
  await page.waitForTimeout(2000);
  
  const loginPage = new LoginPage(page);
  await loginPage.loginWithYourAccount();
  await page.waitForTimeout(2000);
  
  // 3. Homepage after login
  await homePage.openSite();
  await page.waitForTimeout(2000);
  
  // 4. Search Harry Potter
  await homePage.searchBook('Harry Potter');
  await page.waitForTimeout(2000);
  
  // 5. Click first book result
  await page.locator('a[href*="/book/"]').first().click();
  await page.waitForTimeout(2000);
  
  // 6. Verify book detail page
  const bookDetail = new BookDetailPage(page);
  const bookTitle = await bookDetail.getBookTitle();
  console.log('✅ Book detail loaded:', bookTitle);
  
  await expect(bookDetail.bookTitle).toBeVisible();
  await page.waitForTimeout(3000);
});
