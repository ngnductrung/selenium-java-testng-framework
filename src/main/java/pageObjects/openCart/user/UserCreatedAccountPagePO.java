package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.BasePageUI;
import pageUIs.openCart.user.UserCreatedAccountPageUI;

public class UserCreatedAccountPagePO extends BasePage {
    WebDriver driver;

    public UserCreatedAccountPagePO(WebDriver driver){
        this.driver = driver;
    }

    public UserAccountPagePO openAccountPage(){
        waitElementClickable(driver, UserCreatedAccountPageUI.CONTINUE_BUTTON);
        clickToElement(driver, UserCreatedAccountPageUI.CONTINUE_BUTTON);
        return PageGenerator.getPage(UserAccountPagePO.class, driver);
    }

    public UserHomepagePO clickToLogOutButton(){
        waitElementClickable(driver, BasePageUI.LOG_OUT_TEXTLINK);
        clickToElement(driver, BasePageUI.LOG_OUT_TEXTLINK);
        clickToElement(driver, BasePageUI.LOGO_ICON);
        return PageGenerator.getPage(UserHomepagePO.class, driver);
    }
}
