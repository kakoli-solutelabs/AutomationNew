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

public class ConfirmPage extends AbstractComponents{
	
WebDriver driver;


@FindBy(css=".hero-primary")
WebElement confirmMsgText;

public ConfirmPage (WebDriver driver)
	{
		//initializing driver
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	

	public String confirmCheckout()
	{
		return confirmMsgText.getText();
			
	}
	


}
