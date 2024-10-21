package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comcast.crm.baseTest.BaseClass;

public class WebDriverUtility {
public void waitForPageToLoad(WebDriver driver) {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
}
public void WindowMaximize(WebDriver driver) {
	driver.manage().window().maximize();
}
public void waitForElementPresent(WebDriver driver,WebElement element) {
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
	wait.until(ExpectedConditions.visibilityOf(element));
}
public void switchToBrowserTabOnUrl(WebDriver driver,String partialUrl) {
	Set<String>set=driver.getWindowHandles();
	Iterator<String>it=set.iterator();
	while(it.hasNext()) {
		String windowId=it.next();
		driver.switchTo().window(windowId);
		String actUrl=driver.getCurrentUrl();
		if(actUrl.contains(partialUrl)) {
			break;
		}
	}
}
	public void switchToBrowserTabOnTitle(WebDriver driver,String partialTitle) {
		Set<String>set=driver.getWindowHandles();
		Iterator<String>it=set.iterator();
		while(it.hasNext()) {
			String windowId=it.next();
			driver.switchTo().window(windowId);
			String actUrl=driver.getTitle();
			if(actUrl.contains(partialTitle)) {
				break;
			}
		}
	}
	public void switchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver,String nameId) {
		driver.switchTo().frame(nameId);
	}
	public void switchToFrame(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	}
	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	public void switchToAlertAndDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	public void selectDropDownByIndex(WebElement element,int index) {
		Select st=new Select(element);
		st.selectByIndex(index);
	}
	public void selectDropDownByText(WebElement element,String text) {
		Select st=new Select(element);
		st.selectByVisibleText(text);
	}
	public void selectDropDownByValue(WebElement element,String value) {
		Select st=new Select(element);
		st.selectByValue(value);
	}
	public void actionMouseHover(WebDriver driver,WebElement element ) {
		Actions ac=new Actions(driver);
		ac.moveToElement(element).perform();
	}
    public void performDragAndDrop(WebDriver driver, WebElement sourceElement, WebElement targetElement) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(sourceElement, targetElement).perform();
    }
	public void actionDoubleClick(WebDriver driver,WebElement element ) {
		Actions ac=new Actions(driver);
		ac.doubleClick(element).perform();
	}
	public String getScreenshot(WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		return ts.getScreenshotAs(OutputType.BASE64);
	}
}
