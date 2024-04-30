package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Opentable {
    Logger logger = Logger.getLogger(getClass());
    @Test
    public void test1() throws IOException, InterruptedException{
        PropertyConfigurator.configure("D:\\Software Testing\\Day 10\\sec2\\src\\main\\resources\\log4j2.properties");
        FileInputStream fs = new FileInputStream("D:\\Software Testing\\Day 10\\sec2excel.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        String data = row.getCell(0).getStringCellValue();
        
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        // driver.manage().window().maximize();
        driver.get("https://www.opentable.com/");
        logger.info("open url");
        Thread.sleep(3000);
        logger.warn("Wait for 3 sec");
        driver.findElement(By.xpath("//*[@id=\'home-page-autocomplete-input\']")).sendKeys(data);
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/header/div/span/div/div/div[2]/div[2]/button")).click();
        logger.info("select Bangalore");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,1500)", "");
        driver.findElement(By.xpath("//*[@id='mainContent']/div/section/div[6]/div/label[4]/span[2]")).click();
        Thread.sleep(3000);
        logger.warn("Wait for 3 sec");
        driver.findElement(By.xpath("//*[@id='mainContent']/div/div/div[2]/div/div[2]/div/div[1]/a/h6")).click();
        Set<String> tabid = driver.getWindowHandles();
        String window = "";
        for(String k:tabid)
        {
            window=k;
        }
        driver.switchTo().window(window);
        Thread.sleep(3000);
        js.executeScript("window.scrollBy(0,500)", "");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"restProfileMainContentDtpPartySizePicker\"]"));
        Select select = new Select(element);
        select.selectByValue("4");logger.info("selected 4 people");
        driver.findElement(By.xpath("//*[@id=\"restProfileMainContentDtpDayPicker-label\"]")).click();
        String month="";
        while(!month.contains("November"))
        {
        driver.findElement(By.xpath("//*[@id=\"restProfileMainContentDtpDayPicker-wrapper\"]/div/div/div/div/div[2]/button[2]")).click();
        month = driver.findElement(By.xpath("//*[@id='react-day-picker-1']")).getText();
        }
        logger.info("Selected November");
        driver.findElement(By.xpath("//*[@id='restProfileMainContentDtpDayPicker-wrapper']/div/div/div/table/tbody/tr[3]/td[2]/button")).click();
        Thread.sleep(3000);
        element = driver.findElement(By.xpath("//*[@id=\"restProfileMainContenttimePickerDtpPicker\"]"));
        select = new Select(element);
        select.selectByVisibleText("6:30 PM");
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[2]/div[1]/section[2]/div[5]/article[1]/div/div[5]/button")).click();
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String path = "D:\\Software Testing\\Day 10\\sec2log\\image.png";
        FileUtils.copyFile(screenshot, new File(path));
        logger.info("Screenshot taken");
        logger.info("Terminated");
        


    }
}
