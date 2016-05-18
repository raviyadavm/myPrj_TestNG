package myPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class loanCalc {
	static WebDriver driver;
	String strURL = "http://www.mortgagecalculator.org/";
	LoanPage objLoan;
	
	@Test
	@Parameters({"name1", "name2"})
	public void mainclass(String name11, String name22) throws InterruptedException, IOException {
		XSSFWorkbook xlWb;
		XSSFSheet xlSh;
		XSSFCell xlCell;
		XSSFRow xlRow;
		// declaring and initializing variables
		Double strHome, strLoan, strInt;
		String strEMI = null;
		FileInputStream xlInFile;
		FileOutputStream xlOutFile;
		try {
			
			xlInFile = new FileInputStream("C:\\test\\ravi.xlsx");
			xlWb = new XSSFWorkbook(xlInFile);
			xlSh = xlWb.getSheet("Sheet1");
		
			int xlRowCnt = xlSh.getPhysicalNumberOfRows();
			System.out.println("No.of rows in input file are " + xlRowCnt);
			for(int i=1; i<xlRowCnt; i++) {
				xlRow = xlSh.getRow(i);
				//	xlRow.getPhysicalNumberOfCells();
			//	strHome = xlRow.getCell(0).getStringCellValue();
				strHome = xlRow.getCell(0).getNumericCellValue();
				strLoan = xlRow.getCell(1).getNumericCellValue();
				strInt = xlRow.getCell(2).getNumericCellValue();
		
				System.out.println("Hello TestNG " + name11 + " " + name22);
				
				System.out.println("Home Value is : " + strHome);
				System.out.println("Load Value is : " + strLoan);
				System.out.println("Interest Value is : " + strInt);
				
				driver = new FirefoxDriver();
				driver.get(strURL);
				driver.manage().window().maximize();
				
				System.out.println("Entering loan details");
				objLoan = new LoanPage(driver);
				objLoan.EnterLoanDetails(strHome, strLoan, strInt);
				objLoan.clickCalc();
				Thread.sleep(1000);
				strEMI = objLoan.getEMI();
				System.out.println("Monthly Payment should be " + strEMI);
				driver.close();
				xlCell = xlRow.createCell(3);
				xlCell.setCellValue(strEMI);
						
				System.out.println("...End of Script...");
			} // end of for loop
			
			xlOutFile = new FileOutputStream("C:\\test\\ravi.xlsx");
			
			xlWb.write(xlOutFile);
			xlOutFile.close();
			
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
	}	// end of mainclass()
} // end of class()