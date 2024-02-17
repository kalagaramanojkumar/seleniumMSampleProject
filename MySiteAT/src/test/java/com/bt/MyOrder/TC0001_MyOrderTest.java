package com.bt.MyOrder;

import com.bt.MyOrder.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class TC0001_MyOrderTest extends BasePage {
    @BeforeTest
    public void beforeTest() {
        browserLaunch("Edge","https://sit.myquote.bt.com/my-quote/order/#/home");
        driver.navigate().refresh();
    }



    @Test
    public void test() {
        System.out.println("Test");
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("")).getText().equals("")).findFirst().orElse(null);
        prod.findElement(By.cssSelector("")).click();



    }
}

