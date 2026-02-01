package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Email field
    @FindBy(id = "ctl00_phBody_SignIn_txtEmail")
    private WebElement emailField;

    // Password field  
    @FindBy(id = "ctl00_phBody_SignIn_txtPassword")
    private WebElement passwordField;

    // Login button
    @FindBy(id = "ctl00_phBody_SignIn_btnLogin")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).clear();
        emailField.sendKeys(email);
        pause(1000);

        wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
        passwordField.sendKeys(password);
        pause(1000);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        pause(2000);
    }

    // Login with your hardcoded credentials
    public void loginWithYourAccount() {
        login("tiwari.aalok24@gmail.com", "Aalok@123");
    }

    private void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
