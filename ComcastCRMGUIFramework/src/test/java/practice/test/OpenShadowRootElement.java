package practice.test;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class OpenShadowRootElement {
	
	@Test
	public void openShadow() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoapps.qspiders.com/ui/shadow/closed?sublist=1");
		WebElement un = driver.findElement(By.xpath("//div[@class='my-3'][1]"));
		SearchContext user = un.getShadowRoot();
		
	}

}
