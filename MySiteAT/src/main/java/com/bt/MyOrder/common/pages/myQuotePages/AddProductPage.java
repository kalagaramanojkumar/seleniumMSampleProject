package com.bt.MyOrder.common.pages.myQuotePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddProductPage {

    private WebDriver driver;

    public AddProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[starts-with(text(),'QUOTE#')]")
    private WebElement quoteNumberElement;

    @FindBy(xpath = "(//h5[@class='flex-grow-1 product-name'])[1]")
    private WebElement iCG;

    @FindBy(xpath = "(//h5[@class='flex-grow-1 product-name'])[4]")
    private WebElement iCR;

    @FindBy(xpath = "//button[@id='submitBtn' and @aria-label='Save Products']")
    private WebElement saveProductsBtn;
    public void addProduct(String BTConnectproductName) {
        System.out.println("Quote ID: " + quoteNumberElement.getText());
        String productName= BTConnectproductName.toUpperCase();
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        switch (productName) {
            case "ICG":
                iCG.click();
                saveProductsBtn.click();
                break;
            case "ICR":
                iCR.click();
                saveProductsBtn.click();
                break;
        }


    }
}
