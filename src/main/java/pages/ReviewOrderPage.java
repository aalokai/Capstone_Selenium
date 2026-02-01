package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReviewOrderPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "ctl00_cpBody_ShoppingCart_lvCart_savecontinue")
    private WebElement saveAndContinueButton;

    public ReviewOrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void clickSaveAndContinue() {
        // Scroll into view
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block: 'center'});", 
            saveAndContinueButton);
        
        wait.until(driver -> saveAndContinueButton.isDisplayed() && saveAndContinueButton.isEnabled());
        
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveAndContinueButton);
        
        // **PAUSE: 3 seconds to see review order page**
        pause(3000);
    }

    private void pause(long millis) {
        try { Thread.sleep(millis); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
