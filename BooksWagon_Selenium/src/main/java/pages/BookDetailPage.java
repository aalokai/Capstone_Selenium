package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookDetailPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Your Buy Now button
    @FindBy(xpath = "//a[@class='btn border themebackground text-white d-block mb-2' and @onclick='BuyNowProduct();']")
    private WebElement buyNowButton;

    public BookDetailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void clickBuyNow() {
        // **1. SCROLL Buy Now into center of viewport**
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", 
            buyNowButton);
        pause(1500);

        // **2. Close chat bot if present (common selectors)**
        closeChatBot();

        // **3. Scroll again + wait**
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block: 'center'});", buyNowButton);
        wait.until(ExpectedConditions.elementToBeClickable(buyNowButton));
        pause(500);

        // **4. JavaScript click (bypasses chat bot overlay completely)**
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", buyNowButton);
        pause(3000);
    }

    private void closeChatBot() {
        String[] chatSelectors = {
            "//button[contains(@class, 'close')]",
            "//div[contains(@class, 'chat')]//button",
            "//iframe[contains(@src, 'chat')]",
            "//div[contains(@class, 'chatbot')]//i",
            "//button[contains(@aria-label, 'close')]"
        };
        
        for (String selector : chatSelectors) {
            try {
                WebElement chatClose = driver.findElement(org.openqa.selenium.By.xpath(selector));
                chatClose.click();
                pause(500);
                break;
            } catch (Exception ignored) {
                // Continue if no chat bot found
            }
        }
    }

    private void pause(long millis) {
        try { Thread.sleep(millis); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
