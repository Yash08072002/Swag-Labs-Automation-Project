package com.swag_labs.testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.swag_labs.utilities.Readconfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseclass {
	// get url and browser details from Readconfigfile
	Readconfig config = new Readconfig();
	String url = config.Baseurl();
	String browser = config.Browser();

//constructer for webdriver and logger
	public static WebDriver driver;
	public static Logger logger;
	
	
	
	SoftAssert sa = new SoftAssert();

	// cones the browser
	@BeforeMethod
	public void setup() {
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		default:
			driver = null;
			break;
		}
		// implicity wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		logger = LogManager.getLogger("Swag_Labs");

		driver.get(url);
		logger.info("url open");

	}

	@AfterMethod
	public void teardown() {
		driver.close();
		//driver.quit();
	}
	
	public void capturerScreenShot() throws IOException {
		
		 Date CurrentDate = new Date();
		 String screenshotfilename= CurrentDate.toString().replace(" ", "-").replace(":", "-"); 
		 File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	     File dst = new File(System.getProperty("user.dir") + "\\ScreenShots\\" + screenshotfilename + ".png");
		 FileUtils.copyFile(screenshotFile,dst );	 
	}
	
}
