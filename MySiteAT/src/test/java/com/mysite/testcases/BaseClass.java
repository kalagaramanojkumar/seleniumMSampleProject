package com.mysite.testcases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentTest;
import com.mysite.utilities.ReadConfig;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();
	String url = readConfig.getURL();
	String browser = readConfig.getBrowser();
	public static WebDriver driver;
	public static Logger logger;

	@BeforeClass
	public void setup() {
//		switch (browser.toLowerCase()) {
//		case "edge":
//			EdgeOptions options = new EdgeOptions();
////        options.addArguments("--enable-popup-blocking");
////        options.addArguments("--disable-extensions");
////        options.addArguments("--disable-gpu");
////        options.addArguments("--disable-setuid-sandbox");
////        options.addArguments("--disable-web-security");
////        options.addArguments("--disable-features=IsolateOrigins,site-per-process");
////        options.addArguments("--disable-blink-features=AutomationControlled");
////        options.addArguments("--no-sandbox");
////        options.addArguments("--disable-dev-shm-usage");
////        options.addArguments("--start-maximized");
////        options.addArguments("profile-directory=Profile 6");
////        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
////        Map<String, Object> lPrefs = new HashMap<>();
////        lPrefs.put("browser.show_hub_apps_tower", false);
////        options.setExperimentalOption("prefs", lPrefs);
////        driver= new EdgeDriver(options);
//			options.addArguments("--enable-popup-blocking");
//			options.addArguments("--disable-extensions");
//			options.addArguments("--disable-gpu");
//			options.addArguments("--disable-setuid-sandbox");
//			options.addArguments("--disable-web-security");
//			options.addArguments("--disable-features=IsolateOrigins,site-per-process");
//			options.addArguments("--disable-blink-features=AutomationControlled");
//			options.addArguments("--no-sandbox");
//			options.addArguments("--disable-dev-shm-usage");
//			options.addArguments("--start-maximized");
//			options.addArguments("profile-directory=Profile 6");
//			Map<String, Object> lPrefs = new HashMap<>();
//			lPrefs.put("browser.show_hub_apps_tower", false);
//			options.setExperimentalOption("prefs", lPrefs);
//			driver = new EdgeDriver(options);
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				throw new RuntimeException(e);
//			}
//			break;
//
//		case "chrome":
//			driver = new ChromeDriver();
//			break;
//
//		default:
//			driver = null;
//			break;
//		}
		
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
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(62));
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
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(62));
            driver.navigate().to(url);
        }
		
		
		
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));

		logger = LogManager.getLogger("MySite");

	}

	@AfterClass(enabled = false)
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
	public void captureScreenshot(WebDriver driver, String testName)  
	{
		TakesScreenshot screenshot = ((TakesScreenshot)driver);
		
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png");
		try {
			FileUtils.copyFile(src,dest);
			logger.info("Screenshot captured.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
