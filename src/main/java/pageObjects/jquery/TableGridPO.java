package pageObjects.jquery;

import core.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pageUIs.jquery.TableGridUI;
import pageUIs.orangeHRM.BasePageUI;

import java.util.ArrayList;
import java.util.List;

public class TableGridPO extends BasePage {
    WebDriver driver;
    public TableGridPO(WebDriver driver){
        this.driver = driver;
    }

    public void clickPageNumber(int pageNumber){
        waitListElementVisible(driver, TableGridUI.DYNAMIC_PAGE_NUMBER, String.valueOf(pageNumber));
        clickToElement(driver, TableGridUI.DYNAMIC_PAGE_NUMBER, String.valueOf(pageNumber));
    }

    public Boolean isPageActiveByNumber(int pageNumber) {
        waitElementVisible(driver, TableGridUI.DYNAMIC_ACTIVE_PAGE_NUMBER, String.valueOf(pageNumber));
        return isElementDisplayed(driver, TableGridUI.DYNAMIC_ACTIVE_PAGE_NUMBER, String.valueOf(pageNumber));
    }

    public void searchByColumnValue(String columnName, String keyToSend) {
        waitElementClickable(driver, TableGridUI.DYNAMIC_COLUMN_SEACHBOX, columnName);
        sendKeyToElement(driver, keyToSend, TableGridUI.DYNAMIC_COLUMN_SEACHBOX, columnName);
        sendKeyToElement(driver, Keys.ENTER, TableGridUI.DYNAMIC_COLUMN_SEACHBOX, columnName);
    }


    public Boolean isDataRowCorrect(String... rowData) {
        waitElementVisible(driver, TableGridUI.DYNAMIC_ROW_DATA, rowData);
        return isElementDisplayed(driver, TableGridUI.DYNAMIC_ROW_DATA, rowData);
    }

    public void enterToCellTextBox(int rowIndex, String columnHeader, String keyToSend) {
        int columnIndex  = getListWebElement(driver, TableGridUI.DYNAMIC_PRECEDING_COLUMNS, columnHeader).size() + 1;
        System.out.println(columnIndex);
        waitElementVisible(driver, TableGridUI.DYNAMIC_CELL_INPUT, String.valueOf(rowIndex), String.valueOf(columnIndex));
        sendKeyToElement(driver, keyToSend, TableGridUI.DYNAMIC_CELL_INPUT, String.valueOf(rowIndex), String.valueOf(columnIndex));
    }

    public void selectCellDropdown(int rowIndex, String columnHeader, String valueItem) {
        int columnIndex  = getListWebElement(driver, TableGridUI.DYNAMIC_PRECEDING_COLUMNS, columnHeader).size() + 1;
        waitElementVisible(driver, TableGridUI.DYNAMIC_CELL_DROPDOWN, String.valueOf(rowIndex), String.valueOf(columnIndex));
        selectItemInDefaultDropdown(driver, valueItem, TableGridUI.DYNAMIC_CELL_DROPDOWN, String.valueOf(rowIndex), String.valueOf(columnIndex));
    }

    public void selectCellCheckbox(int rowIndex, String columnHeader) {
        int columnIndex  = getListWebElement(driver, TableGridUI.DYNAMIC_PRECEDING_COLUMNS, columnHeader).size() + 1;
        waitElementVisible(driver, TableGridUI.DYNAMIC_CELL_INPUT, String.valueOf(rowIndex), String.valueOf(columnIndex));
        clickToCheckBox(driver, TableGridUI.DYNAMIC_CELL_INPUT, String.valueOf(rowIndex), String.valueOf(columnIndex));
    }

    public void clickToLoadButton(){
        waitElementClickable(driver, TableGridUI.LOAD_BUTTON);
        clickToElement(driver, TableGridUI.LOAD_BUTTON);
    }

    public List<String> getColumnValueList() throws InterruptedException {
        Thread.sleep(5000);
        List<String> countryColumnValue = new ArrayList<String>();
        List<WebElement> allPage = getListWebElement(driver, TableGridUI.PAGE_NUMBER_LIST);
        int columnIndex = waitListElementVisible(driver, TableGridUI.DYNAMIC_COLUMN_HEADER, "Country").size() + 1;

        for (WebElement pageNumber : allPage){

            pageNumber.click();

            List<WebElement> countryList = getListWebElement(driver, TableGridUI.DYNAMIC_COLUMN_INDEX, String.valueOf(columnIndex));

            for (WebElement country : countryList){
                countryColumnValue.add(country.getText());
            }
        }
        return countryColumnValue;
    }


}
