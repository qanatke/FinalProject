package tests;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;


public abstract class BasicTest {

	protected WebDriver driver;
	protected WebDriverWait waiter;
	protected JavascriptExecutor js;
	protected String mail = "customer@dummyid.com";
	protected String pass = "12345678a";
	protected String locationName = "City Center - Albany";
	protected String baseUrl = "http://demo.yo-meals.com/";
	protected String path2file = "images/slika.jpg";


	@BeforeClass
	public void beforeClass () {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		this.driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		this.js = (JavascriptExecutor) this.driver;
	}
	
	
	
	
	@AfterMethod
	public void afterTest(ITestResult result) throws Exception {
		this.driver.manage().deleteAllCookies();
		
		if (result.getStatus() == ITestResult.FAILURE) {
			File ss = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
			String fileName = new SimpleDateFormat("yyyyMMddHHmmss'.png'").format(new Date());
			File save = new File("screenshots/" + fileName);
			FileHandler.copy(ss, save);
		}

	}
	
	@AfterClass
	public void afterClass () {
//		this.driver.quit();
	}

}
