package homeworkTestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.orm.objectrepositoryUtiity.HomePage;
import com.comcast.orm.objectrepositoryUtiity.LoginPage;
import com.comcast.orm.objectrepositoryUtiity.TrubleticketPage;

public class testcase_3 {

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
			
			String  ticketName = eLib.getDataFromExcel("Troubleticket", 1,2) + jLib.getRandomNumber();
			
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
			lp.loginToapp(URL,USERNAME, PASSWORD);
			
			HomePage hp = new HomePage(driver);
			hp.getTroubletcktlnk().click();
			
			TrubleticketPage ttp = new TrubleticketPage(driver);
			ttp.trobletckt(ticketName);
			
			ttp.getSaveBtn().click();
			
			//validation
			String actticketName = ttp.getTroTicketheadermsg().getText();
			if(actticketName.contains(ticketName)) {
				System.out.println(ticketName + " name is verified == PASS");
			}else {
				System.out.println(ticketName + " name is not verified == FAIL");
			}
			
			
			String acttickettitleName = ttp.getTroTicketverify().getText();
			if(acttickettitleName.contains(ticketName)) {
				System.out.println(ticketName + " name is verified == PASS");
			}else {
				System.out.println(ticketName + " name is not verified == FAIL");
			}
			
			
			
			//logout
			hp.logout();
			
			driver.quit();


	}

}
