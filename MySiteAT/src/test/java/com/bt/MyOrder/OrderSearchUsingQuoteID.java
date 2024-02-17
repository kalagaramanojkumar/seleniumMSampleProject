package com.bt.MyOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class OrderSearchUsingQuoteID {

    WebDriver driver;
    String quoteID="000000010945725";

    @BeforeMethod
    public void beforeMethod() {

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-translate");
        options.addArguments("--disable-extensions");

        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://sit.myquote.bt.com/my-quote/order/#/home");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void test() throws InterruptedException {
        WebElement orderRadioBtn = driver.findElement(By.xpath("//div[@id='srhOrdr_radio']"));
        orderRadioBtn.click();
        Thread.sleep(2000);
        WebElement seachTextBox = driver.findElement(By.xpath("//input[@id='searchParam']"));
        seachTextBox.sendKeys(quoteID);
        Thread.sleep(2000);
        WebElement searchBtn = driver.findElement(By.xpath("//button[@class='search-button']"));
        searchBtn.click();
        Thread.sleep(5000);
        WebElement quoteFriendlyIDColoumn = driver.findElement(By.xpath("(//td[@class='ng-star-inserted'])[1]"));
        String quoteFriendlyID = quoteFriendlyIDColoumn.getText();
        System.out.println(quoteFriendlyID);
        Thread.sleep(2000);
        if (quoteFriendlyID.contains(quoteID)) {
            System.out.println("Order details fetched with the given QuoteID: "+quoteID);
        }
        else {
            System.out.println("Order details not fetched with the given QuoteID: "+quoteID);
        }


    }
    // create a method to close the opened browser
    public void closeBrowser() {
        driver.close();
    }
    //create a method to launch edge browser with URl
    public void launchEdgeBrowserWithURL() {
        driver.get("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //Edge browser launch with URl "https://sit.myquote.bt.com/my-quote/order/#/home"
    public void launchEdgeBrowser() {

    }



}
