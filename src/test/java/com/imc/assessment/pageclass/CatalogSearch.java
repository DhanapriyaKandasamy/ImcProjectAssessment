package com.imc.assessment.pageclass;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CatalogSearch extends BasePage {

    public CatalogSearch(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@role = 'heading' and contains(text(),'Catalogue')]")
    WebElement header_Catalog_txt;

    @FindBy(xpath = "//span[@class = 'imc-filter-summary-bar']")
    WebElement txt_FilterResult;
    @FindBy(xpath = "//*[text() = ' Language Skills ']//parent::a")
    WebElement link_LanguageSkills;

    @FindBy(xpath = "//button[@title = 'Language Skills']/span[2]")
    WebElement btn_RemoveLanguageFilter;

    @FindBy(xpath = "//*[text() = ' MS Office ']//parent::a")
    WebElement link_MSOffice;

    @FindBy(linkText = "Show more results")
    WebElement btn_loadMoreOption;

    @FindBy(xpath = "//button[contains(text(),'Remove all')]")
    WebElement btn_RemoveAll;

    @FindBy(xpath = "//button//span[contains(text(),'MS Office')]")
    WebElement btn_MsOfficeFilter;

    @FindBy(xpath = "//img[@alt='User profile folder']//parent::a")
    WebElement btn_userProfile;

    @FindBy(xpath = "//span[contains(text(),'Sign out')]//parent::a")
    WebElement btn_signOut;

    @FindBy(id = "searchField")
    WebElement txt_Search;

    @FindBy(id = "startSearchBtn")
    WebElement btn_Search;

    @FindBy(xpath = "//button//div[contains(text(),'Enrol')]")
    WebElement btn_Enrol;

    @FindBy(xpath = "//*[contains(text(),'TD #Course #WarnTiming')]")
    List<WebElement> list_WarnTimingCourse;

    @FindBy(xpath = "//div[@class = 'components']")
    WebElement msg_Enrolment;

    @FindBy(xpath = "//*[contains(text(),'TD #CourseTemplate #WarnTiming')]")
    List<WebElement> list_WarnTimingCourseTemplate;

    @FindBy(xpath = "//div[contains(text(),'Course enrolment')]")
    WebElement header_CourseEnrollment;

    @FindBy(id = "enrolment_cancel_button")
    WebElement btn_CancelEnrol;

    @FindBy(id = "enrolment_proceed_button")
    WebElement btn_ProceedEnrol;

    public void isHeaderPresent() {
        if(header_Catalog_txt.isDisplayed()){
            logger.info("Catalog content is loaded successfully");
        }else {
            logger.error("Catalog content not loaded successfully");
        }
    }

    public void clickLanguageSkillsLink(){
        link_LanguageSkills.click();
        logger.info("Clicked Language skills link");
    }

    public void clickRemoveLanguageSkillFilterOption(){
        btn_RemoveLanguageFilter.click();
        logger.info("Language skills filter removed");
    }

    public void isResultLoadedWithMSOfficeFilter(){
        try {
                if(btn_MsOfficeFilter.isDisplayed()) {
                    logger.info("Loaded with MS Office filter");
                }
        }catch(NoSuchElementException e){
            logger.error("MS Office filter is not included");
        }
    }

    public void isResultLoadedWithoutFilter(){
        try {
            if(!btn_RemoveAll.isDisplayed())
                logger.info("Filter is removed as expected");
        }catch(NoSuchElementException e){
            logger.info("Filter is not removed as expected");
        }
    }

    public void isResultLoadedWithFilter(int filterCount){
        try {
            if(btn_RemoveAll.isDisplayed())
                logger.info("Filter is not removed as expected");
        }catch(NoSuchElementException e){
            logger.info("Filter is removed and results is displayed");
        }
        checkFilterResult(filterCount);
    }

    public void checkFilterResult(int filterCount){
        String filterResult = txt_FilterResult.getText();
        String[] result = filterResult.split(" ");
        int courseFiltered = Integer.parseInt(result[0]);
        if (courseFiltered == filterCount){
            logger.info("Course filtered is " +courseFiltered+ "as expected");
        }else {
            logger.info("Course filtered is " +courseFiltered+ "!! not as expected");
        }
    }

    public void clickMSOfficeLink(){
        link_MSOffice.click();
        logger.info("Clicked MSOffice link");
    }
    public void clickShowMoreResult(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();",btn_loadMoreOption );
        try {
            btn_loadMoreOption.click();
            logger.info("LoadMore option is clicked");
        }catch(Exception e){
            logger.info("LoadMore option not available");
        }
    }

    public void setSearchText(String searchText){
        txt_Search.sendKeys(searchText);
        logger.info("Entered text #WarnTiming");
    }

    public void clickSearchButton(){
        btn_Search.click();
        logger.info("Clicked search button");
    }

    public void countWarnTimingCourse(int courseCount){
        if(courseCount == list_WarnTimingCourse.size()){
            logger.info(list_WarnTimingCourse.size() + "WarnTiming Courses are found");
        }else {
            logger.error(list_WarnTimingCourse.size() + "WarnTiming Courses are found..Mismatch!!");
        }
    }

    public void countWarnTimingCourseTemplate(int courseTemplateCount){
        if(courseTemplateCount == list_WarnTimingCourseTemplate.size()){
            logger.info(list_WarnTimingCourseTemplate.size() + "WarnTiming Course Templates are found");
        }else {
            logger.error(list_WarnTimingCourseTemplate.size() + "WarnTiming Course Templates are found..Mismatch!!");
        }
    }

    public void select_LongAvailabilityCourse(){
        for(WebElement element: list_WarnTimingCourse){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();",element );
            if(element.getText().equals("TD #Course #WarnTiming #LongAvailability #SecondTemplate")){
                element.click();
                logger.info("Selected TD #Course #WarnTiming #LongAvailability");
                break;
            }
        }
    }

    public void selectEnrollButton(){
        btn_Enrol.click();
        logger.info("Selected Enrol Button");
    }

    public void isCourseEnrollmentAlertPresent(boolean isTimeIncluded){
        if(header_CourseEnrollment.isDisplayed()){
            logger.info("Message prompted for enrollment confirmation");
        }else {
            logger.info("Message prompted for enrollment confirmation");
        }
        isTimeFrameIncludedInCourseEnrollment(isTimeIncluded);
    }

    public void isTimeFrameIncludedInCourseEnrollment(boolean isTimeIncluded){
        String message = msg_Enrolment.getText();
        System.out.println(message);
        if(isTimeIncluded){
            if(message.contains("time frame")){
                logger.info("Message prompted with TimeFrame");
            }else{
                logger.error("Message prompted without TimeFrame");
            }
        }else{
            if(!message.contains("time frame")){
                logger.info("Message prompted without TimeFrame");
            }else{
                logger.error("Message prompted with TimeFrame");
            }
        }
    }
    public void cancelEnrolment(){
        btn_CancelEnrol.click();
        logger.info("Enrolment cancelled");
    }

    public void proceedEnrolment(){
        btn_ProceedEnrol.click();
        logger.info("Clicked Enrolment button");
    }

    public void logoutUser(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();",btn_userProfile );
        btn_userProfile.click();
        btn_signOut.click();
        logger.info("User Signed Out");
    }
}
