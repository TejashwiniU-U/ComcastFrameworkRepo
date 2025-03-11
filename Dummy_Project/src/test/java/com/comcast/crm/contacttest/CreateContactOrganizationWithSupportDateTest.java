package com.comcast.crm.contacttest;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.orm.objectrepositoryUtiity.ContactInfoPage;
import com.comcast.orm.objectrepositoryUtiity.CreateNewContactOrg;
import com.comcast.orm.objectrepositoryUtiity.HomePage;
import com.comcast.orm.objectrepositoryUtiity.LoginPage;

public class CreateContactOrganizationWithSupportDateTest {

	public static void main(String[] args) throws Throwable {
		
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
	    WebDriverUtility wLib = new WebDriverUtility();   
	        
	        
			String URL = fLib.getDataFromPropertiesFile("url");
			String BROWSER = fLib.getDataFromPropertiesFile("browser");
			String USERNAME = fLib.getDataFromPropertiesFile("username");
			String PASSWORD = fLib.getDataFromPropertiesFile("password");
			
			
			
			
			
			//read testScript data from Excel file
			String lastname = eLib.getDataFromExcel("contact", 1,2) + jLib.getRandomNumber();
			
			//date
			String startDate =  jLib.getSystemDateYYYYDDMM();
			String endDate = jLib.getRequiredDateYYYYDDMM(30);
			
			
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
			
			//login to app
            LoginPage lp = new LoginPage(driver);
		    lp.loginToapp(URL, USERNAME, PASSWORD);
			
		    //click on contact link
			HomePage hp = new HomePage(driver);
			hp.getContactlnk().click();
			
			//click on plus button
			CreateNewContactOrg cnc = new CreateNewContactOrg(driver);
			cnc.getCreatenewcontactBtn().click();
			
			
			//give start date and end date
			cnc.enterStartDate(startDate);
			cnc.enterEndDate(endDate);
			cnc.createContact(lastname);
			
			//verify start date and end date after creation of contact page
			ContactInfoPage cip = new ContactInfoPage(driver);
			String actstartDate = cip.getStartverify().getText();
			if(actstartDate.trim().contains(startDate)) {
				System.out.println(startDate + " name is verified == PASS");
			}else {
				System.out.println(startDate + " name is not verified == FAIL");
			}
			
			
			String actendDate = cip.getEndverify().getText();
			if(actendDate.trim().contains(endDate)) {
				System.out.println(endDate + " name is verified == PASS");
			}else {
				System.out.println(endDate + " name is not verified == FAIL");
			}
			
			//logout
			//hp.logout();
			
			//driver.quit();

	}

}
