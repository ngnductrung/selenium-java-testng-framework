package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.BasePageUI;

public class UserAccountPagePO extends BasePage {
    WebDriver driver;

    public UserAccountPagePO(WebDriver driver){
        this.driver = driver;
    }

    public UserLogOutPagePO clickToLogOutButton(){
        waitElementClickable(driver, BasePageUI.LOG_OUT_TEXTLINK);
        clickToElement(driver, BasePageUI.LOG_OUT_TEXTLINK);
        return PageGenerator.getPage(UserLogOutPagePO.class, driver);
    }
}
