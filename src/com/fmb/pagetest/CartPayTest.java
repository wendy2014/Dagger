package com.fmb.pagetest;

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
	/*
	 * 景点票加入购物车下订单支付流程
	 */
	
	@Test(groups="joinJingDianTicket", dependsOnMethods="Login")
	public void joinJingDianTicket() throws InterruptedException
	{
		CartPay.clickCartBtn(be);
		CartPay.clearCarts(be);
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
	@Test(groups="joinJingDianTicket",dependsOnMethods="joinJingDianTicket")
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
	@Test(groups="joinJingDianTicket",dependsOnMethods="payJingDianTicket")
	public void cancelOrder() throws Exception
	{
		Pay.openMyOrderPage(be);
		Thread.sleep(3000);
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "cancelOrder");
		Pay.cancelOrders(be, ExcelUtils.getCellData(1, 1));
		Thread.sleep(3000);
		be.refresh();
	}
	/*
	 * 演出票加入购物车下订单支付流程
	 */
	@Test(groups="joinYanChuTicket" , dependsOnGroups="joinJingDianTicket")
	public void joinYanChuTicket() throws InterruptedException
	{
		CommonFun.openTestYanchuUrl(be);
		Thread.sleep(3000);
		CartPay.selectYanChuTicketType(be);
		CartPay.selectNumPls(be);
		CartPay.getYanChuPageContent(be);
		CartPay.clickJoinCart(be);
		CartPay.clickGoToCart(be);
		CartPay.getCartPageContent(be);
		CartPay.assertYanChuContent();
	}
	@Test(groups="joinYanChuTicket" , dependsOnGroups="joinJingDianTicket", dependsOnMethods="joinYanChuTicket")
	public void payYanChuTicket() throws InterruptedException
	{
		this.payJingDianTicket();
	}
	@Test(groups="joinYanChuTicket" , dependsOnGroups="joinJingDianTicket", dependsOnMethods="payYanChuTicket")
	public void cancelYanChuOrder() throws Exception
	{
		this.cancelOrder();
	}
	/*
	 * 通用票加入购物车下订单支付流程，money_sum为小计的金额，没有加入邮费
	 */
	@Test(groups="joinTongYongTicket" , dependsOnGroups="joinYanChuTicket")
	public void joinTongYongTicket() throws InterruptedException
	{
		CommonFun.openTestTongyongUrl(be);
		Thread.sleep(3000);
		CartPay.selectTongYongTicketType(be);
		CartPay.selectNumPls(be);
		CartPay.getTongYongPageContent(be);
		CartPay.clickJoinCart(be);
		CartPay.clickGoToCart(be);
		CartPay.getCartPageContent(be);
		CartPay.assertTongYongContent();
	}
	@Test(groups="joinTongYongTicket" , dependsOnGroups="joinYanChuTicket", dependsOnMethods="joinTongYongTicket")
	public void payTongYongTicket() throws InterruptedException
	{
		this.payJingDianTicket();
	}
	@Test(groups="joinTongYongTicket" , dependsOnGroups="joinYanChuTicket", dependsOnMethods="payTongYongTicket")
	public void cancelTongYongOrder() throws Exception
	{
		this.cancelOrder();
	}
	
	/*
	 * 将以上三个票种合为一个test
	 */
	@Test(groups="AllTickets", dependsOnGroups="joinTongYongTicket")
	public void joinAllTickets() throws InterruptedException
	{
		CartPay.clickCartBtn(be);
		CartPay.clearCarts(be);
		CommonFun.openTestJingDianUrl(be);
		Thread.sleep(1000);
		CartPay.selectJingDianTicketDate(be);
		CartPay.getJingDianPageContent(be);
		CartPay.clickJoinCart(be);
		CartPay.clickContinueShop(be);
		CommonFun.openTestYanchuUrl(be);
		Thread.sleep(1000);
		CartPay.selectYanChuTicketType(be);
		CartPay.getYanChuPageContent(be);
		CartPay.clickJoinCart(be);
		CartPay.clickContinueShop(be);
		CommonFun.openTestTongyongUrl(be);
		Thread.sleep(1000);
		CartPay.selectTongYongTicketType(be);
		CartPay.getTongYongPageContent(be);
		CartPay.clickJoinCart(be);
		CartPay.clickGoToCart(be);
		CartPay.getCartPageAllContent(be);
		CartPay.assertCartPageAllContent();
	}
	@Test(groups="AllTickets", dependsOnGroups="joinTongYongTicket", dependsOnMethods="joinAllTickets")
	public void payAllTickets() throws InterruptedException
	{
		CartPay.getCartPageAllContent(be);
		CartPay.clickGoToPay(be);
		Thread.sleep(1000);
		Pay.getConfirmOrderAllContent(be);
		Pay.assertConfirmOrderAllContent();
		Pay.submitOrder(be);
		Pay.payOrderAlipay(be);
	}
	@Test(groups="AllTickets", dependsOnGroups="joinTongYongTicket", dependsOnMethods="payAllTickets")
	public void cancelAllTicketsOrder() throws Exception
	{
		this.cancelOrder();
	}
	@AfterClass
	public void quit()
	{
		be.quit();
	}

}
