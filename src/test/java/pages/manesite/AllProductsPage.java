package pages.manesite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AllProductsPage {
    private EventFiringWebDriver driver;
    private By loginButtonLocator = By.xpath("//div[@class='user-info']/a");
    private By productListLocator = By.xpath("//div[@class='products row']/article");

    public AllProductsPage(EventFiringWebDriver driver){
        this.driver = driver;
    }

    public boolean isProductAdd(String name, Integer count, Float price){
        boolean isAdd = false;
        waitForElement(loginButtonLocator);
        isProductAddByName(name);
        return isAdd;
    }

    private void waitForElement(By locator){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private boolean isProductAddByName(String name){
        boolean isAdd = false;
        int count = driver.findElements(productListLocator).size();
        WebElement item;
        for (int i = 1; i <= count; i++){
            item = driver.findElement(By.xpath("//div[@class='products row']/article[" + i + "]//h1/a"));
            if (item.getText().toString().equals(name)){
                isAdd = true;
            }
        }
        return isAdd;
    }

}
