package com.bt.MyOrder;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class AddProductAfterQuoteCreation {
    WebDriver driver;
    String uRL = "https://sit.myquote.bt.com/my-quote/quote/#/home";
    public String friendlyOfferNameValue = "ICR_BAUOVQ_MNJ_AutomatedOffer";



    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        System.out.println("=*=*=*=*=*=*=*=*=*=*=Executing CustomerSearch Scenario=*=*=*=*=*=*=*=*=*=*=*=*=");
        EdgeOptions option = new EdgeOptions();
        option.addArguments("--start-maximized");
        option.addArguments("--disable-notifications");
        option.addArguments("--remote-allow-origins=*");
        option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        Map<String, Object> lPrefs = new HashMap<>();
        lPrefs.put("browser.show_hub_apps_tower", false);
        option.setExperimentalOption("prefs", lPrefs);
        driver = new EdgeDriver(option);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5000));
        driver.get(uRL);
        Thread.sleep(5000);
        driver.navigate().refresh();
    }

    @Test
    public void customerSearch() throws InterruptedException {
        String customerName="MYQUOTE TEST CUSTOMER";
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement searchBox = driver.findElement(By.xpath("//*[contains(@placeholder,'Enter search keyword')]"));
        try {
            searchBox.sendKeys(customerName);
        } catch (Exception e) {
        }
        WebElement searchButton = driver.findElement(By.xpath("//div[@class='d-flex ai-center']//button[@class='search-button']"));
        try {
            searchButton.click();
        } catch (Exception e1) {
        }


        Thread.sleep(4000);
        WebElement searchResult = driver.findElement(By.xpath("//main[@class='ng-tns-c231462670-0']"));

        if(searchResult.isDisplayed()) {

            Thread.sleep(50000);
            WebElement customerNameResultField = driver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
            Thread.sleep(5000);
            if (customerNameResultField.isDisplayed() && customerNameResultField.getText().contains(customerName)) {
                WebElement salesChannel;
                try {
                    salesChannel = driver.findElement(By.xpath("//td[@title='BT GERMANY']"));
                } catch (Exception e) {
                    System.out.println("BTGS UK Sales Channel Not Found, Trying Again");
                    searchButton.click();
                    Thread.sleep(3500);
                    salesChannel = driver.findElement(By.xpath("//td[@title='BT GERMANY']"));
                }
                System.out.println("**************Results Based on Customer Name " + customerNameResultField.getText() + "**************");

                System.out.println("=*=*=*=*=*=*=*=*=*=*Customer Searched Successfully*=*=*=*=*=*=*=*=*=*=");


                if (salesChannel.getText().equalsIgnoreCase("BT GERMANY")) {
                    WebElement plusIcon = driver.findElement(with(By.xpath("//button[@title='Create Quote']")).near(salesChannel));
                    plusIcon.click();
                    Thread.sleep(2000);

                    {
                        WebElement createQuote = driver.findElement(By.xpath("//a[@id='CREATE_QUOTE']"));
                        //System.out.println(createQuote.getText());
                        createQuote.click();
                        Thread.sleep(6000);
                        WebElement quoteNameTextBox = driver.findElement(By.xpath("//input[@id='quoteName']"));
                        // WebElement quoteOptionNameTextBox = driver.findElement(By.xpath("//input[@name='quoteOptionName']"));
                        WebElement opportunityReferenceTextBox = driver.findElement(By.xpath("//input[@id='orNumber']"));
                        WebElement term24 = driver.findElement(By.xpath("//button[@aria-label='24']"));

                        quoteNameTextBox.sendKeys("AutomatedQuoteName");
                        opportunityReferenceTextBox.sendKeys("OpportunityReferenceName");
                        term24.click();
                        Thread.sleep(5000);

                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000), Duration.ofSeconds(1000));

                        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//tw-button[@label='Submit']"))));
                        WebElement submitButton = driver.findElement(By.xpath("//tw-button[@label='Submit']"));
                        submitButton.click();
                        Thread.sleep(8000);
                        if (driver.findElement(By.xpath("//div[@role='dialog']")).isDisplayed()) {
                            WebElement groupElement = driver.findElement(By.xpath("//input[@id='groupName']"));
                            groupElement.sendKeys("AutomatedGroupName");
                            WebElement colour = driver.findElement(By.xpath("//div[@aria-label='Amber']"));
                            colour.click();
                            Thread.sleep(2000);
                            WebElement greenColour = driver.findElement(By.xpath("//li[@aria-label='Green']"));
                            greenColour.click();
                            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tw-button[@inputid='submitBtn']")));
                            WebElement submitGroupButton = driver.findElement(By.xpath("//tw-button[@inputid='submitBtn']"));
                            submitGroupButton.click();
                            Thread.sleep(7000);
                            WebElement addProduct = driver.findElement(By.xpath("//h3[normalize-space()='Add Product']"));
                            WebElement quoteID = driver.findElement(By.xpath("//span[starts-with(text(),'QUOTE#')]"));
                            if (addProduct.getText().equalsIgnoreCase("Add Product") && quoteID.toString().contains("QUOTE#")) {
                                Thread.sleep(5000);
                                System.out.println("  =*=*=*=*=*Quote Created successfully without any issues*=*=*=*=*= ");
                                System.out.println("        =*=*=*Quote id :" + quoteID.getText() + "*=*=*=          ");
                                WebElement product = driver.findElement(By.xpath("(//h5[@class='flex-grow-1 product-name'])[4]"));
                                WebElement productAddIcon = driver.findElement(with(By.xpath("//button[@id='addBtn']")).near(product));
                                productAddIcon.click();
                                Thread.sleep(1000);
                                WebElement saveProductBtn = driver.findElement(By.xpath("//button[@id='submitBtn' and @aria-label='Save Products']"));
                                saveProductBtn.click();
                                Thread.sleep(17000);
                                WebElement willCPEbeCustomerProvidedDropDownBtn = driver.findElement(By.xpath("(//span[text()='Select an option'])[1]"));
                                willCPEbeCustomerProvidedDropDownBtn.click();
                                Thread.sleep(2000);
                                if (willCPEbeCustomerProvidedDropDownBtn.isDisplayed()) {
                                    WebElement noValue = driver.findElement(By.xpath("//li[@aria-label='No']"));
                                    noValue.click();
                                    Thread.sleep(9000);
                                    WebElement minRequiredUpstreamSpeed = driver.findElement(By.xpath("(//div[@aria-label='Please Select'])[1]"));

                                    minRequiredUpstreamSpeed.click();
                                    Thread.sleep(10000);
                                    WebElement filterfieldTxtBox = driver.findElement(By.xpath("//input[@placeholder='Type to filter']"));
                                    filterfieldTxtBox.sendKeys("10Mbps");
                                    Thread.sleep(2000);
                                    WebElement minRequiredUpstreamSpeedValue = driver.findElement(By.xpath("//li[@aria-label='10Mbps']"));
                                    minRequiredUpstreamSpeedValue.click();
                                    Thread.sleep(5000);
                                    WebElement minRequiredDownstreamSpeed = driver.findElement(By.xpath("(//div[@aria-label='Please Select'])[1]"));
                                    minRequiredDownstreamSpeed.click();
                                    Thread.sleep(5000);
                                    WebElement filterfieldTxtBox1 = driver.findElement(By.xpath("//input[@placeholder='Type to filter']"));
                                    filterfieldTxtBox1.sendKeys("10Mbps");
                                    WebElement minRequiredDownstreamSpeedValue = driver.findElement(By.xpath("//li[@aria-label='10Mbps']"));
                                    minRequiredDownstreamSpeedValue.click();
                                    Thread.sleep(8000);
                                    WebElement saveConfigurationBtn = driver.findElement(By.xpath("//button[@aria-label='Save configuration']"));
                                    saveConfigurationBtn.click();
                                    Thread.sleep(8000);
                                    WebElement nextBtn = driver.findElement(By.xpath("//button[@id='submitConfig']"));
                                    nextBtn.click();
                                    Thread.sleep(8000);
                                     // String siteName = "ERFURT WEIMAR AIRPORT MNJ";
                                    String siteName = "AUSTRIA, VIENNA";
                                      WebElement searchtextField = driver.findElement(By.xpath("(//input[@type='text'])[1]"));
                                      searchtextField.sendKeys(siteName);
                                    Thread.sleep(3500);
//                                    String siteID = "1254323";
//                                    WebElement siteIDFilterBtn = driver.findElement(By.xpath("//button[@id='TblFilter_LPSNU3E9JXV9U3LCA_menubtn']"));
//                                    siteIDFilterBtn.click();
//                                    Thread.sleep(2000);
//                                    WebElement siteIdFilterTextBox = driver.findElement(By.id("TblFilter_LPSNU3E9JXV9U3LCA_form_0_condtn"));
//                                    siteIdFilterTextBox.sendKeys(siteID);
//                                    Thread.sleep(2000);
//                                    WebElement applyFilterBtn = driver.findElement(By.xpath("//button[@aria-label='Apply']"));
//                                    applyFilterBtn.click();
//                                    Thread.sleep(3000);
                                    WebElement siteRow = driver.findElement(By.xpath("//tr[@class='t-selectable-row ng-star-inserted']"));
                                    siteRow.click();
                                    Thread.sleep(3000);
                                    WebElement submitBtn = driver.findElement(By.xpath("//button[@type='button' and @aria-label='Submit']"));
                                    submitBtn.click();
                                    Thread.sleep(5500);
                                    WebElement  priceBtn = driver.findElement(By.xpath("//button[@type='button' and @aria-label='Price']"));
                                    priceBtn.click();
                                    //WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMinutes(10));

                                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='stage-btn d-flex ai-center Pricing']")));


                                    Thread.sleep(95500);
                                    WebElement viewPricingBtn= driver.findElement(By.xpath("//button[@class='stage-btn d-flex ai-center Pricing']"));
                                    viewPricingBtn.click();
                                    Thread.sleep(8000);
//                                    WebDriverWait waitn = new WebDriverWait(driver, Duration.ofMinutes(1), Duration.ofSeconds(10));
//                                    waitn.until(ExpectedConditions.(By.xpath("//button[@aria-label='Proceed']")));
                                    WebElement proceedBtn=driver.findElement(By.xpath("//button[@aria-label='Proceed']"));
                                    Thread.sleep(8000);
                                    proceedBtn.click();
                                    Thread.sleep(8000);
//                                    Set<String> windowsNames = driver.getWindowHandles();
//                                    System.out.println("WindowsNames :"+windowsNames);
                                    WebElement proceedToSeedBtn = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/main[1]/div[1]/ng-component[1]/common-layout-main[1]/div[3]/quote-pricing-summary[1]/tw-tabview[1]/div[1]/div[2]/tw-tabpanel[1]/div[1]/tw-dialog[1]/div[1]/div[1]/div[4]/tw-footer[1]/tw-button[2]/button[1]"));
                                    proceedToSeedBtn.click();
                                    Thread.sleep(60000);
                                    WebElement arrowBtn= driver.findElement(By.xpath("//body[1]/app-root[1]/main[1]/div[1]/ng-component[1]/div[1]/quote-list[1]/tw-datatable[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[10]/button[1]"));
                                    arrowBtn.click();
                                    Thread.sleep(15555);
                                    WebElement statusColumn = driver.findElement(By.xpath("(//td[@class='ng-star-inserted'])[15]"));
                                    statusColumn.click();

                                    Thread.sleep(9000);
                                    WebElement readyToOffer = driver.findElement(By.xpath("//button[@class='ng-tns-c1529155250-19 status-button status-button-secondary status-display']"));
                                    readyToOffer.click();

                                    Thread.sleep(5000);
                                    WebElement SitesSelectionCheckBox = driver.findElement(By.xpath("(//div[@role='checkbox'])[2]"));
                                    SitesSelectionCheckBox.click();
                                    Thread.sleep(7000);

                                    WebElement createOffer = driver.findElement(By.xpath("//button[@type='button' and @aria-label='Create Offer']"));
                                    createOffer.click();
                                    Thread.sleep(7000);
                                    WebElement bauOVQ= driver.findElement(By.xpath("//label[text()='BAU OVQ']"));
                                    bauOVQ.click();
                                    Thread.sleep(6000);
                                    WebElement friendlyOfferName= driver.findElement(By.xpath("//input[@type='text']"));
                                    friendlyOfferName.sendKeys(friendlyOfferNameValue);

                                    Thread.sleep(3000);
                                    WebElement createAndProceedBtn= driver.findElement(By.xpath("//button[@aria-label='Create & Proceed']"));
                                    createAndProceedBtn.click();
                                    Thread.sleep(9500);


                                    System.out.println("* Test Passed! *");
                                }


//                                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
//                                LocalDateTime now = LocalDateTime.now();
//                                String timestamp = dtf.format(now);
//                                String dest = System.getProperty("user.dir") + "\\Reports" + "\\SuccessScreenShot\\" + "QuoteCreationScreenshot " +quoteID.getText()+ timestamp + ".png";
//                                try {
//                                    FileHandler.copy(screenshot, new File(dest));
//                                    System.out.println("           =* Success Screenshot captured *=   ");
//                                } catch (IOException e) {
//
//                                    e.printStackTrace();
//                                }
                            } else {
                                System.out.println("  =*=*=*=*=*Quote Creation Failed*=*=*=*=*= ");
                                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
                                LocalDateTime now = LocalDateTime.now();
                                String timestamp = dtf.format(now);
                                String dest = System.getProperty("user.dir") + "\\Reports" + "\\FailureScreenShots\\" + "QuoteCreationFailedScreenshot " + timestamp + ".png";
                                try {
                                    FileHandler.copy(screenshot, new File(dest));
                                    System.out.println("      =* Test Failed, Screenshot captured *=   ");
                                } catch (IOException e) {

                                    e.printStackTrace();
                                }

                            }

                        }

                    }
                }


            } else{
                System.out.println("No Customer Entry Found With CustomerName as : "+ customerName+ ",Try Again with different CustomerName");
                File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
                LocalDateTime now1 = LocalDateTime.now();
                String timestamp = dtf1.format(now1);
                String dest1 = System.getProperty("user.dir") + "\\Reports" + "\\FailureScreenShots\\" + "customerSearchPageFailed " + timestamp + ".png";

                try {
                    FileHandler.copy(screenshot1, new File(dest1));
                    System.out.println("Failure Screenshot captured ");
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("customer Search Failed, Try Again");
            File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
            LocalDateTime now1 = LocalDateTime.now();
            String timestamp = dtf1.format(now1);
            String dest1 = System.getProperty("user.dir") + "\\Reports" + "\\FailureScreenShots\\" +"customerSearchPage " +timestamp+".png" ;

            try {
                FileHandler.copy(screenshot1, new File(dest1 ));
                System.out.println("Failure Screenshot captured ");
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
    @AfterMethod
    public void threadsleep() throws InterruptedException {
        Thread.sleep(8000);
        //driver.close();

    }
    @AfterTest
    public void browserClose(){

       // driver.close();

    }


}
