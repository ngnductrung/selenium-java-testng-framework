package pageFactory;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPageObject extends BasePageFactory {
    private WebDriver driver;

    @FindBy(xpath = "//span[text()='PIM']//parent::a")
    private WebElement PIMModule;

    public DashboardPageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickToPIMModule() {
        waitElementClickable(driver, PIMModule);
        clickToElement(driver, PIMModule);
    }
}
