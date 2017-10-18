package _tests;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.adminpanel.AddProductPage;
import pages.adminpanel.DashboardPage;
import pages.adminpanel.LoginPage;
import pages.adminpanel.ProductPage;
import pages.manesite.AllProductsPage;
import pages.manesite.MainPage;
import pages.manesite.ProductDescriptionPage;
import utils.BaseTest;
import utils.RandomString;

import java.util.Random;


public class MyTest {
    EventFiringWebDriver driver;
    private String product_name;
    private Integer product_count;
    private Float product_price;
    private boolean isProductDescriptionPageOpen;

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
    public void checkProductIsAdd(String url){
       MainPage mainPage = new MainPage(driver);
       mainPage.goToAllProducts(url);
       AllProductsPage allProductsPage = new AllProductsPage(driver);
       boolean isProductAdd = allProductsPage.isProductAdd(product_name);
       Assert.assertTrue(isProductAdd,"Продукт не добавлен!");
       isProductDescriptionPageOpen = allProductsPage.goToProductDescription();
   }

   @Test (dependsOnMethods = "checkProductIsAdd")
   public void checkProductProperties(){
       if(!isProductDescriptionPageOpen){
           System.out.println("Не удалось открыть страницу с описанием товара!");
           return;
       }
       ProductDescriptionPage productDescriptionPage = new ProductDescriptionPage(driver);
       boolean isProductNameAddRight = productDescriptionPage.isNameAddRight(product_name);
       boolean isProductPriceAddRight = productDescriptionPage.isPriceAddRight(product_price);
       boolean isProductCountAddRight = productDescriptionPage.isCountAddRight(product_count);
       SoftAssert softAssert = new SoftAssert();
       softAssert.assertTrue(isProductNameAddRight, "Имя товара добавлено не верно");
       softAssert.assertTrue(isProductPriceAddRight, "Цена товара добавлена не верно");
       softAssert.assertTrue(isProductCountAddRight, "Количество товара добавлено не верно");

       softAssert.assertAll();
   }

}
