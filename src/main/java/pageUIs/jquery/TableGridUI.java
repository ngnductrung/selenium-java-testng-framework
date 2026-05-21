package pageUIs.jquery;

public class TableGridUI {
    public static final String DYNAMIC_PAGE_NUMBER= "XPATH=//li[@class='qgrd-pagination-page']//a[text()='%s']";
    public static final String DYNAMIC_ACTIVE_PAGE_NUMBER= "XPATH=//li[@class='qgrd-pagination-page']//a[contains(@class,'active') and text()='%s']";
    public static final String DYNAMIC_COLUMN_SEACHBOX = "xpath=//div[text()='%s']//parent::div//following-sibling::input";
    public static final String DYNAMIC_ROW_DATA ="XPATH=//td[@data-key='females' and text()='%s']//following-sibling::td[@data-key='country' and text()='%s']//following-sibling::td[@data-key='males' and text()='%s']//following-sibling::td[@data-key='total' and text()='%s']";
    public static final String PAGE_NUMBER_LIST = "XPATH=//li[@class='qgrd-pagination-page']//a";
    public static final String DYNAMIC_PRECEDING_COLUMNS= "XPATH=//th[text()='%s']//preceding::th";
    public static final String DYNAMIC_CELL_INPUT="XPATH=//tr[contains(@id,%s)]//td[%s]//input";
    public static final String DYNAMIC_CELL_DROPDOWN="XPATH=//tr[contains(@id,%s)]//td[%s]//select";
    public static final String LOAD_BUTTON= "XPATH=//button[text()='Load Data']";
    public static final String COLUMN_VALUE= "XPATH=//td[@data-key='Country']//a";
    public static final String DYNAMIC_COLUMN_HEADER = "xpath=//div[text()='%s']//ancestor::th//preceding-sibling::th";
    public static final String DYNAMIC_COLUMN_INDEX = "xpath=//td[%s]";
}
