package amazon.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import amazon.AbstractComponensts.AbstarctComponent;

public class LandingPage extends AbstarctComponent{
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
	super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(id="userEmail")
	WebElement Email;
	
	@FindBy(id="userPassword")
	WebElement Password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='ng-tns']")
	WebElement errorMessage;
//	By errorMessage= By.cssSelector("[class*='ng-tns']");
	
	public ProductCatalogue loginApplication(String useremail, String userpassword) {
		Email.sendKeys(useremail);
		Password.sendKeys(userpassword);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}

	public String getErrorMessage() {
		
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
		}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

}