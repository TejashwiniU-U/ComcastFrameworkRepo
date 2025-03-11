package com.comcast.crm.contacttest;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.orm.objectrepositoryUtiity.ContactInfoPage;
import com.comcast.orm.objectrepositoryUtiity.CreateNewContactOrg;
import com.comcast.orm.objectrepositoryUtiity.HomePage;
import com.comcast.orm.objectrepositoryUtiity.LoginPage;

public class CreateContactOrganizationTest {

	public static void main(String[] args) throws Throwable {
		
		/* Create Object*/
		 FileUtility fLib = new FileUtility();
	     ExcelUtility eLib = new ExcelUtility();
	     JavaUtility jLib = new JavaUtility();
	     WebDriverUtility wLib = new WebDriverUtility();
	     
	        
			String URL = fLib.getDataFromPropertiesFile("url");
			String BROWSER = fLib.getDataFromPropertiesFile("browser");
			String USERNAME = fLib.getDataFromPropertiesFile("username");
			String PASSWORD = fLib.getDataFromPropertiesFile("password");
			
			
			
			
			
			
			String lastname = eLib.getDataFromExcel("contact",1,2) + jLib.getRandomNumber();
			
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
			hp.getContactlnk().click();
			
			CreateNewContactOrg cnc = new CreateNewContactOrg(driver);
			cnc.getCreatenewconatctBtn().click();
			
			cnc.createContact(lastname);
			
			
			
			
			//validation
			ContactInfoPage cip = new ContactInfoPage(driver);
			String actlastname = cip.getContactheaderMsg().getText();
			if(actlastname.contains(lastname)) {
				System.out.println(lastname + " name is verified == PASS");
			}else {
				System.out.println(lastname + " name is not verified == FAIL");
			}
			
			//logout
			hp.logout();
			
			driver.quit();
		

	}

}
