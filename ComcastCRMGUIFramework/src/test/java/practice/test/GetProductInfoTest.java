//package practice.test;
//
//import java.io.IOException;
//import java.time.Duration;
//
//import org.apache.poi.EncryptedDocumentException;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import com.comcast.crm.generic.fileutility.ExcelUtility;
//
//public class GetProductInfoTest {
//@Test(dataProvider = "getData")
//public void getProductInfoTest(String brandName,String productName) {
//	WebDriver driver=new ChromeDriver();
//	driver.manage().window().maximize();
//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//	driver.get("http://amazon.in");
//	
//	//search product
//	driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
//	//capture product info
//	//span[text()='Apple iPhone 15 Pro (128 GB) - Blue Titanium']/../../../../div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/a/span/span[2]/span[2]
//		String x="//span[text()='"+productName+"']/../../../../descendant::span[@class='a-price-whole']";
//		String price=driver.findElement(By.xpath(x)).getText();		
//		System.out.println(price);
//	driver.quit();
//}
//@DataProvider
//public Object[][] getData() throws EncryptedDocumentException, IOException{
//	ExcelUtility eLib=new ExcelUtility();
//	int rowcount=eLib.getRowCount("product");
//	int cellCount=eLib.getCellCount("product", 0);
//	Object [][] objArr=new Object[rowcount][cellCount];
//	for (int i = 0; i < rowcount; i++) {
//		for(int j=0;j<cellCount;j++) {
//		objArr[i][j]=eLib.getDataFromExcel("product",i+1,j);
//		}
//	}
//	return objArr;
//}
//
//}
