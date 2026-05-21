package jquery;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.jquery.TableGridPO;



public class Login_08_TableGrid extends BaseTest {
    private String urlPath;

        @Parameters({"browserName", "urlPath"})
        @BeforeClass
        public void beforeClass(String browserName, String urlPath){

            //Open user URL
            driver = getBrowserDriver(browserName, urlPath);

            this.urlPath = urlPath;

            System.out.println("This looks good and Im in development mode");
        }

//        @Test
//        public void Table_01(){
//            // 1. Open any page base on page number
//            tableGrid = PageGenerator.getPage(TableGridPO.class, driver);
//            tableGrid.clickPageNumber(8);
//            Assert.assertTrue(tableGrid.isPageActiveByNumber(8));
//
//            // 2. Search in any header textbox based on column header
//            tableGrid.clickPageNumber(1);
//            tableGrid.searchByColumnValue("Females", "750");
//            // 3. Verify any country information
//
//            Assert.assertTrue(tableGrid.isDataRowCorrect("750", "Aruba", "756", "1504"));
//            // 4. Can delete / edit any countries based on Country name
//
//        }

//        @Test
//        public void Table_02(){
//            tableGrid = PageGenerator.getPage(TableGridPO.class, driver);
//            tableGrid.enterToCellTextBox(1, "Company", "Apple");
//            tableGrid.enterToCellTextBox(1, "Contact Person", "Steve Jobs");
//            tableGrid.enterToCellTextBox(1, "Order Placed", "8");
//            tableGrid.selectCellDropdown(1, "Country", "United Kingdom");
//            tableGrid.selectCellCheckbox(1, "NPO?");
//
//        }

        @Test
        public void Table_03() throws InterruptedException {
            tableGrid = PageGenerator.getPage(TableGridPO.class, driver);
            System.out.println(tableGrid.getColumnValueList());
        }

        @AfterClass
        public void afterClass(){
            driver.quit();
        }

    private WebDriver driver;
    private TableGridPO tableGrid;
}


