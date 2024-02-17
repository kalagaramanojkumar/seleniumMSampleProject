package com.mysite.testcases;

import com.bt.MyOrder.common.BasePage;
import com.mysite.pageobject.ExistingSitesPage;
//import com.bt.MyOrder.common.mySitePages.HomePage;
import com.mysite.pageobject.HomePage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class TC_LoginTest extends BasePage {


    @BeforeTest
    public void setup(){
        browserLaunch("Edge","https://customerinventory.t1.nat.bt.com/mice-apps/site/#/home");
    }

    @Ignore
    @Test(priority = 2)
    public void searchCustomer(){
        HomePage hp = new HomePage(driver);
        hp.searchWithCustomerName("Test+");
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
    	esp.addNewSubLocation("Ayodhya", "108", "0", "AutomationTesting");
    }

}