package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.openCart.user.UserRegisterPageUI;

public class UserRegisterPagePO extends BasePage {
    WebDriver driver;

    public UserRegisterPagePO(WebDriver driver){
        this.driver = driver;
    }

    public void enterToFirstName(String keyToSend){
        waitElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, keyToSend);
    }

    public void enterToLastName(String keyToSend){
        waitElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, keyToSend);
    }

    public void enterToEmail(String keyToSend){
        waitElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, keyToSend);
    }

    public void enterToPassword(String keyToSend){
        waitElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, keyToSend);
    }

    public void clickToPolicyButton(){
        waitElementClickable(driver, UserRegisterPageUI.AGREE_POLICY_BUTTON);
        clickToElement(driver, UserRegisterPageUI.AGREE_POLICY_BUTTON);
    }

    public UserCreatedAccountPagePO clickToContinueButton(){
        waitElementClickable(driver, UserRegisterPageUI.CONTINUE_BUTTON);
        clickToElement(driver, UserRegisterPageUI.CONTINUE_BUTTON);
        return PageGenerator.getPage(UserCreatedAccountPagePO.class, driver);
    }

}
