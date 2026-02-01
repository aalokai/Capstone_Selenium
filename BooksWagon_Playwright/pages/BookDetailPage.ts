import { Page, Locator } from '@playwright/test';

export class BookDetailPage {
  readonly page: Page;
  readonly bookTitle: Locator;
  readonly buyNowButton: Locator;

  constructor(page: Page) {
    this.page = page;
    this.bookTitle = page.locator('h1:not(#ctl00_phBody_UserFeedBack_fbThanku)');
    // YOUR exact Buy Now button
    this.buyNowButton = page.locator('a[onclick="BuyNowProduct()"], a:has-text("Buy Now").btn');
  }

  async buyNow() {
    await this.buyNowButton.first().click();
    await this.page.waitForTimeout(2000);
  }

  async getBookTitle(): Promise<string> {
    return await this.bookTitle.first().textContent() || '';
  }
}
