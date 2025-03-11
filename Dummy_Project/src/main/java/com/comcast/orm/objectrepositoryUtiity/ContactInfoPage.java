package com.comcast.orm.objectrepositoryUtiity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	
public WebElement getOrgNameVerify() {
		return orgNameVerify;
	}

WebDriver driver;
	
	public ContactInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement contactheaderMsg;

	public WebElement getContactheaderMsg() {
		return contactheaderMsg;
	}
	@FindBy(id="mouseArea_Support Start Date")
	private WebElement startverify;
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement orgNameVerify;
	
	@FindBy(id="mouseArea_Support End Date")
	private WebElement endverify;
	
	@FindBy(id="dtlview_Last Name_")
	private WebElement lastverify;

	public WebElement getLastverify() {
		return lastverify;
	}

	public WebElement getStartverify() {
		return startverify;
	}

	public WebElement getEndverify() {
		return endverify;
	}
	

}
