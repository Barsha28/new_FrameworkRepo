package com.comcast.crm.objectRepositryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateNewOrganizationPage extends WebDriverUtility{
	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
@FindBy(name="accountname")
private WebElement orgNameEdit;

@FindBy(xpath="//input[contains(@class,'crmbutton small save')]")
private WebElement saveBtn;

@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
private WebElement lookUpBtn;

@FindBy(name="industry")
private WebElement industryTxtBox;

@FindBy(name="accounttype")
private WebElement accountTypeTxtBox;


public WebElement getOrgNameEdit() {
	return orgNameEdit;
}

public WebElement getSaveBtn() {
	return saveBtn;
}
public WebElement getindustryTxtBox() {
	return industryTxtBox;
}
public WebElement getaccountTypeTxtBox() {
	return accountTypeTxtBox;
}
public WebElement getLookUpBtn() {
	return lookUpBtn;
}

public void createOrg(String orgName) {
	orgNameEdit.sendKeys(orgName);
	saveBtn.click();	
}
public void createOrg(String orgName,String industry,String type) {
	orgNameEdit.sendKeys(orgName);
	industryTxtBox.sendKeys(industry);
	selectDropDownByText(accountTypeTxtBox, type);
	saveBtn.click();	
}
}
