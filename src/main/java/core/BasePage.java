package core;

import org.jspecify.annotations.NonNull;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.orangeHRM.BasePageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    public static BasePage getInstance(){
        return new BasePage();
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

    public String getWindowID(WebDriver driver){
        return driver.getWindowHandle();
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
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT)).until(ExpectedConditions.alertIsPresent());
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

    public void openNewTab(WebDriver driver, String targetUrlPath){
        driver.switchTo().newWindow(WindowType.TAB).get(targetUrlPath);
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

    public String getDynamicLocatorValue(String locatorValue, String... assignedValues){
        locatorValue = String.format(locatorValue, (Object[]) assignedValues);
        return locatorValue;
    }

    private By getByLocator(String locator){
        if (locator == null || locator.trim().isEmpty()){
            throw new  IllegalArgumentException("Locator cannot be null or empty: " + locator);
        }

        String[] locatorArr = locator.split("=", 2);
        String locatorType = locatorArr[0].trim();
        String locatorValue = locatorArr[1].trim();
        switch (locatorType.toUpperCase()){
            case "XPATH":
                return By.xpath(locatorValue);
            case "CSS":
                return By.cssSelector(locatorValue);
            case "ID":
                return By.id(locatorValue);
            case "NAME":
                return By.name(locatorValue);
            case "CLASS":
                return By.className(locatorValue);
            default:
                throw new  IllegalArgumentException("Invalid locator: " + locator);
        }
    }

    private By getByLocator(String locator, String... assignedValues){
        if (getDynamicLocatorValue(locator, assignedValues) == null || getDynamicLocatorValue(locator, assignedValues).trim().isEmpty()){
            throw new  IllegalArgumentException("Locator cannot be null or empty: " + getDynamicLocatorValue(locator, assignedValues));
        }

        String[] locatorArr = getDynamicLocatorValue(locator, assignedValues).split("=", 2);
        String locatorType = locatorArr[0].trim();
        String locatorValue = locatorArr[1].trim();
        switch (locatorType.toUpperCase()){
            case "XPATH":
                return By.xpath(locatorValue);
            case "CSS":
                return By.cssSelector(locatorValue);
            case "ID":
                return By.id(locatorValue);
            case "NAME":
                return By.name(locatorValue);
            case "CLASS":
                return By.className(locatorValue);
            default:
                throw new  IllegalArgumentException("Invalid locator: " + locator);
        }
    }

    public WebElement getWebElement(WebDriver driver, String locator){
        return driver.findElement(getByLocator(locator));
    }

    public WebElement getWebElement(WebDriver driver, String locatorValue, String... assignedValues){
        return driver.findElement(getByLocator(locatorValue, assignedValues));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String locator){
        return driver.findElements(getByLocator(locator));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String locatorValue, String... assignedValues){
        return driver.findElements(getByLocator(locatorValue, assignedValues));
    }

    public void clickToElement(WebDriver driver, String locator){
        getWebElement(driver,locator).click();
    }

    public void clickToElement(WebDriver driver, String locatorValue, String... assignedValues ){
        getWebElement(driver,locatorValue, assignedValues).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, CharSequence keyToSend){
        getWebElement(driver,locator).sendKeys(keyToSend);
    }

    public void sendKeyToElement(WebDriver driver,  CharSequence keyToSend, String locatorValue, String... assignedValues){
        getWebElement(driver, locatorValue, assignedValues).sendKeys(keyToSend);
    }

    public String getElementText(WebDriver driver, String locator){
        return getWebElement(driver,locator).getText();
    }

    public String getElementText(WebDriver driver, String locatorValue, String... assignedValues){
        return getWebElement(driver,locatorValue, assignedValues).getText();
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String valueItem){
        new Select(getWebElement(driver, locator)).selectByVisibleText(valueItem);
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String valueItem, String locatorValue, String... assignedValues){
        new Select(getWebElement(driver, locatorValue, assignedValues)).selectByVisibleText(valueItem);
    }

    public String getSelectedValueInDefaultDropdown(WebDriver driver, String locator, String valueItem){
        return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public String getSelectedValueInDefaultDropdown(WebDriver driver, String valueItem, String locatorValue, String... assignedValues){
        return new Select(getWebElement(driver, locatorValue, assignedValues)).getFirstSelectedOption().getText();
    }

    public boolean isDefaultDropdownMultiple(WebDriver driver, String locator){
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    public boolean isDefaultDropdownMultiple(WebDriver driver, String locatorValue, String... assignedValues){
        return new Select(getWebElement(driver, locatorValue, assignedValues)).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String textItem){
        clickToElement(driver, parentLocator);
        sleepInSecond(1);

        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(childLocator)));

        List<WebElement> allItems = driver.findElements(getByLocator(childLocator));

        for (WebElement item : allItems){
            if(item.getText().equals(textItem)){
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public String getDOMAttribute(WebDriver driver, String locator, String attributeName){
        return getWebElement(driver, locator).getDomAttribute(attributeName);
    }

    public String getDOMAttribute(WebDriver driver, String attributeName, String locatorValue, String... assignedValues){
        return getWebElement(driver, locatorValue, assignedValues).getDomAttribute(attributeName);
    }

    public String getDOMProperty(WebDriver driver, String locator, String propertyName){
        return getWebElement(driver, locator).getDomProperty(propertyName);
    }

    public String getDOMProperty(WebDriver driver, String propertyName, String locatorValue, String... assignedValues){
        return getWebElement(driver, locatorValue, assignedValues).getDomProperty(propertyName);
    }

    public String getElementCss(WebDriver driver, String locator, String propertyName){
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    public String getElementCss(WebDriver driver, String propertyName, String locatorValue, String... assignedValues){
        return getWebElement(driver, locatorValue, assignedValues).getCssValue(propertyName);
    }

    public String getHexaColourFromRGBA(WebDriver driver, String rbgaValue){
        return Color.fromString(rbgaValue).asHex().toUpperCase();
    }

    public int getListElementNumber(WebDriver driver, String locator){
        return getListWebElement(driver,locator).size();
    }

    public int getListElementNumber(WebDriver driver, String locatorValue, String... assignedValues){
        return getListWebElement(driver,locatorValue, assignedValues).size();
    }

    public void clickToCheckBox(WebDriver driver, String locator){
        if(!getWebElement(driver, locator).isSelected()){
            clickToElement(driver, locator);
        }
    }

    public void clickToCheckBox(WebDriver driver, String locatorValue, String... assignedValues){
        if(!getWebElement(driver, locatorValue, assignedValues).isSelected()){
            clickToElement(driver, locatorValue, assignedValues);
        }
    }

    public void unclickToCheckBox(WebDriver driver, String locator){
        if(getWebElement(driver, locator).isSelected()){
            clickToElement(driver, locator);
        }
    }

    public void unclickToCheckBox(WebDriver driver, String locatorValue, String... assignedValues){
        if(getWebElement(driver, locatorValue, assignedValues).isSelected()){
            clickToElement(driver, locatorValue, assignedValues);
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator){
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, String locatorValue, String... assignedValues){
        return getWebElement(driver, getDynamicLocatorValue(locatorValue, assignedValues)).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String locatorValue, String... assignedValues){
        return getWebElement(driver, getDynamicLocatorValue(locatorValue, assignedValues)).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locatorValue, String assignedValues){
        return getWebElement(driver, getDynamicLocatorValue(locatorValue, assignedValues)).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator){
        return getWebElement(driver, locator).isEnabled();
    }

    public boolean isElementEnabled(WebDriver driver, String locatorValue, String assignedValues){
        return getWebElement(driver, getDynamicLocatorValue(locatorValue, assignedValues)).isEnabled();
    }

    public void switchToFrame(WebDriver driver, String locator){
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver, String locator){
        driver.switchTo().defaultContent();
    }

    public void doubleClick(WebDriver driver, String locator){
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    public void contextClick(WebDriver driver, String locator){
        new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    public void moveToElement(WebDriver driver, String targetLocator){
        new Actions(driver).moveToElement(getWebElement(driver, targetLocator)).perform();
    }

    public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator){
        new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator),getWebElement(driver, targetLocator)).perform();
    }

    public void dragAndDrop(WebDriver driver, String locator, Keys keyToSend){
        new Actions(driver).sendKeys(getWebElement(driver,locator), keyToSend).perform();
    }


    public WebElement waitElementVisible(WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public WebElement waitElementVisible(WebDriver driver, String locatorValue, String... assignedValues){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorValue, assignedValues)));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, String locatorValue, String... assignedValues){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorValue, assignedValues)));
    }

    public boolean waitElementInvisible(WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public boolean waitElementInvisible(WebDriver driver, String locatorValue, String... assignedValues){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorValue, assignedValues)));
    }

    public boolean waitListElementInvisible(WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver,locator)));
    }

    public boolean waitListElementInvisible(WebDriver driver, String locatorValue, String... assignedValues){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorValue, assignedValues)));
    }

    public Boolean waitElementSelected(WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public Boolean waitElementSelected(WebDriver driver, String locatorValue, String... assignedValues){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(locatorValue, assignedValues)));
    }

    public WebElement waitElementClickable(WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public WebElement waitElementClickable(WebDriver driver, String locatorValue, String... assignedValues){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locatorValue, assignedValues)));
    }

    public WebElement waitElementPresent(WebDriver driver, String locatorValue){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locatorValue)));
    }

    public WebElement waitElementPresent(WebDriver driver, String locatorValue, String... assignedValues){
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locatorValue, assignedValues)));
    }

    public boolean waitLoadingSpinnerInvisibile(WebDriver driver){
        return waitListElementInvisible(driver, BasePageUI.LOADING_SPINNER);
    }


}
