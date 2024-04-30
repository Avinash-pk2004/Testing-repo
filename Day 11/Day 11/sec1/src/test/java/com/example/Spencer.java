package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Spencer {
    WebDriver driver;
    Logger logger = Logger.getLogger(getClass());
    ExtentReports reports;
    ExtentTest test;
    @BeforeTest
    public void before() throws Exception{
        ExtentSparkReporter exsprk = new ExtentSparkReporter("D:\\Software Testing\\Day 11\\sec1logandreport\\report1.html");
        reports = new ExtentReports();
        reports.attachReporter(exsprk);
    }
    @Test(priority = 1)
    public void test1() throws Exception
    {
        test = reports.createTest("Test 1", "Add to Cart");
        PropertyConfigurator.configure("D:\\Software Testing\\Day 11\\sec1\\src\\main\\resources\\log4j2.properties");
        FileInputStream fs = new FileInputStream("D:\\Software Testing\\Day 11\\sec1excel.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rnum = sheet.getLastRowNum();
        XSSFRow row = sheet.getRow(1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        for(int i=0;i<rnum;i++)
        {
            if(row.getCell(0).getStringCellValue().equals("url"))
            {
                driver.get(row.getCell(1).getStringCellValue());
                row = sheet.getRow(2);
                logger.info("open URL");
            }
            else if(row.getCell(0).getStringCellValue().equals("search"))
            {
                driver.findElement(By.xpath("//*[@id='html-body']/div[3]/header/div[2]/div[3]/div[2]/div")).click();
                driver.findElement(By.xpath("//*[@id=\"search\"]")).sendKeys(row.getCell(1).getStringCellValue()+Keys.ENTER);
                logger.info("search-chocolate");
            }
            
        }
        driver.findElement(By.xpath("//*[@id='narrow-by-list']/div[1]/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div[1]/div[2]/form/ul/li[5]/a/span")).click();
        logger.info("Dairy prod");
        Thread.sleep(5000);
        logger.warn("Wait for  5 sec");
        driver.findElement(By.xpath("/html/body/div[3]/main/div[4]/div[1]/div[5]/div/div/div[2]/ol/li[1]/div/div[2]/div[5]/div/div/form/button")).submit();
        logger.info("Add cart");
        driver.findElement(By.xpath("//*[@id=\"html-body\"]/div[3]/header/div[2]/div[2]/div[1]/a")).click();
        Thread.sleep(3000);
        logger.warn("Wait for  3 sec");
        String res=driver.findElement(By.xpath("//*[@id='minicart-content-wrapper']/div[2]/div[4]/div/a/span/span")).getText();
        Assert.assertTrue(res.contains("Cart"));
        logger.info("Testcase 1 terminated");
    
    }
    
    @Test(priority = 2)
    public void test2() throws Exception{
        test = reports.createTest("Test 2", "Groceries and Staples");
        FileInputStream fs = new FileInputStream("D:\\Software Testing\\Day 11\\sec1excel.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rnum = sheet.getLastRowNum();
        XSSFRow row = sheet.getRow(1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        for(int i=0;i<rnum-1;i++)
        {
            if(row.getCell(0).getStringCellValue().equals("url"))
            {
                driver.get(row.getCell(1).getStringCellValue());
                logger.info("open URL");
                row = sheet.getRow(2);
            }
        }
        driver.findElement(By.xpath("//*[@id='ui-id-2']")).click();
        logger.info("Groceries & Staples");
        driver.findElement(By.xpath("//*[@id=\"ui-id-20\"]")).click();
        logger.info("view all");
        driver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div[1]/div[2]/form/ul/li[2]/a/span")).click();
        logger.info("edible oil");
        Thread.sleep(5000);
        logger.warn("Wait for  5 sec");
        String res = driver.findElement(By.xpath("//*[@id=\"am-shopby-container\"]/ol/li[1]/div")).getText();
        Assert.assertTrue(res.contains("Oil"));
        logger.info("Testcase 2 terminated");
    }
    @Test(priority = 3)
    public void test3() throws Exception{
        test = reports.createTest("Test 3", "Log in page");
        FileInputStream fs = new FileInputStream("D:\\Software Testing\\Day 11\\sec1excel.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rnum = sheet.getLastRowNum();
        XSSFRow row = sheet.getRow(1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        for(int i=0;i<rnum-1;i++)
        {
            if(row.getCell(0).getStringCellValue().equals("url"))
            {
                driver.get(row.getCell(1).getStringCellValue());
                logger.info("open URL");
            }
        }
        driver.findElement(By.xpath("//*[@id='html-body']/div[3]/header/div[2]/div[2]/div[3]/div[1]/div[1]")).click();
        logger.info("Profile");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"html-body\"]/div[3]/header/div[2]/div[2]/div[3]/div[1]/div[2]/div/div[2]/div[5]/div/div/a/span"));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        element = wait.until(ExpectedConditions.elementToBeClickable(element));
        logger.warn("Wait for  20 sec");
        element.click();
        logger.info("Log in");
        sheet = workbook.getSheetAt(1);
        row = sheet.getRow(0);
        for(int i=0;i<rnum;i++)
        {
            if(row.getCell(0).getStringCellValue().equals("email"))
            {
                driver.findElement(By.xpath("//*[@id='email']")).sendKeys(row.getCell(1).getStringCellValue());
                row = sheet.getRow(1);
                logger.info("email");
            }
            else if(row.getCell(0).getStringCellValue().equals("pass"))
            {
                driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(row.getCell(1).getStringCellValue());
                logger.info("pass");
            }
        }
        driver.findElement(By.xpath("//*[@id=\"bnt-social-login-authentication\"]/span")).click();
        Thread.sleep(3000);
        element = driver.findElement(By.xpath("//*[@id='social-login-authentication']/div/div/div"));
        String res = element.getText();
        Assert.assertTrue(res.contains("Invalid"));
        logger.info("Testcase 3 terminated");
    }
    
    @AfterMethod
    public void afterTest(ITestResult result) throws Exception{
        if(result.getStatus()==ITestResult.FAILURE)
        {
            test.log(Status.FAIL, "TestCase Failed: "+result.getName());
            test.log(Status.FAIL, "Testcase Failed Reason: "+result.getThrowable());
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String path = "D:\\Software Testing\\Day 11\\sec1logandreport\\"+result.getName()+"png";
            FileUtils.copyFile(screenshot,new File(path));
            test.addScreenCaptureFromPath(path);

        }
        else if (result.getStatus()==ITestResult.SUCCESS)
        { test.log(Status.PASS, "Test CAse Passed: "+result.getName());
        }
      else if (result.getStatus()==ITestResult.SKIP)
        { test.log(Status.SKIP, "Test CAse Skipped: "+result.getName());
        }
        reports.flush();
        driver.quit();
    }

}
