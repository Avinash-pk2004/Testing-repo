package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
        
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.moneycontrol.com/");
        driver.findElement(By.xpath("/html/body/div/div[1]/span/a")).click();
        driver.findElement(By.xpath("//*[@id='search_str']")).sendKeys("Reliance Industries");
        
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='autosuggestlist']/ul/li[1]/a")).click();
        String str = driver.findElement(By.xpath("//*[@id='stockName']/h1")).getText();
        if(str.equals("Reliance Industries Ltd."))
        System.out.print("Found");
        else
        System.out.print("Not Found");
        driver.findElement(By.xpath("//*[@id='common_header']/div[1]/div[3]/nav/div/ul/li[10]/a")).click();
        driver.findElement(By.xpath("//*[@id='mc3_return']/div[1]/ul/li[2]/a")).click();
        
        
        
        


    }
}
