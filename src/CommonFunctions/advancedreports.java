package CommonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
public class advancedreports{
ExtentReports report;
ExtentTest test;
WebDriver driver;
String Expected="";
String Actual="";
@BeforeTest
public void generateReport()
{
	//define path to generate html report
	report= new ExtentReports("./Reports/Demo.html");
	}
@BeforeMethod
public void setUp()
{
	driver= new ChromeDriver();
	driver.manage().deleteAllCookies();
	driver.get("https://google.com");
	driver.manage().window().maximize();
}
@Test
public void PassTest()
{
test= report.startTest("Test case Pass");
test.assignAuthor("Ranga");
Expected ="Google";
Actual =driver.getTitle();
if(Actual.equalsIgnoreCase(Expected))
{
	Reporter.log("Title is matching::"+Expected+"  "+Actual,true);
	test.log(LogStatus.PASS, "Title is matching::"+Expected+"  "+Actual);
}
else
{
	Reporter.log("Title is Not matching::"+Expected+"  "+Actual,true);
	test.log(LogStatus.FAIL, "Title is Not matching::"+Expected+"  "+Actual);
}
}
@Test
public void failTest()
{
	test= report.startTest("Test case Fail");
	test.assignAuthor("Ranga");
	Expected ="Gmail";
	Actual =driver.getTitle();
	if(Actual.equalsIgnoreCase(Expected))
	{
		Reporter.log("Title is matching::"+Expected+"  "+Actual,true);
		test.log(LogStatus.PASS, "Title is matching::"+Expected+"  "+Actual);
	}
	else
	{
		Reporter.log("Title is Not matching::"+Expected+"  "+Actual,true);
		test.log(LogStatus.FAIL, "Title is Not matching::"+Expected+"  "+Actual);
	}	
}
@AfterMethod
public void tearDown()
{
	report.endTest(test);
	report.flush();
	driver.close();
}
}









public class advancedreports {

}
