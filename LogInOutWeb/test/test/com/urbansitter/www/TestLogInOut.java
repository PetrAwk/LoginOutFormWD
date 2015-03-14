package test.com.urbansitter.www;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class TestLogInOut {
		
		private static String usName = "Paul Diamond";
		private static String usNameEmail = "pauldiamondparent@gmail.com";
		private static String usPwd = "paul123456";
		private static String homeUrl = "http://www.urbansitter.com";
		private static String homePgTitle = "Babysitters, Nannies, and Child Care – UrbanSitter Babysitting";
		private static WebDriver driver;
		
		@Before
		public void testSetUp()throws InterruptedException {
			//Firefox
			driver = new FirefoxDriver();
			
			//Uncomment section in case Chrome Windows
			//System.setProperty("webdriver.chrome.driver", "C://Selenium//Chromedriver//Win//chromedriver.exe");
			//driver = new ChromeDriver();
			
			//Uncomment section in case Chrome Mac OS X
			//System.setProperty("webdriver.chrome.driver", "C://Selenium//Chromedriver//Mac//chromedriver.exe");
			//driver = new ChromeDriver();
			
			driver.manage().window().maximize();
			driver.navigate().to(homeUrl);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
		
		@Test
		public void testLogInOutWithEmail() throws Exception {
			driver.findElement(By.xpath(".//*[@id='us-header-wrap']/div/div/div[2]/div[1]/button")).click();
			WebElement eMail = 
					driver.findElement(By.id("us-native-input-email"));
			eMail.clear();
			eMail.sendKeys(usNameEmail);
			//testEnterPassword
			WebElement usPasswd = 
					driver.findElement(By.id("us-native-input-password"));
			usPasswd.clear();
			usPasswd.sendKeys(usPwd);
			//testLogInWithEmail
			driver.findElement(By.xpath(".//*[@id='us-native-email-wrap']/div/div[4]/button")).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//Verify Login successful			
			WebDriverWait driverWait = new WebDriverWait(driver, 10);
			WebElement dynamicElement = 
					driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='top-menu-items']/ul/li[1]/span[1]")));
			System.out.println(dynamicElement.getText() + " -> Login: PASS");
			driver.findElement(By.className("custom-logout")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			System.out.println("-> Log out: PASS");
			driver.quit();
		}
		
		//@Test
		public void testAssertUrl() throws Exception {
			assertEquals(homePgTitle, driver.getTitle());
			driver.quit();
		}
		
		//@Test
		public void testUserLogInFormPresent() throws Exception {
			driver.findElement(By.xpath(".//*[@id='us-header-wrap']/div/div/div[2]/div[1]/button")).click();
			WebElement usLogInForm = 
					driver.findElement(By.id("usLoginModal"));
			assertNotNull("usLoginModal", usLogInForm);
			System.out.println("Login form present: PASS");
			driver.quit();
		}
		
		//@Test
		public void testUsLogOut() throws Exception {
		}
			
		//@Test
		public void testFacebookLogIn(){
		}
				
		//@Test
		public void testLinkedinLogIn(){		
		}
		
		@After
		public void testEnd(){
			driver.quit();
		}
}
