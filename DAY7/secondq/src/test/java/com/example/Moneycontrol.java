package com.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Moneycontrol {
    WebDriver driver;
    @BeforeTest
    public void open() 
    {
        WebDriverManager.chromedriver().setup();
        driver  = new ChromeDriver();
        driver.manage().window().maximize();

    } 
    @Test(priority = 1)
    public void test1() throws InterruptedException
    {
        driver.get("https://www.moneycontrol.com/");
        driver.findElement(By.xpath("/html/body/div/div[1]/span/a")).click();
        driver.findElement(By.xpath("//*[@id='search_str']")).sendKeys("Reliance Industries");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='autosuggestlist']/ul/li[1]/a")).click();
    }
    @Test(priority = 2)
    public void test2() throws InterruptedException
    {
        Actions actions  = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//*[@id='common_header']/div[1]/div[3]/nav/div/ul/li[10]/a/span"));
        actions.moveToElement(element).perform();;
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='common_header']/div[1]/div[3]/nav/div/ul/li[10]/div/div/ul/li[2]/ul/li[5]/a")).click();
        element = driver.findElement(By.xpath("//*[@id='ff_id']"));
        Select select = new Select(element);
        select.selectByValue("AA");
        element = driver.findElement(By.xpath("//*[@id='im_id']"));
        select = new Select(element);
        select.selectByValue("MAA586");
        driver.findElement(By.xpath("//*[@id='invamt']")).sendKeys("100000");
        driver.findElement(By.xpath("//*[@id='stdt']")).sendKeys("2021-08-02");
        driver.findElement(By.xpath("//*[@id='endt']")).sendKeys("2023-08-17");
        driver.findElement(By.xpath("//*[@id='mc_mainWrapper']/div[2]/div/div[3]/div[2]/div[2]/form/div[8]/input")).click();
    }
    @Test(priority = 3)
    public void test3() throws InterruptedException
    {
        String invpr = driver.findElement(By.xpath("//*[@id='mc_mainWrapper']/div[2]/div/div[3]/div[2]/div[5]/table/tbody/tr[1]/td[2]")).getText();
        String tai = driver.findElement(By.xpath("//*[@id='mc_mainWrapper']/div[2]/div/div[3]/div[2]/div[5]/table/tbody/tr[3]/td[2]")).getText();
        System.out.println(invpr);
        System.out.println(tai);
    }
    @AfterTest
    public void close()
    {
        driver.quit();
    }
}
