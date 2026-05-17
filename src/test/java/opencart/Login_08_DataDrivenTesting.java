package opencart;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminCustomerPagePO;
import pageObjects.openCart.admin.AdminDashboardPagePO;
import pageObjects.openCart.admin.AdminLoginPagePO;
import pageObjects.openCart.user.*;

import java.util.Random;


public class Login_08_DataDrivenTesting extends BaseTest {
    private String urlPath;
    private String userFirstName = "raph";
    private String userLastName = "user" + new Random().nextInt(9999);

        @Parameters({"browserName", "urlPath"})
        @BeforeClass
        public void beforeClass(String browserName, String urlPath){
            this.urlPath = urlPath;

            //Open user URL
            driver = getBrowserDriver(browserName, urlPath);

        }

        @Test(dataProvider = "LoginData" , dataProviderClass = utilities.DataProviders.class)
        public void OpenCart_01(String userEmail, String userPassword, String status) throws InterruptedException {
            //User -> Admin

            userHomepage = PageGenerator.getPage(UserHomepagePO.class, driver);
            userSiteID = userHomepage.getWindowID(driver);
            userHomepage.clickToMyAccountDropDown();

            userRegisterPage = userHomepage.openRegisterPage();
            userRegisterPage.enterToFirstName(userFirstName);
            userRegisterPage.enterToLastName(userLastName);
            userRegisterPage.enterToEmail(userEmail);
            userRegisterPage.enterToPassword(userPassword);
            userRegisterPage.clickToPolicyButton();
            userCreatedAccount = userRegisterPage.clickToContinueButton();
            userHomepage = userCreatedAccount.clickToLogOutButton();
            userHomepage.clickToMyAccountDropDown();
            userLoginPage = userHomepage.openLoginPage();

            //Log into user
            userLoginPage.enterToEmail(userEmail);
            userLoginPage.enterToPassword(userPassword);
            userAccountPage = userLoginPage.clickToLoginButton();
            userAccountPage.clickToLogOutButton();

        }

        public void Employee_02_Switch_Page(){

        }


        @AfterClass
        public void afterClass(){
            driver.quit();
        }

    private WebDriver driver;
    private UserLoginPagePO userLoginPage;
    private UserHomepagePO userHomepage;
    private UserRegisterPagePO userRegisterPage;
    private AdminLoginPagePO adminLoginPage;
    private AdminDashboardPagePO adminDashboardPage;
    private AdminCustomerPagePO adminCustomerPage;
    private UserAccountPagePO userAccountPage;
    private UserCreatedAccountPagePO userCreatedAccount;
    private UserLogOutPagePO userLogOutPage;
    private String adminSiteID, userSiteID;
}


