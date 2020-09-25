package com.trendyol.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class testCase extends page {
    WebDriverWait wait = new WebDriverWait(driver, 500);

    //Sayfa açılış kontrol
    @Test
    public void test_1() {
        driver.get("https://www.trendyol.com/");
        waitForPageLoad();
        Assert.assertTrue(driver.getTitle().equals("En Trend Ürünler Türkiye'nin Online Alışveriş Sitesi Trendyol'da"));
        System.out.println("Site Açıldı");
    }

    //Login Sayfa Kontrol Testi
    @Test
    public void test_2() throws InterruptedException {
        //Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@class='fancybox-item fancybox-close']")).click();
        driver.findElement(By.className("icon-container")).click();
        wait.until(elementClickableById("loginSubmit"));
        Assert.assertTrue(findById("loginSubmit").getText().equals("Giriş Yap"));
        System.out.println("Sayfa kullanıcı girişi için hazır");
    }

    //Login Kontrol Testi
    @Test
    public void test_3() throws InterruptedException {
        driver.findElement(By.id("email")).sendKeys("mustafa.tturk18@gmail.com");
        findById("password").sendKeys("şifreyazılacak");
        findById("loginSubmit").click();
        Thread.sleep(5000);
        WebElement elem = driver.findElement(By.className("modal-close"));
        elem.click();
        waitForPageLoad();
        Assert.assertTrue(findById("logged-in-container").getText().equals("Hesabım"));
        System.out.println("Kullanıcı Girişi Başarılı");

    }

    // Arama Kontrol Testi
    @Test
    public void test_4() throws InterruptedException{
        findByClassName("search-box").click();
        findByClassName("search-box").sendKeys("bilgisayar");
        Thread.sleep(2000);
        driver.findElement(By.linkText("Bilgisayar")).click();
        System.out.println("Bilgisayar için sonuç bulundu");
    }

    //Rastgele ürün seçimi
    @Test
    public void test_5() throws InterruptedException{
        Thread.sleep(200);
        List <WebElement> listings = driver.findElements(By.xpath("//*[@id=\"search-app\"]/div/div[2]/div[2]/div[2]/div"));
        Random r = new Random();
        int randomValue = r.nextInt(listings.size());
        listings.get(randomValue).click();
        Thread.sleep(1000);
        findByXpad("//*[@id=\"product-detail-app\"]/div/div[2]/div[2]/div[2]/div[1]/button[1]").click();
    }

    @Test
    public void test_6() throws InterruptedException{
        Thread.sleep(1000);
        String fiyat1 = driver.findElement(By.xpath("//span[@class='prc-slg']")).getText();
        //System.out.println(fiyat1);
        Thread.sleep(1000);
        findByXpad("//a[@href='/sepetim#/basket']").click();

        Thread.sleep(1000);
        String fiyat2 = driver.findElement(By.xpath("//*[@id=\"partial-basket\"]/div/div[2]/div[2]/div[3]/div[2]")).getText();
        //System.out.println(fiyat2);

        if(fiyat1.equals(fiyat2))
        {
            System.out.println("Fiyatlar Birbiri ile uyuşmaktadır.");
        }
        else {
            System.out.println("Fiyatlar birbiri ile uyuşmamaktadır.");
        }
    }

    //Ürün adet artırma ve kontrol Testi
    @Test
    public void test_7() throws InterruptedException{
        Thread.sleep(1000);
        findByXpad("//*[@id=\"partial-basket\"]/div/div[2]/div[2]/div[3]/div[1]/div/button[2]").click();
        Assert.assertTrue(!findByClassName("counter-content").getText().equals("2"));
        System.out.println("Ürün adeti arttırılmıştır ve kontrol edilmiştir.");
    }

    @Test
    public void test_8() throws InterruptedException{
        Thread.sleep(1000);
        findByXpad("//*[@id=\"partial-basket\"]/div/div[2]/div[2]/div[3]/button/i").click();
        Thread.sleep(1000);
        findByXpad("//*[@id=\"ngdialog1\"]/div[2]/form/div/div[2]/div/div[1]/button[2]").click();
        Assert.assertTrue(!findByXpad("//*[@id=\"basketNoProductPage\"]/div[2]/div/div[1]/p/span").getText().equals("Sepetinizde ürün bulunmamaktadır."));
        System.out.println("Sepette ürün bulunmamaktadır");
    }
}
