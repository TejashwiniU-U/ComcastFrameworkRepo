package com.comcast.orm.objectrepositoryUtiity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactOrg {
	
WebDriver driver;
	
	public CreateNewContactOrg(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath="//img[@alt='Create Contact...']")
	private WebElement CreatenewcontactBtn;

	public WebElement getCreatenewconatctBtn() {
		return CreatenewcontactBtn;
	}
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	@FindBy(id="jscal_field_support_start_date")
	private WebElement StartDateEdt;
	
	@FindBy(id="jscal_field_support_end_date")
	private WebElement EndDateEdt;
	
	public WebElement getCreatenewcontactBtn() {
		return CreatenewcontactBtn;
	}

	public WebElement getStartDateEdt() {
		return StartDateEdt;
	}

	public WebElement getEndDateEdt() {
		return EndDateEdt;
	}
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement PlusClck;



	public WebElement getPlusClck() {
		return PlusClck;
	}
	@FindBy(id="search_txt")
	private WebElement searchEdt;
	
	@FindBy(name="search")
	private WebElement searchClick;



	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchClick() {
		return searchClick;
	}



	@FindBy(name="lastname")
	private WebElement contactlnEdt;

	public WebElement getContactlnEdt() {
		return contactlnEdt;
	}
	
	public void createContact(String lastname) {
		
		contactlnEdt.sendKeys(lastname);
		saveBtn.click();
	}
	
	public void enterStartDate(String startDate) {
		StartDateEdt.clear();
		StartDateEdt.sendKeys(startDate);
	}
	public void enterEndDate(String endDate) {
		EndDateEdt.clear();
		EndDateEdt.sendKeys(endDate);
	}

}
