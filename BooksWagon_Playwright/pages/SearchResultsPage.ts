import { Page, Locator } from '@playwright/test';

export class SearchResultsPage {
  readonly page: Page;
  readonly firstBookTitle: Locator;
  readonly firstBookImage: Locator;
  readonly firstAddToCart: Locator;

  constructor(page: Page) {
    this.page = page;
    // Update these after you inspect real elements
    this.firstBookTitle = page.locator('.book-title:first-child, h3:first-child a');
    this.firstBookImage = page.locator('.book-image:first-child img');
    this.firstAddToCart = page.locator('.add-cart:first-child, button:has-text("Add"):first-child');
  }

  async clickFirstBook() {
    await this.firstBookImage.click();  // Usually more reliable
  }

  async addFirstBookToCart() {
    await this.firstAddToCart.click();
  }

  async getFirstBookName(): Promise<string> {
    return await this.firstBookTitle.textContent() || '';
  }
}
