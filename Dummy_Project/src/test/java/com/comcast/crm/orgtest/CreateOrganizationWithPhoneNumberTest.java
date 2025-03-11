package com.comcast.crm.orgtest;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.orm.objectrepositoryUtiity.CreatingNewOrganizationPage;
import com.comcast.orm.objectrepositoryUtiity.HomePage;
import com.comcast.orm.objectrepositoryUtiity.LoginPage;
import com.comcast.orm.objectrepositoryUtiity.OrganizationInfoPage;
import com.comcast.orm.objectrepositoryUtiity.OrganizationsPage;

public class CreateOrganizationWithPhoneNumberTest {

	public static void main(String[] args) throws Throwable {
		
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
        WebDriverUtility wLib = new WebDriverUtility();
        
        
		String URL = fLib.getDataFromPropertiesFile("url");
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		
		
		
		
		
		String orgName = eLib.getDataFromExcel("org", 7,2) + jLib.getRandomNumber();
		System.out.println(orgName);
		 String phonenum = eLib.getDataFromExcel("org", 7,3);
		
		
		
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
			
			
			cnop.createOrgPhone(orgName, phonenum);
		
		
		//validation 
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actphone = oip.getPhoneInfo().getText();
		if(actphone.trim().equalsIgnoreCase(phonenum)) {
			System.out.println(phonenum + " name is verified == PASS");
		}else {
			System.out.println(phonenum + " name is not verified == FAIL");
		}
		
		//logout
		hp.logout();
		
		driver.quit();

	}

}
