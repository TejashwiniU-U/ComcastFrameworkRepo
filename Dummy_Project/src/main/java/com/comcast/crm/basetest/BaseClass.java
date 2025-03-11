package com.comcast.crm.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.orm.objectrepositoryUtiity.HomePage;
import com.comcast.orm.objectrepositoryUtiity.LoginPage;

public class BaseClass {

	public DatabaseUtility dbLib = new DatabaseUtility();
	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;
	
	

	@BeforeSuite(groups = {"smokeTest", "regressionTest"})
	public void configBS() {
		System.out.println("==Connect to DB , Report Config==");
		dbLib.getDbconnection();
		
		
				
	}

	@Parameters ("BROWSER")
	@BeforeClass(groups = {"smokeTest", "regressionTest"})
	public void configBC() throws Throwable {
		System.out.println("==Launch the BROWSER==");
		String BROWSER = fLib.getDataFromPropertiesFile("browser");

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();

		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		sdriver = driver;
		UtilityClassObject.setDriver(driver);
	}

	@BeforeMethod(groups = {"smokeTest", "regressionTest"})
	public void configBM() throws Throwable {
		System.out.println("==login==");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.loginToapp(URL, USERNAME, PASSWORD);
	}

	@AfterMethod(groups = {"smokeTest", "regressionTest"})
	public void configAM() {
		System.out.println("==logout==");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}

	@AfterClass(groups = {"smokeTest", "regressionTest"})
	public void configAC() {
		System.out.println("==Close the BROWSER==");
		driver.quit();
	}

	@AfterSuite(groups = {"smokeTest", "regressionTest"})
	public void configAS() {
		System.out.println("==cLOSE DB , Report backUP==");
	}
}
