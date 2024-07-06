package amazon.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import amazon.TestComponents.BaseTest;
import amazon.pageobjects.CartPage;
import amazon.pageobjects.CheckoutPage;
import amazon.pageobjects.ConfirmationPage;
import amazon.pageobjects.LandingPage;
import amazon.pageobjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	LandingPage landingPage;
	ProductCatalogue productCatalogue;
	CheckoutPage checkoutPage;
	ConfirmationPage confirmationPage;

	@Given("I landed on the Ecommerce page")
	public void I_landed_on_the_Ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String name, String password) {
		productCatalogue = landingPage.loginApplication(name, password);
	}

	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductCart(productName);
	}

	@And("^checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkoutPage = cartPage.goToCheckOut();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")

	public void message_displayed_confirmationPage(String string) {
		String confirmMessage = confirmationPage.VerifyConfirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void something_message_displayed(String string)
	{
		Assert.assertEquals(string, landingPage.getErrorMessage());	
		driver.close();
	}
}
