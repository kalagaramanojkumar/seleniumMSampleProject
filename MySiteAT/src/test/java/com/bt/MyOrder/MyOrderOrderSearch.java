package com.bt.MyOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class MyOrderOrderSearch {

    WebDriver driver;
    String myOrderURL="https://sit.myquote.bt.com/my-quote/order/#/home";
    String quoteID="000000010945725";

    @BeforeMethod
    public void setUp() throws InterruptedException {
        System.out.println("=*=*=*=*=*=*=*=*=*=*=Executing MyOrder Order Search=*=*=*=*=*=*=*=*=*=*=*=*=");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--enable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--start-maximized");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setPageLoadTimeout(Duration.ofMinutes(1));
        driver= new EdgeDriver(options);
        driver.navigate().refresh();
        Thread.sleep(2000);
        driver.get(myOrderURL);
        driver.navigate().refresh();
        Thread.sleep(3500);
    }
   @Test
    public void searchOrder() throws InterruptedException {
       WebElement orderRadio = driver.findElement(By.xpath("//div[@id='srhOrdr_radio']"));
       Thread.sleep(2000);
       orderRadio.click();
       Thread.sleep(2000);

       WebElement searchBox = driver.findElement(By.xpath("//input[@id='searchParam']"));
       searchBox.sendKeys(quoteID);
       Thread.sleep(2000);

       WebElement searchBtn = driver.findElement(By.xpath("//button[@class='search-button']"));
       searchBtn.click();
       Thread.sleep(4000);

       WebElement quoteFriendlyIDColoumn = driver.findElement(By.xpath("(//td[@class='ng-star-inserted'])[1]"));

       if(quoteFriendlyIDColoumn.getText().contains(quoteID)){

           System.out.println("=***= Order details fetched based on the QuoteID :"+quoteID+" =***=");
       }
       else{
           System.out.println("Unable to fetch order details based on the QuoteID :"+quoteID);
       }


   }

   @AfterTest
    public void browserClose(){
       // driver.close();
        System.out.println("=***= Browser closed successfully =***=");
   }
}
