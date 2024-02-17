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


public class BasePage {

    public WebDriver driver;
    public void browserLaunch(String browser, String url) {

        if(browser.equalsIgnoreCase("Edge")){
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--enable-popup-blocking");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-setuid-sandbox");
            options.addArguments("--disable-web-security");
            options.addArguments("--disable-features=IsolateOrigins,site-per-process");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--start-maximized");
            options.addArguments("profile-directory=Profile 6");
            Map<String, Object> lPrefs = new HashMap<>();
            lPrefs.put("browser.show_hub_apps_tower", false);
            options.setExperimentalOption("prefs", lPrefs);
            driver= new EdgeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
//            driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(50));
//            driver.manage().timeouts().setScriptTimeout(25, TimeUnit.SECONDS);
            driver.get(url);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//            options.addArguments("user-data-dir=C:\\Users\\612627350\\AppData\\Local\\Microsoft\\Edge");
//            options.addArguments("--remote-allow-origins=*");
//            options.addArguments("--disable-notifications");
//            options.addArguments("--disable-infobars");
//            Map<String, Object> lPrefs = new HashMap<>();
//            lPrefs.put("browser.show_hub_apps_tower", false);
//            options.setExperimentalOption("prefs", lPrefs);
            //options.setExperimentalOption("useAutomationExtension", false);
            //options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        }
        else if (browser.equalsIgnoreCase("Chrome")) {
            ChromeOptions chromeOption = new ChromeOptions();
            chromeOption.addArguments("--remote-allow-origins=*");
            chromeOption.addArguments("--start-maximized");
            chromeOption.addArguments("--disable-notifications");
            driver = new ChromeDriver(chromeOption);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5000));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
            driver.navigate().to(url);
        }

    }
}
