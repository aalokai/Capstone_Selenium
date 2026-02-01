import { Page, Locator } from '@playwright/test';

export class ReviewOrderPage {
  readonly page: Page;
  readonly saveContinueButton: Locator;

  constructor(page: Page) {
    this.page = page;
    this.saveContinueButton = page.locator('#ctl00_cpBody_ShoppingCart_lvCart_savecontinue');
  }

  async saveAndContinue() {
    await this.saveContinueButton.scrollIntoViewIfNeeded();
    await this.saveContinueButton.click({ force: true });
    await this.page.waitForLoadState('networkidle');
  }
}
