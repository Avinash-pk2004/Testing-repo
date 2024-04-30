package com.example;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    WebDriver driver;
    ExtentReports report;
    ExtentTest test;
    @BeforeMethod
    public void bt()
    {
        ExtentSparkReporter sparkReporter=new ExtentSparkReporter("D:\\ExtentHtmlReporter\\report2.html");
        report=new ExtentReports();
        report.attachReporter(sparkReporter);
        test=report.createTest("test2", "testing description");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
        driver = new ChromeDriver(chromeOptions);
    }
    @Test(dataProvider = "dat1")
    public void test1(String loan,String pa,String tenure)
    {
        SoftAssert assert1=new SoftAssert();
        driver.get("https://groww.in/");
        driver.findElement(By.xpath("//*[@id=\"footer\"]/div/div[1]/div/div[1]/div[2]/div[3]/a[2]")).click();
        WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/a[15]/div/p[1]")));
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/a[15]/div/p[1]")).click();
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"LOAN_AMOUNT\"]")));
        try {
            driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/h1"));
            assert1.assertTrue(true);
        } catch (NoSuchElementException e) {
            // TODO: handle exception
            System.out.println(e);
            assert1.assertTrue(false);
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

WebElement element1 = driver.findElement(By.xpath("//*[@id=\"LOAN_AMOUNT\"]"));
element1.clear(); // Clear the existing value
wait.until(ExpectedConditions.textToBePresentInElementValue(element1, "")); // Wait until the value is cleared
element1.sendKeys(loan); // Enter the new value

WebElement element2 = driver.findElement(By.xpath("//*[@id=\"RATE_OF_INTEREST\"]"));
element2.clear(); // Clear the existing value
wait.until(ExpectedConditions.textToBePresentInElementValue(element2, "")); // Wait until the value is cleared
element2.sendKeys(pa); // Enter the new value

WebElement element3 = driver.findElement(By.xpath("//*[@id=\"LOAN_TENURE\"]"));
element3.clear(); // Clear the existing value
wait.until(ExpectedConditions.textToBePresentInElementValue(element3, "")); // Wait until the value is cleared
element3.sendKeys(tenure); // Enter the new value

    }
    @AfterMethod
    public void am(ITestResult result) throws IOException
    {
        if(result.getStatus()==ITestResult.FAILURE)
        {
            test.log(Status.FAIL,"testcase failed:"+result.getName());
            test.log(Status.FAIL, "testcase failed reason:"+result.getThrowable());
            File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String path="D:\\"+result.getName()+".png";
            FileUtils.copyFile(file,new File(path));
        }
        else if(result.getStatus()==ITestResult.SUCCESS)
        {
            test.log(Status.PASS,"Testcase passed: "+result.getName());
        }
        else if(result.getStatus()==ITestResult.SKIP)
        {
             test.log(Status.SKIP,"testcase skipped:"+result.getName());
        }
    }
    @DataProvider(name="dat1")
public Object[][] dp1() throws IOException
{
    FileInputStream fs=new FileInputStream("D:\\Excel data\\book3.xlsx");
    XSSFWorkbook workbook=new XSSFWorkbook(fs);
    XSSFSheet sheet=workbook.getSheetAt(0);
    int rowCount=sheet.getLastRowNum();
    int colCount=sheet.getRow(0).getLastCellNum();
    Object[][] arr=new Object[rowCount][colCount];
     for(int i=0;i<rowCount;i++)
     {
        Row row=sheet.getRow(i+1);
        for(int j=0;j<colCount;j++)
        {
             arr[i][j]=Double.toString(row.getCell(j).getNumericCellValue());
        }
     }

    return arr;
}
}
