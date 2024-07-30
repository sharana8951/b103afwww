
package scripts;

import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;

public class TC4 extends BaseTest
{
	@Test
	public void test1() 
	{
		String data = Utility.getExcelData(EXCEL_PATH,"TC1", 1, 0);
		test.info("Excel data:"+data);
		test.info(driver.getTitle());
		//Assert.fail();
	}
}
