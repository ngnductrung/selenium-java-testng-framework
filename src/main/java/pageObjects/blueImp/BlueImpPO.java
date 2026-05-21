package pageObjects.blueImp;

import core.BasePage;
import core.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.blueImp.BlueImpUI;

import java.nio.file.Files;
import java.util.List;

public class BlueImpPO extends BasePage {
    WebDriver driver;

    public BlueImpPO(WebDriver driver){
        this.driver = driver;
    }

    public void loadFile(String... filesToUpload) {
        String allFilesToUpload = "";

        for(String file : filesToUpload){
            allFilesToUpload = allFilesToUpload + GlobalConstants.UPLOAD_PATH + file + "\n";
        }

        waitElementPresent(driver, BlueImpUI.uploadInput);
        getWebElement(driver, BlueImpUI.uploadInput).sendKeys(allFilesToUpload.trim());
    }

    public boolean isFileLoadSuccess(String fileName){
         waitElementVisible(driver, BlueImpUI.uploadFile, fileName);
         return isElementDisplayed(driver, BlueImpUI.uploadFile, fileName);
    }

    public boolean isFileDeleteSuccess(String fileName){
        waitListElementInvisible(driver, BlueImpUI.uploadFile, fileName);
        return isElementDisplayed(driver, BlueImpUI.uploadFile, fileName);
    }

    public void clickToSideButton(String buttonName){
        List<WebElement> sideButtonList = waitListElementVisible(driver, BlueImpUI.sideButton, buttonName);
        for (WebElement sideButton : sideButtonList){
            sideButton.click();
        }
    }

    public Boolean isProgressBarInvisible(){
        return waitListElementInvisible(driver, BlueImpUI.progressBar);
    }
}
