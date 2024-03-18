package com.swag_labs.utilities;

import java.io.File;
import java.text.SimpleDateFormat;


import org.apache.poi.hpsf.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.swag_labs.testcase.baseclass;

public class ExtendListnerClass extends baseclass implements ITestListener {

	// Creatte the object for extend report
	ExtentSparkReporter htmlreport;
	ExtentReports report;
	ExtentTest test;

	public void configureReport() {

		Readconfig config = new Readconfig();
		
		// initialize the object
		htmlreport = new ExtentSparkReporter("");
		report = new ExtentReports();
		report.attachReporter(htmlreport);

		// add system information/enviroment info to report
		report.setSystemInfo("machine", "laptop");
		report.setSystemInfo("os", "Windows");
		report.setSystemInfo("browser", config.Browser());
		report.setSystemInfo("user name", "yash");

		// configer to change the feel and look of report
		htmlreport.config().setDocumentTitle("Swag labs report");
		htmlreport.config().setResourceCDN("this is first report");
		htmlreport.config().setTheme(Theme.DARK);
	}

	// ItestListner methods

	// OnStart method is called when any Test starts.
	public void onStart(ITestContext Result) {
		configureReport();
		System.out.println("On Start method invoked....");
	}

	// onFinish method is called after all Tests are executed
	public void onFinish(ITestContext Result) {
		System.out.println("On Finished method invoked....");
		report.flush();// it is mandatory to call flush method to ensure information is written to the
						// started reporter.

	}

	// When Test case get failed, this method is called.

	public void onTestFailure(ITestResult Result) {
		System.out.println("Name of test method failed:" + Result.getName());
		test = report.createTest(Result.getName());// create entry in html report
		test.log(Status.FAIL,
				MarkupHelper.createLabel("Name of the failed test case is: " + Result.getName(), ExtentColor.RED));

		String screenShotPath = System.getProperty("user.dir") + "\\ScreenShots\\" + Result.getName() + ".png";

		File screenShotFile = new File(screenShotPath);

		if (screenShotFile.exists()) {
			test.fail("Captured Screenshot is below:" + test.addScreenCaptureFromPath(screenShotPath));

		}
	}

	// When Test case get Skipped, this method is called.

	public void onTestSkipped(ITestResult Result) {
		System.out.println("Name of test method skipped:" + Result.getName());

		test = report.createTest(Result.getName());
		test.log(Status.SKIP,
				MarkupHelper.createLabel("Name of the skip test case is: " + Result.getName(), ExtentColor.YELLOW));
	}

	// When Test case get Started, this method is called.

	public void onTestStart(ITestResult Result) {
		System.out.println("Name of test method started:" + Result.getName());

	}

	// When Test case get passed, this method is called.

	public void onTestSuccess(ITestResult Result) {
		System.out.println("Name of test method sucessfully executed:" + Result.getName());

		test = report.createTest(Result.getName());
		test.log(Status.PASS,
				MarkupHelper.createLabel("Name of the passed test case is: " + Result.getName(), ExtentColor.GREEN));
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {

	}

}
