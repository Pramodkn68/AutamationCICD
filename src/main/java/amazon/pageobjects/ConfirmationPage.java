package amazon.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import amazon.AbstractComponensts.AbstarctComponent;

public class ConfirmationPage extends AbstarctComponent {
	WebDriver driver;

	@FindBy(css = ".hero-primary")
	WebElement confirmationmessage;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public String VerifyConfirmationMessage() {

		return confirmationmessage.getText();
	}
}
