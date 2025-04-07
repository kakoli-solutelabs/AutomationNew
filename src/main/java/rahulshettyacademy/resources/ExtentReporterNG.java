package rahulshettyacademy.resources;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject()
	{
			String path = System.getProperty("user.dir")+"/reports/index.html";
			File reportDir = new File(System.getProperty("user.dir") + "/reports");
		    if (!reportDir.exists()) {
		        reportDir.mkdirs(); // Create reports directory if it doesn't exist
		    }
//			String path = "C:\\Users\\STL-LT-102\\eclipse-workspace\\SeleniumFrameworkDesign" +"\\reports\\index.html";
		    
			ExtentSparkReporter reporter = new ExtentSparkReporter(path);
			ExtentReports extent = new ExtentReports();
			
			reporter.config().setReportName("Web Automation Results");
			reporter.config().setDocumentTitle("Test Results");
			
			extent.attachReporter(reporter);
			extent.setSystemInfo("Tester Name", "Kakoli");
			return extent;
			
		}
		
		

}
