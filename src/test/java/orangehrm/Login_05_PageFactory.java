package orangehrm;


import core.BasePageFactory;
import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageFactory.*;

public class Login_05_PageFactory extends BaseTest {
    BasePageFactory basePage;
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

            loginPage = new LoginPageObject(driver);
        }

        @Test
        public void Employee_01_CreateNewEmployee(){
            //Action of Login
            loginPage.enterToUsernameTextbox("Admin");
            loginPage.enterToPasswordTextbox("admin123");
            loginPage.clickToLoginButton();

            //Action of Dashboard
            dashboardPage = new DashboardPageObject(driver);

            Assert.assertTrue(dashboardPage.waitLoadingSpinnerInvisibile(driver));

            dashboardPage.clickToPIMModule();

            //Action of Employee List
            employeeListPage = new EmployeeListPageObject(driver);
            Assert.assertTrue(employeeListPage.waitLoadingSpinnerInvisibile(driver));
            employeeListPage.clickToAddEmployeeButton();

            Assert.assertTrue(employeeListPage.waitLoadingSpinnerInvisibile(driver));

            //Action of Add Employee
            addEmployeePage = new AddEmployeePageObject(driver);
            addEmployeePage.enterToFirstNameTextbox(firstName);
            addEmployeePage.enterTolastNameTextbox(lastName);
            employeeID = addEmployeePage.getEmployeeID();
            addEmployeePage.clickToSaveButton();
            Assert.assertTrue(addEmployeePage.waitLoadingSpinnerInvisibile(driver));


            //Action of Personal Details
            personalDetailPage = new PersonalDetailPageObject(driver);
            Assert.assertTrue(personalDetailPage.waitLoadingSpinnerInvisibile(driver));
            Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(), firstName);
            Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(), lastName) ;
            Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(), employeeID);

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
    }


