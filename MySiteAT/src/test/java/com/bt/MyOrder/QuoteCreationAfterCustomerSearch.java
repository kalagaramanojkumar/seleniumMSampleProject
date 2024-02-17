package com.bt.MyOrder;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class QuoteCreationAfterCustomerSearch {
    WebDriver driver;
    String uRL = "https://sit.myquote.bt.com/my-quote/quote/#/home";


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
        String customerName="TONY TEST CUSTOIMER R13";
        try {
            Thread.sleep(5000);
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

            Thread.sleep(4000);
            WebElement customerNameResultField = driver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
            if (customerNameResultField.isDisplayed() && customerNameResultField.getText().contains(customerName)) {

                WebElement  salesChannel = driver.findElement(By.xpath("//td[@title='BTGS UK']"));
                System.out.println("**************Results Based on Customer Name "+customerNameResultField.getText()+"**************");

                System.out.println("=*=*=*=*=*=*=*=*=*=*Customer Searched Successfully*=*=*=*=*=*=*=*=*=*=");

                if(salesChannel.getText().equalsIgnoreCase("BTGS UK"))
                {
                    WebElement plusIcon = driver.findElement(with(By.xpath("//button[@title='Create Quote']")).near(salesChannel));
                    plusIcon.click();
                    Thread.sleep(2000);

                    {
                        WebElement createQuote = driver.findElement(By.xpath("//a[@id='CREATE_QUOTE']"));
                        //System.out.println(createQuote.getText());
                        createQuote.click();
                        Thread.sleep(4000);
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
                        Thread.sleep(5000);
                            if(driver.findElement(By.xpath("//div[@role='dialog']")).isDisplayed()){
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
                                Thread.sleep(3000);
                                WebElement addProduct = driver.findElement(By.xpath("//h3[normalize-space()='Add Product']"));
                                WebElement quoteID = driver.findElement(By.xpath("//span[starts-with(text(),'QUOTE#')]"));
                                if(addProduct.getText().equalsIgnoreCase("Add Product") && quoteID.toString().contains("QUOTE#"))
                                {
                                    Thread.sleep(5000);
                                    System.out.println("  =*=*=*=*=*Quote Created successfully without any issues*=*=*=*=*= ");
                                    System.out.println("        =*=*=*Quote id :" +quoteID.getText()+"*=*=*=          ");
                                    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
                                    LocalDateTime now = LocalDateTime.now();
                                    String timestamp = dtf.format(now);
                                    String dest = System.getProperty("user.dir") + "\\Reports" + "\\SuccessScreenShot\\" + "QuoteCreationScreenshot " +quoteID.getText()+ timestamp + ".png";
                                try {
                                     FileHandler.copy(screenshot, new File(dest));
                                     System.out.println("           =* Success Screenshot captured *=   ");
                                    } catch (IOException e) {

                                      e.printStackTrace();
                        }
                                }else{
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
        driver.close();

    }
}
