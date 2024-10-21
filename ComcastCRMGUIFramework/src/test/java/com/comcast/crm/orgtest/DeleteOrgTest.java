package com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectRepositryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepositryutility.HomePage;
import com.comcast.crm.objectRepositryutility.LoginPage;
import com.comcast.crm.objectRepositryutility.OrganizationInfo;
import com.comcast.crm.objectRepositryutility.OrganizationPage;

public class DeleteOrgTest {
public static void main(String[] args) throws IOException {
			//smoke scenario for vtiger application
			//create object
			FileUtility flib=new FileUtility();
			ExcelUtility elib=new ExcelUtility();
			JavaUtility jLib=new JavaUtility();
			WebDriverUtility wlib=new WebDriverUtility();

			String BROWSER=flib.getDataFromPropertiesFile("browser");
			String URL=flib.getDataFromPropertiesFile("url");
			String UN=flib.getDataFromPropertiesFile("username");
			String PWD=flib.getDataFromPropertiesFile("password");
					
			//read the testScript data from excel file
		
			String orgName=elib.getDataFromExcel("Sheet2", 10, 2)+jLib.getRandomNum();
		WebDriver driver=null;
			
			if(BROWSER.equals("chrome")) {
				 driver=new ChromeDriver();
			}
			else if(BROWSER.equals("firefox")) {
				 driver=new FirefoxDriver();
			}
			else if(BROWSER.equals("edge")) {
				 driver=new EdgeDriver();
			}
			else if(BROWSER.equals("firefox")) {
				 driver=new FirefoxDriver();
			}
			else {
				 driver=new ChromeDriver();
			}
			//login to app
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get(URL);
			LoginPage lp=new LoginPage(driver);
			//instead of accessing the element directly i am going to use public getters method
			//single element access
//			lp.getUserNameEdit().sendKeys(UN);
//			lp.getPasswordEdit().sendKeys(PWD);
//			lp.getLoginBtn().click();
			//multiple Element access
			lp.loginToApp(URL,UN, PWD);
			//navigate to organization module
			HomePage hp=new HomePage(driver);
			hp.getNavOrgLink().click();
			//navigate to campaign page
			//hp.navigateToCampaignPage();
			//click on organization  button
			OrganizationPage op=new OrganizationPage(driver);
			op.getOrgBtn().click();
			
			CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
			cnop.createOrg(orgName);
			
			OrganizationInfo oi=new OrganizationInfo(driver);
			String headerText=oi.getHeaderMsg().getText();
			
			if(headerText.contains(orgName)) {
				System.out.println(orgName+" is verified ==pass");
			}
			else {
				System.out.println(orgName+" is not verified ==fail");
				
			}
			//go back to organization page 
			hp.getNavOrgLink().click();
			//search for organization
			op.getSearchTextBox().sendKeys(orgName);
			wlib.selectDropDownByText(op.getSearchDD(), "Organization Name");
			op.getSearchBtn().click();
			//in dynamic webtable select and delete org
			driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
			wlib.switchToAlertAndAccept(driver);
			//logout
			hp.logOut();
			driver.close();
		

}
}
