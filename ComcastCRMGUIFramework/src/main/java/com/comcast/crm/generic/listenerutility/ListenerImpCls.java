package com.comcast.crm.generic.listenerutility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListenerImpCls implements ISuiteListener, ITestListener {
	public static ExtentReports report;
	public static ExtentTest test;

	public void onStart(ISuite suite) {
		System.out.println("report configuration");
		// spark report configuration
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReprt/report" + time + ".html");
		spark.config().setDocumentTitle("CRM test suite results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add environment information and create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "Chrome-100");

	}

	public void onFinish(ISuite suite) {
		System.out.println("report backup");
		report.flush();// back up means,if we will not use flush(),reports will not be saved

	}

	public void onTestStart(ITestResult result) {
		System.out.println("=====" + result.getMethod().getMethodName() + "=====start=====");
		test = report.createTest(result.getMethod().getMethodName());
		// once the test object is created
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName() + "==>started<==");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("=====" + result.getMethod().getMethodName() + "====end======");
		test.log(Status.PASS, result.getMethod().getMethodName() + "==>completed<==");
	}

	public void onTestFailure(ITestResult result) {
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		String testName = result.getMethod().getMethodName();
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		String filepath = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(filepath, testName + "_" + time);
		// test.addScreenCaptureFromBase64String(new
		// WebDriverUtility().getScreenshot(BaseClass.sdriver));

		test.log(Status.FAIL, result.getMethod().getMethodName() + "==>failed<==");
		// File srcfile = ts.getScreenshotAs(OutputType.FILE);

//		try {
//			FileHandler.copy(srcfile, new File("./Screenshot/" + testName + "+" + time + "+.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getMethod().getMethodName() + "==>skipped<==");

	}

}
