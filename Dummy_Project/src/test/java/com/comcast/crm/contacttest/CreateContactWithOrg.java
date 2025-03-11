package com.comcast.crm.contacttest;

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
import com.comcast.orm.objectrepositoryUtiity.CreatingNewOrganizationPage;
import com.comcast.orm.objectrepositoryUtiity.HomePage;
import com.comcast.orm.objectrepositoryUtiity.LoginPage;
import com.comcast.orm.objectrepositoryUtiity.OrganizationInfoPage;
import com.comcast.orm.objectrepositoryUtiity.OrganizationsPage;

public class CreateContactWithOrg {

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
		
		String orgName = eLib.getDataFromExcel("contact", 7, 2)+ jLib.getRandomNumber();
		String lastname = eLib.getDataFromExcel("contact", 1, 2)+ jLib.getRandomNumber();
		
		
		
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
		
		
		
		wLib.waitForPageToLoad(driver);
		
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
		
		
		//validation
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if(actOrgName.contains(orgName)) {
			System.out.println(orgName + " name is verified == PASS");
		}else {
			System.out.println(orgName + " name is not verified == FAIL");
		}
		
		//click on  contact link
		hp.getContactlnk().click();
		
		CreateNewContactOrg cnc = new CreateNewContactOrg(driver);
		cnc.getCreatenewconatctBtn().click();
		
		cnc.getContactlnEdt().sendKeys(lastname);
		
		cnc.getPlusClck().click();
		
		//switch to child window
		wLib.switchToTabOnURL(driver, "module=Accounts");
		
		
		//identify search text field
		
		cnc.getSearchEdt().sendKeys(orgName);
		
		//identify search button
		cnc.getSearchClick().click();
		
		//after entering createorgtest then click on link and identify the path but doing dynamically
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();
		
		//control switch to parent window
		wLib.switchToTabOnURL(driver, "Contacts&action");
		
		cnop.getSaveBtn().click();
		
		//verify org message after entering and save
	    ContactInfoPage cip = new ContactInfoPage(driver);
		String actOrgverifyName = cip.getOrgNameVerify().getText();
		if(actOrgverifyName.trim().contains(orgName)) {
			System.out.println(orgName + " name is verified == PASS");
		}else {
			System.out.println(orgName + " name is not verified == FAIL");
		}
		
		//logout
		hp.logout();
		
		driver.quit();
	


	}

}
