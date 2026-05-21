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
import pageObjects.openCart.user.UserRegisterPagePO;

import java.util.Random;


public class Login_06_SwitchUrl extends BaseTest {
    private String userUrlPath;
    private String adminUrlPath;
    private String userFirstName = "raph";
    private String userLastName = "user" + new Random().nextInt(9999);
    private String adminUserName = "admin";
    private String adminPassword = "UIOjkl123!@#";



    @Parameters({"browserName", "userUrlPath" , "adminUrlPath"})
        @BeforeClass
        public void beforeClass(String browserName, String userUrlPath, String adminUrlPath){
            this.userUrlPath = userUrlPath;
            this.adminUrlPath = adminUrlPath;

            //Open user URL
            driver = getBrowserDriver(browserName, userUrlPath);

        }

        @Test(dataProvider = "LoginData" , dataProviderClass = utilities.DataProviders.class)
        public void OpenCart_01(String userEmail, String userPassword){
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
            userAccountPage = userCreatedAccount.openAccountPage();
            userLogOutPage = userAccountPage.clickToLogOutButton();
            userHomepage = userLogOutPage.clickToContinueButton();

            adminLoginPage = userHomepage.openAdminSite(adminUrlPath);
            adminSiteID = adminLoginPage.getWindowID(driver);

            //Thao tac ben Admin
            adminLoginPage.enterToUserName(adminUserName);
            adminLoginPage.enterToPassword(adminPassword);

            adminDashboardPage = adminLoginPage.clickLoginButton();

            //Log out from Admin
            adminLoginPage = adminDashboardPage.clickLogOutButton();

            //Admin -> User
            userHomepage = adminLoginPage.switchDriverToUserSite(adminSiteID);
            userHomepage.clickToMyAccountDropDown();
            userLoginPage = userHomepage.openLoginPage();

            //Log into user
            userLoginPage.enterToEmail(userEmail);
            userLoginPage.enterToPassword(userPassword);
            userAccountPage = userLoginPage.clickToLoginButton();

        }

        public void Employee_02_Switch_Page(){

        }


        @AfterClass
        public void afterClass(){
//            driver.quit();
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


