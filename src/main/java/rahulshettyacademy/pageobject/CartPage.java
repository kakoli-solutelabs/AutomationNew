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

public class CartPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CartPage (WebDriver driver)
	{
		//initializing driver
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}


	//Page Factory
	
	@FindBy(css=".cartSection h3")
	List <WebElement> CartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	

	
	public Boolean verifyProducts(String productName)
	{
		Boolean match = CartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equals(productName));
		return(match);	
	}
	
	public PlaceOrder CheckOut()
	{
		checkOut.click();
		return new PlaceOrder(driver);
	}
	
	
	
	
}
