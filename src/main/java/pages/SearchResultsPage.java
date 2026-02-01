package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // **YOUR EXACT FIRST BOOK LINK:**
//    @FindBy(xpath = "//a[@href='https://www.bookswagon.com/book/selenium-java-a-beginners-guide/9789391392680']")
//    private WebElement firstBookTitle;
//    
 // Generic first book (for dynamic results):
    @FindBy(xpath = "(//a[contains(@href, '/book/')])[1]")
    private WebElement firstBookTitle;

    

    // **YOUR EXACT BUY NOW BUTTON:**
    @FindBy(xpath = "//a[contains(@class, 'btn border themebackground text-white d-block mb-2') and contains(@onclick, 'BuyNowProduct')]")
    private WebElement buyNowButton;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void clickFirstBook() {
        wait.until(ExpectedConditions.elementToBeClickable(firstBookTitle)).click();
        pause(2000);
    }

    public void clickBuyNowOnFirstBook() {
        wait.until(ExpectedConditions.elementToBeClickable(buyNowButton)).click();
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
