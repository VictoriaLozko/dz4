package myprojects.automation.webinar.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.List;

public class SomeTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty(
                "webdriver.chrome.driver",
                new File(SomeTest.class.getResource("/chromedriver.exe").getFile()).getPath());
        driver = new ChromeDriver();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void loginTest() {
        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        driver.findElement(By.id("email")).sendKeys("webinar.test@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("Xcg7299bnSmMuRLp9ITw");
        driver.findElement(By.name("submitLogin")).click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.titleContains("Dashboard"));
    }

    @Test(dependsOnMethods = "loginTest")
    public void checkStatsFilterButtons() {
        Reporter.log("Open Statistics section <br />");
        driver.findElement(By.cssSelector("#subtab-AdminStats a")).click();

        Reporter.log("Check filter buttons amount <br />");
        List<WebElement> filterButtons = driver.findElements(By.cssSelector("#calendar_form .btn-group button"));
        Assert.assertEquals(
                filterButtons.size(), 6,
                "Wrong filter buttons amount"
        );

        Reporter.log("Check filter buttons visibility <br />");
        SoftAssert assertVisibility = new SoftAssert();
        for (WebElement btn: filterButtons) {
            assertVisibility.assertTrue(
                    btn.isDisplayed(),
                    String.format("Filter button %s is not visible.", btn.getAttribute("name"))
            );
        }
        assertVisibility.assertAll();
    }

    @Parameters({"nameParam"})
    @Test
    public void test3(String name) {
        Reporter.log(String.format("Hello, %s!", name));

    }
}
