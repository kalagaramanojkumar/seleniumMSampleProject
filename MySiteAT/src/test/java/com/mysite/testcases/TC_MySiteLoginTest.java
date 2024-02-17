package com.mysite.testcases;

import org.testng.annotations.Test;

import com.mysite.pageobject.HomePage;

public class TC_MySiteLoginTest extends BaseClass {
	
	@Test
	public void verifyMySiteLogin()
	{
		driver.get(url);
		logger.info(" Given URL Opened");
		
		
		HomePage hp = new HomePage(driver);
		hp.verifysearchHeader("Search");
		logger.info("Login Verified, User succesfully logged into MySite");
	}
	

}
