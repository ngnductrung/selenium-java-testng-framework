package pageObjects.openCart.admin;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.admin.AdminDashboardPageUI;

public class AdminDashboardPagePO extends BasePage {
    WebDriver driver;

    public AdminDashboardPagePO(WebDriver driver){
        this.driver = driver;
    }

    public AdminLoginPagePO clickLogOutButton() {
        waitElementClickable(driver, AdminDashboardPageUI.LOG_OUT_BUTTON);
        clickToElement(driver, AdminDashboardPageUI.LOG_OUT_BUTTON);
        return PageGenerator.getPage(AdminLoginPagePO.class, driver);
    }
}
