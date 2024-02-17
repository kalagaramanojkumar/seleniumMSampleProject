package com.bt.MyOrder;


import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class CustomerSearchSampleTest {
    WebDriver driver;
    String uRL = "https://sit.myquote.bt.com/my-quote/quote/#/home";


    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        System.out.println("=====================Executing CustomerSearch Scenario==========================");
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
        Thread.sleep(1000);
    }

    @Test
    public void customerSearch() throws InterruptedException {
        String customerName="TONY TEST CUSTOIMER R13 ";
        try {
            Thread.sleep(3000);
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
                System.out.println("Results Based on Customer Name "+customerNameResultField.getText());
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
                LocalDateTime now = LocalDateTime.now();
                String timestamp = dtf.format(now);
                String dest = System.getProperty("user.dir") + "\\Reports" + "\\SuccessScreenShot\\" + "customerSearchPagePassed " + timestamp + ".png";
                try {
                    FileHandler.copy(screenshot, new File(dest));
                    System.out.println("Success Screenshot captured ");
                } catch (IOException e) {

                    e.printStackTrace();
                }
                System.out.println("=====================Customer Searched Successfully==========================");
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
    public void BrowserClose() throws InterruptedException {
        Thread.sleep(8000);
        //driver.close();

    }
}