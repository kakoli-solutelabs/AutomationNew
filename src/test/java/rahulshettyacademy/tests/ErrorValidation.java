package rahulshettyacademy.tests;

import java.io.IOException;

import org.testng.Assert;
//import org.junit.jupiter.api.Test;
import org.testng.annotations.Test;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;

public class ErrorValidation extends BaseTest{

	@Test (groups = {"ErrorHandling"}, retryAnalyzer= Retry.class )
	public void SubmitOrderError() throws IOException, InterruptedException
	{
	

		landingPage.loginPage("sso12@yopmail.com", "Test@123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

		
				
	}

	}
