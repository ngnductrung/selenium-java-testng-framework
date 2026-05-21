package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.user.UserLoginPageUI;

public class UserLoginPagePO extends BasePage {
    WebDriver driver;

    public UserLoginPagePO(WebDriver driver){
        this.driver = driver;
    }

    public void enterToEmail(String keyToSend){
        waitElementClickable(driver, UserLoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, keyToSend);
    }

    public void enterToPassword(String keyToSend){
        waitElementClickable(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, keyToSend);
    }

    public UserAccountPagePO clickToLoginButton(){
        waitElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getPage(UserAccountPagePO.class, driver);
    }

    public UserRegisterPagePO openRegisterPage(){
        waitElementClickable(driver, UserLoginPageUI.CONTINUE_BUTTON);
        clickToElement(driver, UserLoginPageUI.CONTINUE_BUTTON);
        return PageGenerator.getPage(UserRegisterPagePO.class, driver);
    }
}
