package pages.manesite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ProductDescriptionPage {
    private EventFiringWebDriver driver;

    private By loginButtonLocator = By.xpath("//div[@class='user-info']/a");
    private By nameLocator = By.xpath("//div[@class = 'col-md-6']/h1");
    private By priceLocator = By.xpath("//div[@class = 'current-price']/span");
    private By countLocator = By.xpath("//div[@class = 'product-quantities']/span");




    public ProductDescriptionPage(EventFiringWebDriver driver){
        this.driver = driver;
    }

    public boolean isNameAddRight(String name){
        boolean isRight = false;

        waitForElement(loginButtonLocator);
        waitForElement(nameLocator);
        WebElement nameElement = driver.findElement(nameLocator);

        if(nameElement.getText().toString().equals(name)){
            isRight = true;
        }

        return isRight;
    }

    public boolean isPriceAddRight(Float price){
        boolean isRight = false;

        waitForElement(priceLocator);
        WebElement priceElement = driver.findElement(priceLocator);
        String str_price = priceElement.getAttribute("content");

        if(str_price.equals(price.toString())){
            isRight = true;
        }

        return isRight;
    }

    public boolean isCountAddRight(Integer count){
        boolean isRight = false;
        waitForElement(countLocator);
        WebElement countElement = driver.findElement(countLocator);
        String cnt = countElement.getText().toString();
        int i = cnt.indexOf(" ");
        String str_count = cnt.substring(0,i);

        if(str_count.equals(count.toString())){
            isRight = true;
        }

        return isRight;
    }

    private void waitForElement(By locator){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
