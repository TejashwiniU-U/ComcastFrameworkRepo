package com.comcast.orm.objectrepositoryUtiity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TrubleticketPage {
	

	WebDriver driver;

	public TrubleticketPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@title='Create Ticket...']")
	private WebElement TroTicketplus;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}

	@FindBy(name = "ticket_title")
	private WebElement TroTicketEdt;
	
	public WebElement getTicketprioSelect() {
		return TicketprioSelect;
	}

	public WebElement getTicketprioverify() {
		return Ticketprioverify;
	}

	@FindBy(name = "ticketpriorities")
	private WebElement TicketprioSelect;
	
	@FindBy(id = "mouseArea_Priority")
	private WebElement Ticketprioverify;

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement TroTicketheadermsg; 
	
	@FindBy(id = "dtlview_Title")
	private WebElement TroTicketverify;
	
	public WebElement getTroTicketplus() {
		return TroTicketplus;
	}

	public WebElement getTroTicketEdt() {
		return TroTicketEdt;
	}

	public WebElement getTroTicketheadermsg() {
		return TroTicketheadermsg;
	}

	public WebElement getTroTicketverify() {
		return TroTicketverify;
	}

	public void trobletckt(String ticketName) {
		TroTicketplus.click();
		TroTicketEdt.sendKeys(ticketName);
		
	}
	
	public void trobletckt(String ticketName , String priority) {
		TroTicketplus.click();
		TroTicketEdt.sendKeys(ticketName);
		Select sel = new Select(TicketprioSelect);
		sel.selectByVisibleText(priority);

		
	}
}
