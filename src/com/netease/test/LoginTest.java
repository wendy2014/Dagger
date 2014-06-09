package com.netease.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fmb.common.Common;


public class LoginTest 
{
	public WebDriver dr = null;
	Common comm = null;
	/*
	 * 打开浏览器并打开首页
	 */
	@BeforeClass
	  public void beforeClass() throws InterruptedException 
	  {
//		Chrome浏览器
		System.setProperty("webdriver.chrome.driver", "G:\\Application\\chromedriver.exe");
		System.setProperty("webdriver.chrome.bin","C:\\Users\\user\\AppData\\Local\\Google\\Chrome\\Application\\Chrome.exe");    
		dr = new ChromeDriver();
//		
//		IE浏览器，会报错[Forwarding newSession on session null to remote]-[INFO] I/O exception (java.net.SocketException) caught when processing request: Software caused connection abort: recv failed
//		System.setProperty("webdriver.ie.driver", "G:\\Application\\IEDriverServer.exe");
//		System.setProperty("webdriver.ie.bin", "C:\\Program Files (x86)\\Internet Explorer\\iexplore.exe");
//		WebDriver dr = new InternetExplorerDriver();
		
//		Firefox浏览器
//		WebDriver dr = new FirefoxDriver();
		
		dr.get("http://bj.fumubang.net/");
		Thread.sleep(3000);
		dr.manage().window().maximize();
	  }
	/*
	 * 登录
	 */
	@Test
	public void LoginTestCase() throws InterruptedException
	{
//		dr.findElement(By.linkText("登录")).click();
		dr.findElement(By.xpath("//li[@class='login_none']/a")).click();
		
		dr.switchTo().frame(1);
//		WebElement username = dr.findElement(By.id("email"));
//		WebElement password = dr.findElement(By.id("password"));
//		WebElement submit = dr.findElement(By.id("loginBtn"));
		
		WebElement username = dr.findElement(By.xpath("//input[@id='email']"));
		WebElement password = dr.findElement(By.xpath("//input[@id='password']"));
		WebElement submit = dr.findElement(By.xpath("//button[@id='loginBtn']"));
		
		username.sendKeys("123@11.cn");
		password.sendKeys("123456");
		submit.click();
		System.out.println("LoginTestCase方法：" + dr.getCurrentUrl());
	}
//	  @Test
//	  public void LoginTestCase()
//	  {
//		  dr.switchTo().frame(1);
//		  Login lp = new Login(dr);
//		  lp.setUsername("Shuwen.Zhang@linktone.com");
//		  lp.setPassword("123456");
//		  lp.clickSubmit();
//	  }
	
	/*
	 * 进入到亲子社区-淘货秀场页
	 */
	  @Test(dependsOnMethods="LoginTestCase")
	  public void afterLoginTest()
	  {
		  dr.switchTo().defaultContent();
		  dr.findElement(By.className("zs_sq")).click();
		  dr.findElement(By.linkText("淘货秀场")).click();
		  comm = new Common(dr);
		  comm.switchToWindow("淘货秀场-父母邦亲子社区");
		  System.out.println("afterLoginTest方法：" + dr.getCurrentUrl());
	  }
	  /*
	   * 判断是否加入圈子，未加入的话就加入圈子，已加入的话就直接发帖
	   */
	  @Test(dependsOnMethods="afterLoginTest")
	  public void SheQuTestCase() throws InterruptedException
	  {
//		  comm = new Common(dr);                         //@Test写有依赖就不用写这两行语句了
//		  comm.switchToWindow("淘货秀场-父母邦亲子社区");      
		  System.out.println("SheQuTestCase方法：" + dr.getCurrentUrl());
		  Thread.sleep(5000);
		  WebElement createtopic = dr.findElement(By.xpath("//a[@class='n_txt create_topic_btn']"));
		  if(comm.isElementExist(By.xpath("//a[@class='join_group jrqz']")))   //如果存在加入圈子元素
			{
			  dr.findElement(By.xpath("//a[@class='join_group jrqz']")).click();
			  Thread.sleep(5000);
			  dr.findElement(By.xpath("//a[@class='n_txt create_topic_btn']")).click();
			}
			else if(comm.isElementExist(By.className("tcqz")))     //如果存在退出圈子元素
			{
				createtopic.click();
			}
	  }
	  /*
	   * 发帖子
	   */
	  @Test(dependsOnMethods="SheQuTestCase")
	  public void createTopicTestCase() 
	  {
		System.out.println("createTopicTestCase方法：" + dr.getCurrentUrl());
		WebElement title = dr.findElement(By.id("group_title"));
		title.sendKeys("测试标题");
		dr.switchTo().frame("ueditor_0");
		WebElement content = dr.findElement(By.xpath("//body"));
		content.sendKeys("测试一下正文输入");
		dr.switchTo().defaultContent();
		WebElement post = dr.findElement(By.id("post_btn"));
		post.click();
		WebElement confirm = dr.findElement(By.xpath("//p[@class='tc_p_b']/a"));
		confirm.click();
	  }
	  /*
	   * 删除帖子
	   */
	  @Test(dependsOnMethods="createTopicTestCase")
	  public void deleTopicTestCase() throws InterruptedException
	  {
		  Thread.sleep(5000);
		  WebElement del = dr.findElement(By.xpath("//*[@id='topic_del']"));
		  del.click();
		  WebElement delconf = dr.findElement(By.xpath("//p[@class='tc_p_b']/a[1]"));
		  delconf.click();
	  }
	  
//	  @AfterClass
//	  public void quit()
//	  {
//		  dr.quit();
//	  }
	  
	
}
