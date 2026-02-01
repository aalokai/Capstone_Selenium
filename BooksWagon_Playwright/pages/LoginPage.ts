import { Page, Locator } from '@playwright/test';

export class LoginPage {
  readonly page: Page;
  readonly emailField: Locator;
  readonly passwordField: Locator;
  readonly loginButton: Locator;

  constructor(page: Page) {
    this.page = page;
    this.emailField = page.locator('#ctl00_phBody_SignIn_txtEmail');
    this.passwordField = page.locator('#ctl00_phBody_SignIn_txtPassword');
    this.loginButton = page.locator('#ctl00_phBody_SignIn_btnLogin');
  }

  async login(email: string, password: string) {
    await this.emailField.fill(email);
    await this.passwordField.fill(password);
    await this.loginButton.click();
  }

  // Your hardcoded account from Java
  async loginWithYourAccount() {
    await this.login('tiwari.aalok24@gmail.com', 'Aalok@123');
  }
}
