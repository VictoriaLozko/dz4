package _tests;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;
import pages.adminpanel.AddProductPage;
import pages.adminpanel.DashboardPage;
import pages.adminpanel.LoginPage;
import pages.adminpanel.ProductPage;
import pages.manesite.AllProductsPage;
import pages.manesite.MainPage;
import utils.BaseTest;
import utils.RandomString;

import java.util.Random;


public class MyTest {
    EventFiringWebDriver driver;
    private String product_name;
    private Integer product_count;
    private Float product_price;

    @Parameters({"browserName"})
    @BeforeClass
    public void setup(String browser) {
        driver = BaseTest.getConfiguredDriver(browser);
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
       Random random = new Random();
       ProductPage productPage = new ProductPage(driver);
       productPage.clickAddProductIcon();

       AddProductPage addProductPage = new AddProductPage(driver);
       int i = random.nextInt(100);
       if(i == 0){
           i = 1;
       }
       product_count = new Integer(i);
       product_price = new Float(99.99);
       product_name = RandomString.getRandomString();
       addProductPage.addProduct(product_name, product_count, product_price);
   }

  @Test (dependsOnMethods = "addProduct")
   public void logout(){
       DashboardPage dashboardPage = new DashboardPage(driver);
       dashboardPage.clickLogoutButtonWithJS();
   }

   @Parameters({"mainPageUrl"})
   @Test (dependsOnMethods = "logout")
    public void checkProduct(String url){
       MainPage mainPage = new MainPage(driver);
       mainPage.goToAllProducts(url);
       AllProductsPage allProductsPage = new AllProductsPage(driver);
       allProductsPage.isProductAdd(product_name,product_count,product_price);
   }

}
