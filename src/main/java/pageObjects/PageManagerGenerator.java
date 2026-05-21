package pageObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.orangeHRM.AddEmployeePageObject;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject;

public class PageManagerGenerator {
    public static LoginPageObject getLoginPageInstance(WebDriver driver){
        return new LoginPageObject(driver);
    }


    public static DashboardPageObject getDashboardPageInstance(WebDriver driver){
        return new DashboardPageObject(driver);
    }

    public static EmployeeListPageObject getEmployeeListPageInstance(WebDriver driver){
        return new EmployeeListPageObject(driver);
    }

    public static AddEmployeePageObject getAddEmployeePageInstance(WebDriver driver){
        return new AddEmployeePageObject(driver);
    }

    public static PersonalDetailPageObject getPersonalDetailPageInstance(WebDriver driver){
        return new PersonalDetailPageObject(driver);
    }


}
