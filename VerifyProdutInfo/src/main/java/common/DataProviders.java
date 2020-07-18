package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataProviders{
	public Object[][] GetInput(String SheetName) throws FileNotFoundException ,IOException
	{
		File src = new File("./src/Ebay-Input.xlsx");
		FileInputStream fis = new FileInputStream(src);
	    XSSFWorkbook workbook = new XSSFWorkbook(fis); //get my workbook
	    XSSFSheet  sheet=workbook.getSheet(SheetName);// get my sheet from workbook
	    int Row=sheet.getLastRowNum()+1;
	    int ColNum= sheet.getRow(0).getLastCellNum(); // get last ColNum
	    Object inputdata[][]= new Object[Row-1][ColNum]; // pass my count data in array
	    for (int i=1; i<Row; i++) {
	  	        XSSFRow row = sheet.getRow(i);
	        for (int j=0; j<ColNum; j++) {
	             XSSFCell cell = row.getCell(j);
	                String value = String.valueOf(cell);    
	                inputdata[i-1][j] = value;
	                 	         
	    
	        }}
	   
	return inputdata;
	    }



	@DataProvider(name ="login")
	protected Object[][] Login() throws FileNotFoundException, IOException {
	    return GetInput("Login");
	}
	







}
