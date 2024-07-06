package amazon.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import amazon.TestComponents.BaseTest;
import amazon.TestComponents.Retry;
import amazon.pageobjects.CartPage;
import amazon.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer= Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

		landingPage.loginApplication("pramodkn68@gmail.com", "Pamod@0303");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productname = "ZARA COAT 3";

		ProductCatalogue productCatalogue = landingPage.loginApplication("pramodkn68@gmail.com", "Pramod@0303");

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductCart(productname);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}


}
