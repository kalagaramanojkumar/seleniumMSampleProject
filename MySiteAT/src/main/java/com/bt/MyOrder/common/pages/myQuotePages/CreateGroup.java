package com.bt.MyOrder.common.pages.myQuotePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateGroup {

    private WebDriver driver;

    public CreateGroup(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@role='dialog']")
    private WebElement createGroupDialog;

    @FindBy(xpath = "//input[@id='groupName']")
    private WebElement groupNameTxtBox;

    @FindBy(xpath = "//tw-button[@inputid='submitBtn']")
    private WebElement submitBtn;

    public void createGroup(String groupName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='dialog']")));

        groupNameTxtBox.sendKeys(groupName);

    }
    public void clickSubmitBtn(){
        submitBtn.click();

    }

}
