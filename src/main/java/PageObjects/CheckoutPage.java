package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;
	Actions actions;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		actions = new Actions(driver);
		PageFactory.initElements(driver, this); // PageFactory Initialization
	}

	// PageFactory
	@FindBy(css = "input[placeholder*='Country']")
	WebElement inputCountry;

	@FindBy(css = ".ta-results span")
	List<WebElement> countries;

	@FindBy(css = ".action__submit")
	WebElement placeOrder;

	public void selectCountry(String searchTerm, String countryName) throws InterruptedException

	{
		inputCountry.sendKeys(searchTerm);
		waitForElementToAppear(By.cssSelector(".ta-results span"));
		WebElement country = countries.stream().filter(c -> c.getText().trim().equalsIgnoreCase(countryName))
				.findFirst().orElse(null);

		actions.moveToElement(country).click().perform();

	}

	public ConfirmationPage placeOrder() {
		actions.moveToElement(placeOrder).click().perform();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;

	}

}
