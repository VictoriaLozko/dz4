package pages.adminpanel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SharedMethods;

import java.util.concurrent.TimeUnit;

public class AddProductPage {


    private void fillAddProductName(String name){
        WebElement inputNameElement = driver.findElement(inputNameLocator);
        inputNameElement.sendKeys(name);
    }private EventFiringWebDriver driver;
    private By saveLocator = By.xpath("//input[@class='btn btn-primary save uppercase']");
    private By inputNameLocator = By.xpath("//input [@id = 'form_step1_name_1']");
    private By inputCountLocator = By.id("form_step1_qty_0_shortcut");
    private By inputPriceLocator = By.id("form_step1_price_shortcut");
    private By switchLocator = By.cssSelector("div.switch-input");
    private By messageLocator = By.xpath("//div[@class='growl-message']");
    private By closeMessageLocator = By.xpath("//div[@class='growl-close']");

    public AddProductPage(EventFiringWebDriver driver){
        this.driver = driver;
    }

    public void addProduct(String name, Integer count, Float price){
        waitForElement(saveLocator);

        fillAddProductName(name);
        fillAddProductCount(count);
        fillAddProductPrice(price);
        waitForElement(switchLocator);

        switchProduct();

        if (isMessageShown()){
            closeMessage();
        }

        saveProduct();

        if (isMessageShown()){
            closeMessage();
        }

    }

    private void fillAddProductCount(Integer count){
        WebElement inputCountElement = driver.findElement(inputCountLocator);
        Actions actions = new Actions(driver);
        actions.sendKeys(inputCountElement, Keys.BACK_SPACE).build().perform();
        inputCountElement.sendKeys(count.toString());
    }

    private void fillAddProductPrice(Float price){
        WebElement inputPriceElement = driver.findElement(inputPriceLocator);
        Actions actions = new Actions(driver);

        for (int i= 0; i<8; i++){
            actions.sendKeys(inputPriceElement, Keys.BACK_SPACE).build().perform();

        }

        inputPriceElement.sendKeys(price.toString());
    }

    private void waitForElement(By locator){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void switchProduct(){
        WebElement switchElement = driver.findElement(switchLocator);
        switchElement.click();
    }
    private void saveProduct(){
        WebElement saveElement = driver.findElement(saveLocator);
        saveElement.click();
    }

    private boolean isMessageShown(){
        waitForElement(messageLocator);
        return SharedMethods.isElementPresent(driver,messageLocator);
    }

    private void closeMessage(){
        WebElement closeMessageElement = driver.findElement(closeMessageLocator);
        closeMessageElement.click();
    }
}
