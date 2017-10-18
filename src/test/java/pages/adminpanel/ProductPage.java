package pages.adminpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SharedMethods;

public class ProductPage {
    private EventFiringWebDriver driver;
    private By addLocator = By.id("page-header-desc-configuration-add");

    public ProductPage (EventFiringWebDriver driver){
        this.driver = driver;
    }

    public void clickAddProductIcon(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(addLocator));

        WebElement addIconElement = driver.findElement(addLocator);
        addIconElement.click();
        return;
    }

}
