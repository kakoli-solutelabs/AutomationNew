package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
//import org.junit.jupiter.api.Test;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobject.CartPage;
import rahulshettyacademy.pageobject.ConfirmPage;
import rahulshettyacademy.pageobject.LandingPage;
import rahulshettyacademy.pageobject.OrderPage;
import rahulshettyacademy.pageobject.PlaceOrder;
import rahulshettyacademy.pageobject.ProductCatelog;

public class StandAloneNewTest extends BaseTest{

	@Test (dataProvider="getData", groups = "Purchase")
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	{
	
		landingPage.loginPage(input.get("email"), input.get("password"));
				
		ProductCatelog productCatelog = new ProductCatelog(driver);
		productCatelog.addProductToCart(input.get("productName"));
		productCatelog.goToCartPage();
				
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.verifyProducts(input.get("productName"));
		Assert.assertTrue(match);
		PlaceOrder placeOrder = cartPage.CheckOut();
		
		placeOrder.selectCountry("India");
		ConfirmPage confirmPage = placeOrder.orderPlace();
		
		String confirmMsg = confirmPage.confirmCheckout();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
		
				
	}
	
	@Test (dependsOnMethods = {"SubmitOrder"})
	public void OrderHistoryTest()
	{
		landingPage.loginPage("sso1@yopmail.com", "Test@123");
		ProductCatelog productCatelog = new ProductCatelog(driver);
		OrderPage orderPage = productCatelog.goToOrderPage();
		Boolean match = orderPage.verifyOrder("ZARA COAT 3");
		Assert.assertTrue(match);
		
	}
	
	
//	public File getScreenshot(String testcaseName) throws IOException 
//	{
//		TakesScreenshot ts = (TakesScreenshot) driver;
//		File source = ts.getScreenshotAs(OutputType.FILE);
//		File target = new File(System.getProperty("user.dir"+ "//reports" + testcaseName + ".png"));
//		FileUtils.copyFile(source, target);
//		return target;
//	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}

	}


//HashMap<String, String> map1 = new HashMap<String, String>();
//map1.put("email", "sso1@yopmail.com");
//map1.put("password", "Test@123");
//map1.put("productName", "ZARA COAT 3");
//
//HashMap<String, String> map2 = new HashMap<String, String>();
//map2.put("email", "sso2@yopmail.com");
//map2.put("password", "Test@1234");
//map2.put("productName", "ADIDAS ORIGINAL");
