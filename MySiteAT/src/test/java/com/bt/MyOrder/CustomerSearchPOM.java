package com.bt.MyOrder;

import com.bt.MyOrder.common.BasePage;
import com.bt.MyOrder.common.pages.myQuotePages.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CustomerSearchPOM extends BasePage {

    @BeforeTest
    public void setUp() {
        browserLaunch("Edge","https://sit.myquote.bt.com/my-quote/quote/#/home");
    }
    @Test(priority = 0)
    public void customerSearch() {
        CustomerSearchPage csp = new CustomerSearchPage(driver);
        csp.customerSearch("MYQUOTE TEST CUSTOMER");
        csp.salesChannelSearchAndCreateQuote("BT JAPAN");
    }

    @Test(priority = 1)
    public void quoteCreate() {
        QuoteCreationPage qcp = new QuoteCreationPage(driver);
        qcp.enterQuoteName("AutomatedQuoteCreatedByMyOrderTeam");
        qcp.enterOpportunityReferenceName("AutomatedOpportunityReferenceCreatedByMyOrderTeam");
        qcp.selectTerm();
        qcp.clickSubmitBtn();
    }

    @Test(priority = 2)
    public void groupCreation() {
        CreateGroup cg = new CreateGroup(driver);
        cg.createGroup("ICR");
        cg.clickSubmitBtn();
    }

    @Test(priority = 3)
    public void productAdd () {
        AddProductPage app = new AddProductPage(driver);
        app.addProduct("icR");
    }

    @Test(priority = 4)
    public void configProduct() {
        ProductConfigurationPage pcp = new ProductConfigurationPage(driver);
        pcp.configProductWithGivenValues();
    }
}
