package com.bt.MyOrder.common;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseDriver {

    public static WebDriver driver;

    public BaseDriver(WebDriver driver) {
        this.driver=driver;
    }
    public  void openBrowser(String browserName){


       // String browser=Utilities.getProperty(browserName);
        if (browserName.equalsIgnoreCase("Edge")){
            EdgeOptions option = new EdgeOptions();
            option.addArguments("--start-maximized");
            option.addArguments("--disable-notifications");
            option.addArguments("--remote-allow-origins=*");
            option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            Map<String, Object> lPrefs = new HashMap<>();
            // Disable Hub Apps Tower
            lPrefs.put("browser.show_hub_apps_tower", false);
//                EdgeOptions egdeOptions = new EdgeOptions();
            option.setExperimentalOption("prefs", lPrefs);
            driver= new EdgeDriver(option);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5000));
            driver.navigate().refresh();
        } else if (browserName.equalsIgnoreCase("Chrome")) {
            ChromeOptions chromeOption = new ChromeOptions();
            chromeOption.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(chromeOption);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5000));
            driver.navigate().refresh();
        } else{
                    throw new RuntimeException("No Browser Specified to open");
        }

    }

    public static void openUrl(String URL){

        driver.get(URL);
    }


    public static void closeBrowser(){
        driver.close();
        driver.quit();
    }
}
