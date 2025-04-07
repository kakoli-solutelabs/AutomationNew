package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
ExtentReports extent = ExtentReporterNG.getReportObject();
ExtentTest test;
ThreadLocal<ExtentTest> extentTest = new ThreadLocal();

@Override
public void onTestStart(ITestResult result) {
	// TODO Auto-generated method stub
	test = extent.createTest(result.getMethod().getMethodName());
	extentTest.set(test);
}

@Override
public void onTestSuccess(ITestResult result) {
	// TODO Auto-generated method stub
	extentTest.get().log(Status.PASS, "Test Case Passed");
}

@Override
public void onTestFailure(ITestResult result){
	// TODO Auto-generated method stub
	try {
		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	extentTest.get().fail(result.getThrowable());
	String filepath = null;
	try {
		filepath = getScreenshot(result.getMethod().getMethodName(), driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	extentTest.get().log(Status.FAIL, "Test Case Failed");
	
}

@Override
public void onTestSkipped(ITestResult result) {
	// TODO Auto-generated method stub
	extentTest.get().log(Status.SKIP, "Test Case Skipped");
}


@Override
public void onFinish(ITestContext context) {
	// TODO Auto-generated method stub
	extent.flush();
}



}
 