import { Page, Locator } from '@playwright/test';

export class PaymentPage {
  readonly page: Page;
  readonly ccaVenueButton: Locator;

  constructor(page: Page) {
    this.page = page;
    this.ccaVenueButton = page.locator('#ctl00_cpBody_btnCCAvenue');
  }

  async payWithCCAvenue() {
    await this.ccaVenueButton.scrollIntoViewIfNeeded();
    await this.ccaVenueButton.click({ force: true });
    await this.page.waitForLoadState('networkidle');
  }
}
