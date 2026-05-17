package orangehrm;

import core.BasePage;
import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Login_03_Multiple_Browser extends BaseTest {
    BasePage basePage;
    WebDriver driver;
    String urlPath;

        @Parameters({"browserName", "urlPath"})
        @BeforeClass
        public void beforeClass(String browserName, String urlPath){
            basePage = BasePage.getInstance();
            this.urlPath = urlPath;
            driver = getBrowserDriver(browserName, urlPath);
        }

        @Test
        public void Empty(){
            basePage.waitElementVisible(driver, "//input[@name='username']");
            basePage.getWebElement(driver, "//input[@name='username']");
            basePage.getWebElement(driver, "//input[@name='password']");
            basePage.clickToElement(driver, "//button[contains(@class,'orangehrm-login-button')]");

            Assert.assertEquals(basePage.getElementText(driver, "//input[@name='username']/parent::div/following-sibling::span"), "Required");
            Assert.assertEquals(basePage.getElementText(driver, "//input[@name='password']/parent::div/following-sibling::span"), "Required");

        }

        @AfterClass
        public void afterClass(){
            driver.quit();
        }
    }


