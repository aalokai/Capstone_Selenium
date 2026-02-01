package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // **YOUR EXACT CCAVENUE BUTTON:**
    @FindBy(id = "ctl00_cpBody_btnCCAvenue")
    private WebElement ccAvenueButton;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void selectCCAvenuePayment() {
        // Scroll into view
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block: 'center'});", 
            ccAvenueButton);
        
        // Wait for clickable + click
        wait.until(driver -> ccAvenueButton.isDisplayed() && ccAvenueButton.isEnabled());
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ccAvenueButton);
        
        // **PAUSE: 3 seconds to see payment gateway redirect**
        pause(3000);
    }

    private void pause(long millis) {
        try { Thread.sleep(millis); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
