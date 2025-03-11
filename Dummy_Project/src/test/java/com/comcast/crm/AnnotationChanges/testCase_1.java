package com.comcast.crm.AnnotationChanges;

import java.time.Duration;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.orm.objectrepositoryUtiity.HomePage;
import com.comcast.orm.objectrepositoryUtiity.NewProductsPage;
import com.comcast.orm.objectrepositoryUtiity.TrubleticketPage;

public class testCase_1 extends BaseClass {

	@Test
	public void testcase_1Test() throws Throwable {
		// read testScript data from Excel file
		String proName = eLib.getDataFromExcel("product", 1, 2) + jLib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getProductlnk().click();

		NewProductsPage npp = new NewProductsPage(driver);
		npp.createproduct(proName);

		npp.getSaveBtn().click();

		// validation
		String actproName = npp.getProheaderverify().getText();
		if (actproName.contains(proName)) {
			System.out.println(proName + " name is verified == PASS");
		} else {
			System.out.println(proName + " name is not verified == FAIL");
		}

		// validation 2
		String actproeditName = npp.getProheaderverify().getText();
		if (actproeditName.contains(proName)) {
			System.out.println(proName + " name is verified == PASS");
		} else {
			System.out.println(proName + " name is not verified == FAIL");
		}
	}

	@Test
	public void test_2Test() throws Throwable {
		String proName = eLib.getDataFromExcel("product", 4, 2) + jLib.getRandomNumber();
		String category = eLib.getDataFromExcel("product", 4, 3);

		HomePage hp = new HomePage(driver);
		hp.getProductlnk().click();

		NewProductsPage npp = new NewProductsPage(driver);
		npp.createproduct(proName);

		npp.createproduct(proName, category);

		npp.getSaveBtn().click();

		// validation
		String actcategName = npp.getProductheadermsg().getText();
		if (actcategName.contains(category)) {
			System.out.println(category + " name is verified == PASS");
		} else {
			System.out.println(category + " name is not verified == FAIL");
		}

	}

	@Test
	public void testcase_3Test() throws Throwable {
		String ticketName = eLib.getDataFromExcel("Troubleticket", 1, 2) + jLib.getRandomNumber();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		HomePage hp = new HomePage(driver);
		hp.getTroubletcktlnk().click();

		TrubleticketPage ttp = new TrubleticketPage(driver);
		ttp.trobletckt(ticketName);

		ttp.getSaveBtn().click();

		// validation
		String actticketName = ttp.getTroTicketheadermsg().getText();
		if (actticketName.contains(ticketName)) {
			System.out.println(ticketName + " name is verified == PASS");
		} else {
			System.out.println(ticketName + " name is not verified == FAIL");
		}

		String acttickettitleName = ttp.getTroTicketverify().getText();
		if (acttickettitleName.contains(ticketName)) {
			System.out.println(ticketName + " name is verified == PASS");
		} else {
			System.out.println(ticketName + " name is not verified == FAIL");
		}
	}

	@Test
	public void testcase_4() throws Throwable {
		String ticketName = eLib.getDataFromExcel("Troubleticket", 4, 2) + jLib.getRandomNumber();
		String priority = eLib.getDataFromExcel("Troubleticket", 4, 3);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		HomePage hp = new HomePage(driver);
		hp.getTroubletcktlnk().click();

		TrubleticketPage ttp = new TrubleticketPage(driver);
		ttp.trobletckt(ticketName);

		ttp.trobletckt(ticketName, priority);

		ttp.getSaveBtn().click();

		// verification
		String actticketprioName = ttp.getTicketprioverify().getText();
		if (actticketprioName.contains(priority)) {
			System.out.println(priority + " name is verified == PASS");
		} else {
			System.out.println(priority + " name is not verified == FAIL");
		}
	}

}
