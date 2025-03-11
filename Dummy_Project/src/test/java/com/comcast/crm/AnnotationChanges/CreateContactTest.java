package com.comcast.crm.AnnotationChanges;

import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.orm.objectrepositoryUtiity.ContactInfoPage;
import com.comcast.orm.objectrepositoryUtiity.CreateNewContactOrg;
import com.comcast.orm.objectrepositoryUtiity.CreatingNewOrganizationPage;
import com.comcast.orm.objectrepositoryUtiity.HomePage;
import com.comcast.orm.objectrepositoryUtiity.OrganizationInfoPage;
import com.comcast.orm.objectrepositoryUtiity.OrganizationsPage;

public class CreateContactTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createContactTest() throws Throwable {

		String lastname = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		HomePage hp = new HomePage(driver);
		hp.getContactlnk().click();

		CreateNewContactOrg cnc = new CreateNewContactOrg(driver);
		cnc.getCreatenewconatctBtn().click();

		cnc.createContact(lastname);

		// validation
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actheader = cip.getContactheaderMsg().getText();
		
		boolean status = actheader.contains(lastname);
		Assert.assertEquals(status, true);
		
		String actlastname = cip.getLastverify().getText();
		Assert.assertEquals(actlastname, lastname);
	}
	
	@Test(groups = "regressionTest")
	public void createContctWithSupportDateTest() throws Throwable {
		
			//read testScript data from Excel file
			String lastname = eLib.getDataFromExcel("contact", 1,2) + jLib.getRandomNumber();
			
			//date
			String startDate =  jLib.getSystemDateYYYYDDMM();
			String endDate = jLib.getRequiredDateYYYYDDMM(30);
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	
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
	}
	
	@Test(groups = "regressionTest")
	public void createContctWithOrgTest() throws Throwable {
	
		//read testScript data from Excel file
		
		String orgName = eLib.getDataFromExcel("contact", 7, 2)+ jLib.getRandomNumber();
		String lastname = eLib.getDataFromExcel("contact", 1, 2)+ jLib.getRandomNumber();
		
		wLib.waitForPageToLoad(driver);
		
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
		
		
	}

	}


























