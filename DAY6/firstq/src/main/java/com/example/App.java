package com.example;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://economictimes.indiatimes.com/et-now/results/");
        Thread.sleep(3000);
        WebElement mf=driver.findElement(By.linkText("Mutual Funds"));
        mf.click();
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,1100)", "");
        WebElement amc=driver.findElement(By.name("amc"));
        Select s1=new Select(amc);
        s1.selectByVisibleText("Canara Robeco");
        WebElement amc1=driver.findElement(By.name("schemenm"));
        Select s2=new Select(amc1);
        s2.selectByVisibleText("Canara Robeco Bluechip Equity Direct-G");
        WebElement gd=driver.findElement(By.id("getDetails"));
        gd.click();
        Thread.sleep(5000);
        ArrayList<String> w2=new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(w2.get(1));
        WebElement d1=driver.findElement(By.xpath("//*[@id='installment_amt']/li/i"));
        d1.click();
        WebElement d1a=driver.findElement(By.xpath("//*[@id='installment_amt']/li/ul/li[3]/span"));
        d1a.click();
        WebElement d2=driver.findElement(By.xpath("//*[@id='installment_period']/li/i"));
        d2.click();
        WebElement d2a=driver.findElement(By.xpath("//*[@id='installment_period']/li/ul/li[1]/span"));
        d2a.click();
        JavascriptExecutor jse1=(JavascriptExecutor)driver;
        jse1.executeScript("window.scrollBy(0,1000)", "");
        WebElement table=driver.findElement(By.xpath("//*[@id='mfReturns']/div[2]/div[2]/ul/li[1]/table"));
        List<WebElement> firstrow=table.findElements(By.xpath("//*[@id='mfReturns']/div[2]/div[2]/ul/li[1]/table/tbody/tr[1]"));
        for(WebElement cell:firstrow)
        {
            System.out.println(cell.getText());
        }
    }
}
