package scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Utility;
import page.LoginPage;

public class InvalidLogin extends BaseTest
{
	@Test(priority = 2)
	public void testInvalidLogin() 
	{
		String un=Utility.getExcelData(EXCEL_PATH, "InvalidLogin", 1, 0);
		String pw=Utility.getExcelData(EXCEL_PATH, "InvalidLogin", 1, 1);
		LoginPage loginpage=new LoginPage(driver);
		loginpage.setUserName(un);
		loginpage.setPassword(pw);
		loginpage.clickGoButton();
		boolean result = loginpage.verifyErrMsgIsDisplayed(wait);
		Assert.assertTrue(result);
	}
}
