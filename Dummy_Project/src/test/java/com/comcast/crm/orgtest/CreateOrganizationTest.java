package com.comcast.crm.orgtest;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.orm.objectrepositoryUtiity.CreatingNewOrganizationPage;
import com.comcast.orm.objectrepositoryUtiity.HomePage;
import com.comcast.orm.objectrepositoryUtiity.LoginPage;
import com.comcast.orm.objectrepositoryUtiity.OrganizationInfoPage;
import com.comcast.orm.objectrepositoryUtiity.OrganizationsPage;

import org.openqa.selenium.edge.EdgeDriver;


public class CreateOrganizationTest {

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
		
		//Step 1 : login to app
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		LoginPage lp = new LoginPage(driver);
		
		lp.loginToapp(URL, USERNAME, PASSWORD);
		
		//step 2 : navigate to Organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		
		
		//step 3 : click on "create Organization" Button
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();
		
		//step 4 : enter all the details & create new Organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		//verify Header msg Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if(actOrgName.contains(orgName)) {
			System.out.println(orgName + " name is verified == PASS");
		}else {
			System.out.println(orgName + " name is not verified == FAIL");
		}
		
		//Step 5 : logout
		hp.logout();
		
		driver.quit();
	}

}
