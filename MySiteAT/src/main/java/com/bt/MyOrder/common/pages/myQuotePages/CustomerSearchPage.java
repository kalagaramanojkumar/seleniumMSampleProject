package com.bt.MyOrder.common.pages.myQuotePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class CustomerSearchPage {
    private WebDriver driver;
    public CustomerSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//*[contains(@placeholder,'Enter search keyword')]")
    private  WebElement searchBoxText;
    @FindBy(xpath = "//div[@class='d-flex ai-center']/button[@class='search-button']")
    private  WebElement searchButton;

    @FindBy(xpath = "//tbody[@class='t-datatable-tbody']")
    private WebElement searchResult;

    @FindBy(xpath="//button[@title='Create Quote']")
    private WebElement plusIcon;

    @FindBy(xpath="//a[@id='CREATE_QUOTE']")
    private WebElement createQuoteOption;

    private WebElement  salesChannelLocator;
    private WebElement createQuoteBtn;

    public  void customerSearch(String customerName) {

        try {
            Thread.sleep(3500);
            searchBoxText.sendKeys(customerName);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@class='d-flex ai-center']/button[@class='search-button']"))));
            searchButton.click();
        } catch (Exception e) {
        }
    }
    public void salesChannelSearchAndCreateQuote(String salesChannelName){

        try {
            Thread.sleep(2000);
            List<WebElement> resultvalues = searchResult.findElements(By.xpath("//tbody[@class='t-datatable-tbody']/*[@class='ng-star-inserted']"));
            for(int i = 0;i<resultvalues.size();i++){
                 salesChannelLocator = resultvalues.get(i).findElement(By.xpath("//*[@title='BT JAPAN']"));
                if(salesChannelLocator.getText().contains(salesChannelName))
                {
                     createQuoteBtn = driver.findElement(with(By.xpath("//button[@title='Create Quote']")).near(salesChannelLocator));
                    createQuoteBtn.click();
                    createQuoteOption.click();
                    break;
                }
            }
        }
        catch (Exception e){}
    }
}