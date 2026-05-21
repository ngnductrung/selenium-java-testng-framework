package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;

import java.time.Duration;


public class BaseTest  {
    WebDriver driver;
    public Logger logger;
    BrowserList browserList;


    public WebDriver getBrowserDriver(String browserName, String urlPath){
        logger = LogManager.getLogger(this.getClass());
        browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList){
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            case SAFARI:
                driver = new SafariDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid!");
        }
        driver.get(urlPath);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        return driver;
    }

//    protected void verifyTrue(boolean condition) {
//        try {
//            Assert.assertTrue(condition);
//            logger.info("========== PASSED ==========");
//        } catch (Throwable e) {
//            logger.error("========== FAILED ==========");
//            throw e;
//        }
//    }

//    protected void verifyFalse(boolean condition) {
//        try {
//            Assert.assertFalse(condition);
//            logger.info("========== PASSED ==========");
//        } catch (Throwable e) {
//            logger.error("========== FAILED ==========");
//            throw e;
//        }
//    }
//
//    protected void verifyEquals(Object actual, Object expected) {
//        try {
//            Assert.assertEquals(actual, expected);
//            logger.info("========== PASSED ==========");
//        } catch (Throwable e) {
//            logger.error("========== FAILED ==========");
//            logger.error("Expected: " + expected);
//            logger.error("Actual: " + actual);
//            throw e;
//        }
//    }
protected boolean verifyTrue(boolean condition) {
    boolean pass = true;
    try {
        Assert.assertTrue(condition);
        logger.info("=== PASSED ===");
    } catch (Throwable e) {
        pass = false;

        logger.error("=== FAILED ===");
        logger.error(e.getMessage());
        VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
        Reporter.getCurrentTestResult().setThrowable(e);
    }
    return pass;
}

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            logger.info("=== PASSED ===");
        } catch (Throwable e) {
            pass = false;

            logger.error("=== FAILED ===");
            logger.error(e.getMessage());
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            logger.info("=== PASSED ===");
        } catch (Throwable e) {
            pass = false;

            logger.error("=== FAILED ===");
            logger.error(e.getMessage());
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }
}
