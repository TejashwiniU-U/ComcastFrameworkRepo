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
import com.comcast.orm.objectrepositoryUtiity.NewProductsPage;

public class testcase_2 {

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
		
		//read testScript data from Excel file
		String proName = eLib.getDataFromExcel("product", 4,2) + jLib.getRandomNumber();
		String category = eLib.getDataFromExcel("product", 4,3);
		
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
		hp.getProductlnk().click();
		
		NewProductsPage npp = new NewProductsPage(driver);
		npp.createproduct(proName);
		
		npp.createproduct(proName, category);
		
		npp.getSaveBtn().click();
		
		
		
		//validation
		String actcategName = npp.getProductheadermsg().getText();
		if(actcategName.contains(category)) {
			System.out.println(category + " name is verified == PASS");
		}else {
			System.out.println(category + " name is not verified == FAIL");
		}
		
		
		
		//logout
		hp.logout();
		
		driver.quit();

	}

}
