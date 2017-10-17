package _tests;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;
import pages.*;
import utils.BaseTest;


public class MyTest {
    EventFiringWebDriver driver;

    @BeforeClass
    public void setup() {
        driver = BaseTest.getConfiguredDriver();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @DataProvider
    public Object[][] loginPassword() {
        return new Object[][]{
                {"webinar.test@gmail.com","Xcg7299bnSmMuRLp9ITw"},};
    }

    @Test (dataProvider = "loginPassword")
    public void login(String email, String password) {
        LoginPage loginPage = new LoginPage(driver, email, password);

        loginPage.open();
        loginPage.fillEmailInput();
        loginPage.fillPasswordInput();
        loginPage.clickLoginButton();
    }

   /* @Test(dependsOnMethods = "loginTest")
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
    */
   @Test (dependsOnMethods = "login")
   public void selectProduct(){
       DashboardPage dashboardPage = new DashboardPage(driver);
       dashboardPage.selectProduct();
   }

   @Test(dependsOnMethods = "selectProduct")
   public void addProduct(){
       ProductPage productPage = new ProductPage(driver);
       productPage.clickAddProductIcon();

       AddProductPage addProductPage = new AddProductPage(driver);
       Integer count = new Integer(10);
       Float price = new Float(100);
       addProductPage.addProduct("QWERTY",count,price);
   }

  @Test (dependsOnMethods = "selectProduct")
   public void logout(){
       DashboardPage dashboardPage = new DashboardPage(driver);
       dashboardPage.clickLogoutButtonWithJS();
   }


    @Parameters({"nameParam"})
    @Test
    public void test3(String name) {
        Reporter.log(String.format("Hello, %s!", name));

    }
}
