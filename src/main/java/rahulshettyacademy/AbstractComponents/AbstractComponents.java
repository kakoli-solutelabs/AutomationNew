package rahulshettyacademy.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import rahulshettyacademy.pageobject.OrderPage;

public class AbstractComponents {

WebDriver driver;	

@FindBy(css="[routerlink*='cart']")
WebElement cart;

@FindBy(css="[routerlink*='myorders']")
WebElement OrderHeader;

public AbstractComponents(WebDriver driver) {
		
	this.driver = driver;
	PageFactory.initElements(driver, this);
	}


public void waitForElementToAppear(By findBy) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
}

public void waitForWebElementToAppear(WebElement element) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(element));
}

public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
	Thread.sleep(1000);
//	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//	wait.until(ExpectedConditions.invisibilityOf(ele));
}


public void goToCartPage()
{
	cart.click();
	
}

public OrderPage goToOrderPage()
{
	OrderHeader.click();
	OrderPage orderPage = new OrderPage(driver);
	return orderPage;
	
	
}

}
