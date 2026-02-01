import { Page, Locator } from '@playwright/test';

export class HomePage {
  readonly page: Page;
  private readonly myAccountMenu: Locator;
  private readonly loginOption: Locator;
  private readonly searchBox: Locator;
  private readonly searchButton: Locator;

  constructor(page: Page) {
    this.page = page;
    this.myAccountMenu = page.locator('#ctl00_lblUser');  // YOUR exact span!
    this.loginOption = page.locator('a:has-text("Login")');
    this.searchBox = page.locator('.inputbar');
    this.searchButton = page.locator('#btnTopSearch');
  }

  async openSite() {
    await this.page.goto('/');
  }

  async goToLoginFromMyAccount() {
    await this.myAccountMenu.click();  // Span is clickable!
    await this.loginOption.click();
  }

  async searchBook(bookName: string) {
    await this.searchBox.fill(bookName);
    await this.searchButton.click();
  }
}
