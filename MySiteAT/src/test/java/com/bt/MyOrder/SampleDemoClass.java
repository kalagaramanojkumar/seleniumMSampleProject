package com.bt.MyOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class SampleDemoClass {

    WebDriver driver;
    String uRl = "https://sit.myquote.bt.com/my-quote/order/#/home";
    String quoteID = "000000010945725";

    @BeforeTest
    public void beforeTest() throws InterruptedException {

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--enable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--start-maximized");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new EdgeDriver(options);
        driver.navigate().refresh();
        Thread.sleep(2000);
        driver.get(uRl);
        Thread.sleep(2000);


    }

    @Test
    public void test() throws InterruptedException {

        WebElement orderRadioBtn = driver.findElement(By.xpath("//div[@id='srhOrdr_radio']"));
        orderRadioBtn.click();
        Thread.sleep(2000);

        WebElement searchTextBox = driver.findElement(By.xpath("//input[@id='searchParam']"));
        searchTextBox.sendKeys(quoteID);
        Thread.sleep(2000);

        WebElement searchBtn = driver.findElement(By.xpath("//button[@class='search-button']"));
        searchBtn.click();
        Thread.sleep(5000);

        WebElement quoteFriendlyIdcoloumnValue = driver.findElement(By.xpath("(//td[@class='ng-star-inserted'])[1]"));

        if (quoteFriendlyIdcoloumnValue.getText().contains(quoteID)) {
            System.out.println("Quote ID is correct");
        }
        else {
            System.out.println("Unable to fetch quoteid");
        }


    }


}
