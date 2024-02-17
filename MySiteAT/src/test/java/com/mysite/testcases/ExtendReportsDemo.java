package com.mysite.testcases;

import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtendReportsDemo {
	
	
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeTest
	public void config()
	{
	//	extent = new ExtentReports(System.getProperty("user.dir")+"/Reports/LogGeneration.html");
	}

}
