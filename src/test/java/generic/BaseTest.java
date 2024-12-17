package generic;

import java.lang.reflect.Method;
import java.time.Duration;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest
{
	public WebDriver driver;
	public static final String CONFIG_PATH="./config.properties";
	public static final String REPORT_PATH="./target/report3.html";
	public static final String EXCEL_PATH="./data/input.xlsx";
	public WebDriverWait wait;
	public static ExtentReports extent;
	//public ExtentReports extent;
	public ExtentTest test;
	
	
	@BeforeSuite
	public void createReport()
	{
		extent=new ExtentReports();
		ExtentSparkReporter spark=new ExtentSparkReporter(REPORT_PATH);
		extent.attachReporter(spark);
	}
	
	@AfterSuite
	public void publishReport()
	{
		extent.flush();
	}

	@BeforeMethod
	public void preCondition(Method method) throws MalformedURLException 
	{
		test = extent.createTest(method.getName());
		
		String grid=Utility.getProperty(CONFIG_PATH,"GRID");
		String grid_url=Utility.getProperty(CONFIG_PATH,"GRID_URL");
		String browser=Utility.getProperty(CONFIG_PATH,"BROWSER");
		String appURL=Utility.getProperty(CONFIG_PATH,"APP_URL");
		String ito=Utility.getProperty(CONFIG_PATH,"ITO");
		String eto=Utility.getProperty(CONFIG_PATH,"ETO");
		
		if (grid.equalsIgnoreCase("yes")) 
		{
			test.info("Open the "+browser+"browser in remote system");
			if (browser.equalsIgnoreCase("chrome"))
			{
				driver=new RemoteWebDriver(new URL(grid_url),new ChromeOptions());
				
			} 
			else
			{
				driver=new RemoteWebDriver(new URL(grid_url),new EdgeOptions());
			}
		}
		else
		{
			test.info("Open the "+browser+"browser in local system");
			if (browser.equalsIgnoreCase("chrome"))
			{
				driver=new ChromeDriver();
			} 
			else
			{
				driver=new EdgeDriver();
			}
		}
				
		test.info("Enter the URL:"+appURL);
		driver.get(appURL);
		
		test.info("Maximize window");
		driver.manage().window().maximize();
		
		test.info("SET ITO:"+ito);
		int iITO=Integer.parseInt(ito);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(iITO));
		
		test.info("SET ETO:"+eto);
		int iETO=Integer.parseInt(eto);
		wait=new WebDriverWait(driver,Duration.ofSeconds(iETO));
		
	}
	
	@AfterMethod
	public void postCondition(ITestResult testResult)
	{
		String testName = testResult.getMethod().getMethodName();
		
		if (testResult.getStatus()==1)
		{
			test.pass("Test is passed");
		} 
		else
		{
			String path="./target/"+testName+".png";
		    Utility.getScreenShot(driver,"./target/"+testName+".png");
			// Utility.getScreenShot(driver,path);
		    Media m = MediaEntityBuilder.createScreenCaptureFromPath("./"+testName+".png").build();
			test.fail(m);

		}
		
		test.info("CLose the browser");
		driver.quit();
	}
}