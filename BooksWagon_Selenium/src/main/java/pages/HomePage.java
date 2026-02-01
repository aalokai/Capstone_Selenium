package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(linkText = "My Account")
    private WebElement myAccountMenu;

    @FindBy(linkText = "Login")
    private WebElement loginOption;

    // **YOUR LOCATORS:**
    @FindBy(className = "inputbar")
    private WebElement searchBox;

    @FindBy(id = "btnTopSearch")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void openSite() {
        driver.get("https://www.bookswagon.com");
        pause(1000);
    }

    public void goToLoginFromMyAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccountMenu)).click();
        pause(2000);
        wait.until(ExpectedConditions.elementToBeClickable(loginOption)).click();
        pause(2000);
    }

    public void searchBook(String bookName) {
        wait.until(ExpectedConditions.visibilityOf(searchBox)).clear();
        searchBox.sendKeys(bookName);
        pause(1000);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        pause(2000);
    }

    private void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
