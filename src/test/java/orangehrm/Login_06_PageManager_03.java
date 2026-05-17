package orangehrm;

import core.BaseTest;

public class Login_06_PageManager_03 extends BaseTest {
    /*
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

            loginPage = new LoginPageObject(driver);
        }

        @Test
        public void Employee_01_CreateNewEmployee(){
            //Action of Login
            loginPage.enterToUsernameTextbox("Admin");
            loginPage.enterToPasswordTextbox("admin123");


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
    */
    }


