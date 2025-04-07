package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
	
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("userEmail")).sendKeys("sso1@yopmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@123");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("ZARA COAT 3")).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		Thread.sleep(1000); 
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List <WebElement> CartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = CartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equals("ZARA COAT 3"));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.cssSelector(".action__submit"));
		js.executeScript("arguments[0].scrollIntoView(true);", element1);
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element1);
		Thread.sleep(1000); // Small wait to stabilize
		element1.click();
		
		
		String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
		
		driver.close();
		
	}

	}
