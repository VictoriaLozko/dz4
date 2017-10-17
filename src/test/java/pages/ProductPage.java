package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SharedMethods;

public class ProductPage {
    private EventFiringWebDriver driver;
    private By addLocator = By.id("page-header-desc-configuration-add");
    private By messageLocator = By.xpath("//div[@class='alert alert-success']");
    private By goBackToListLocator = By.id("desc-category-back");
    private By sortUpLocator = By.className("icon-caret-up");

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

    public boolean isMessageShown(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(addLocator));

        return SharedMethods.isElementPresent(driver,messageLocator);
    }

    public boolean isCategoryAdd(String name){
        clickGoBackToList();
        sortByName();
        return isCategoryAddByName(name);
    }

    private void clickGoBackToList(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(goBackToListLocator));

        WebElement goBackToListElement = driver.findElement(goBackToListLocator);
        goBackToListElement.click();

        return;
    }
    private void sortByName(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(addLocator));

        driver.findElements(sortUpLocator).get(1).click();

    }

    private boolean isCategoryAddByName(String name){
        boolean isAdd = false;
        int count = driver.findElements(By.xpath("//tbody/tr")).size();
        WebElement item;
        for (int i = 1; i <= count; i++){
            item = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[@class='pointer'][1]"));
            if (item.getText().toString().equals(name)){
                isAdd = true;
            }
        }
        return isAdd;
    }
}
