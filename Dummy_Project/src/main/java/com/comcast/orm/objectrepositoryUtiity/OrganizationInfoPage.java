package com.comcast.orm.objectrepositoryUtiity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	
    
    WebDriver driver;

    public OrganizationInfoPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
}

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerMsg;
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	@FindBy(id="dtlview_Industry")
	private WebElement indInfo;
	
	@FindBy(id="dtlview_Type")
	private WebElement typeInfo;

	public WebElement getIndInfo() {
		return indInfo;
	}

	public WebElement getTypeInfo() {
		return typeInfo;
		
	}
	@FindBy(id="dtlview_Phone")
	private WebElement phoneInfo;

	public WebElement getPhoneInfo() {
		return phoneInfo;
	}

	

}
