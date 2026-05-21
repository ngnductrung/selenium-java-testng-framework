package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.user.UserLogOutPageUI;

public class UserLogOutPagePO extends BasePage {
    WebDriver driver;

    public UserLogOutPagePO(WebDriver driver){
        this.driver = driver;
    }

    public UserHomepagePO clickToContinueButton(){
        waitElementClickable(driver, UserLogOutPageUI.CONTINUE_BUTTON);
        return PageGenerator.getPage(UserHomepagePO.class, driver);
    }
}
