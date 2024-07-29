package generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility 
{
	public static String getExcelData(String path,String sheetname,int row,int col) 
	{
		String value="";
		try 
		{
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			value=wb.getSheet(sheetname).getRow(row).getCell(col).getStringCellValue();
			System.out.println(value);
		}
		catch (IOException e) 
		{
		
			e.printStackTrace();
		}
		return value;
	}
	public static void getScreenShot(WebDriver driver,String path) 
	{
		TakesScreenshot t=(TakesScreenshot)driver;
		File srcFile = t.getScreenshotAs(OutputType.FILE);
		File dstFIle=new File(path);
		try {
			FileUtils.copyFile(srcFile, dstFIle);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String path,String Key) 
	{
		Properties p=new Properties();
		try 
		{
			p.load(new FileInputStream(path));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = p.getProperty(Key);
		return value;
	}
}






