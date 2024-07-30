package scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import page.HomePage;
import page.LoginPage;

public class ValifLogIn extends BaseTest
{
	@Test(priority = 1)
	public void testValidLogin() 
	{
		//1.enter the valid user-name
		LoginPage loginPage=new LoginPage(driver);
		loginPage.setUserName("admin");
		//2.enter the valid password
		loginPage.setPassword("pointofsale");
		
		//3. click on login button
		loginPage.clickGoButton();
		//4.very home page is displayed
		HomePage homePage=new HomePage(driver);
		boolean result=homePage.verifyHomePageIsDisplayed(wait);
		Assert.assertTrue(result);
	}
}