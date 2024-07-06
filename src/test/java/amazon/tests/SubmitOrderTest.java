package amazon.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import amazon.TestComponents.BaseTest;
import amazon.TestComponents.Retry;
import amazon.pageobjects.CartPage;
import amazon.pageobjects.CheckoutPage;
import amazon.pageobjects.ConfirmationPage;
import amazon.pageobjects.OrderPage;
import amazon.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	// String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = "Purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException {

		// LandingPage landingPage= launchApplication();
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckOut();

		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.VerifyConfirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dataProvider = "getData",dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest(HashMap<String, String> input) throws IOException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(input.get("productName")));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\amazon\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

//	@DataProvider
//	public Object[][] getData1() {
//		Object[][] data =new Object[][] {{"pramodkn68@gmail.com","Pramod@0303", "ZARA COAT 3"}, {"pramodkn00@gmail.com","Kindleus123","ADIDAS ORIGINAL"}};
//		return data;
	// }

//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "pramodkn00@gmail.com");
//	map.put("password", "Kindleus123");
//	map.put("productName", "ADIDAS ORIGINAL");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "pramodkn68@gmail.com");
//	map1.put("password", "Pramod@0303");
//	map1.put("productName", "ZARA COAT 3");

}
