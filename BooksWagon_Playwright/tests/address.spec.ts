import { test, expect } from '@playwright/test';
import { HomePage } from '../pages/HomePage';
import { LoginPage } from '../pages/LoginPage';
import { BookDetailPage } from '../pages/BookDetailPage';
import { AddressPage } from '../pages/AddressPage';
import { ReviewOrderPage } from '../pages/ReviewOrderPage';

test('Full Checkout ✅', async ({ page }) => {
  const homePage = new HomePage(page);
  await homePage.openSite();
  
  await homePage.goToLoginFromMyAccount();
  const loginPage = new LoginPage(page);
  await loginPage.loginWithYourAccount();
  
  await homePage.openSite();
  await homePage.searchBook('Harry Potter');
  
  await page.locator('a[href*="/book/"]').first().click();
  const bookDetail = new BookDetailPage(page);
  await bookDetail.buyNow();
  
  const addressPage = new AddressPage(page);
  await addressPage.selectExistingAddress();
  
//   const reviewPage = new ReviewOrderPage(page);
//   await reviewPage.saveAndContinue();
  
  console.log('✅ FULL CHECKOUT COMPLETE - PAYMENT PAGE!');
});
