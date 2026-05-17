package orangehrm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login_02_BasePage_Extend extends core.BasePage{
    WebDriver driver;

        @BeforeClass
        public void beforeClass(){
            driver = new FirefoxDriver();
            getPageUrl(driver, "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            driver.manage().window().maximize();
        }

        @Test
        public void Empty(){
            waitElementVisible(driver, "//input[@name='username']");
            getWebElement(driver, "Xpath=//input[@name='username']");
            getWebElement(driver, "Xpath=//input[@name='password']");
            clickToElement(driver, "Xpath=//button[contains(@class,'orangehrm-login-button')]");

            Assert.assertEquals(getElementText(driver, "Xpath=//input[@name='username']/parent::div/following-sibling::span"), "Required");
            Assert.assertEquals(getElementText(driver, "Xpath=//input[@name='password']/parent::div/following-sibling::span"), "Required");
        }

        @AfterClass
        public void afterClass(){
            driver.quit();
        }
    }


