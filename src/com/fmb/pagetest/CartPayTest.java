package com.fmb.pagetest;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fmb.common.BrowserEmulator;
import com.fmb.datadriver.Constant;
import com.fmb.datadriver.ExcelUtils;
import com.fmb.page.CartPay;
import com.fmb.page.CommonFun;
import com.fmb.page.Login;
import com.fmb.page.Pay;

public class CartPayTest 
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
	public void joinJingDianTicket() throws InterruptedException
	{
		CommonFun.openTestJingDianUrl(be);
		Thread.sleep(3000);
		CartPay.selectJingDianTicketDate(be);
		CartPay.selectNumPls(be);
		CartPay.getJingDianPageContent(be);
		CartPay.clickJoinCart(be);
		CartPay.clickGoToCart(be);
		CartPay.getCartPageContent(be);
		CartPay.assertJingDianContent();
	}
	@Test(dependsOnMethods="joinJingDianTicket")
	public void payJingDianTicket() throws InterruptedException
	{
		CartPay.getCartPageContent(be);
		CartPay.clickGoToPay(be);
		Thread.sleep(3000);
		Pay.getConfirmOrderContent(be);
		Pay.assertConfirmOrderContent();
		Pay.submitOrder(be);
		Pay.payOrderAlipay(be);
	}
	@Test(dependsOnMethods="payJingDianTicket")
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
