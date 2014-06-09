package com.netease.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

public class BaiDu 
{
	@Test
	public void BaiduTest() throws InterruptedException
	{
//		System.setProperty("webdriver.chrome.driver", "G:\\Application\\chromedriver.exe");
//		System.setProperty("webdriver.chrome.bin","C:\\Users\\user\\AppData\\Local\\Google\\Chrome\\Application\\Chrome.exe");
//		WebDriver dr = new ChromeDriver();
		
		WebDriver dr = new FirefoxDriver();
		
//		System.setProperty("webdriver.ie.driver", "G:\\Application\\IEDriverServer.exe");
//		System.setProperty("webdriver.ie.bin", "C:\\Program Files (x86)\\Internet Explorer\\iexplore.exe");
//		WebDriver dr = new InternetExplorerDriver();   //IE找不到frame中的元素
		
		dr.get("http://bj.fumubang.net/");
		dr.findElement(By.xpath("//li[@class='login_none']/a")).click();
		Thread.sleep(3000);
		dr.switchTo().frame(1);
		Thread.sleep(3000);
		WebElement username = dr.findElement(By.xpath("//input[@id='email']"));
		WebElement password = dr.findElement(By.xpath("//input[@id='password']"));
		WebElement submit = dr.findElement(By.xpath("//button[@id='loginBtn']"));
		
		username.sendKeys("123@11.cn");
		password.sendKeys("123456");
		submit.click();
		System.out.println("LoginTestCase方法：" + dr.getCurrentUrl());
		
	}

}
