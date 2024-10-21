package com.comcast.crm.objectRepositryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
/**
 * 
 * @author Barsha
 * contains login page element
 *
 */

public class LoginPage extends WebDriverUtility{
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);//this statement is used to bind the WebElement which is defined with @findBy
		//annotation with the actual elements on the webpage
	}
	//1-create separate java class
						//2-object creation
	@FindBy(name="user_name")
	private WebElement userNameEdit;

	@FindBy(name="user_password")
	private WebElement PasswordEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	//3-object initialization
	//4-object Encapsulation

	public WebElement getUserNameEdit() {
		return userNameEdit;
	}

	public WebElement getPasswordEdit() {
		return PasswordEdit;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	//provide action
	/**
	 * login to app
	 * @param url
	 * @param username
	 * @param password
	 */
	public void loginToApp(String url,String username,String password) {
		waitForPageToLoad(driver);
		driver.manage().window().maximize();
		driver.get(url);
		userNameEdit.sendKeys(username);
		PasswordEdit.sendKeys(password);
		loginBtn.click();
	}
}
