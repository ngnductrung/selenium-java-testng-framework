package orangehrm;

import core.BasePage;
import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;
import pageObjects.orangeHRM.AddEmployeePageObject;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import pageObjects.orangeHRM.editNavigation.ContactDetailPageObject;
import pageObjects.orangeHRM.editNavigation.DependentsPageObject;
import pageObjects.orangeHRM.editNavigation.JobPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;

public class Login_06_PageManager_04 extends BaseTest {
    BasePage basePage;
    WebDriver driver;
    String urlPath;
    String firstName, lastName;


        @Parameters({"browserName", "urlPath"})
        @BeforeClass
        public void beforeClass(String browserName, String urlPath){
            this.urlPath = urlPath;
            driver = getBrowserDriver(browserName, urlPath);

            firstName = "Raph";
            lastName = "Wreck-it";

            loginPage = PageGenerator.getPage(LoginPageObject.class, driver);
        }

        @Test
        public void Employee_01_CreateNewEmployee(){
            //Action of Login
            loginPage.enterToUsernameTextbox("raphadmin");
            loginPage.enterToPasswordTextbox("UIOjkl123!@#");


            //Action of Dashboard
            dashboardPage = loginPage.clickToLoginButton();
            Assert.assertTrue(dashboardPage.waitLoadingSpinnerInvisibile(driver));

            //Action of Employee List
            employeeListPage = dashboardPage.clickToPIMModule();
            Assert.assertTrue(dashboardPage.waitLoadingSpinnerInvisibile(driver));

            //Action of Add Employee
            addEmployeePage = employeeListPage.clickToAddEmployeeButton();
            Assert.assertTrue(employeeListPage.waitLoadingSpinnerInvisibile(driver));
            addEmployeePage.enterToFirstNameTextbox(firstName);
            addEmployeePage.enterTolastNameTextbox(lastName);
            employeeID = addEmployeePage.getEmployeeID();

            //Action of Personal Details
            personalDetailPage = addEmployeePage.clickToSaveButton();

            Assert.assertTrue(addEmployeePage.waitLoadingSpinnerInvisibile(driver));
            Assert.assertTrue(personalDetailPage.waitLoadingSpinnerInvisibile(driver));
            Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(), firstName);
            Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(), lastName) ;
            Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(), employeeID);

        }

        public void Employee_02_Switch_Page(){
            //At Contact Detail page, go to Personal Detail
            contactDetailPage = personalDetailPage.openContactDetailPage();

            //At Contact Detail page, go to Job
            jobPage = contactDetailPage.openJobPage();

            //At Job, go to Dependent
            dependentsPage = jobPage.openDependentsPage();

            //At Dependent, go to Personal
            personalDetailPage = dependentsPage.openPersonalDetailPage();

            //At Personal, go to Job
            jobPage = personalDetailPage.openJobPage();
        }


        @AfterClass
        public void afterClass(){
//            driver.quit();
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


