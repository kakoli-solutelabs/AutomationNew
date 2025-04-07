package rahulshettyacademy.stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobject.CartPage;
import rahulshettyacademy.pageobject.ConfirmPage;
import rahulshettyacademy.pageobject.LandingPage;
import rahulshettyacademy.pageobject.PlaceOrder;
import rahulshettyacademy.pageobject.ProductCatelog;

public class StepDefinitionImpl extends BaseTest {
	public LandingPage landingpage;
	public ProductCatelog productCatelog;
	public ConfirmPage confirmPage;
	
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingpage = LaunchApplication();
	}
	
	@Given("^Logged in with Username (.+) and Password (.+)$")
	public void Logged_in_with_Username_and_Password(String uname, String pwd)
	{
		landingPage.loginPage(uname, pwd);		
	}

	@When("^I add Productname (.+) from cart$")
	public void I_add_Productname_from_cart(String pname) throws InterruptedException
	{
		productCatelog = new ProductCatelog(driver);
		productCatelog.addProductToCart(pname);
		productCatelog.goToCartPage();
	}
	
	@When("^Checkout (.+) and Submit the order$")
	public void Checkout_product_and_Submit_the_order(String pname) throws InterruptedException
	{
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.verifyProducts(pname);
		Assert.assertTrue(match);
		PlaceOrder placeOrder = cartPage.CheckOut();
		
		placeOrder.selectCountry("India");
		confirmPage = placeOrder.orderPlace();
	}
	
	@Then("{string} message is displayed in ConfirmationPage")
	public void message_in_ConfirmationPage(String string)
	{
		String confirmMsg = confirmPage.confirmCheckout();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_in_displayed(String string)
	{
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}
}
