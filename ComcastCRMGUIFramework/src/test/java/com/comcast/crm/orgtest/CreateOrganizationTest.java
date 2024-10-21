package com.comcast.crm.orgtest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectRepositryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepositryutility.HomePage;
import com.comcast.crm.objectRepositryutility.OrganizationInfo;
import com.comcast.crm.objectRepositryutility.OrganizationPage;

/**
 * 
 * @author Barsha
 * contains organization test
 *
 */
@Listeners(com.comcast.crm.generic.listenerutility.ListenerImpCls.class)
public class CreateOrganizationTest extends BaseClass {
	@Test(groups="smoke test")
	public void createOrganizationWithMandatoryTest () throws IOException
	{
		/* smoke scenario for vtiger application*/
		// create object
//			FileUtility flib=new FileUtility();
//			ExcelUtility elib=new ExcelUtility();
//			JavaUtility jLib=new JavaUtility();
//
//			String BROWSER=flib.getDataFromPropertiesFile("browser");
//			String URL=flib.getDataFromPropertiesFile("url");
//			String UN=flib.getDataFromPropertiesFile("username");
//			String PWD=flib.getDataFromPropertiesFile("password");

		// read the testScript data from excel file
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");

		String orgName = elib.getDataFromExcel("org", 1, 2) + jLib.getRandomNum();
//		WebDriver driver=null;
//			
//			if(BROWSER.equals("chrome")) {
//				 driver=new ChromeDriver();
//			}
//			else if(BROWSER.equals("firefox")) {
//				 driver=new FirefoxDriver();
//			}
//			else if(BROWSER.equals("edge")) {
//				 driver=new EdgeDriver();
//			}
//			else if(BROWSER.equals("firefox")) {
//				 driver=new FirefoxDriver();
//			}
//			else {
//				 driver=new ChromeDriver();
//			}
		// login to app
//			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//			driver.get(URL);
//		LoginPage lp = new LoginPage(driver);
		// instead of accessing the element directly i am going to use public getters
		// method
		// single element access
//			lp.getUserNameEdit().sendKeys(UN);
//			lp.getPasswordEdit().sendKeys(PWD);
//			lp.getLoginBtn().click();
		// multiple Element access
//		lp.loginToApp(URL, UN, PWD);
		// navigate to organization module
		//ListenerImpCls.test.log(Status.INFO, "navigate to org page");
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		
		HomePage hp = new HomePage(driver);
		hp.getNavOrgLink().click();
		// navigate to campaign page
		// hp.navigateToCampaignPage();
		// click on organization button
		UtilityClassObject.getTest().log(Status.INFO, "click on organization button");
		OrganizationPage op = new OrganizationPage(driver);
		op.getOrgBtn().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "create new org ");
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO, orgName+"==>create a new org");

		
		
		OrganizationInfo oi = new OrganizationInfo(driver);
		String headerText = oi.getHeaderMsg().getText();

		if (headerText.contains(orgName)) {
			System.out.println(orgName + " is verified ==pass");
		} else {
			System.out.println(orgName + " is not verified ==fail");

		}
		// verify header orgName info expected result
		String act_OrgName = oi.getActOrgName().getText();
		Assert.assertEquals(true, act_OrgName.contains(orgName));
//		if (act_OrgName.contains(orgName)) {
//			System.out.println(orgName + " information is verified ==pass");
//		} else {
//			System.out.println(orgName + " information is not verified ==fail");
//		}
		// logout
//		hp.logOut();
//		driver.close();

	}
	@Test
	public void createOrgWithIndTest() throws EncryptedDocumentException, IOException {
		//read the testScript data from excel file
				UtilityClassObject.getTest().log(Status.INFO, "read data from excel");

				String orgName=elib.getDataFromExcel("Sheet2", 4, 2)+jLib.getRandomNum();
				String industry=elib.getDataFromExcel("Sheet2", 4, 3);
				String type=elib.getDataFromExcel("Sheet2", 4, 4);

					//navigate to organization module
					UtilityClassObject.getTest().log(Status.INFO, "navigate to org mod");
					HomePage hp=new HomePage(driver);
					hp.getNavOrgLink().click();
					//click on organization  button
					UtilityClassObject.getTest().log(Status.INFO, "click on org button");
					OrganizationPage op=new OrganizationPage(driver);
					op.getOrgBtn().click();
					UtilityClassObject.getTest().log(Status.INFO, "create org with industry and type");
					CreateNewOrganizationPage cno=new CreateNewOrganizationPage(driver);
					cno.createOrg(orgName, industry, type);
					//verify the dropdown industry and type info
					String actIndustry=driver.findElement(By.id("dtlview_Industry")).getText();
					Assert.assertEquals(true, actIndustry.contains(industry));

//					if(actIndustry.equals(industry)){
//						System.out.println(industry+" information is verified ==pass");
//					}
//					else {
//						System.out.println(industry+" information is not verified ==fail");
//					}
					
					//verify header orgName info expected result
					String actType=driver.findElement(By.id("dtlview_Type")).getText();
					Assert.assertEquals(true, actType.contains(type));

					
		}
	@Test
	public void deleteOrg() throws EncryptedDocumentException, IOException {
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		String orgName=elib.getDataFromExcel("Sheet2", 10, 2)+jLib.getRandomNum();
			//navigate to organization module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org mod");
			HomePage hp=new HomePage(driver);
			hp.getNavOrgLink().click();
			//navigate to campaign page
			//hp.navigateToCampaignPage();
			//click on organization  button
			UtilityClassObject.getTest().log(Status.INFO, "click on org button");
			OrganizationPage op=new OrganizationPage(driver);
			op.getOrgBtn().click();
			UtilityClassObject.getTest().log(Status.INFO, "create new org ");
			CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
			cnop.createOrg(orgName);
			
			OrganizationInfo oi=new OrganizationInfo(driver);
			String headerText=oi.getHeaderMsg().getText();
			Assert.assertEquals(true, headerText.contains(orgName));

//			if(headerText.contains(orgName)) {
//				System.out.println(orgName+" is verified ==pass");
//			}
//			else {
//				System.out.println(orgName+" is not verified ==fail");
//				
//			}
			
			//go back to organization page 
			UtilityClassObject.getTest().log(Status.INFO, "back to organization page");
			hp.getNavOrgLink().click();
			//search for organization
			UtilityClassObject.getTest().log(Status.INFO, "search for organization");
			op.searchOrganization(orgName,"Organization Name" );
			//in dynamic webtable select and delete org
			driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
			op.switchToAlertAndAccept(driver);
		}

	}


