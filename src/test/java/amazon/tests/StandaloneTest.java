package amazon.tests;

import java.time.Duration;

import java.util.List;

//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import amazon.pageobjects.LandingPage;

public class StandaloneTest {

	public static void main(String[] args) {
		String productname = "ZARA COAT 3";
		// WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");

		LandingPage landingPage = new LandingPage(driver);

		driver.findElement(By.id("userEmail")).sendKeys("pramodkn68@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Pramod@0303");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cart = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean match = cart.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productname));

		Assert.assertTrue(match);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)");

		WebElement e = driver.findElement(By.cssSelector(".totalRow button"));
		js.executeScript("arguments[0].click(0);", e);

		// js.executeScript("window.scrollBy(0,200)");

		// Actions a = new Actions(driver);
		// a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select
		// Country']")), "india").build().perform();

		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("india");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		js.executeScript("window.scrollBy(0,100)");
		WebElement f = driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]"));
		js.executeScript("arguments[0].click(0);", f);
		// driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();

		js.executeScript("window.scrollBy(0,100)");

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
		driver.findElement(By.cssSelector(".action__submit")).click();

		String confirmation = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(confirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		driver.close();

	}

}
