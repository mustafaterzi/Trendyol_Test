package com.trendyol.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class setting {
    protected static WebDriver driver;
    static WebDriverWait wait;
    static String webSiteMainUrl="https://www.trendyol.com/";

    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Mustafa Terzi\\IdeaProjects\\trendyol_1\\drive\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void after() throws InstantiationException{
        System.out.println("Hataaa");
    }
    public String getUrl(){
        return webSiteMainUrl;
    }
    public WebDriver getDriver(){
        return driver;
    }
}
