package com.imc.assessment.testbase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseClass {

    static public WebDriver driver;
    static public String baseURL = "https://14-17-3-masterdb.imc-ms-deployment.imc-cs.com/ilp/pages/external-dashboard.jsf?menuId=1104&locale=en-GB#/?dashboardId=6";
    public Logger logger;

    @BeforeClass
    @Parameters({"OS","Browser"})
    public void setup(@Optional("windows") String OS,@Optional("chrome") String browser){

        logger = LogManager.getLogger(this.getClass());
        switch (browser.toLowerCase()){
            case "chrome" :  driver = new ChromeDriver();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                logger.info("Chrome browser launched successfully");
                break;
            case "edge" :  driver = new EdgeDriver();
                logger.info("Edge browser launched successfully");
                break;
            case "firefox" :  driver = new FirefoxDriver();
                logger.info("FireFox browser launched successfully");
                break;
            case "ie" : driver = new InternetExplorerDriver();
                logger.info("IE browser launched successfully");
                break;
            case "safari" : driver = new SafariDriver();
                logger.info("Safari browser launched successfully");
                break;
            default:
                logger.info("Browser value not matched");
                return;
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(baseURL);
    }

    @AfterClass
    public void teardown(){
        driver.close();
    }
}
