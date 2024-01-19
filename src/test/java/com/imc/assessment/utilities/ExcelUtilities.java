package com.imc.assessment.utilities;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {


    public FileInputStream fileInput;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    String path;

    public ExcelUtilities(String path)
    {
        this.path=path;
    }

    public int getRowCount(String sheetName) throws IOException
    {
        fileInput=new FileInputStream(path);
        workbook=new XSSFWorkbook(fileInput);
        sheet=workbook.getSheet(sheetName);
        int rowcount=sheet.getLastRowNum();
        workbook.close();
        fileInput.close();
        return rowcount;
    }

    public int getCellCount(String sheetName,int rowNumber) throws IOException
    {
        fileInput=new FileInputStream(path);
        workbook=new XSSFWorkbook(fileInput);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rowNumber);
        int cellCount=row.getLastCellNum();
        workbook.close();
        fileInput.close();
        return cellCount;
    }


    public String getCellData(String sheetName,int rowNumber,int columnNumber) throws IOException
    {
        fileInput=new FileInputStream(path);
        workbook=new XSSFWorkbook(fileInput);
        sheet=workbook.getSheet(sheetName);
        row=sheet.getRow(rowNumber);
        cell=row.getCell(columnNumber);

        DataFormatter formatter = new DataFormatter();
        String data;
        try{
            data = formatter.formatCellValue(cell);
        }
        catch(Exception e)
        {
            data="";
        }
        workbook.close();
        fileInput.close();
        return data;
    }
}



