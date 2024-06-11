package Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import PageObjects.LandingPage;
import PageObjects.ProductCatalogue;
import TestComponents.BaseTest;

public class ErrorValidationTest extends BaseTest {

	@Test
	public void submitOrder() throws IOException, InterruptedException {

		

		landingPage.login("neelam@gmail.com", "Nelam@123");
		String errorMessage= landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", errorMessage);

	}
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";

		ProductCatalogue productCatalogue = landingPage.login("neelam@gmail.com", "Neelam@123");
		productCatalogue.addToCart(productName);

		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertTrue(match);

		

	}


}