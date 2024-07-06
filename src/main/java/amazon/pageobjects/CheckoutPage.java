package amazon.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import amazon.AbstractComponensts.AbstarctComponent;

public class CheckoutPage extends AbstarctComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement selectCountr;

	@FindBy(css = ".action__submit")
	WebElement submit;

	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {

	//	JavaExecutor(selectCountr);
		country.click();
		country.sendKeys(countryName);
		waitForElementToAppear(results);
		selectCountr.click();
	}

	public ConfirmationPage submitOrder() {

		JavaExecutor(submit);
	//	submit.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
}
