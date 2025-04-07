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

public class ProductCatelog extends AbstractComponents{
	
	WebDriver driver;
	
	public ProductCatelog (WebDriver driver)
	{
		//initializing driver
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	//Page Factory
	@FindBy(css=".mb-3")
	List <WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement animation;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css=".cartSection h3")
	List <WebElement> CartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By message = By.cssSelector("#toast-container");
	
	
	public List <WebElement> getProductsList(){
		
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(message);
		waitForElementToDisappear(animation);
		Thread.sleep(1000);
		cart.click();
		
	}
	
	
	
}
