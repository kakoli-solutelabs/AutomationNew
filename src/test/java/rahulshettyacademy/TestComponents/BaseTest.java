package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobject.LandingPage;


public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializedDriver() throws IOException
	{

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
//		String browserName = prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if (browserName.contains("headless"))
		{
		options.addArguments("headless");
		}
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));
	
		}
		else if (browserName.equalsIgnoreCase("FireFox"))
		{
			//firefox
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		//System.getProperty
		File target = new File(System.getProperty("user.dir")+ "/reports" + testcaseName + ".png");
		FileUtils.copyFile(source, target);
		//System.getProperty
		return System.getProperty("user.dir")+ "/reports" + testcaseName + ".png";
	}
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>()
		{
			
		});
		return data;
	}
	
	
	
	@BeforeMethod (alwaysRun=true)
	public LandingPage LaunchApplication() throws IOException
	{
//		System.out.println("Before");
		driver = initializedDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod (alwaysRun=true)
	public void closeSubmitOrder()
	{
//		System.out.println("After");
		driver.close();
	}


	
}
