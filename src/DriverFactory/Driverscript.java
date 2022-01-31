package DriverFactory;

import org.openqa.selenium.WebDriver;

import CommonFunctions.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class Driverscript {
	public static WebDriver driver;
	String inputpath ="C:\\Users\\user\\eclipse-workspace\\SeleniumliveProject1\\Testinput\\hybridData.xlsx";
	String outputpath ="C:\\Users\\user\\eclipse-workspace\\SeleniumliveProject1\\test-output\\hybridresults.xlsx";
	public void startTest()throws Throwable
	{
		//to access excel methods
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//iterate all rows in Mastertestcases sheet
		for(int i=1;i<=xl.rowCount("MasterTestCases");i++)
		{
			String moduleStatus="";
			if(xl.getCellData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{
				//store corresponding Sheet into TCModule
				String TCModule =xl.getCellData("MasterTestCases", i, 1);
				//iterate all in TCModule sheet
				for(int j=1;j<=xl.rowCount(TCModule);j++)
				{
					//read all cells from TCModule
					String Description =xl.getCellData(TCModule, j, 0);
					String FunctionName =xl.getCellData(TCModule, j, 1);
					String LocatorType =xl.getCellData(TCModule, j, 2);
					String LocatorValue =xl.getCellData(TCModule, j, 3);
					String TestData = xl.getCellData(TCModule, j, 4);
					try
					{
					 if(FunctionName.equalsIgnoreCase("startBrowser"))	
					 {
						 driver =FunctionLibrary.startBrowser();
					 }
					 else if(FunctionName.equalsIgnoreCase("openApplication"))
					 {
						 FunctionLibrary.openApplication(driver);
					 }
					 else if(FunctionName.equalsIgnoreCase("waitForElement"))
					 {
						 FunctionLibrary.waitForElement(driver, LocatorType, LocatorValue, TestData);
					 }
					 else if(FunctionName.equalsIgnoreCase("typeAction"))
					 {
						 FunctionLibrary.typeAction(driver, LocatorType, LocatorValue, TestData);
					 }
					 else if(FunctionName.equalsIgnoreCase("clickAction"))
					 {
						 FunctionLibrary.clickAction(driver, LocatorType, LocatorValue);
					 }
					 else if(FunctionName.equalsIgnoreCase("validateTitle"))
					 {
						 FunctionLibrary.validateTitle(driver, TestData);
					 }
					 else if(FunctionName.equalsIgnoreCase("closeBrowser"))
					 {
						 FunctionLibrary.closeBrowser(driver);
					 }
					//write as pass into status cell in TCModule
					 xl.setCellData(TCModule, j, 5, "Pass", outputpath);
					 moduleStatus="True";
					}catch(Throwable t)
					{
						System.out.println(t.getMessage());
						//write as fail into status cell in TCModule
						xl.setCellData(TCModule, j, 5, "Fail", outputpath);
						moduleStatus="False";

					}
					if(moduleStatus.equalsIgnoreCase("True"))
					{
						xl.setCellData("MasterTestCases", i, 3, "Pass", outputpath);
					}
					if(moduleStatus.equalsIgnoreCase("False"))
					{
						xl.setCellData("MasterTestCases", i, 3, "Fail", outputpath);
					}
				}
			}
			else 
			{
				//write as blocked into status cell which are flaged into N
				xl.setCellData("MasterTestCases", i, 3, "Blocked", outputpath);
			}
		}
	}
}
	
