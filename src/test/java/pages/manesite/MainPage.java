package pages.manesite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MainPage {
    private EventFiringWebDriver driver;
    private By allProductsLocator = By.xpath("//section[@class = 'featured-products clearfix']/a");

    public MainPage(EventFiringWebDriver driver){
        this.driver = driver;
    }

    public void goToAllProducts(String url){
        goToMainSitePage(url);

        waitForElement(allProductsLocator);
        //WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.elementToBeClickable(allProductsLocator));

        WebElement allProductsElement = driver.findElement(allProductsLocator);
        allProductsElement.click();
    }
    private void goToMainSitePage(String url){
        driver.get(url);
    }

    private void waitForElement(By locator){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
