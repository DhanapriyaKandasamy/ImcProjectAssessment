package com.imc.assessment.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.imc.assessment.testbase.BaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.util.Date;

public class ExtentManager extends BaseClass {
    private static ExtentReports extent;
    public static String screenshotName;
    public static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle(fileName);
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setReportName(fileName);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Project : ", "IMC Assessment");
        extent.setSystemInfo("Environment : ", "Local");
//        String OS = testContext.getCurrentXmlTest().getParameter("OS");
        extent.setSystemInfo("Operating System", "Windows");
//        String browser = testContext.getCurrentXmlTest().getParameter("Browser");
        extent.setSystemInfo("Browser", "Chrome");
        return extent;
    }

    public static void captureScreenshot() {
        Date d = new Date();
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        try
        {
            screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
            FileUtils.copyFile(sourceFile,new File(System.getProperty("user.dir") + "\\screenshots\\" + screenshotName));
            System.out.println("Successfully captured a screenshot");
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
        }
    }
}
