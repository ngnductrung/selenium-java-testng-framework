package blueImp;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.PageGenerator;
import pageObjects.blueImp.BlueImpPO;


public class UploadFile_01_BlueImp extends BaseTest {
        private String urlPath;
        private SoftAssert softAssert;

        @Parameters({"browserName", "urlPath"})
        @BeforeClass
        public void beforeClass(String browserName, String urlPath){

            driver = getBrowserDriver(browserName, urlPath);
            softAssert = new SoftAssert();
            this.urlPath = urlPath;
        }


        @Test
        public void Upload_01()  {
            blueImp = PageGenerator.getPage(BlueImpPO.class, driver);
            blueImp.loadFile("Mountain.jpg", "River.jpg", "Sea.jpg");

            blueImp.isFileLoadSuccess("Mountain.jpg");
            blueImp.isFileLoadSuccess("River.jpg");
            blueImp.isFileLoadSuccess("Sea.jpg");
            
            blueImp.clickToSideButton("start");
            softAssert.assertTrue(blueImp.isProgressBarInvisible());

            blueImp.clickToSideButton("delete");
            softAssert.assertTrue(blueImp.isProgressBarInvisible());

            softAssert.assertFalse(blueImp.isFileDeleteSuccess("Mountain.jpg"));
            softAssert.assertFalse(blueImp.isFileDeleteSuccess("River.jpg"));
            softAssert.assertFalse(blueImp.isFileDeleteSuccess("Sea.jpg"));

            softAssert.assertAll();

        }

        @AfterClass
        public void afterClass(){
//            driver.quit();
        }

    private WebDriver driver;
    private BlueImpPO blueImp;
}


