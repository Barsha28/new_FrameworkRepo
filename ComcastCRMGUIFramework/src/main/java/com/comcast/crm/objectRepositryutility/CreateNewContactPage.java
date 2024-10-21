package com.comcast.crm.objectRepositryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateNewContactPage extends WebDriverUtility{
	WebDriver driver;
	public CreateNewContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
@FindBy(name = "lastname")
private WebElement lastNTxtBox;

@FindBy(xpath = "//input[@title='Save [Alt+S]']")
private WebElement saveBtn;

@FindBy(name = "support_start_date")
WebElement supportStartDate;

@FindBy(name = "support_end_date")
WebElement supportEndDate;

@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
WebElement lookup;

@FindBy(name="search_text")
WebElement searchTxtBox;

@FindBy(name="search")
WebElement searchBtn;


public WebElement getLastNTxtBox() {
	return lastNTxtBox;
}
public WebElement getSaveBtn() {
	return saveBtn;
}
public WebElement getSupportStartDate() {
	return supportStartDate;
}
public WebElement getSupportEndDate() {
	return supportEndDate;
}
public WebElement getLookupBtn() {
	return lookup;
}
public void CreateContact(String lastName) {
	lastNTxtBox.sendKeys(lastName);
	saveBtn.click();
}
public void CreateContact(String lastName,String startDate,String endDate) {
	lastNTxtBox.sendKeys(lastName);
	supportStartDate.clear();
	supportStartDate.sendKeys(startDate);
	supportEndDate.clear();
	supportEndDate.sendKeys(endDate);
	saveBtn.click();
}
public void CreateContact(String lastName,String orgName) {
	lastNTxtBox.sendKeys(lastName);
	getLookupBtn().click();
	switchToBrowserTabOnUrl(driver, "module=Accounts");
	searchTxtBox.sendKeys(orgName);
	searchBtn.click();
	driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();
	switchToBrowserTabOnUrl(driver, "module=Contacts");
	saveBtn.click();
}
}
