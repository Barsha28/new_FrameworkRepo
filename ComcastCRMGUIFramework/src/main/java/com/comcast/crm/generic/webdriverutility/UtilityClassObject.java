package com.comcast.crm.generic.webdriverutility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject {
public static ThreadLocal<ExtentTest>test=new ThreadLocal<ExtentTest>();
public static ThreadLocal<WebDriver>driver=new ThreadLocal<WebDriver>();
//getter method to use the object of Extenttest object
public static ExtentTest getTest() {
	return test.get();
	//this method will give the extent report object  for the multiple threads to achieve parallel execution
	//it will share the object even though it is static  by taking help of ThreadLocal class
}
//setter method
public static void setTest(ExtentTest actTest) {
	test.set(actTest);
}
public static WebDriver getDriver() {
	return driver.get();
	//this method will give the extent report object  for the multiple threads to achieve parallel execution
	//it will share the object even though it is static  by taking help of ThreadLocal class
}
public static void setDriver(WebDriver actDriver) {
	driver.set(actDriver);
}
}
//this class help us  to share my static variable for multiple threads in case of parallel execution