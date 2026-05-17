package orangehrm;

import com.google.common.base.Verify;
import core.BasePage;
import core.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
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

import java.util.Random;

@Slf4j
public class Login_06_DynamicLocator_01 extends BaseTest {
    BasePage basePage;
    WebDriver driver;
    String urlPath;
    String adminUsername, adminPassword, userFirstName, userLastName;


        @Parameters({"browserName", "urlPath"})
        @BeforeClass
        public void beforeClass(String browserName, String urlPath){
            this.urlPath = urlPath;
            driver = getBrowserDriver(browserName, urlPath);

            adminUsername = "raphadmin";
            adminPassword = "UIOjkl123!@#";

            userFirstName = "Raph";
            userLastName = "Wreck-it" + new Random().nextInt(9999);

            loginPage = PageGenerator.getPage(LoginPageObject.class, driver);
        }

        @Test
        public void TC_02_CreateNewEmployee(){

                logger.info("NewEmployee - STEP 01: Enter to Username and Password with info: " + adminUsername + "/" + adminPassword);
                //Action of Login
                loginPage.enterToUsernameTextbox(adminUsername);
                loginPage.enterToPasswordTextbox(adminPassword);

                logger.info("NewEmployee - STEP 02: Navigate to Dashboard page");
                //Action of Dashboard
                dashboardPage = loginPage.clickToLoginButton();
                Assert.assertTrue(dashboardPage.waitLoadingSpinnerInvisibile(driver));

                logger.info("NewEmployee - STEP 03: Navigate to Employee Search page");
                //Action of Employee List
                employeeListPage = dashboardPage.clickToPIMModule();
                Assert.assertTrue(dashboardPage.waitLoadingSpinnerInvisibile(driver));

                logger.info("NewEmployee - STEP 04: Navigate to Add Employee page");
                //Action of Add Employee
                addEmployeePage = employeeListPage.clickToAddEmployeeButton();
                Assert.assertTrue(employeeListPage.waitLoadingSpinnerInvisibile(driver));

                logger.info("NewEmployee - STEP 05: Enter FirstName and LastName with info: " + userFirstName + "|" + userLastName);
                addEmployeePage.enterToFirstNameTextbox(userFirstName);
                addEmployeePage.enterTolastNameTextbox(userLastName);
                employeeID = addEmployeePage.getEmployeeID();

                logger.info("NewEmployee - STEP 06: Navigate to Personal Details page");
                //Action of Personal Details
                personalDetailPage = addEmployeePage.clickToSaveButton();

                Assert.assertTrue(addEmployeePage.waitLoadingSpinnerInvisibile(driver));
                Assert.assertTrue(personalDetailPage.waitLoadingSpinnerInvisibile(driver));

                logger.info("NewEmployee - STEP 07: Verify Employee info displayed: " + userFirstName + "|" + userLastName + "|" + employeeID);

                verifyEquals(personalDetailPage.getFirstNameTextboxValue(), userLastName);
                verifyEquals(personalDetailPage.getLastNameTextboxValue(), userFirstName);
                verifyEquals(personalDetailPage.getEmployeeIDTextboxValue(), employeeID);

        }

        @Test
        public void TC_02_EmployeeSwitchPage() throws InterruptedException {
            //At Contact Detail page, go to Personal Detail
            contactDetailPage = PageGenerator.getPage(ContactDetailPageObject.class, driver);
            contactDetailPage.openEditNavigationPage("Personal Details");

            //At Personal Detail page, go to Job
            personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);
            personalDetailPage.openEditNavigationPage("Job");

            //At Job, go to Dependent
            dependentsPage = PageGenerator.getPage(DependentsPageObject.class, driver);
            dependentsPage.openEditNavigationPage("Dependents");

            //At Dependent, go to Personal
            personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);
            personalDetailPage.openEditNavigationPage("Personal Details");

            //At Personal, go to Job
            jobPage = PageGenerator.getPage(JobPageObject.class, driver);
            jobPage.openEditNavigationPage("Job");

            logger.info("--------------------TC_02_EmployeeSwitchPage is completed--------------------");
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


