package pageObjects.orangeHRM.editNavigation;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.*;
import pageUIs.orangeHRM.BasePageUI;
import pageUIs.orangeHRM.PersonalDetailPageUI;

public class EditNavigationPageObject extends BasePage {
    WebDriver driver;

    public EditNavigationPageObject(WebDriver driver){
        this.driver = driver;
    }

    public ContactDetailPageObject openContactDetailPage() {
        waitElementVisible(driver, BasePageUI.CONTACT_DETAILS_TEXTLINK);
        clickToElement(driver, BasePageUI.CONTACT_DETAILS_TEXTLINK);
        return PageGenerator.getPage(ContactDetailPageObject.class, driver);
    }

    public JobPageObject openJobPage() {
        waitElementVisible(driver, BasePageUI.JOB_TEXTLINK);
        clickToElement(driver, BasePageUI.JOB_TEXTLINK);
        return PageGenerator.getPage(JobPageObject.class, driver);
    }

    public PersonalDetailPageObject openPersonalDetailPage() {
        waitElementVisible(driver, PersonalDetailPageUI.CONTACT_DETAIL_TEXTLINK);
        clickToElement(driver, PersonalDetailPageUI.JOB_TEXTLINK);
        return PageGenerator.getPage(PersonalDetailPageObject.class, driver);
    }

    public DependentsPageObject openDependentsPage() {
        waitElementClickable(driver, BasePageUI.DEPENDENTS_TEXTLINK);
        clickToElement(driver, BasePageUI.DEPENDENTS_TEXTLINK);
        return PageGenerator.getPage(DependentsPageObject.class, driver);
    }

    public void openEditNavigationPage(String... pageName){
        clickToElement(driver, BasePageUI.EDIT_NAVIGATION_TEXTLINK, pageName);
    }
}
