package com.imc.assessment.pageclass;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver){
        super(driver);
    }
    static String originalWindow;
    @FindBy(xpath = "//a[@title = 'Categories switch']")
    WebElement lnk_Categories;

    @FindBy(id = "administrator")
    WebElement btn_Admin;

    @FindBy(xpath = "//a[@title = 'People']")
    WebElement menu_People;

    @FindBy(xpath = "//span[contains(@id,'default_save-btnIconEl')]/parent::span")
    WebElement btn_Save;

    @FindBy(xpath = "//span[contains(text(),'Catalogue')]")
    WebElement menu_Catalogue;

    @FindBy(id = "navi_item_organisation_client_manager")
    WebElement dropdown_Client;

    @FindBy(id = "headline_mainText")
    WebElement header_Client;

    @FindBy(id = "iframe_navi:1243")
    WebElement iframe_navi1234;

    @FindBy(id = "contentframe")
    WebElement iframe_contentframe;

    @FindBy(xpath ="//td[@data-qtip='Clients']//div[contains(text(),'Clients')]/ancestor::tr")
    WebElement td_Clients;

    @FindBy(id = "tbb_editGroup")
    WebElement btn_Edit;

    @FindBy(xpath = "//span[contains(text(),'GLOBAL')]")
    WebElement option_GLOBAL;

    @FindBy(xpath = "//span[contains(text(),'Settings')]/ancestor::a")
    WebElement menu_Settings;

    @FindBy(xpath = "//iframe[contains(@id,'iframe_edit')]")
    WebElement iframe_edit;

    @FindBy(xpath = "//fieldset[@id = 'enrollment']/div/span/div/table[@id = 'enrollment.warningForTimeConflicts']/tbody/tr/td[1]/div[2]/input")
    WebElement checkbox_TimeConflictsWarning;

    @FindBy(xpath = "//img[@alt='User profile folder']//parent::a")
    WebElement btn_userProfile;

    @FindBy(xpath = "//span[contains(text(),'Sign out')]//parent::a")
    WebElement btn_signOut;

    public void clickCategoriesLink(){
        lnk_Categories.click();
        logger.info("Clicked on Categories Link");
    }

    public void clickAdminButton(){
        btn_Admin.click();
        logger.info("Changed to Admin Mode");
    }

    public void clickPeopleMenu(){
        menu_People.click();
        logger.info("Clicked on People Dropdown");
    }

    public void clickCatalogueMenu(){
        menu_Catalogue.click();
        logger.info("Navigated to Catalogue search page");
    }

    public void clickClientOption(){
        dropdown_Client.click();
        logger.info("Clicked on Client Option");
        isClientHeaderPresent();
    }

    public void isClientHeaderPresent(){
        driver.switchTo().frame(iframe_contentframe);
        driver.switchTo().frame(iframe_navi1234);
        String header = header_Client.getText();
        if(header.equals("Clients")){
            logger.info("Client Header validated - Navigation successful");
        }
        driver.switchTo().defaultContent();
    }
    public void selectClientsRow(){
        driver.switchTo().frame(iframe_contentframe);
        driver.switchTo().frame(iframe_navi1234);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();",td_Clients );
        try {
            td_Clients.click();
            logger.info("Selected Clients row");
        }catch(Exception e){
            logger.error("Clients row not selected!!");
        }
        driver.switchTo().defaultContent();
    }

    public void selectEditSettingsOption()throws Exception{
        driver.switchTo().frame(iframe_contentframe);
        driver.switchTo().frame(iframe_navi1234);
        btn_Edit.click();
        originalWindow = driver.getWindowHandle();
        option_GLOBAL.click();
        logger.info("Selected Edit -> GLOBAL option");
        driver.switchTo().window(originalWindow);
        driver.switchTo().defaultContent();
        Thread.sleep(1000);
        for (String windowHandle : driver.getWindowHandles()){
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Thread.sleep(1000);
        driver.switchTo().defaultContent();
    }

    public void checkClientSetting(){
        driver.switchTo().frame(iframe_contentframe);
        driver.switchTo().frame(iframe_edit);
        menu_Settings.click();
        logger.info("Moved to Settings tab");
        driver.switchTo().defaultContent();
    }
    public void isEnrollmentWarningChecked(boolean isWarningChecked){
        driver.switchTo().frame(iframe_contentframe);
        driver.switchTo().frame(iframe_edit);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", checkbox_TimeConflictsWarning);
        boolean isChecked = checkbox_TimeConflictsWarning.isSelected();
        if(isWarningChecked){
            if(isChecked){
                logger.info("Warnings of Time conflicts checkbox selected");
            }else{
                logger.error("Warnings of Time conflicts checkbox not selected");
            }
        }else{
            if(!isChecked){
                logger.info("Warnings of Time conflicts checkbox not selected");
            }else{
                logger.error("Warnings of Time conflicts checkbox selected");
            }
        }
        driver.switchTo().defaultContent();
    }

    public void selectEnrollmentWarningCheckbox(){
        driver.switchTo().frame(iframe_contentframe);
        driver.switchTo().frame(iframe_edit);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", checkbox_TimeConflictsWarning);
        checkbox_TimeConflictsWarning.click();
        logger.info("Enrolment -> Time conflict warning checked");
        driver.switchTo().defaultContent();
    }

    public void saveSettingChanges()throws Exception{
        driver.switchTo().frame(iframe_contentframe);
        driver.switchTo().frame(iframe_edit);
        btn_Save.click();
        Thread.sleep(1000);
        logger.info("Client setting saved successfully");
        driver.switchTo().defaultContent();
    }

    public void logoutUser(){
        for (String windowHandle : driver.getWindowHandles()){
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.close();
                break;
            }
        }
        driver.switchTo().window(originalWindow);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();",btn_userProfile );
        btn_userProfile.click();
        btn_signOut.click();
        logger.info("User Loggedout successfully");
    }



}
