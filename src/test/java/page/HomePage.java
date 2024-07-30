package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class HomePage
{
	@FindBy(xpath="//a[text()='Logout']")
	private WebElement LogoutBTN;
	public HomePage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyHomePageIsDisplayed(WebDriverWait wait) 
	{
		try 
		{
			wait.until(ExpectedConditions.visibilityOf(LogoutBTN));
			Reporter.log("HomePage is displayed",true);
			return true;
			
		} 
		catch (Exception e)
		{
			Reporter.log(e.getMessage(),true);
			Reporter.log("HomePage is NOT displayed",true);
			return false;
		}
		
	}
}
