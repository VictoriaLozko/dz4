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
    private By productsPagesLocator = By.xpath("//ul[@class='page-list clearfix text-xs-center']/li");
    private String productsPagesLocatorString = "//ul[@class='page-list clearfix text-xs-center']/li";
    private int product_index;

    public AllProductsPage(EventFiringWebDriver driver){
        this.driver = driver;
    }

    public boolean isProductAdd(String name){
        boolean isAdd = false;
        int index= isProductAddByName(name);
        if( index != 0){
            System.out.println("ТОВАР ДОБАВЛЕН!");
            isAdd = true;
            product_index = index;
        }
        return isAdd;
    }

    private void waitForElement(By locator){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private int isProductAddByName(String name){
        int isAdd = 0;
        waitForElement(loginButtonLocator);
        int index = findProductByName(name);
        if (index != 0){
            return index;
        }
        waitForElement(productsPagesLocator);
        int cnt = driver.findElements(productsPagesLocator).size();
        for (int i=3; i <= (cnt -1); i++){
            WebElement nextPage = driver.findElement(By.xpath(productsPagesLocatorString + "[" + i + "]"));
            nextPage.click();

            try{
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            waitForElement(loginButtonLocator);
            index = findProductByName(name);
            if (index != 0){
                return index;
            }
        }

        return isAdd;
    }

    private int findProductByName(String name){
        int index = 0;
        int count = driver.findElements(productListLocator).size();
        WebElement item;
        for (int i = 1; i <= count; i++){
            item = driver.findElement(By.xpath("//div[@class='products row']/article[" + i + "]//h1/a"));
            if (item.getText().toString().equals(name)){
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean goToProductDescription(){
        if(product_index < 1){
            System.out.println("Недопустимый индекс");
            return false;
        }
        WebElement product = driver.findElement(By.xpath("//div[@class='products row']/article[" + product_index + "]//h1/a"));
        product.click();

        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return true;
    }

}
