package com.imc.assessment.testcases;

import com.imc.assessment.pageclass.CatalogSearch;
import com.imc.assessment.pageclass.HomePage;
import com.imc.assessment.pageclass.LoginPage;
import com.imc.assessment.testbase.BaseClass;
import com.imc.assessment.utilities.DataProviders;
import org.testng.annotations.Test;

public class TC_002_LMSILS_44824 extends BaseClass {


    LoginPage login;
    HomePage homePage;
    CatalogSearch catalog;

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    public void verify_ClientEnrollment(String userLogin, String loginPassword)throws Exception {
        login = new LoginPage(driver);
        homePage = new HomePage(driver);
        catalog = new CatalogSearch(driver);

        String[] userName = userLogin.split(",");
        String[] password = loginPassword.split(",");

        logger.info("****Starting TestCase TC1_LMSILS_46752");
        logger.info("Step 1 : Login with QAAutomation1! user and check for time conflict warning is unchecked");

        login.userLogin(userName[0],password[0]);
        selectEditClientOption();
        checkSettings(false);
        logoutUser();

        logger.info("Step2 : Login with warninguser1 user and search for course warntimimg");
        login.userLogin(userName[1],password[1]);
        clickCatalogueMenuSelection();
        searchCourse("#warntiming",4,2);

        logger.info("Step 3 and 4: Search course 'Long Availability', click enroll and check no time conflict warning is present and click Cancel");
        selectCourseAndClickEnroll(false,false);
        signOutUser();

        logger.info("Login with QAAutomation1! user and change settings under enrolment for time conflict warning as true");
        login.userLogin(userName[0],password[0]);
        selectEditClientOption();
        changeSettings();
        logoutUser();

        logger.info("Step6 and 7 : Login with warninguser1 user and search for course warntimimg and enroll check Time conflict in message prompted.");
        login.userLogin(userName[1],password[1]);
        clickCatalogueMenuSelection();
        searchCourse("#warntiming",4,2);
        selectCourseAndClickEnroll(true,false);

        logger.info("Enroll Course for User");
        selectCourseAndClickEnroll(true,true);
        signOutUser();
    }


    public void selectEditClientOption()throws Exception{
        homePage.clickCategoriesLink();
        homePage.clickAdminButton();
        homePage.clickPeopleMenu();
        homePage.clickClientOption();
        homePage.selectClientsRow();
        homePage.selectEditSettingsOption();
    }

    public void checkSettings(boolean warningCheckBoxSelection){
        homePage.checkClientSetting();
        homePage.isEnrollmentWarningChecked(warningCheckBoxSelection);
    }

    public void changeSettings()throws Exception{
        homePage.checkClientSetting();
        homePage.selectEnrollmentWarningCheckbox();
        homePage.saveSettingChanges();
    }

    public void logoutUser(){
        homePage.logoutUser();
    }

    public void clickCatalogueMenuSelection(){
        homePage.clickCatalogueMenu();
    }

    public void searchCourse(String courseName,int courseCount, int courseTemplateCount){
        catalog.setSearchText(courseName);
        catalog.clickSearchButton();
        catalog.countWarnTimingCourse(courseCount);
        catalog.countWarnTimingCourseTemplate(courseTemplateCount);
        catalog.select_LongAvailabilityCourse();
    }

    public void selectCourseAndClickEnroll(boolean isTimeIncluded,boolean needEnroll){
        catalog.selectEnrollButton();
        catalog.isCourseEnrollmentAlertPresent(isTimeIncluded);
        if(needEnroll) {
            catalog.proceedEnrolment();
        }else {
            catalog.cancelEnrolment();
        }
    }

    public void signOutUser(){
        catalog.logoutUser();
    }
}