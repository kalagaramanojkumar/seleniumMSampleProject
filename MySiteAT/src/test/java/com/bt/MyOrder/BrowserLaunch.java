package com.bt.MyOrder;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class BrowserLaunch {
    WebDriver driver;

    @Test
    public void launchBrowser() {

        //entire code to launch edge browser with the given url

        System.setProperty("webdriver.edge.driver", "C:\\Users\\sarah\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        driver = new org.openqa.selenium.edge.EdgeDriver();
        driver.get("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        driver.manage().window().maximize();



    }



}
