package rahulshettyacademy.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	
	public LandingPage (WebDriver driver)
	{
		super(driver);
		//initializing driver
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
//	WebElement userEmail = driver.findElement(By.id("userEmail"));

	//Page Factory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement ErrorMessage;
	
	
	public void loginPage(String email, String pwd) {
		userEmail.sendKeys(email);
		password.sendKeys(pwd);
		submit.click();
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(ErrorMessage);
		return ErrorMessage.getText();
	}
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client");
		
	}

}
