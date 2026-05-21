package pageObjects.orangeHRM.editNavigation;

import org.openqa.selenium.WebDriver;

public class JobPageObject extends EditNavigationPageObject {
WebDriver driver;

    public JobPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
}
