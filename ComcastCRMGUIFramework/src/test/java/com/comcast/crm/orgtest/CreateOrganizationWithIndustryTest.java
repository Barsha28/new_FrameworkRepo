package com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationWithIndustryTest {
public static void main(String[] args) throws IOException {
	//tc-02-check wheather we are able to create organization with industry and type
			//create object
			FileUtility flib=new FileUtility();
			ExcelUtility elib=new ExcelUtility();
			JavaUtility jLib=new JavaUtility();
		
		String BROWSER=flib.getDataFromPropertiesFile("browser");
		String URL=flib.getDataFromPropertiesFile("url");
		String UN=flib.getDataFromPropertiesFile("username");
		String PWD=flib.getDataFromPropertiesFile("password");
		
		//read the testScript data from excel file
		String orgName=elib.getDataFromExcel("Sheet2", 4, 2)+jLib.getRandomNum();
		String industry=elib.getDataFromExcel("Sheet2", 4, 3);
		String type=elib.getDataFromExcel("Sheet2", 4, 4);

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
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get(URL);
			driver.findElement(By.name("user_name")).sendKeys(UN);
			driver.findElement(By.name("user_password")).sendKeys(PWD);
			driver.findElement(By.id("submitButton")).click();
			//navigate to organization module
			driver.findElement(By.linkText("Organizations")).click();
			//click on organization  button
			driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			WebElement accountName = driver.findElement(By.name("accountname"));
			accountName.sendKeys(orgName);
			WebElement sel_Industry=driver.findElement(By.name("industry"));
			Select st= new Select(sel_Industry);
			st.selectByVisibleText(industry);
			WebElement sel_Type=driver.findElement(By.name("accounttype"));
			st= new Select(sel_Type);
			st.selectByVisibleText(type);
			
			driver.findElement(By.xpath("//input[contains(@class,'crmbutton small save')]")).click();
			//verify the dropdown industry and type info
			String actIndustry=driver.findElement(By.id("dtlview_Industry")).getText();
			if(actIndustry.equals(industry)){
				System.out.println(industry+" information is verified ==pass");
			}
			else {
				System.out.println(industry+" information is not verified ==fail");
			}
			
			//verify header orgName info expected result
			String actType=driver.findElement(By.id("dtlview_Type")).getText();
			if(actType.contains(type)) {
				System.out.println(type+" information is verified ==pass");
			}
			else {
				System.out.println(type+" information is not verified ==fail");
			}
			
			driver.close();
		

}
}
