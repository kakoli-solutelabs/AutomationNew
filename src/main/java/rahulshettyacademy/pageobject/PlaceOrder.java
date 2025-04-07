package rahulshettyacademy.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class PlaceOrder extends AbstractComponents{
	
WebDriver driver;
Actions a;
JavascriptExecutor js;

@FindBy(css="[placeholder='Select Country']")
WebElement country;

@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
WebElement countryActual;

@FindBy(css=".action__submit")
WebElement submit;

@FindBy(css=".hero-primary")
WebElement confirmMsgText;

By countryList = By.cssSelector(".ta-results");
	
	public PlaceOrder (WebDriver driver)
	{
		//initializing driver
		super(driver);
		this.driver = driver;
		Actions a = new Actions(this.driver);
		this.a = a;
		JavascriptExecutor js = (JavascriptExecutor) this.driver;
		this.js = js;
		PageFactory.initElements(driver, this);
		
	}
	
	public void selectCountry(String txt) 
	{
		country.sendKeys(txt);
//		a.sendKeys(country, txt);
		waitForElementToAppear(countryList);
		countryActual.click();
	}
	
	public ConfirmPage orderPlace() throws InterruptedException
	{
		js.executeScript("arguments[0].scrollIntoView(true);", submit);
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", submit);
		Thread.sleep(1000); // Small wait to stabilize
		submit.click();
		return new ConfirmPage(driver);
	}
	

//	public void confirmCheckout()
//	{
//		String confirmMsg = confirmMsgText.getText();
//		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
////		if (confirmMsg.equalsIgnoreCase("Thankyou for the order."))
//
//		
//	}
	
	
//	String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
//	Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));

}
