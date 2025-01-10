//package com.comcast.crm.purchaseOrder;
//
//import java.io.IOException;
//
//import org.apache.poi.EncryptedDocumentException;
//import org.testng.annotations.Test;
//
//import com.comcast.crm.baseTest.BaseClass;
//import com.comcast.crm.objectRepositryutility.CreateNewPurchaseOrderPage;
//import com.comcast.crm.objectRepositryutility.HomePage;
//import com.comcast.crm.objectRepositryutility.PurchaseOrderPage;
//
//public class CreatePurchaseOrderTest extends BaseClass {
//	@Test
//	public void createPurchaseOrder() throws EncryptedDocumentException, IOException  {
//		String vendorName=elib.getDataFromExcel("Vendor", 1, 2) +jLib.getRandomNum();
//		String prdName=elib.getDataFromExcel("product", 1, 2) +jLib.getRandomNum();
//		HomePage hp=new HomePage(driver);
//		hp.getMoreLink().click();
//		hp.getNavPurchaseOdrLink().click();
//		PurchaseOrderPage cp=new PurchaseOrderPage(driver);
//		cp.getPurChaseOrderBtn().click();
// 		CreateNewPurchaseOrderPage cpo=new CreateNewPurchaseOrderPage(driver);
//		cpo.createpurchaseorder("dal", vendorName, "hyderabd", "hyderabd", prdName, "10");
//	}
//}
