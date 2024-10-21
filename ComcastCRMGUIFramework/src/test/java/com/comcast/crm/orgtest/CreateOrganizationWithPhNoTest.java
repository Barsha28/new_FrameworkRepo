package com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationWithPhNoTest {
public static void main(String[] args) throws IOException {
		//create org with phone no and verify
				//create object
				FileUtility flib=new FileUtility();
				ExcelUtility elib=new ExcelUtility();
				JavaUtility jLib=new JavaUtility();
			
			String BROWSER=flib.getDataFromPropertiesFile("browser");
			String URL=flib.getDataFromPropertiesFile("url");
			String UN=flib.getDataFromPropertiesFile("username");
			String PWD=flib.getDataFromPropertiesFile("password");
			
			//read the testScript data from excel file
			String orgName=elib.getDataFromExcel("Sheet2", 7, 2)+jLib.getRandomNum();
			String phNo=elib.getDataFromExcel("Sheet2", 7, 3);
			
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
			driver.findElement(By.id("phone")).sendKeys(phNo);
			driver.findElement(By.xpath("//input[contains(@class,'crmbutton small save')]")).click();
			String actPh=driver.findElement(By.id("dtlview_Phone")).getText();
			if(actPh.contains(phNo)) {
				System.out.println(phNo+" is verified ==pass");
			}
			else {
				System.out.println(phNo+" is not verified ==fail");
				
			}
			//verify header orgName info expected result
			String actOrgName=driver.findElement(By.id("dtlview_Organization Name")).getText();
			if(actOrgName.contains(orgName)) {
				System.out.println(orgName+" information is verified ==pass");
			}
			else {
				System.out.println(orgName+" information is not verified ==fail");
				
			}
			driver.close();
		

}
}
