package com.comcast.orm.objectrepositoryUtiity;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Documents {

	@FindBy(xpath="//img[@alt='Create Document...']")
	private WebElement DocPlus;
	

	@FindBy(xpath="seardchBtn")
	private WebElement ele3;

	@FindBy(xpath="search")
	private WebElement ele2;
//github.com/TejashwiniU-U/ComcastFrameworkRepo.git
	
	
}
