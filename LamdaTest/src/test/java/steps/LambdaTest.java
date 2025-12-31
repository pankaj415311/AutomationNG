package steps;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LambdaTest {

	private WebDriver driver;

	@BeforeClass
	@Parameters({ "browser", "version", "platform" })
	public void setUp(@Optional("chrome") String browser, @Optional("143.0") String version,
			@Optional("Windows 11") String platform) throws Exception {
		
		  // Selenium Grid Hub URL (replace with your Grid hub) 
		String username = "pankajsutar16";
		String accessKey = "LT_cE0HGPa47eQiRwJ0Y2zMylkXjRHxV1HdPVJ3NsT35Ij9EOm";
		  
		  URL hubURL = new URL("https://" + username + ":" + accessKey +"@hub.lambdatest.com/wd/hub");
		  
		  // Desired Capabilities for Grid 
		  MutableCapabilities caps = new MutableCapabilities();
		  caps.setCapability("browserName", browser);
		  caps.setCapability("browserVersion", version);
		  caps.setCapability("platformName", platform);
		  driver = new RemoteWebDriver(hubURL, caps);
		 	
	}

	@Test
	public void testLambdaIntegrationFlow() throws InterruptedException {
		// 1. Navigate
		driver.manage().window().maximize();
		driver.get("https://www.lambdatest.com");

		// 2. Explicit wait for DOM
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("body")));

		// 3. Scroll to "Explore all Integrations"
		WebElement exploreIntegrations = driver.findElement(By.xpath("//*[text()='Explore all Integrations']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", exploreIntegrations);
		System.out.println("link:-" + exploreIntegrations);
		// 4. Click link (opens new tab)

		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		exploreIntegrations = wait1
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Explore all Integrations']")));

		Actions actions = new Actions(driver);

		// Hold CONTROL and click
		actions.keyDown(Keys.CONTROL).click(exploreIntegrations).keyUp(Keys.CONTROL).build().perform();

		// exploreIntegrations.click();

		// 5. Save window handles
		Set<String> handles = driver.getWindowHandles();
		List<String> windowList = new ArrayList<>(handles);
		System.out.println("Window Handles: " + windowList);

		// Switch to new tab
		driver.switchTo().window(windowList.get(1));

		// 6. Verify URL
		String expectedURL = "https://www.lambdatest.com/integrations";
		Assert.assertEquals(driver.getCurrentUrl(), expectedURL, "URL mismatch!");

		// 7. Scroll to "Codeless Automation"
		WebElement codelessAutomation = driver.findElement(By.xpath("//h2[contains(text(),'Codeless Automation')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", codelessAutomation);

		// 8. Click TestingWhiz link
		WebElement testingWhizLink = driver.findElement(By.linkText("INTEGRATE TESTING WHIZ WITH LAMBDATEST"));
		testingWhizLink.click();

		// 9. Verify title
		String expectedTitle = "TestingWhiz Integration With LambdaTest";
		String ActualTitle=driver.getTitle();
		System.out.println("Actual Title:"+ActualTitle);
		SoftAssert softA = new SoftAssert();
		softA.assertEquals(ActualTitle, expectedTitle, "Title mismatch!");

		// 10. Close current window
		driver.close();

		// 11. Print window count
		driver.switchTo().window(windowList.get(0));
		System.out.println("Window count after closing: " + driver.getWindowHandles().size());

		// 12. Navigate to blog
		driver.get("https://www.lambdatest.com/blog");

		// 13. Click Community link
		WebElement communityLink = driver.findElement(By.linkText("Community"));
		communityLink.click();

		// Switch to new tab if opened
		List<String> newHandles = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(newHandles.get(newHandles.size() - 1));

		Assert.assertEquals(driver.getCurrentUrl(), "https://community.lambdatest.com/", "Community URL mismatch!");

		// 14. Close browser
		driver.quit();
	}
}
