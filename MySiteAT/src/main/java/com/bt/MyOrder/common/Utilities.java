package com.bt.MyOrder.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static com.bt.MyOrder.common.BaseDriver.driver;
import static java.util.Objects.nonNull;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class Utilities {
    private static int max_retry = 2;

    public static String getProperty(String property)  {
        String filepath = System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties";
        FileInputStream file = null;
        try {
            file = new FileInputStream(filepath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Properties prop = new Properties();
        try {
            prop.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop.getProperty(property);
    }

    public static boolean waitUntilFound(WebElement ele) {
        int counter = 0;
        boolean elementVisible = false, retryOnFail=true;
        WebElement element=null;
        do {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                element = wait.until(webDriver -> elementToBeClickable(ele)).apply(driver);
                if (nonNull(element)) {
                    if (element.isDisplayed()) {
                        retryOnFail = false;
                        elementVisible = true;
                    }
                }

            } catch (Exception e) {
                if (retryOnFail) {
                    System.out.println("retrying...");
                    counter++;
                } else {
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        } while (retryOnFail && counter <= max_retry);
        return elementVisible;
    }
}