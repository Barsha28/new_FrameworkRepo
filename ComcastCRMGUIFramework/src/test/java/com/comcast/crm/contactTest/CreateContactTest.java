package com.comcast.crm.contactTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.objectRepositryutility.ContactPage;
import com.comcast.crm.objectRepositryutility.CreateNewContactPage;
import com.comcast.crm.objectRepositryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepositryutility.HomePage;
import com.comcast.crm.objectRepositryutility.OrganizationInfo;
import com.comcast.crm.objectRepositryutility.OrganizationPage;
//@Listeners(com.comcast.crm.generic.listenerutility.ListenerImpCls.class)
public class CreateContactTest extends BaseClass {
	@Test(groups ={"smoke test","regression test"})
	public void createContactTest() throws EncryptedDocumentException, IOException {
		// read the testScript data from excel file
		String lastName = elib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNum();
		// navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		// click on + button
		ContactPage cp = new ContactPage(driver);
		cp.getContactBtn().click();
		CreateNewContactPage ccp = new CreateNewContactPage(driver);
		ccp.CreateContact(lastName);

		// verify header msg expected result
		OrganizationInfo ori = new OrganizationInfo(driver);
		String headerText = ori.getHeaderMsg().getText();
		boolean status=headerText.contains(lastName);
		Assert.assertTrue(status);
		
		String actlastNameTxt=ori.getActLastName().getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(actlastNameTxt, lastName);
		soft.assertAll();
	}
	@Test(groups ="regression test")
	public void createContactWithSupportDateTest() throws Throwable {
		//read the testScript data from excel file
				String lastName=elib.getDataFromExcel("contact", 4, 2)+jLib.getRandomNum();

			
			//navigate to contact module
			HomePage hp=new HomePage(driver);
			hp.getContactLink().click();
			//driver.findElement(By.linkText("Contacts")).click();
			//click on +  button
			//driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
			ContactPage cp=new ContactPage(driver);
			cp.getContactBtn().click();
			//WebElement lastname = driver.findElement(By.name("lastname"));
			//lastname.sendKeys(lastName);
			//Support date 
			String startDate=jLib.getSystemDateyyyymmdd();
//			
//			driver.findElement(By.name("support_start_date")).clear();
//			driver.findElement(By.name("support_start_date")).sendKeys(startDate);
			//End date 
			String endDate=jLib.getReqDateyyyymmdd(30);

//				driver.findElement(By.name("support_end_date")).clear();
//				driver.findElement(By.name("support_end_date")).sendKeys(endDate);
				Thread.sleep(2000);
				//driver.findElement(By.xpath("//input[contains(@class,'crmbutton small save')]")).click();
				CreateNewContactPage cnp=new CreateNewContactPage(driver);
				cnp.CreateContact(lastName, startDate, endDate);
				
			//verify lastname  info expected result
			String actLastName=driver.findElement(By.id("dtlview_Last Name")).getText();
			if(actLastName.equals(lastName)) {
				System.out.println(lastName+" is verified==pass");
			}
			else {
				System.out.println(lastName+" is not verified==fail");
			}
			//verify start and end date
			String actStartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
			if(actStartDate.equals(startDate)) {
				System.out.println(startDate+" is verified==pass");
			}
			else {
				System.out.println(startDate+" is not verified==fail");
			}
			String actEndDate=driver.findElement(By.id("dtlview_Support End Date")).getText();
			if(actEndDate.equals(endDate)) {
				System.out.println(endDate+" is verified==pass");
			}
			else {
				System.out.println(endDate+" is not verified==fail");
			}
		}

	@Test(groups="regression test")
	public void createContactWithOrgTest() throws EncryptedDocumentException, IOException {
		String lastName = elib.getDataFromExcel("contact", 7, 3);
		String orgName = elib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNum();
		
		HomePage hp = new HomePage(driver);
		hp.getNavOrgLink().click();
		OrganizationPage op = new OrganizationPage(driver);
		op.getOrgBtn().click();
		CreateNewOrganizationPage cno = new CreateNewOrganizationPage(driver);
		cno.createOrg(orgName);

		String headerText = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerText.contains(orgName)) {
			System.out.println(orgName + " is verified ==pass");
		} else {
			System.out.println(orgName + " is not verified ==fail");
		}

		hp.getContactLink().click();

		ContactPage cp = new ContactPage(driver);
		cp.getContactBtn().click();

		CreateNewContactPage cnp = new CreateNewContactPage(driver);
		cnp.CreateContact(lastName, orgName);
		// select lookup
		//	driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		// switch to child window
		//wlib.switchToBrowserTabOnUrl(driver, "module=Accounts");
//
//		driver.findElement(By.name("search_text")).sendKeys(orgName);
//		driver.findElement(By.name("search")).click();
//		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		// switch to parent window
		//wlib.switchToBrowserTabOnUrl(driver, "module=Contacts");

	//	driver.findElement(By.xpath("//input[contains(@class,'crmbutton small save')]")).click();

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
	}

	}


