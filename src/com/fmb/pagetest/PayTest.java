package com.fmb.pagetest;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fmb.common.BrowserEmulator;
import com.fmb.datadriver.Constant;
import com.fmb.datadriver.ExcelUtils;
import com.fmb.page.*;

public class PayTest 
{
    BrowserEmulator be = null;
    
	@BeforeClass
	  public void beforeClass() 
	  {
		  be = new BrowserEmulator();
	  }
	@Test
	public void Login() throws Exception
	{
		Login.openURL(be);
		Login.clickLoginButton(be);
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Login");
		Login.typeInIframe(be, ExcelUtils.getCellData(1, 1), ExcelUtils.getCellData(1, 2));
	}
	@Test(dependsOnMethods="Login")
	public void payAlipay() throws InterruptedException
	{
		Pay.openTestUrl(be);
		Pay.submitOrder(be);
		Pay.payOrderAlipay(be);
	}
	@Test(dependsOnMethods="payAlipay")
	public void payYinlian() throws InterruptedException
	{
		Pay.openMyOrderPage(be);
		Pay.payOrderYinlian(be);
	}
	@Test(dependsOnMethods="payYinlian")
	public void cancelOrder() throws Exception
	{
		Pay.openMyOrderPage(be);
		Thread.sleep(3000);
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "cancelOrder");
		Pay.cancelOrder(be, ExcelUtils.getCellData(1, 1));
		Thread.sleep(3000);
		be.refresh();
	}
	@AfterClass
	public void quit()
	{
		be.quit();
	}
	
}
