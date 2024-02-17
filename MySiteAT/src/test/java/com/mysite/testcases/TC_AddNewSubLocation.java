package com.mysite.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.mysite.pageobject.ExistingSitesPage;
import com.mysite.pageobject.HomePage;

public class TC_AddNewSubLocation extends BaseClass {

	
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
    	logger.info("customersearchSuccessful with CustomerID ");
    }
    
    @Test(priority =2)
    public void createNewSubLocation()
    {
    	ExistingSitesPage esp = new ExistingSitesPage(driver);
    	esp.sitesearch("MYSITENEWSITEMNJ");
    	logger.info("SiteSearched successfully");
    	esp.addNewSubLocation("Hay12", "51", "1", "MySiteAutomation1");
    	//esp.toCheckSublocationCreated();
    	
//    	if(esp.sublocationToastMessage.isDisplayed() && esp.sublocationToastMessage.getText().contains("Sublocation with this name already exists"))
//    	{
//    		captureScreenshot(driver, "createNewSubLocation");
//    		logger.info("SubLocation Creation Failed");
//    		Assert.assertTrue(false);	
//    	}
//    	else
//    	{
//    		logger.info("Sublocation Created successfully");		
//    		Assert.assertTrue(true);
//    	}
    	
    }
}
