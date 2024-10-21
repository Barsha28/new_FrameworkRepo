package com.comcast.crm.objectRepositryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfo {
	WebDriver driver;
	public ContactInfo(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
@FindBy(xpath = "//span[@class='dvHeaderText']")
private WebElement headerMsg;

@FindBy(id="dtlview_Last Name")
private WebElement actLastName;

public WebElement getHeaderMsg() {
	return headerMsg;
}
public WebElement getActLastName() {
	return actLastName;
}

}
