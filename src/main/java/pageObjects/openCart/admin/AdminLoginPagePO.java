package pageObjects.openCart.admin;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageObjects.openCart.user.UserHomepagePO;
import pageUIs.openCart.admin.AdminCustomerPageUI;
import pageUIs.openCart.admin.AdminLoginPageUI;

public class AdminLoginPagePO extends BasePage {
    WebDriver driver;

    public AdminLoginPagePO(WebDriver driver){
        this.driver = driver;
    }

    public void enterToUserName(String keyToSend) {
        waitElementVisible(driver, AdminLoginPageUI.USERNAME_TEXTBOX);
        sendKeyToElement(driver, AdminLoginPageUI.USERNAME_TEXTBOX, keyToSend);
    }

    public void enterToPassword(String keyToSend) {
        waitElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, keyToSend);
    }

    public AdminDashboardPagePO clickLoginButton() {
        waitElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getPage(AdminDashboardPagePO.class, driver);
    }

    public UserHomepagePO openUserSite(String userUrlPath) {
        getPageUrl(driver, userUrlPath);
        return PageGenerator.getPage(UserHomepagePO.class,driver);
    }

    public UserHomepagePO switchDriverToUserSite(String adminSiteID) {
        switchToWindowByID(driver, adminSiteID);
        return PageGenerator.getPage(UserHomepagePO.class, driver);
    }
}
