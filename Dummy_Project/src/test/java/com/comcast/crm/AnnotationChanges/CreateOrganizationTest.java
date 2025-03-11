package com.comcast.crm.AnnotationChanges;

import java.time.Duration;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.listenerutility.ListImpClass;
import com.comcast.orm.objectrepositoryUtiity.CreatingNewOrganizationPage;
import com.comcast.orm.objectrepositoryUtiity.HomePage;
import com.comcast.orm.objectrepositoryUtiity.OrganizationInfoPage;
import com.comcast.orm.objectrepositoryUtiity.OrganizationsPage;

public class CreateOrganizationTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createOrganizationTest() throws Throwable {
		
		UtilityClassObject.getTest().log(Status.INFO, "read data from Excel");
		// read testScript data from Excel file
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// step 2 : navigate to Organization module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Org Page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step 3 : click on "create Organization" Button
		UtilityClassObject.getTest().log(Status.INFO, "create a new Org");
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// step 4 : enter all the details & create new Organization
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create Org Page ");
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO, orgName + "===> Create a new Org" );
		
		// verify Header msg Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + " name is verified == PASS");
		} else {
			System.out.println(orgName + " name is not verified == FAIL");
		}

	}

	@Test(groups = "regressionTest")
	public void createOrganizationWithIndustryTest() throws Throwable {

		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);

		cnop.createOrgs(orgName, industry, type);

		// validation industry
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actInd = oip.getIndInfo().getText();
		if (actInd.contains(industry)) {
			System.out.println(industry + " name is verified == PASS");
		} else {
			System.out.println(industry + " name is not verified == FAIL");
		}

		// validation type
		OrganizationInfoPage oip1 = new OrganizationInfoPage(driver);
		String acttype = oip1.getTypeInfo().getText();
		if (acttype.contains(type)) {
			System.out.println(type + " name is verified == PASS");
		} else {
			System.out.println(type + " name is not verified == FAIL");
		}

	}

	@Test(groups = "regressionTest")
	public void createOranizationWithPhonenumberTest() throws Throwable {

		String orgName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String phonenum = eLib.getDataFromExcel("org", 7, 3);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrgPhone(orgName, phonenum);

		// validation
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actphone = oip.getPhoneInfo().getText();
		if (actphone.trim().equalsIgnoreCase(phonenum)) {
			System.out.println(phonenum + " name is verified == PASS");
		} else {
			System.out.println(phonenum + " name is not verified == FAIL");
		}
	}

}
