package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminLoginPagePO;
import pageUIs.openCart.BasePageUI;

public class UserHomepagePO extends BasePage {
    WebDriver driver;

    public UserHomepagePO(WebDriver driver){
        this.driver = driver;
    }

    public void clickToMyAccountDropDown() {
        waitElementClickable(driver, BasePageUI.MY_ACCOUNT_DROPDOWN);
        clickToCheckBox(driver, BasePageUI.MY_ACCOUNT_DROPDOWN);
    }

    public UserRegisterPagePO openRegisterPage(){
        waitElementClickable(driver, BasePageUI.MY_ACCOUNT_DROPDOWN_REGISTER_OPTION);
        clickToElement(driver, BasePageUI.MY_ACCOUNT_DROPDOWN_REGISTER_OPTION);
        return PageGenerator.getPage(UserRegisterPagePO.class, driver);
    }

    public UserLoginPagePO openLoginPage(){
        waitElementClickable(driver, BasePageUI.MY_ACCOUNT_DROPDOWN_LOGIN_OPTION);
        clickToElement(driver, BasePageUI.MY_ACCOUNT_DROPDOWN_LOGIN_OPTION);
        return PageGenerator.getPage(UserLoginPagePO.class, driver);
    }

    public AdminLoginPagePO openAdminSite(String urlPath) {
        openNewTab(driver, urlPath);
        return PageGenerator.getPage(AdminLoginPagePO.class, driver);

    }

    public void switchToAdminSite(String userUrlPath){
    }
}
