package core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;


public class BaseTest  {
    WebDriver driver;
    public Logger logger;
    BrowserList browserList;


    public WebDriver getBrowserDriver(String operatingSystem, String browserName, String urlPath) throws IOException {
        logger = LogManager.getLogger(this.getClass());

        //Loading config file
        FileReader file = new FileReader("./src/main/resources/config.properties");
        Properties p = new Properties();
        p.load(file);

        //os
        if(p.getProperty("executionEnvironment").equalsIgnoreCase("remote")){
            DesiredCapabilities capabilities =  new DesiredCapabilities();

            //operating system
            if(operatingSystem.equalsIgnoreCase("WINDOWS")){
                capabilities.setPlatform(Platform.WIN11);
            }
            else if(operatingSystem.equalsIgnoreCase("MAC")) {
                capabilities.setPlatform(Platform.MAC);
            }
            else {
                System.out.println("No matching operating system!");
            }

            //browser
            browserList = BrowserList.valueOf(browserName.toUpperCase());
            switch (browserList){
                case FIREFOX:
                    capabilities.setBrowserName("firefox");
                    break;
                case CHROME:
                    capabilities.setBrowserName("chrome");
                    break;
                case EDGE:
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                case SAFARI:
                    capabilities.setBrowserName("safari");
                    break;
                default:
                    throw new IllegalArgumentException("Invalid browser: " + browserName);
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

        } else if (p.getProperty("executionEnvironment").equalsIgnoreCase("local")) {
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
