package pageObjects.orangeHRM.editNavigation;

import org.openqa.selenium.WebDriver;

public class DependentsPageObject  extends EditNavigationPageObject {
    WebDriver driver;
    public DependentsPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
}
