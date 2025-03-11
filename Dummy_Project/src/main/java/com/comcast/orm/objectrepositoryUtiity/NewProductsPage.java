package com.comcast.orm.objectrepositoryUtiity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class NewProductsPage {

	WebDriver driver;

	public NewProductsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//img[@title='Create Product...']")
	private WebElement productplus;
	
	@FindBy(name="productname")
	private WebElement productEdt;
	
	@FindBy(name="productcategory")
	private WebElement procateselect;
	
	public WebElement getProductheadermsg() {
		return productheadermsg;
	}

	@FindBy(id = "dtlview_Product Category")
	private WebElement productheadermsg;
	
	public WebElement getProductplus() {
		return productplus;
	}

	public WebElement getProductEdt() {
		return productEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement proheaderverify;
	
	@FindBy(id= "dtlview_Product Name")
	private WebElement proEditorverify;
	
	
	public WebElement getProEditorverify() {
		return proEditorverify;
	}

	public WebElement getProheaderverify() {
		return proheaderverify;
	}

	public void createproduct(String proName) {
		
		productplus.click();
		productEdt.sendKeys(proName);
	}
	
	public void createproduct(String proName , String category) {
		productplus.click();
		productEdt.sendKeys(proName);
		Select sel = new Select(procateselect);
		sel.selectByVisibleText(category);
		
	}

	public WebElement getProcateselect() {
		return procateselect;
	}
	
}
