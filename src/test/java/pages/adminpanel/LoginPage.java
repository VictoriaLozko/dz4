package pages.adminpanel;

import utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class LoginPage {
    private EventFiringWebDriver driver;
    private By emailInput = By.id("email");
    private By passwordInput = By.id("passwd");
    private By loginBtn = By.name("submitLogin");
    private String email;
    private String password;

    public LoginPage(EventFiringWebDriver driver, String email, String password){
        this.driver = driver;
        this.email = email;
        this.password=password;
    }

    public void open(){
        driver.get(Properties.getBaseAdminUrl());
    }

    public void fillEmailInput(){
        driver.findElement(emailInput).sendKeys(email);
    }

    public void fillPasswordInput(){
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton(){
        driver.findElement(loginBtn).click();
    }
}
