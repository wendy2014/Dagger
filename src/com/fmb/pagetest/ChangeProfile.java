package com.fmb.pagetest;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fmb.common.BrowserEmulator;
import com.fmb.datadriver.Constant;
import com.fmb.datadriver.ExcelUtils;
import com.fmb.page.Login;

public class ChangeProfile 
{
	BrowserEmulator be = null;
	
	@BeforeClass
	  public void beforeClass() 
	  {
		  be = new BrowserEmulator();
	  }
	
//	@Test
//	public void Login() throws Exception
//	{
//		Login.openURL(be);
//		Login.clickLoginButton(be);
//		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Login");
//		Login.typeInIframe(be, ExcelUtils.getCellData(1, 1), ExcelUtils.getCellData(1, 2));
//	}
//	@Test(dependsOnMethods="Login")
//	public void setProfile()
//	{
//		be.click("//a[@class='home_url']");
//		be.click("//a[contains(text(), '个人设置')]");
//		be.click("//a[contains(text(), '更改头像')]");   
//	}
//	@Test
//	public void setFile() throws IOException, InterruptedException
//	{
//		be.open("http://www.fumubang.net/shop/review_album_upload?id=65777");
//		be.click("//object");
//		Runtime.getRuntime().exec("D:\\AotuItFile\\UploadProfileFile.exe");
//		Thread.sleep(5000);
//	}
	@Test
	public void test() throws IOException, InterruptedException
	{
		be.open("http://www.toolsqa.com/automation-practice-form/");
		Thread.sleep(5000);
		be.click("//input[@id='photo']");
		Runtime.getRuntime().exec("D:\\AotuItFile\\UploadProfileFile.exe");
	}

}
