package com.bt.MyOrder.common.pages.myQuotePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuoteCreationPage {
    private WebDriver driver;

    public QuoteCreationPage(WebDriver driver)
    {
            this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(xpath="//input[@id='quoteName']")
    private WebElement quoteNameTxtBox;

    @FindBy(xpath = "//input[@id='orNumber']")
    private WebElement  oppurtunityReferenceTxtBox;

    @FindBy(xpath="//*[contains(@aria-label,'24')]")
    private WebElement term24;

    @FindBy(xpath="//tw-button[@label='Submit']")
    private WebElement submitBtn;

    public void enterQuoteName(String quoteName) {
        quoteNameTxtBox.sendKeys(quoteName);
    }

    public void enterOpportunityReferenceName(String opportunityReferenceName) {
        oppurtunityReferenceTxtBox.sendKeys(opportunityReferenceName);
    }

    public void selectTerm() {
        term24.click();
    }

    public void clickSubmitBtn() {
        submitBtn.click();
    }


}
