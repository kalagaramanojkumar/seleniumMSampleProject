package com.mysite.testcases;

import org.testng.annotations.Test;

import com.mysite.pageobject.ExistingSitesPage;
import com.mysite.pageobject.HomePage;

public class TC_AddNewSublocationTest extends BaseClass {
	
	@Test(priority = 0)
	public void verifyMySiteLogin()
	{
		driver.get(url);
		logger.info(" Given URL Opened");
		
		HomePage hp = new HomePage(driver);
		hp.verifysearchHeader("Search");
		logger.info("Login Verified, User successfully logged into MySite");
	}
	

	
	@Test(priority = 1)
    public void searchCustomerID()
    {
    	HomePage hp = new HomePage(driver);
    	hp.selectFromDropDown("CustomerID");
    	hp.searchWithCustomerId("14187");
    }
    
    @Test(priority =2)
    public void searchExistingSites()
    {
    	ExistingSitesPage esp = new ExistingSitesPage(driver);
    	esp.sitesearch("MYSITENEWSITEMNJ");
    	logger.info("SiteSearched successfully");
    	
    	
    }
    
    @Test(priority =3)
    public void createNewSubLocation()
    {
    	ExistingSitesPage esp = new ExistingSitesPage(driver);
    	esp.addNewSubLocation("Pune43", "4", "6", "AutomationTesting");
    	
    }
	
}
