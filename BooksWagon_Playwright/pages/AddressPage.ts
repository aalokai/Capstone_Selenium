import { Page, Locator } from '@playwright/test';

export class AddressPage {
  readonly page: Page;
  readonly useExistingAddressButton: Locator;

  constructor(page: Page) {
    this.page = page;
    this.useExistingAddressButton = page.locator('#ctl00_cpBody_lvCustomerAdd_ctrl0_btnUseAddress');
  }

  async selectExistingAddress() {
    // Scroll + direct click (no nested locator)
    await this.useExistingAddressButton.scrollIntoViewIfNeeded();
    await this.useExistingAddressButton.click({ force: true });
    await this.page.waitForTimeout(3000);
  }
}
