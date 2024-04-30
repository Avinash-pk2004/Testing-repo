package com.example;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Before;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

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
    public void bm()
    {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
        driver = new ChromeDriver(chromeOptions);
    }
    @Test(dataProvider = "dat1")
    public void test1(String from,String to,String departure,String return1,String tc)
    {
        driver.get("https://www.ixigo.com/");
        WebElement element1=driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div/div/div/p"));
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].innerText = arguments[1];",element1, from); 
        WebElement element2=driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div/div/div/p"));
        js.executeScript("arguments[0].innerText = arguments[1];", element2,to);
        WebElement element3=driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[1]/div/div/div/div/p[2]"));
        js.executeScript("arguments[0].innerText = arguments[1];", element3,departure);
        WebElement element4=driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[2]/div/div/div/div/p"));
        js.executeScript("arguments[0].innerText = arguments[1];", element4,return1);
        WebElement element5=driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div/div/div/div/p[2]"));
        js.executeScript("arguments[0].innerText = arguments[1];", element5,tc);
         driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/button")).click();
        }
        @Test
        public void test2()
        {
            driver.get("https://www.ixigo.com/");
            driver.findElement(By.xpath("/html/body/main/div[3]/div[2]/div/div[2]/div[1]/p[1]")).click();
           Set<String> id=driver.getWindowHandles();
           for(String it:id)
           {
            if(!it.equals(driver.getWindowHandle()))
            {
                driver.switchTo().window(it);
                break;
            }
           }
            Assert.assertTrue(driver.getCurrentUrl().contains("about"));
        }
    @DataProvider(name="dat1")
    public Object[][] dp1() throws IOException
    {
        FileInputStream fs=new FileInputStream("D:\\Excel data\\Book2.xlsx");
        XSSFWorkbook workbook=new XSSFWorkbook(fs);
        XSSFSheet sheet=workbook.getSheetAt(0);
        int rowcount=sheet.getLastRowNum();
        int colcount=sheet.getRow(0).getLastCellNum();
        Object[][] obj=new Object[rowcount][colcount];
        for(int i=0;i<rowcount;i++)
        {
            Row row=sheet.getRow(i+1);
            for(int j=0;j<colcount;j++)
            {
                 obj[i][j]=row.getCell(j).getStringCellValue();
            }
        }
        return obj;
    }
}
