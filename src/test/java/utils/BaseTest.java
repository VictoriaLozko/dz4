package utils;

import utils.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
    private static WebDriver getDriver(String browser) {
        //String browser = Properties.getBrowser();
        switch (browser) {
            case "firefox":
                System.setProperty(
                        "webdriver.gecko.driver",
                        new File(BaseTest.class.getResource("/geckodriver").getFile()).getPath());
                return new FirefoxDriver();

            case "chrome":
            default:
                System.setProperty(
                        "webdriver.chrome.driver",
                        new File(BaseTest.class.getResource("/chromedriver").getFile()).getPath());
                return new ChromeDriver();
        }
    }

    public static EventFiringWebDriver getConfiguredDriver(String browser){
        WebDriver driver = getDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        EventFiringWebDriver wrappedDriver = new EventFiringWebDriver(driver);

        wrappedDriver.register(new EventHandler());

        return wrappedDriver;
    }

    public static void quitDriver(WebDriver driver){
        driver.quit();
    }
}