package com.bt.MyOrder.common.mySitePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
	//https://customerinventory.t1.nat.bt.com/mice-apps/site/#/home
	
	private WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
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
		}else if(dropdownvalue.equalsIgnoreCase("ContractReference")) {
			dropdownContractReference.click();
		}else if(dropdownvalue.equalsIgnoreCase("ContractID")) {
			dropdownContractID.click();
		}
		
	}

}
