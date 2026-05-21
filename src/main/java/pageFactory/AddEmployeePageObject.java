package pageFactory;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePageObject extends BasePageFactory {
    private WebDriver driver;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement FIRST_NAME_TEXTBOX;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement LAST_NAME_TEXTBOX;

    @FindBy(xpath = "//label[text()='Employee Id']//parent::div//following-sibling::div//input")
    private WebElement EMPLOYEE_ID_TEXTBOX;

    @FindBy(xpath = "//button[contains(string(),'Save')]")
    private WebElement SAVE_BUTTON;

    public AddEmployeePageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void enterToFirstNameTextbox(String firstName) {
        waitElementVisible(driver, FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver, FIRST_NAME_TEXTBOX, firstName);
    }

    public void enterTolastNameTextbox(String lastName) {
        waitElementVisible(driver, LAST_NAME_TEXTBOX);
        sendKeyToElement(driver, LAST_NAME_TEXTBOX, lastName);
    }

    public String getEmployeeID() {
        waitElementVisible(driver, EMPLOYEE_ID_TEXTBOX);
        return getDOMProperty(driver, EMPLOYEE_ID_TEXTBOX, "_value");
    }

    public void clickToSaveButton() {
        waitElementClickable(driver, SAVE_BUTTON);
        clickToElement(driver, SAVE_BUTTON);
    }
}
