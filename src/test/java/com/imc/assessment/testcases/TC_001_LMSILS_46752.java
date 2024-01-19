package com.imc.assessment.testcases;

import com.imc.assessment.pageclass.CatalogSearch;
import com.imc.assessment.pageclass.DashboardPage;
import com.imc.assessment.pageclass.LoginPage;
import com.imc.assessment.testbase.BaseClass;
import com.imc.assessment.utilities.DataProviders;
import org.testng.annotations.Test;

public class TC_001_LMSILS_46752 extends BaseClass {
    LoginPage login;
    DashboardPage dashboard;
    CatalogSearch catalog;

    @Test(dataProvider="LoginData",dataProviderClass= DataProviders.class)
    public void verify_DashboardPage(String userName, String password){
        String navigationURL = "https://14-17-3-masterdb.imc-ms-deployment.imc-cs.com/ilp/pages/internal-dashboard.jsf?menuId=1107&locale=en-GB#/?dashboardId=927747";
        logger.info("****Starting TestCase TC1_LMSILS_46752");

        logger.info("Step 1 : Login with DVuser05 user");
        login = new LoginPage(driver);
        login.userLogin(userName,password);

        logger.info("Step 2 : Navigate to Dashboard ID : 927747");
        driver.navigate().to(navigationURL);
        logger.info("Navigation successful");

        logger.info("Step 3 : In the Dashboard page click search and check for content loading");
        dashboard = new DashboardPage(driver);
        catalog = new CatalogSearch(driver);
        dashboard.clickSearchButton();
        catalog.isHeaderPresent();

        logger.info("Step 4 : Navigate back to Dashboard page");
        driver.navigate().back();
        logger.info("Navigation successful");

        logger.info("Step 5 : Click Language skills and check filter is applied");
        catalog.clickLanguageSkillsLink();
        catalog.isResultLoadedWithFilter(1);

        logger.info("Step 6 : Remove Language skills link and check filter is applied");
        catalog.clickRemoveLanguageSkillFilterOption();
        catalog.isResultLoadedWithoutFilter();

        logger.info("Step 7 : Navigate back to Dashboard page");
        driver.navigate().back();
        logger.info("Navigation successful");

        logger.info("Step 8 : Click MSOffice link and check filter is applied");
        catalog.clickMSOfficeLink();
        catalog.isResultLoadedWithMSOfficeFilter();

        logger.info("Step 9 : Scroll down and click Show more results ");
        catalog.clickShowMoreResult();

        logger.info("Step 10 : User logout");
        catalog.logoutUser();
    }
}
