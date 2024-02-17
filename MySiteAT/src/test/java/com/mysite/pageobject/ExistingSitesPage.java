package com.mysite.pageobject;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mysite.testcases.BaseClass;

public class ExistingSitesPage extends BaseClass {

	private WebDriver driver;

	public ExistingSitesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//input[@type='text'])[1]")
	private WebElement siteSearch;
	@FindBy(xpath = "//button[@class='t-row-toggler']")
	private WebElement expandBtn;
	@FindBy(xpath = "(//button[@aria-label='Add new'])[1]")
	private WebElement addNewSublocationBtn;
	@FindBy(xpath = "//input[@id='subPremise']")
	private WebElement subLocationlocator;
	@FindBy(xpath = "//input[@id='floor']")
	private WebElement floorlocator;
	@FindBy(xpath = "//input[@id='room']")
	private WebElement roomlocator;
	@FindBy(xpath = "//input[@id='comments']")
	private WebElement commentlocator;
	@FindBy(xpath = "//button[@aria-label='Submit']")
	private WebElement sublocationSubmitBtn;
	@FindBy(xpath = "//*[contains(@class,'t-toast-detail ng-tns-')]")
	private WebElement sublocationToastMessage;
	@FindBy(xpath = "//button[@title='Expand to see locations and contacts for this site']")
	private WebElement siteArrayKey;
	@FindBy(xpath = "(//tbody[@class='t-datatable-tbody'])[2]")
	private WebElement sublocationTable;
	String givenSublocationName;
	@FindBy(xpath="(//a[@role='menuitem'])[2]")
	private WebElement addANewSiteTab;

	public void sitesearch(String siteName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));
		wait.until(ExpectedConditions.visibilityOf(siteSearch));
		siteSearch.sendKeys(siteName);

		wait.until(ExpectedConditions.visibilityOf(expandBtn));
		expandBtn.click();
		logger.info("SiteSearch expandButton Clicked");
	}
	
	public void clickonAddaNewSite()
	{
		addANewSiteTab.click();
		logger.info("Clicked on Add a New Site Tab");
	}

	public void addNewSubLocation(String subLocationName, String floor, String room, String comments) {
		givenSublocationName = subLocationName;
		System.out.println(givenSublocationName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(addNewSublocationBtn));
		addNewSublocationBtn.click();
		logger.info("clicked on add new sublocation button");
		wait.until(ExpectedConditions.visibilityOf(subLocationlocator));
		subLocationlocator.sendKeys(subLocationName);
		floorlocator.sendKeys(floor);
		roomlocator.sendKeys(room);
		commentlocator.sendKeys(comments);
		wait.until(ExpectedConditions.visibilityOf(sublocationSubmitBtn));
		sublocationSubmitBtn.click();
		logger.info("Clicked on createSubLocation Button");

//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		try {
			if(sublocationToastMessage.isDisplayed())
			{
				logger.info("Sublocation creation Failed.");
				captureScreenshot(driver, "createNewSubLocation");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			
			logger.info("New Sublocation Created successfully.");
			Assert.assertTrue(true);
		}

			
		

//		if () {
//
//			List<WebElement> sublocationtablevalues = sublocationTable
//					.findElements(By.xpath("//tbody[@class='t-datatable-tbody']/tr[@class='ng-star-inserted']"));
//			System.out.println(sublocationtablevalues.size());
//			WebElement locationName = sublocationtablevalues.stream()
//					.filter(sublocationtablevalue -> sublocationtablevalue.findElement(By.tagName("td")).getText()
//							.equals(givenSublocationName))
//					.findFirst().orElse(null);
//			System.out.println(locationName);
//			if (locationName.getText().equals(givenSublocationName)) {
//				logger.info("SublocationCreated Successfully");
//				Assert.assertTrue(true);
//			} else {
//				logger.info("Sublocation Creation Failed");
//				Assert.assertTrue(false);
//				captureScreenshot(driver, "createNewSubLocation");
//			}
//		} else if (sublocationToastMessage.isDisplayed()) {
//			logger.info("Sublocation Creation Failed");
//			captureScreenshot(driver, "createNewSubLocation");
//			Assert.assertTrue(false);
//			
//		}

//			wait.until(ExpectedConditions.visibilityOf(siteArrayKey));
//			if (siteArrayKey.isDisplayed()) {
//
//				List<WebElement> sublocationtablevalues = sublocationTable
//						.findElements(By.xpath("//tbody[@class='t-datatable-tbody']/tr[@class='ng-star-inserted']"));
//				WebElement locationName = sublocationtablevalues.stream()
//						.filter(sublocationtablevalue -> sublocationtablevalue.findElement(By.tagName("td")).getText()
//								.equals(givenSublocationName))
//						.findAny().orElse(null);
//				if (locationName.getText().equals(givenSublocationName)) {
//					logger.info("SublocationCreated Successfully");
//					Assert.assertTrue(true);
//				} else {
//					logger.info("Sublocation Creation Failed");
//					Assert.assertTrue(false);
//					captureScreenshot(driver, "createNewSubLocation");
//				}
//			}
	}

//	public void toCheckSublocationCreated() {
//		if (sublocationToastMessage.isDisplayed()) {
//			captureScreenshot(driver, "createNewSubLocation");
//			logger.info("SubLocation Creation Failed");
//			Assert.assertTrue(false);
//
//		} else {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			wait.until(ExpectedConditions.visibilityOf(siteArrayKey));
//			if (siteArrayKey.isDisplayed()) {
//
//				List<WebElement> sublocationtablevalues = sublocationTable
//						.findElements(By.xpath("//tbody[@class='t-datatable-tbody']/tr[@class='ng-star-inserted']"));
//				WebElement locationName = sublocationtablevalues.stream()
//						.filter(sublocationtablevalue -> sublocationtablevalue.findElement(By.tagName("td")).getText()
//								.equals(givenSublocationName))
//						.findAny().orElse(null);
//				if (locationName.getText().equals(givenSublocationName)) {
//					logger.info("SublocationCreated Successfully");
//					Assert.assertTrue(true);
//				} else {
//					logger.info("Sublocation Creation Failed");
//					Assert.assertTrue(false);
//					captureScreenshot(driver, "createNewSubLocation");
//				}
//			}
//		}
//	}
}
