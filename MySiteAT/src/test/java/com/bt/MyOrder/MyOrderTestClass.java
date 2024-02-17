package com.bt.MyOrder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class MyOrderTestClass {

    // create a main method
    public static void main(String[] args) {

        WebDriver driver;
        driver = new EdgeDriver();
        driver.get("www.google.com");
        driver.quit();



    }
}
