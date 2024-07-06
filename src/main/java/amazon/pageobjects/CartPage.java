package amazon.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import amazon.AbstractComponensts.AbstarctComponent;

public class CartPage extends AbstarctComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> carProducts;

	@FindBy(css = ".totalRow button")
	WebElement checkoutfile;

	public Boolean VerifyProductDisplay(String productname) {
		Boolean match = carProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productname));
		return match;
	}

	public CheckoutPage goToCheckOut() {
		
		JavaExecutor(checkoutfile);
	//	checkoutfile.click();
		CheckoutPage checkoutPage= new CheckoutPage(driver);
		
		return checkoutPage;
	}

}