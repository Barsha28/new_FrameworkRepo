package com.comcast.crm.baseTest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectRepositryutility.HomePage;
import com.comcast.crm.objectRepositryutility.LoginPage;

public class BaseClass {
	// create object
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public DataBaseUtility dblib = new DataBaseUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;

	@BeforeSuite(groups = { "smoke test", "reg ression test" })
	public void configBS() throws SQLException {
		System.out.println("===connect to DB , Report config===");
		dblib.getDbConnection();
	}

	// @Parameters("Browser")
	@BeforeClass(groups = { "smoke test", "regression test" })
//	public void configBC(String browser) throws IOException {
	public void configBC() throws IOException {

		System.out.println("==launch browser==");
//	String BROWSER=	browser;
		//String BROWSER = flib.getDataFromPropertiesFile("browser");
		String BROWSER=System.getProperty("browser",flib.getDataFromPropertiesFile("browser"));
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();

		}
		sdriver = driver;
		//create utilityclassobject to set driver object
		UtilityClassObject.setDriver(driver);
	}

	@BeforeMethod(groups = { "smoke test", "regression test" })
	public void configBM() throws IOException {
		System.out.println("==login==");
//		String URL = flib.getDataFromPropertiesFile("url");
//		String UN = flib.getDataFromPropertiesFile("username");
//		String PWD = flib.getDataFromPropertiesFile("password");
		String URL=System.getProperty("url",flib.getDataFromPropertiesFile("url"));
		String UN=System.getProperty("username",flib.getDataFromPropertiesFile("username"));
		String PWD=System.getProperty("password",flib.getDataFromPropertiesFile("password"));

		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, UN, PWD);
	}

	@AfterMethod(groups = { "smoke test", "regression test" })
	public void configAM() {
		System.out.println("==logout==");
		HomePage hp = new HomePage(driver);
		hp.logOut();
	}

	@AfterClass(groups = { "smoke test", "regression test" })
	public void configAC() {
		System.out.println("==close the Browser==");
		driver.quit();
	}

	@AfterSuite(groups = { "smoke test", "regression test" })
	public void configAS() throws SQLException {
		System.out.println("===close connection===");
		dblib.closeConnection();

	}
}
