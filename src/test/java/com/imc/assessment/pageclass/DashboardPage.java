package com.imc.assessment.pageclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage{

    public DashboardPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//button[@title = 'Start search']")
    WebElement btn_Search;

    public void clickSearchButton(){
        btn_Search.click();
        logger.info("Step 2 : Clicked on search button");
    }

}
