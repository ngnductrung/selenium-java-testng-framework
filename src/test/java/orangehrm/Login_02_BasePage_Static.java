package orangehrm;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login_02_BasePage_Static {
    BasePage basePage;
    WebDriver driver;

        @BeforeClass
        public void beforeClass(){
            driver = new FirefoxDriver();
            basePage = BasePage.getInstance();
            basePage.getPageUrl(driver, "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            driver.manage().window().maximize();
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


