package pageObjects.orangeHRM;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageManagerGenerator;
import pageUIs.orangeHRM.DashboardPageUI;

public class DashboardPageObject extends BasePage {
    private WebDriver driver;

    public DashboardPageObject(WebDriver driver){
        this.driver = driver;
    }

    public EmployeeListPageObject clickToPIMModule() {
        waitElementClickable(driver,DashboardPageUI.PIMModule);
        clickToElement(driver, DashboardPageUI.PIMModule);
        return PageManagerGenerator.getEmployeeListPageInstance(driver);
    }
}
