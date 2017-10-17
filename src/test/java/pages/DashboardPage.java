package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DashboardPage {
    private EventFiringWebDriver driver;
    private By logoutImage = By.cssSelector("span.employee_avatar_small");
    private By logoutButton = By.id("header_logout");
    private By catalogTab = By.id("subtab-AdminCatalog");
    private By productLocator = By.id("subtab-AdminProducts");

    public DashboardPage(EventFiringWebDriver driver){
        this.driver = driver;
    }

    public void clickLogoutButtonWithJS(){
        WebElement element = driver.findElement(logoutButton);

        //WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.elementToBeClickable(element));

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
    }


    public void selectProduct(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(logoutImage));

        WebElement categoryTabElement = driver.findElement(catalogTab);
        Actions actions = new Actions(driver);
        actions.moveToElement(categoryTabElement).build().perform();

        WebElement productElement = driver.findElement(productLocator);
        wait.until(ExpectedConditions.elementToBeClickable(productLocator));
        productElement.click();
    }

}
