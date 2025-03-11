package practice.testing;

import org.testng.annotations.Test;

public class DependsOnTest {

	
	@Test()
	public void createContactTest() {
		
		System.out.println("Execute createContactTest");
	}
	
	@Test(dependsOnMethods = "createContactTest")
	public void modifyContactTest() {
		
		System.out.println("Execute modifyContactTest");
	}
	
	@Test(dependsOnMethods = "modifyContactTest")
	public void deleteContactTest() {
		
		System.out.println("Execute deleteContactTest");
	}
}
