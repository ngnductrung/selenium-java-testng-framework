package orangehrm;

import core.BasePage;
import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.AddEmployeePageObject;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import pageObjects.orangeHRM.editNavigation.ContactDetailPageObject;
import pageObjects.orangeHRM.editNavigation.DependentsPageObject;
import pageObjects.orangeHRM.editNavigation.JobPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;

import java.io.IOException;
import java.util.Random;

public class Login_03_Multiple_Browser extends BaseTest {
    BasePage basePage;
    WebDriver driver;
    String urlPath;
    String adminUsername, adminPassword, userFirstName, userLastName;

    @Parameters({"operatingSystem", "browserName", "urlPath"})
    @BeforeClass
    public void beforeClass(String operatingSystem, String browserName, String urlPath) throws IOException {
        this.urlPath = urlPath;
        driver = getBrowserDriver(operatingSystem, browserName, urlPath);

        adminUsername = "raphadmin";
        adminPassword = "UIOjkl123!@#";

        userFirstName = "Raph";
        userLastName = "Wreck-it" + new Random().nextInt(9999);

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);
    }

        @Test
        public void Empty(){
            basePage.waitElementVisible(driver, "//input[@name='username']");
            basePage.getWebElement(driver, "//input[@name='username']");
            basePage.getWebElement(driver, "//input[@name='password']");
            basePage.clickToElement(driver, "//button[contains(@class,'orangehrm-login-button')]");

            Assert.assertEquals(basePage.getElementText(driver, "//input[@name='username']/parent::div/following-sibling::span"), "Required");
            Assert.assertEquals(basePage.getElementText(driver, "//input[@name='password']/parent::div/following-sibling::span"), "Required");

        }

        @AfterClass
        public void afterClass(){
            driver.quit();
        }

    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailPageObject personalDetailPage;
    private String employeeID;
    private ContactDetailPageObject contactDetailPage;
    private JobPageObject jobPage;
    private DependentsPageObject dependentsPage;
    }


