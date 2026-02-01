package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddressPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "ctl00_cpBody_lvCustomerAdd_ctrl0_btnUseAddress")
    private WebElement deliverToThisAddressButton;

    public AddressPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void selectSavedAddress() {
        // Scroll into view
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block: 'center'});", 
            deliverToThisAddressButton);
        
        wait.until(driver -> deliverToThisAddressButton.isDisplayed() && deliverToThisAddressButton.isEnabled());
        
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deliverToThisAddressButton);
        
        // **PAUSE: 3 seconds to see address selection**
        pause(3000);
    }

    private void pause(long millis) {
        try { Thread.sleep(millis); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
