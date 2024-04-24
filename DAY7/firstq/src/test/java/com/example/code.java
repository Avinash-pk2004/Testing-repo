package com.example;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class code {
    WebDriver driver;
    @Test
    public void test1()
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://economictimes.indiatimes.com/et-");
        driver.findElement(By.linkText("Mutual Funds")).click();
    }
    @Test
    public void test2()
    {
        WebElement amc=driver.findElement(By.name("amc"));
        Select d1=new Select(amc);
        d1.selectByVisibleText("Canara Robeco");
        WebElement amc1=driver.findElement(By.name("schemenm"));
        Select d2=new Select(amc1);
        d2.selectByVisibleText("Canara Robeco Bluechip Equity Direct-G");
    }
    @Test
    public void test3()
    {
        driver.findElement(By.linkText("Get Details")).click();
        ArrayList<String> l1=new ArrayList<>(driver.getWindowHandles());
        String second=l1.get(1);
        driver.switchTo().window(second);
        driver.findElement(By.xpath("//*[@id='installment_amt']/li/span")).click();
        driver.findElement(By.xpath("//*[@id='installment_amt']/li/ul/li[3]/span")).click();
        driver.findElement(By.xpath("//*[@id='installment_period']/li/i")).click();
        driver.findElement(By.xpath("//*[@id='installment_period']/li/ul/li[1]/span")).click();
        //*[@id="mfNav"]/div/ul/li[2]
        driver.findElement(By.xpath("//*[@id='mfNav']/div/ul/li[2]")).click();
        WebElement table=driver.findElement(By.xpath("//*[@id='mfReturns']/div[2]/div[2]/ul/li[1]/table"));
        List<WebElement> firstrow=table.findElements(By.xpath("//*[@id='mfReturns']/div[2]/div[2]/ul/li[1]/table/tbody/tr[1]"));
        for(WebElement cell:firstrow)
        {
            System.out.println(cell.getText());
        }
    }
}
