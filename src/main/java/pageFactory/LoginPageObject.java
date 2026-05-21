package pageFactory;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject extends BasePageFactory {
    private WebDriver driver;

    @FindBy(xpath = "//input[@name='username']")
    private WebElement USER_NAME_TEXTBOX;

    @FindBy(xpath = "//input[@name='                                                password']")
    private WebElement PASSWORD_TEXTBOX;

    @FindBy(xpath = "//button[contains(@class,orangehrm-login-button)]")
    private WebElement LOGIN_BUTTON;


    public LoginPageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterToUsernameTextbox(String username) {
        waitElementVisible(driver, USER_NAME_TEXTBOX);
        sendKeyToElement(driver, USER_NAME_TEXTBOX,username);
    }

    public void enterToPasswordTextbox(String password) {
        waitElementVisible(driver, PASSWORD_TEXTBOX);
        sendKeyToElement(driver, PASSWORD_TEXTBOX,password);
    }

    public void clickToLoginButton() {
        waitElementClickable(driver, LOGIN_BUTTON);
        clickToElement(driver, LOGIN_BUTTON);
    }
}
