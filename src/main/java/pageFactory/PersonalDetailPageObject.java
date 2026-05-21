package pageFactory;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalDetailPageObject extends BasePageFactory {
    private WebDriver driver;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement FIRST_NAME_TEXTBOX;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement LAST_NAME_TEXTBOX;

    @FindBy(xpath = "//label[text()='Employee Id']//parent::div//following-sibling::div//input")
    private WebElement EMPLOYEE_ID_TEXTBOX;

    public PersonalDetailPageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getFirstNameTextboxValue(){
        waitElementVisible(driver, FIRST_NAME_TEXTBOX);
        return getDOMProperty(driver, FIRST_NAME_TEXTBOX , "_value");
    }

    public String getLastNameTextboxValue(){
        waitElementVisible(driver, LAST_NAME_TEXTBOX);
        return getDOMProperty(driver, LAST_NAME_TEXTBOX , "_value");
    }

    public String getEmployeeIDTextboxValue(){
        waitElementVisible(driver, EMPLOYEE_ID_TEXTBOX);
        return getDOMProperty(driver, EMPLOYEE_ID_TEXTBOX , "_value");
    }
}
