package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePageFactory {

    public static BasePageFactory getInstance(){
        return new BasePageFactory();
    }

    public void getPageUrl(WebDriver driver, String pageUrl){
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver){
         return driver.getPageSource();
    }

    public void backToPage(WebDriver driver){
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }

    public void refreshPage(WebDriver driver){
        driver.navigate().refresh();
    }

    public Alert waitAlertPresence(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIMEOUT)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptToAlert(WebDriver driver){
        waitAlertPresence(driver).accept();
    }

    public void dismissToAlert(WebDriver driver){
        waitAlertPresence(driver).dismiss();
    }

    public void sendKeyToAlert(WebDriver driver, String keyToSend){
        waitAlertPresence(driver).sendKeys(keyToSend);
    }

    public String getAlertText(WebDriver driver){
        return waitAlertPresence(driver).getText();
    }

    public void sleepInSecond(int timeInSecond){
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToWindowByID(WebDriver driver, String parentWindowID) {

        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String windowID : allWindowIDs){
            if(!windowID.equals(parentWindowID)){
                driver.switchTo().window(windowID);
            }
        }
        sleepInSecond(2);
    }

    public void switchToWindowByTitle(WebDriver driver, String targetPageTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String windowID : allWindowIDs){
            driver.switchTo().window(windowID);
            if(driver.getTitle().equals(targetPageTitle)){
                break;
            }
        }
    }

    public void closeAllTabsExceptMain(WebDriver driver, String mainWindowID){
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String windowID : allWindowIDs){
            if(!windowID.equals(mainWindowID)){
                driver.switchTo().window(mainWindowID);
                driver.close();
            }
        }
    }

    public void clickToElement(WebDriver driver, WebElement element){
        element.click();
    }

    public void sendKeyToElement(WebDriver driver, WebElement element, String keyToSend){
        element.sendKeys(keyToSend);
    }

    public String getElementText(WebDriver driver, WebElement element){
        return element.getText();
    }

    public void selectItemInDefaultDropdown(WebDriver driver, WebElement element, String valueItem){
        new Select(element).selectByVisibleText(valueItem);
    }

    public String getSelectedValueInDefaultDropdown(WebDriver driver, WebElement element, String valueItem){
        return new Select(element).getFirstSelectedOption().getText();
    }

    public boolean isDefaultDropdownMultiple(WebDriver driver, WebElement element){
        return new Select(element).isMultiple();
    }


    public String getDOMAttribute(WebDriver driver, WebElement element, String attributeName){
        return element.getDomAttribute(attributeName);
    }

    public String getDOMProperty(WebDriver driver, WebElement element, String propertyName){
        return element.getDomProperty(propertyName);
    }

    public String getElementCss(WebDriver driver, WebElement element, String propertyName){
        return element.getCssValue(propertyName);
    }

    public String getHexaColourFromRGBA(WebDriver driver, String rbgaValue){
        return Color.fromString(rbgaValue).asHex().toUpperCase();
    }

    public int getListElementNumber(WebDriver driver, List<WebElement> elements){
        return elements.size();
    }

    public void clickToCheckBox(WebDriver driver, WebElement element){
        if(!element.isSelected()){
            clickToElement(driver, element);
        }
    }

    public void unclickToCheckBox(WebDriver driver, WebElement element){
        if(element.isSelected()){
            clickToElement(driver, element);
        }
    }

    public boolean isElementDisplayed(WebDriver driver, WebElement element){
        return element.isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, WebElement element){
        return element.isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, WebElement element){
        return element.isEnabled();
    }

    public void switchToFrame(WebDriver driver, WebElement element){
        driver.switchTo().frame(element);
    }

    public void switchToDefaultContent(WebDriver driver, String locator){
        driver.switchTo().defaultContent();
    }

    public void doubleClick(WebDriver driver, WebElement element){
        new Actions(driver).doubleClick(element).perform();
    }

    public void contextClick(WebDriver driver, WebElement element){
        new Actions(driver).contextClick(element).perform();
    }

    public void moveToElement(WebDriver driver, WebElement targetElement){
        new Actions(driver).moveToElement(targetElement).perform();
    }

    public void dragAndDrop(WebDriver driver, WebElement sourceElement, WebElement targetElement){
        new Actions(driver).dragAndDrop(sourceElement,targetElement).perform();
    }

    public void dragAndDrop(WebDriver driver, WebElement element, Keys keyToSend){
        new Actions(driver).sendKeys(element, keyToSend).perform();
    }


    public WebElement waitElementVisible(WebDriver driver, WebElement element){
        return new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIMEOUT)).until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, List<WebElement> elements){
        return new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public boolean waitElementInvisible(WebDriver driver, WebElement element){
        return new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIMEOUT)).until(ExpectedConditions.invisibilityOf(element));
    }

    public boolean waitListElementInvisible(WebDriver driver, WebElement element){
        return new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIMEOUT)).until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitElementSelected(WebDriver driver, WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(element));
    }

    public void waitElementClickable(WebDriver driver, WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitLoadingSpinnerInvisibile(WebDriver driver ){
        PageFactory.initElements(driver, this);
        return waitListElementInvisible(driver, LOADING_SPINNER);
    }

    @FindBy(xpath = "//div[@class='oxd-loading-spinner']")
    private WebElement LOADING_SPINNER;

    private final int SHORT_TIMEOUT = 10;
    private final int LONG_TIMEOUT = 30;
}
