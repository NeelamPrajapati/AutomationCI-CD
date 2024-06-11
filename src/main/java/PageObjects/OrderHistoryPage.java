package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class OrderHistoryPage extends AbstractComponent {

	WebDriver driver;

	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // PageFactory Initialization
	}

	// PageFactory
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderProducts;

	By orderProductsBy = By.cssSelector("tr td:nth-child(3)");

	public Boolean verifyProductDisplay(String productName) throws InterruptedException {
		waitForElementToAppear(orderProductsBy);
		Boolean match = orderProducts.stream().anyMatch(orderProduct -> orderProduct.getText().equalsIgnoreCase(productName));
		return match;
		
		}

	

}
