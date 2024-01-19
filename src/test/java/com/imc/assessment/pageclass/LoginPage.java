package com.imc.assessment.pageclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "externalForm:login")
    WebElement txt_Login;

    @FindBy(id = "externalForm:password")
    WebElement txt_password;

    @FindBy(xpath = "//button/span[text() = 'Login']")
    WebElement btn_Login;

    public void setUserName(String userName){
        txt_Login.sendKeys(userName);
        logger.info("Logged with :"+userName);
    }

    public void setPassword(String password){
        txt_password.sendKeys(password);
    }
    public void clickLogin(){
        btn_Login.click();
        logger.info("User Login successfull!");
    }

    public void userLogin(String userName,String password){
        setUserName(userName);
        setPassword(password);
        clickLogin();
    }

}
