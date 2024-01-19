package com.imc.assessment.utilities;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name="LoginData")
    public Object [][] getData(Method methodName) throws IOException
    {
        String path=".\\testData\\IMC_LoginData.xlsx";
        ExcelUtilities excel=new ExcelUtilities(path);
        int noOfRows=excel.getRowCount("Sheet1");
        int noOfCols=excel.getCellCount("Sheet1",1);
        Object[][] loginData = new Object[1][noOfCols-1];
        for(int i=1;i<=noOfRows;i++)
        {
            if(excel.getCellData("Sheet1",i,0).equals(methodName.getName())) {
                for (int j = 1; j < noOfCols; j++) {
                    loginData[0][j-1] = excel.getCellData("Sheet1", i, j);  //1,0
                }
            }
        }
        return loginData;
    }
}

