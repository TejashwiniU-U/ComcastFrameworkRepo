package Listener;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryAnalyzerClass {
	
	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImp.class)
	public void activateSim() {
		
		System.out.println("execute activateSim");
		
		Assert.assertEquals("", "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
		
	}

}
