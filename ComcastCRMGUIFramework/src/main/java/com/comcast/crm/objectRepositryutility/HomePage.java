package com.comcast.crm.objectRepositryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
@FindBy (linkText = "Products")
private WebElement NavProductLink;	

@FindBy (linkText = "Organizations")
private WebElement NavOrgLink;

@FindBy(linkText  = "Contacts")
private WebElement contactLink;
@FindBy(linkText = "Campaigns")
private WebElement campaignLink;


@FindBy(linkText="More")
private WebElement moreLink;

@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
private WebElement adminimg;

@FindBy(linkText="Sign Out")
private WebElement signOutLink;

public WebElement getNavOrgLink() {
	return NavOrgLink;
}
public WebElement getNavProductLink() {
	return NavProductLink;
}

public WebElement getContactLink() {
	return contactLink;
}
public WebElement getCampaignLink() {
	return campaignLink;
}
public WebElement getMoreLink() {
	return moreLink;
}

public WebElement getAdminstration() {
	return adminimg;
}

//business method
public void navigateToCampaignPage() {
	Actions ac=new Actions(driver);
	ac.moveToElement(moreLink).perform();
	campaignLink.click();
}
public void logOut() {
	Actions ac=new Actions(driver);
	ac.moveToElement(adminimg).perform();
	signOutLink.click();
}
}
