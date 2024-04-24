package com.example;

import java.io.IOException;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    
    WebDriver driver;
    @BeforeMethod
    public void open() throws InterruptedException
    {
        
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("http://dbankdemo.com/bank/login");
        Thread.sleep(3000);
    }
    @Test(dataProvider="dat")
    public void test1(String username,String password) throws InterruptedException
    {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).submit();
        String url=driver.getCurrentUrl();
        String homeurl="http://dbankdemo.com/bank/home";
        Assert.assertEquals(homeurl, url );
        
    }
    @Test(dataProvider="dat")
    public void test2(String username,String password) throws InterruptedException
    {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).submit();
        driver.findElement(By.linkText("Deposit")).click();
        WebElement dropdown=driver.findElement(By.id("selectedAccount"));
        Select select = new Select(dropdown);
        select.selectByIndex(1);
        driver.findElement(By.id("amount")).sendKeys("5000");
        driver.findElement(By.xpath("//*[@id='right-panel']/div[2]/div/div/div/div/form/div[2]/button[1]")).submit();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,600)","");
        String amount=driver.findElement(By.xpath("//*[@id='transactionTable']/tbody/tr[1]/td[4]")).getText();
        System.out.println(amount);
        if(amount.equals("$5000"))
        {
            Assert.assertTrue(true);
        }
        else{
            Assert.assertFalse(false);
        }
    }
    
    @Test(dataProvider="dat")
    public void test3(String username,String password) throws InterruptedException
    {
        
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).submit();
        driver.findElement(By.linkText("Withdraw")).click();
        WebElement dropdown=driver.findElement(By.id("selectedAccount"));
        Select select = new Select(dropdown);
        select.selectByIndex(1);
        driver.findElement(By.id("amount")).sendKeys("5000");
        driver.findElement(By.xpath("//*[@id='right-panel']/div[2]/div/div/div/div/form/div[2]/button[1]")).submit();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,600)","");
        String amount=driver.findElement(By.xpath("//*[@id='transactionTable']/tbody/tr[1]/td[4]")).getText();
        System.out.println(amount);
   
        if(amount.equals("$-5000"))
        {
            Assert.assertTrue(true);
        }
        else{
            Assert.assertFalse(false);
        }   
    }
    

    @DataProvider(name = "dat")
    public Object[][] fetchData() throws IOException
    {
        Object [][] arr=new Object[1][2];
        arr[0][0]="S@gmail.com";
        arr[0][1]="P@ssword12";
        return arr;
    }
}
