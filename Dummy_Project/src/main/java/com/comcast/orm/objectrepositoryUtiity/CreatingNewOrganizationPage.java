package com.comcast.orm.objectrepositoryUtiity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {
	
WebDriver driver;
	
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "accountname")
	private WebElement orgNameEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name = "industry")
	private WebElement industryDB;
	
	@FindBy(name = "accounttype")
	private WebElement typeDB;
	
	@FindBy(id="phone")
	private WebElement phoneDB;
	
	public WebElement getPhoneDB() {
		return phoneDB;
	}


	public WebElement getIndustryDB() {
		return industryDB;
	}


	public WebElement getTypeDB() {
		return typeDB;
	}


	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}
	
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createOrg(String orgName) {
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void createOrg(String orgName , String industry) {
		orgNameEdt.sendKeys(orgName);
		Select sel = new Select(industryDB);
		sel.selectByVisibleText(industry);
		saveBtn.click();
	}
	
	public void createOrgs(String orgName , String industry , String type) {
		orgNameEdt.sendKeys(orgName);
		Select sel = new Select(industryDB);
		Select sel1 = new Select(typeDB);
		sel.selectByVisibleText(industry);
		sel1.selectByVisibleText(type);
		saveBtn.click();
	}
	
	public void createOrgPhone(String orgName , String phonenum) {
		orgNameEdt.sendKeys(orgName);
		phoneDB.sendKeys(phonenum);
		saveBtn.click();
	}
	
	
	
	

}
