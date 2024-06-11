package StepDef;

import java.io.IOException;

import org.testng.Assert;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import PageObjects.LandingPage;
import PageObjects.ProductCatalogue;
import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SubmitOrderStepDe extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue  productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerse webpage")
	public void I_landed_on_Ecommerse_webpage() throws IOException
	{
		landingPage =launchApplication();
	}
	
	@Given("^Logged in with username (.+) and passowrd (.+)$")
	public void Logged_in_with_username_and_passowrd(String username,String password)
	{
		 productCatalogue = landingPage.login(username,password);
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String product)
	{
		productCatalogue.addToCart(product);
	}
	
	@When("^Checkout product (.+) and submit order$")
	public void checkout_product_and_submitOrder(String product) throws InterruptedException
	{
		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.verifyProductDisplay(product);
		Assert.assertTrue(match);

		// CheckoutPage checkoutPage = new CheckoutPage(driver);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("Ind", "India");

		 confirmationPage = checkoutPage.placeOrder();
	}
	
	@Then("{string} message is displayed on confirmation page")
	public void message_displayed_on_confirmationPage(String string)
	{
		String confimrationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confimrationMessage.equalsIgnoreCase(string));
		driver.close();
	}

}
