package Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import PageObjects.LandingPage;
import PageObjects.OrderHistoryPage;
import PageObjects.ProductCatalogue;
import TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.login(input.get("username"),input.get("password"));
		productCatalogue.addToCart(input.get("product"));

		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

		// CheckoutPage checkoutPage = new CheckoutPage(driver);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("Ind", "India");

		ConfirmationPage confirmationPage = checkoutPage.placeOrder();
		String confimrationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confimrationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test(dependsOnMethods={"submitOrder"})
	public void orderHistory() throws InterruptedException
	{
		ProductCatalogue productCatalogue = landingPage.login("neelam@gmail.com", "Neelam@123");
	    OrderHistoryPage orderHistoryPage=	productCatalogue.goToOrders();
		Assert.assertTrue(orderHistoryPage.verifyProductDisplay(productName));
	}
	
	// Dataprovide with Object[][]
//	@DataProvider
//	public Object[][] getData()
//	{
//		Object[][] data = {{"neelam@gmail.com","Neelam@123","ZARA COAT 3"},{"ayan123@gmail.com","Ayan@123","ADIDAS ORIGINAL"}};
//		return data;
//		
//	}
	
	// DataProvider with HashMap
//	@DataProvider
//	public Object[][] getData()
//	{
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("userName","neelam@gmail.com");
//		map1.put("password","Neelam@123");
//		map1.put("productName","ZARA COAT 3");
//
//		HashMap<String,String> map2 = new HashMap<String,String>();
//		map2.put("userName","ayan123@gmail.com");
//		map2.put("password","Ayan@123");
//		map2.put("productName","ADIDAS ORIGINAL");
//		
//		Object[][] data = {{map1},{map2}};
//		return data;
//		
//	}
	// DataProvider with JSON
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\TestComponents\\PurchaseOrder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };
		
	}
}