package com.mysite.testcases;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.mysite.pageobject.AddANewSite;
import com.mysite.pageobject.ExistingSitesPage;
import com.mysite.pageobject.HomePage;

public class TC_AddNewSite extends BaseClass{
	
	@Test
	public void createNewSite()
	{
		HomePage hp = new HomePage(driver);
		hp.selectFromDropDown("CustomerID");
		hp.searchWithCustomerId("14187");
		ExistingSitesPage esp = new ExistingSitesPage(driver);
		esp.clickonAddaNewSite();
		AddANewSite addsite = new AddANewSite(driver);
		addsite.enterSiteName("MNJNewSiteAutoTest9");
		addsite.clickonMapSearchBtn();
		addsite.searchinMap("K6 Telephone Boxes Great George St London SW1A 2HQ United Kingdom");
		addsite.clickonSubmitSiteBtn();
		addsite.siteCreationcheck();

		
	}

	
}
