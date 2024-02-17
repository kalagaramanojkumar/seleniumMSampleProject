package com.mysite.pageobject;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mysite.testcases.BaseClass;

public class AddANewSite extends BaseClass {
	WebDriver driver;

	public AddANewSite(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "siteName")
	private WebElement siteNameTxtBox;
	@FindBy(xpath = "//button[@aria-label='Map Search']")
	private WebElement mapSearchBtn;
	@FindBy(xpath = "//input[@placeholder='Search in Map']")
	private WebElement searchinMaptxtBox;
	@FindBy(xpath = "(//span[@class='address-text'])[1]")
	private WebElement searchResult;
	@FindBy(xpath = "//button[@aria-label='Use this address']")
	private WebElement useThisAddressBtn;
	@FindBy(xpath = "//button[@aria-label='Submit']")
	private WebElement submitBtn;
	@FindBy(xpath = "//div[contains(@class,'t-toast-message-text-content')]")
	private WebElement toastMsgBanner;
	//@FindBy(xpath = "//div[contains(@class,'t-toast-message-text-content')]/div[contains(@class,'t-toast-title']")
//	@FindBy(xpath = "//div[contains(@class,'t-toast-title']")
	@FindBy(xpath = "//div[starts-with(@class,'t-toast-title')]")
	private WebElement toastResult;

	public void enterSiteName(String siteName) {
		siteNameTxtBox.sendKeys(siteName);
	}

	public void clickonMapSearchBtn() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(mapSearchBtn));
		mapSearchBtn.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void searchinMap(String address) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(searchinMaptxtBox));
		searchinMaptxtBox.sendKeys(address);
		searchinMaptxtBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(searchResult));
		searchResult.click();
		wait.until(ExpectedConditions.visibilityOf(useThisAddressBtn));
		useThisAddressBtn.click();

	}

	public void clickonSubmitSiteBtn() {
	

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(submitBtn));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
//		Actions action = new Actions(driver);
//		action.sendKeys(Keys.HOME);
//		action.sendKeys(Keys.END);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}


		
		submitBtn.click();
	}

	public void siteCreationcheck()

	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOf(toastMsgBanner));
		if (toastMsgBanner.isDisplayed()) {
			String result = toastResult.getText();
			System.out.println(result);
			logger.info("Site Creation status: "+result);
			if (result.equalsIgnoreCase("Success")) {
				Assert.assertTrue(true);
			} else if (result.equalsIgnoreCase("Error")) {
				captureScreenshot(driver, "createNewSite");
				Assert.assertTrue(false);
				logger.info("SiteCreationFailed with error message");
			}
		} else {
			logger.info("SiteCreationFailed");
		}
	}

}
