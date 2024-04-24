package com.example;

import java.time.Duration;

import org.checkerframework.checker.units.qual.t;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.dockerjava.api.model.Driver;

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
        driver.get("https://www.demoblaze.com/");

        //wait for visible field input
        WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(5));


        WebElement lap=driver.findElement(By.linkText("Laptops"));
        lap.click();
        Thread.sleep(7000);
        WebElement lap1=driver.findElement(By.linkText("MacBook air"));
        lap1.click();
        Thread.sleep(5000);
        WebElement lap2=driver.findElement(By.linkText("Add to cart"));
        lap2.click();
        Thread.sleep(3000);
        //MOVING TO ALERT BOX.....

        driver.switchTo().alert().accept();
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,10000)", "");
        Thread.sleep(5000);

        WebElement lap3=driver.findElement(By.linkText("Cart"));
        lap3.click();
        Thread.sleep(5000);
        String title=driver.findElement(By.xpath("//*[@id='tbodyid']/tr/td[2]")).getText();
        String price=driver.findElement(By.xpath("//*[@id='tbodyid']/tr/td[3]")).getText();
        System.out.println(title+": $"+price);
        

    }
}
