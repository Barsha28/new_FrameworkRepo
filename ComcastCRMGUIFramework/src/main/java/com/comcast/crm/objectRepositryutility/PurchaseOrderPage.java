package com.comcast.crm.objectRepositryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchaseOrderPage {
	WebDriver driver;

	public PurchaseOrderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@title='Create Purchase Order...']")
	private WebElement CreatePurChaseOrderBtn;

	public WebElement getPurChaseOrderBtn() {
		return CreatePurChaseOrderBtn;
	}
}
