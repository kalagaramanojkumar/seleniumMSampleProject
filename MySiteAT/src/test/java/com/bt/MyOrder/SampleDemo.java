package com.bt.MyOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SampleDemo {

    WebDriver driver;
    String  url = "https://sit.myquote.bt.com/my-quote/order/#/home";
    String quoteID="000000010945725";

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-translate");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-setuid-sandbox");
        options.addArguments("--disable-blink-features=AutomationControlled");


        driver = new EdgeDriver(options);
        driver.get(url);
        Thread.sleep(2000);


    }

    @Test
    public void test() throws InterruptedException {



        WebElement orderRadioButton = driver.findElement(By.xpath("//div[@id='srhOrdr_radio']"));
        orderRadioButton.click();
        Thread.sleep(2000);
        WebElement searchTextBox = driver.findElement(By.xpath("//input[@id='searchParam']"));
        searchTextBox.sendKeys(quoteID);
        Thread.sleep(2000);
        WebElement searchBtn = driver.findElement(By.xpath("//button[@class='search-button']"));
        searchBtn.click();
        Thread.sleep(2000);
        WebElement quoteFriendlyIdcoloumnValue = driver.findElement(By.xpath("(//td[@class='ng-star-inserted'])[1]"));
        System.out.println(quoteFriendlyIdcoloumnValue.getText());
        if (quoteFriendlyIdcoloumnValue.getText().contains(quoteID)) {
            System.out.println("Order id is present for the given QuoteID: "+quoteID);
        } else {
            System.out.println("Order id is not present for the given QuoteID: " + quoteID);
        }
    }
}
