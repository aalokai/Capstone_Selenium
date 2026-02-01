import { test, expect } from '@playwright/test';
import { HomePage } from '../pages/HomePage';
import { LoginPage } from '../pages/LoginPage';
import { BookDetailPage } from '../pages/BookDetailPage';
import { AddressPage } from '../pages/AddressPage';
import { ReviewOrderPage } from '../pages/ReviewOrderPage';
import { PaymentPage } from '../pages/PaymentPage';

test('Detailed Checkout Flow ✅', async ({ page }) => {
  console.log('🎬 Starting Detailed Checkout...');
  
  // Phase 1: Login
  const homePage = new HomePage(page);
  await homePage.openSite();
  console.log('1️⃣ Homepage loaded');
  
  await homePage.goToLoginFromMyAccount();
  const loginPage = new LoginPage(page);
  await loginPage.loginWithYourAccount();
  await page.waitForLoadState('networkidle');  // Safe pause
  console.log('✅ Login complete');
  
  // Phase 2: Search & Select
  await homePage.openSite();
  await homePage.searchBook('Harry Potter');
  await page.waitForLoadState('networkidle');
  console.log('2️⃣ Search results');
  
  await page.locator('a[href*="/book/"]').first().click();
  await page.waitForLoadState('networkidle');
  console.log('3️⃣ Book detail page');
  
  const bookDetail = new BookDetailPage(page);
  await bookDetail.buyNow();
  await page.waitForLoadState('networkidle');
  console.log('✅ Buy Now clicked');
  
  // Phase 3: Checkout Flow
  const addressPage = new AddressPage(page);
  await addressPage.selectExistingAddress();
  await page.waitForLoadState('networkidle');
  console.log('4️⃣ Address selected');
  
  const reviewPage = new ReviewOrderPage(page);
  await reviewPage.saveAndContinue();
  await page.waitForLoadState('networkidle');
  console.log('5️⃣ Review order passed');
  
  const paymentPage = new PaymentPage(page);
  await paymentPage.payWithCCAvenue();
  await page.waitForLoadState('networkidle');
  
  console.log('🎉 FULL CHECKOUT SUCCESS → CCAVENUE GATEWAY!');
  await page.screenshot({ path: 'complete-checkout.png', fullPage: true });
});
