package com.comcast.crm.objectRepositryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateNewPurchaseOrderPage extends WebDriverUtility{
	WebDriver driver;

	public CreateNewPurchaseOrderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='subject']")
	private WebElement PurChaseOrderSubjectEdit;
	@FindBy(name = "bill_street")
	private WebElement billingAddresstxtArea;
	@FindBy(xpath = "//input[@name='vendor_name']/following-sibling::img")
	private WebElement vendorLookUp;
	@FindBy(name = "ship_street")
	private WebElement shippingAddresstxtArea;
	@FindBy(id = "productName1")
	private WebElement productNametxtbox;
	@FindBy(id = "qty1")
	private WebElement qtytxtbox;
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	@FindBy(id="search_txt")
	private WebElement searchTxt;
	@FindBy(id="search")
	private WebElement searchBtn;
	
	@FindBy(id="searchIcon1")
	private WebElement searchIcon;


	@FindBy(id="selected_id")
	private WebElement prdChkbox;	
	@FindBy(xpath = "//input[@value='Select Products']")
	private WebElement selectedItem;
	
	public WebElement getSearchIcon() {
		return searchIcon;
	}
	public WebElement getPurChaseOrderSubject() {
		return PurChaseOrderSubjectEdit;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getPurChaseOrderSubjectEdit() {
		return PurChaseOrderSubjectEdit;
	}

	public WebElement getVendorLookUp() {
		return vendorLookUp;
	}

	public WebElement getBillingAddresstxtArea() {
		return billingAddresstxtArea;
	}

	public WebElement getShippingAddresstxtArea() {
		return shippingAddresstxtArea;
	}

	public WebElement getProductNametxtbox() {
		return productNametxtbox;
	}

	public WebElement getQtytxtbox() {
		return qtytxtbox;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public WebElement getSearchTxt() {
		return searchTxt;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}
	public WebElement getPrdChkbox() {
		return prdChkbox;
	}
	public WebElement getSelectedItem() {
		return selectedItem;
	}
	public void createpurchaseorder(String subName,String vendorName,String billAdd,String shipAdd,String prdName,String qty) {
		getPurChaseOrderSubject().sendKeys(subName);
		getVendorLookUp().click();
		switchToBrowserTabOnUrl(driver,"module=Vendors");
		getSearchTxt().sendKeys(vendorName);
		getSearchBtn().click();
		driver.findElement(By.xpath("//a[text()='"+vendorName+"']"));
		switchToBrowserTabOnUrl(driver, "module=PurchaseOrder");
		getBillingAddresstxtArea().sendKeys(billAdd);
		getShippingAddresstxtArea().sendKeys(shipAdd);
		getSearchIcon().click();
		switchToBrowserTabOnUrl(driver, "module=Products");
		getSearchTxt().sendKeys(prdName);
		getSearchBtn().click();
		getPrdChkbox().click();
		getSelectedItem().click();
		switchToBrowserTabOnUrl(driver, "module=PurchaseOrder");
		getQtytxtbox().sendKeys(qty);
		getSaveBtn().click();
	}
}
