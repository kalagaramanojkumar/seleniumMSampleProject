package com.bt.MyOrder.common.pages.myQuotePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductConfigurationPage {

    private WebDriver driver;

    public ProductConfigurationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//span[text()='Select an option'])[1]")
    private WebElement cPEbeCustomerProvidedDropDown;
    @FindBy(xpath = "//li[@aria-label='No']")
    private WebElement noValue;

    @FindBy(xpath = "(//div[@aria-label='Please Select'])[1]")
    private WebElement minRequiredUpstreamSpeed;

    @FindBy(xpath = "//input[@placeholder='Type to filter']")
    private WebElement filterfieldTxtBox;

    @FindBy(xpath = "//li[@aria-label='10Mbps']")
    private WebElement minRequiredUpstreamSpeedValue;


   // private WebElement minRequiredDownstreamSpeed = driver.findElement(By.xpath("(//div[@aria-label='Please Select'])[1]"));
    @FindBy(xpath = "(//div[@aria-label='Please Select'])[1]")
    private WebElement minRequiredDownstreamSpeed;
    @FindBy(xpath = "//input[@placeholder='Type to filter']")
    private WebElement filterfieldTxtBox1;
    //private WebElement filterfieldTxtBox1;

    //private WebElement filterfieldTxtBox1 = driver.findElement(By.xpath("//input[@placeholder='Type to filter']"));

    @FindBy(xpath = "//li[@aria-label='10Mbps']")
    private WebElement minRequiredDownstreamSpeedValue;

    @FindBy(xpath = "//button[@id='Button_LRGGEFDYM851F2H10']")
    private WebElement saveConfigurationBtn;

    @FindBy(xpath = "//button[@aria-label='Next']")
    private WebElement nxtBtn;


    public void configProductWithGivenValues() {
        cPEbeCustomerProvidedDropDown.click();
        noValue.click();
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(minRequiredUpstreamSpeed));
        minRequiredUpstreamSpeed.click();
        wait.until(ExpectedConditions.visibilityOf(filterfieldTxtBox));
        filterfieldTxtBox.sendKeys("10Mbps");
        wait.until(ExpectedConditions.elementToBeClickable(minRequiredUpstreamSpeedValue));
        minRequiredUpstreamSpeedValue.click();
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //wait.until(ExpectedConditions.elementToBeClickable(minRequiredDownstreamSpeed));

        minRequiredDownstreamSpeed.click();
        filterfieldTxtBox1.sendKeys("10Mbps");
        minRequiredDownstreamSpeedValue.click();
        try{
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        wait.until(ExpectedConditions.elementToBeClickable(saveConfigurationBtn));
//        saveConfigurationBtn.click();

        wait.until(ExpectedConditions.elementToBeClickable(nxtBtn));
        nxtBtn.click();

    }
}
