package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.orm.objectrepositoryUtiity.CreatingNewOrganizationPage;
import com.comcast.orm.objectrepositoryUtiity.HomePage;
import com.comcast.orm.objectrepositoryUtiity.LoginPage;
import com.comcast.orm.objectrepositoryUtiity.OrganizationInfoPage;
import com.comcast.orm.objectrepositoryUtiity.OrganizationsPage;

public class CreateOrganizationWithIndustryTest {

	public static void main(String[] args) throws Throwable {
		
//read common data from JSONfile
		
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
        WebDriverUtility wLib = new WebDriverUtility();
        
        
        
		String URL = fLib.getDataFromPropertiesFile("url");
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		
		//generate the random Number
		
		
		
		//read testScript data from Excel file
		
		String orgName = eLib.getDataFromExcel("org", 1,2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("org", 4,3);
		String type = eLib.getDataFromExcel("org", 4,4);
		
		
		WebDriver driver = null;
		
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
			
		}else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if(BROWSER.equals("edge")){
			driver = new EdgeDriver();
		}else {
			driver = new ChromeDriver();
		}
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
        LoginPage lp = new LoginPage(driver);
		
		lp.loginToapp(URL, USERNAME, PASSWORD);
		
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();
		
		
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		
		
		cnop.createOrgs(orgName, industry, type);
		
		//validation industry
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actInd = oip.getIndInfo().getText();
		if(actInd.contains(industry)) {
			System.out.println(industry + " name is verified == PASS");
		}else {
			System.out.println(industry + " name is not verified == FAIL");
		}
		
		//validation type
		OrganizationInfoPage oip1 = new OrganizationInfoPage(driver);
		String acttype = oip1.getTypeInfo().getText();
		if(acttype.contains(type)) {
			System.out.println(type + " name is verified == PASS");
		}else {
			System.out.println(type + " name is not verified == FAIL");
		}
		
		
		
		//logout
		hp.logout();
		
		driver.quit();
	}


	}


