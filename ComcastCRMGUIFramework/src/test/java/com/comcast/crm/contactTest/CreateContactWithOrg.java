package com.comcast.crm.contactTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectRepositryutility.ContactPage;
import com.comcast.crm.objectRepositryutility.CreateNewContactPage;
import com.comcast.crm.objectRepositryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepositryutility.HomePage;
import com.comcast.crm.objectRepositryutility.LoginPage;
import com.comcast.crm.objectRepositryutility.OrganizationPage;

public class CreateContactWithOrg {
	public static void main(String[] args) throws IOException {
		// integration test
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wlib = new WebDriverUtility();

		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String UN = flib.getDataFromPropertiesFile("username");
		String PWD = flib.getDataFromPropertiesFile("password");

		// read the testScript data from excel file

		String lastName = elib.getDataFromExcel("contact", 7, 3);
		String orgName = elib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNum();

		WebDriver driver = null;

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		// login to app
//	wlib.WindowMaximize(driver);
//	wlib.waitForPageToLoad(driver);
//	driver.get(URL);
//	driver.findElement(By.name("user_name")).sendKeys(UN);
//	driver.findElement(By.name("user_password")).sendKeys(PWD);
//	driver.findElement(By.id("submitButton")).click();[instead of that we will create business library of pom class]
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, UN, PWD);
		// navigate to organization module
		// driver.findElement(By.linkText("Organizations")).click();
		HomePage hp = new HomePage(driver);
		hp.getNavOrgLink().click();
		// click on organization button
		// driver.findElement(By.xpath("//img[@title='Create
		// Organization...']")).click();
		OrganizationPage op = new OrganizationPage(driver);
		op.getOrgBtn().click();
		// fill the details and save
//	WebElement accountName = driver.findElement(By.name("accountname"));
//	accountName.sendKeys(orgName);
//	driver.findElement(By.xpath("//input[contains(@class,'crmbutton small save')]")).click();
		CreateNewOrganizationPage cno = new CreateNewOrganizationPage(driver);
		cno.createOrg(orgName);

		// verify header orgName info expected result
		String headerText = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerText.contains(orgName)) {
			System.out.println(orgName + " is verified ==pass");
		} else {
			System.out.println(orgName + " is not verified ==fail");
		}

		// navigate to contact module
//	driver.findElement(By.linkText("Contacts")).click();
//	driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
//	WebElement lastname = driver.findElement(By.name("lastname"));
//	lastname.sendKeys(lastName);
		hp.getContactLink().click();

		ContactPage cp = new ContactPage(driver);
		cp.getContactBtn().click();

		CreateNewContactPage cnp = new CreateNewContactPage(driver);
		cnp.CreateContact(lastName);
		// select lookup
		//	driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		// switch to child window
		wlib.switchToBrowserTabOnUrl(driver, "module=Accounts");

		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		// switch to parent window
		wlib.switchToBrowserTabOnUrl(driver, "module=Contacts");

		driver.findElement(By.xpath("//input[contains(@class,'crmbutton small save')]")).click();

		String headerText1 = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		// verify header msg expected result
		if (headerText1.contains(lastName)) {
			System.out.println(lastName + " is verified ==pass");
		} else {
			System.out.println(lastName + " is not verified ==fail");

		}
		// verify organization name
		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if (actOrgName.contains(orgName)) {
			System.out.println(orgName + " is verified ==pass");
		} else {
			System.out.println(orgName + " is not verified ==fail");

		}

		driver.quit();
	}
}
