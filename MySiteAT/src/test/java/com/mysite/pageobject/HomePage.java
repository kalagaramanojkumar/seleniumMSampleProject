package com.mysite.pageobject;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.mysite.testcases.BaseClass;

public class HomePage extends BaseClass {
	
	WebDriver driver;
	
	
public HomePage(WebDriver driver)
{
	this.driver= driver;
	PageFactory.initElements(driver, this);
}


@FindBy(xpath = "//input[starts-with(@id,'TAutoComplete_LR')]") WebElement searchText;
@FindBy(xpath="//input[@id='TAutoComplete_LRHLTT6TTSCFJONJ8']") WebElement customerNamesearchTxtBox;
@FindBy(xpath="//button[@class='searchButton bg-bt-indigo-shade-2']") WebElement searchBtn;
@FindBy(xpath = "//button[@id='TSelButton_LRHM39DL9V755TM3Z']") WebElement customerTextBtn;
@FindBy(xpath="//button[@aria-haspopup='listbox']") WebElement listBoxDropDown;
@FindBy(xpath="//li[@aria-label='In Customer ID']") WebElement dropdownCustomerID;
@FindBy(xpath="//li[@aria-label='In Contract Reference']") WebElement dropdownContractReference;
@FindBy(xpath="//li[@aria-label='In Contract ID']") WebElement dropdownContractID;
@FindBy(xpath="//input[@type='number']") WebElement customerIDSearchTxtBox;
@FindBy(xpath="//tbody/tr[1]/td[9]/tw-tableaction[1]/button[1]") WebElement arrowBtn;
//@FindBy(xpath="(//button[@class='t-component t-table-action t-clickable'])[1]") WebElement arrowBtn;
@FindBy(xpath="//h3[@class='mb-4']") WebElement searchHeader;

public void clickonSearchBtn() {
	searchBtn.click();
}

public void searchWithCustomerName(String customerName) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOf(searchText));
	searchText.sendKeys(customerName);
	searchBtn.click();
}

public void selectFromDropDown(String dropdownvalue) {
	listBoxDropDown.click();
	if(dropdownvalue.equalsIgnoreCase("CustomerID")) {
		dropdownCustomerID.click();
		logger.info("CustomerID selected from dropdown");
	}else if(dropdownvalue.equalsIgnoreCase("ContractReference")) {
		dropdownContractReference.click();
		logger.info("ContractReference selected from dropdown");
	}else if(dropdownvalue.equalsIgnoreCase("ContractID")) {
		dropdownContractID.click();
		logger.info("ContractID selected from dropdown");
	}
	
}

public void searchWithCustomerId(String customerID) 
{
	customerIDSearchTxtBox.sendKeys(customerID);
	searchBtn.click();
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	wait.until(ExpectedConditions.visibilityOf(arrowBtn));
	if(arrowBtn.isDisplayed())
	{
		FluentWait fwait =new FluentWait(driver); 
		fwait.withTimeout(Duration.ofSeconds(5000));
		fwait.pollingEvery(Duration.ofSeconds(100));
		fwait.ignoring(NoSuchElementException.class);
		fwait.until(ExpectedConditions.visibilityOf(arrowBtn));
	arrowBtn.click();
	Assert.assertTrue(true);
	logger.info("Results shown for searchWithCustomerID: "+customerID);
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		
		e.printStackTrace();
	}
	}
	else
	{
		captureScreenshot(driver, "searchCustomerID");
		Assert.assertTrue(false);
		logger.info("Failed to show results for searchWithCustomerID: "+customerID);
		
	}
}

public void verifysearchHeader(String header)
{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	wait.until(ExpectedConditions.visibilityOf(searchHeader));
	String text = searchHeader.getText();
	if(text.equalsIgnoreCase(header))
	{
		logger.info("Login Test Passed");

		Assert.assertTrue(true);
	}
	else
	{
		logger.info("Login Test Failed");
		captureScreenshot(driver, "verifyMySiteLogin");

		Assert.assertTrue(false);
		
	}
		
}

}

