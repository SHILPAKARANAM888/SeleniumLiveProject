package CommonFunctions;


	import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

	public class PropertyFile {
	WebDriver driver;
	Properties p;
	@BeforeTest
	public void setUp() throws Throwable
	{
		p= new Properties();
		p.load(new FileInputStream("C:\\Users\\user\\eclipse-workspace\\SeleniumliveProject1\\Login.properties"));
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("Url"));
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}
	@Test
	public void verifyLogin()
	{
		driver.findElement(By.xpath(p.getProperty("Objreset"))).click();
		driver.findElement(By.xpath(p.getProperty("Objusername"))).sendKeys(p.getProperty("enteruser"));
		driver.findElement(By.xpath(p.getProperty("Objpassword"))).sendKeys(p.getProperty("enterpass"));
		driver.findElement(By.xpath(p.getProperty("ObjLogin"))).sendKeys(Keys.ENTER);
	}
	@AfterTest
	public void tearDown()throws Throwable
	{
		Thread.sleep(5000);
		driver.quit();
	}
	}


