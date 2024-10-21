package com.comcast.crm.objectRepositryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class OrganizationPage extends WebDriverUtility{
	WebDriver driver;
	public OrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
@FindBy(xpath="//img[@title='Create Organization...']")
private WebElement createNewOrgBtn;

@FindBy(name = "search_text")
private WebElement searchTextBox;

@FindBy(name = "search_field")
private WebElement searchDD;

@FindBy(name = "submit")
private WebElement searchBtn;

public WebElement getOrgBtn() {
	return createNewOrgBtn;
}
public WebElement getSearchTextBox() {
	return searchTextBox;
}
public WebElement getSearchDD() {
	return searchDD;
}
public WebElement getSearchBtn() {
	return searchBtn;
}

public void searchOrganization(String orgName,String text) {
	getSearchTextBox().sendKeys(orgName);
	selectDropDownByText(getSearchDD(), text);
	getSearchBtn().click();
}
}
