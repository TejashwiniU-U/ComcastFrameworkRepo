package homeworkTestCases;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.orm.objectrepositoryUtiity.HomePage;
import com.comcast.orm.objectrepositoryUtiity.LoginPage;
import com.comcast.orm.objectrepositoryUtiity.TrubleticketPage;

public class testcase_4 {

	public static void main(String[] args) throws Throwable {
		
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();	
        
        
		String URL = fLib.getDataFromPropertiesFile("url");
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		
		String  ticketName = eLib.getDataFromExcel("Troubleticket", 4,2) + jLib.getRandomNumber();
		String  priority = eLib.getDataFromExcel("Troubleticket", 4,3);
		
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
		hp.getTroubletcktlnk().click();
		
		TrubleticketPage ttp = new TrubleticketPage(driver);
		ttp.trobletckt(ticketName);
		
		ttp.trobletckt(ticketName, priority);
		
		ttp.getSaveBtn().click();
		
		//verification
		String actticketprioName = ttp.getTicketprioverify().getText();
		if(actticketprioName.contains(priority)) {
			System.out.println(priority + " name is verified == PASS");
		}else {
			System.out.println(priority + " name is not verified == FAIL");
		}
		
		//logout
		hp.logout();
		
		driver.quit();

		

	}

}
