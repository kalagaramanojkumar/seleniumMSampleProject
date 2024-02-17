package com.bt.MyOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class MyOrderOrderSeachUsingQuoteID {

   WebDriver driver;
    String myOrderURL="https://sit.myquote.bt.com/my-quote/order/#/home";
    String quoteid="000000010945725";
    @BeforeMethod
    public void setUp() throws InterruptedException {
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
        Thread.sleep(4500);


    }

    @Test
    public void myOrderOrderSeachUsingQuoteID1() throws InterruptedException {
        WebElement orderRadioButton = driver.findElement(By.xpath("//div[@id='srhOrdr_radio']"));
        Thread.sleep(2000);
        orderRadioButton.click();
        Thread.sleep(2000);
        WebElement searchTextBox = driver.findElement(By.xpath("//input[@id='searchParam']"));
        searchTextBox.sendKeys(quoteid);
        Thread.sleep(2000);
        WebElement searchBtn = driver.findElement(By.xpath("//button[@class='search-button']"));
        searchBtn.click();
        Thread.sleep(2000);
        WebElement quoteFriendlyIdcoloumnValue = driver.findElement(By.xpath("(//td[@class='ng-star-inserted'])[1]"));
        System.out.println(quoteFriendlyIdcoloumnValue.getText());
        if (quoteFriendlyIdcoloumnValue.getText().contains(quoteid)) {
            System.out.println("Order id is present for the given QuoteID: "+quoteid);
        } else {
            System.out.println("Order id is not present for the given QuoteID: " + quoteid);
        }
    }
    // create a method to close the browser
    public void closeBrowser() {
        driver.close();
    }
    // create a method to launch edge browser with URl "https://sit.myquote.bt.com/my-quote/order/#/home"
    public void launchEdgeBrowser() {
        driver.get(myOrderURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.navigate().refresh();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(30));
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.navigate().refresh();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(30));
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.navigate().refresh();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(30));
        driver.manage().deleteAllCookies();
        //add edgeoptions of disable notification, disable extensions, disable infobars, start maximized, pageloadstrategy, pageloadtimeout, scripttimeout
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--enable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--start-maximized");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setPageLoadTimeout(Duration.ofMinutes(1));

    }
    //Edge browser launch with URl "https://sit.myquote.bt.com/my-quote/order/#/home"
    public void launchEdgeBrowserWithURL() {
        driver.get(myOrderURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.navigate().refresh();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(30));
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.navigate().refresh();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(30));
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.navigate().refresh();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(30));
        driver.manage().deleteAllCookies();
        //add edgeoptions of disable notification, disable extensions, disable infobars, start maximized, pageloadstrategy, pageloadtimeout, scripttimeout
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--enable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--start-maximized");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setPageLoadTimeout(Duration.ofMinutes(1));


    }
    // create a method to launch edge browser with URl "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
    public void launchEdgeBrowserWithURL1() {

    }

    // create a method to launch edge browser with URl "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
    public void launchEdgeBrowserWithURL2() {

    }

    // create a method to launch edge browser with URl "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
    public void launchEdgeBrowserWithURL3() {

    }
    };

