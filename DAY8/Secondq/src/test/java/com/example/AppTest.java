package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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
    @Test
    public void test1() throws InterruptedException
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://demoblaze.com/");
        driver.findElement(By.linkText("Laptops")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("MacBook air")).click();
        Thread.sleep(3000);
        String name=driver.findElement(By.className("name")).getText();
        Thread.sleep(3000);
        driver.findElement(By.linkText("Add to cart")).click();
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        Thread.sleep(3000);
        driver.findElement(By.linkText("Cart")).click();
        Thread.sleep(3000);
        String name1=driver.findElement(By.xpath("//*[@id='tbodyid']/tr/td[2]")).getText();
        if(name1.equals(name))
        {
            Assert.assertTrue(true);
        }
        else{
            Assert.assertFalse(false);
        }
    }
    @Test
    public void test2() throws InterruptedException, IOException
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://demoblaze.com/");
        driver.findElement(By.linkText("Log in")).click();
        Thread.sleep(3000);
        FileInputStream fs=new FileInputStream("D:\\Selenium Testing\\sampledata.xlsx");
        XSSFWorkbook book= new XSSFWorkbook(fs);
        XSSFSheet sheet=book.getSheetAt(0);
        int rowcount=sheet.getLastRowNum();
        int colcount=sheet.getRow(0).getLastCellNum();
        String username=sheet.getRow(1).getCell(0).getStringCellValue();
        String pass=sheet.getRow(1).getCell(1).getStringCellValue();
        driver.findElement(By.id("loginusername")).sendKeys(username);
        driver.findElement(By.id("loginpassword")).sendKeys(pass);
        driver.findElement(By.xpath("//*[@id='logInModal']/div/div/div[3]/button[2]")).click();
        boolean present;
        try {
        driver.findElement(By.id("nameofuser"));
        present = true;
        } catch (NoSuchElementException e) {
        present = false;
        }
        Assert.assertTrue(present);
        
        
    }
}
