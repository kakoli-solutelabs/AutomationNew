package rahulshettyacademy.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;
import org.testng.Assert;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	
	WebDriver driver;
	
	public OrderPage (WebDriver driver)
	{
		//initializing driver
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}


	//Page Factory
	
	@FindBy(xpath="//td[2]")
	List <WebElement> OrderProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	

	
	public Boolean verifyOrder(String productName)
	{
		Boolean match = OrderProducts.stream().anyMatch(orderProduct->orderProduct.getText().equals(productName));
		return(match);	
	}
	
//	public PlaceOrder CheckOut()
//	{
//		checkOut.click();
//		return new PlaceOrder(driver);
//	}
	
	
	
	
}
