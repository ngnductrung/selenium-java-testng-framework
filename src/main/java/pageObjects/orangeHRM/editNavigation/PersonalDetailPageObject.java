package pageObjects.orangeHRM.editNavigation;

import org.openqa.selenium.WebDriver;
import pageUIs.orangeHRM.PersonalDetailPageUI;

public class PersonalDetailPageObject extends EditNavigationPageObject {
    private WebDriver driver;

    public PersonalDetailPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public String getFirstNameTextboxValue(){
        waitElementVisible(driver, PersonalDetailPageUI.FIRST_NAME_TEXTBOX);
        return getDOMProperty(driver, PersonalDetailPageUI.FIRST_NAME_TEXTBOX , "_value");
    }

    public String getLastNameTextboxValue(){
        waitElementVisible(driver, PersonalDetailPageUI.LAST_NAME_TEXTBOX);
        return getDOMProperty(driver, PersonalDetailPageUI.LAST_NAME_TEXTBOX , "_value");
    }

    public String getEmployeeIDTextboxValue(){
        waitElementVisible(driver, PersonalDetailPageUI.EMPLOYEE_ID_TEXTBOX);
        return getDOMProperty(driver, PersonalDetailPageUI.EMPLOYEE_ID_TEXTBOX , "_value");
    }


}
