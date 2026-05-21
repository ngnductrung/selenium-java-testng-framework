package pageFactory;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmployeeListPageObject extends BasePageFactory {
    private WebDriver driver;

    @FindBy(xpath = "//a[text()='Add Employee']//parent::li")
    private WebElement ADD_EMPLOYEE_BUTTON;

    public EmployeeListPageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickToAddEmployeeButton() {
        waitElementClickable(driver, ADD_EMPLOYEE_BUTTON);
        clickToElement(driver, ADD_EMPLOYEE_BUTTON);
    }
}
