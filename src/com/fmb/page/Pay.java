package com.fmb.page;

import org.testng.Assert;

import com.fmb.common.BrowserEmulator;
import com.fmb.common.GetYamlFileConfig;
import com.fmb.common.LogTools;

public class Pay 
{
	public static String test_url;
	static GetYamlFileConfig yamlconf;
	static String orderid;
    static String ordersum;
    static String orderid_alipay;
    static String ordersum_alipay;
    static String ordersum_yinlian;
    static String orderid_yinlian;
	public static void openTestUrl(BrowserEmulator be)
	{
		yamlconf = new GetYamlFileConfig();
		test_url = yamlconf.getYamlValue("fmb_test_url");
		be.open(test_url);
	}
	public static void submitOrder(BrowserEmulator be) throws InterruptedException
	{
		be.maxBrowser();
		Thread.sleep(3000);
		be.click(yamlconf.getYamlValue("fmb_select_ticket"));         //选择票务种类
		Thread.sleep(3000);
		be.click(yamlconf.getYamlValue("fmb_buy"));    //点击立即购买
		Thread.sleep(3000);
		be.click(yamlconf.getYamlValue("fmb_submit_order"));              //点击提交订单
		Thread.sleep(3000);
	}
	public static void payOrderAlipay(BrowserEmulator be)
	{
		orderid = be.getText(yamlconf.getYamlValue("fmb_order_id"));    //找到订单号
		ordersum = be.getText(yamlconf.getYamlValue("fmb_order_should_pay_sum"));      //找到订单应付总额
		ordersum = ordersum.substring(1);
//		System.out.println(orderid);
//		System.out.println(ordersum);
		
		be.click(yamlconf.getYamlValue("fmb_pay_order"));   //点击立即支付
		LogTools.screenShot(be);            //截取支付宝当前页面
		orderid_alipay = be.getText(yamlconf.getYamlValue("fmb_order_alipay_order_id"));  //支付宝页面获取到的订单id
		ordersum_alipay = be.getText(yamlconf.getYamlValue("fmb_order_alipay_pay_sum"));  //支付宝页面获取到的订单金额
//		System.out.println(orderid);
//		System.out.println(ordersum);
		Assert.assertEquals(orderid_alipay, orderid);
		Assert.assertEquals(ordersum_alipay, ordersum);
	}
	public static void payOrderYinlian(BrowserEmulator be)
	{
		be.click(yamlconf.getYamlValue("fmb_myorder_payorder"));     //我的订单页面点击去支付按钮
		be.click(yamlconf.getYamlValue("fmb_pay_yinlian"));          //选择银联支付
		be.click(yamlconf.getYamlValue("fmb_pay_order"));           //点击立即支付按钮
		LogTools.screenShot(be);             //截取银联当前页面
		orderid_yinlian = be.getText(yamlconf.getYamlValue("fmb_order_yinlian_order_id"));    //银联页面获取到的订单id
		orderid_yinlian = orderid_yinlian.substring(5);                               
		ordersum_yinlian = be.getText(yamlconf.getYamlValue("fmb_order_yinlian_pay_sum"));  //银联页面获取到的订单金额
//		System.out.println(orderid_yinlian + ordersum_yinlian);
		Assert.assertEquals(orderid_alipay, orderid);
		Assert.assertEquals(ordersum_alipay, ordersum);
	}
	public static void openMyOrderPage(BrowserEmulator be)
	{
		be.open(yamlconf.getYamlValue("fmb_myorder_url"));
	}
	public static void cancelOrder(BrowserEmulator be, String cancelreason)
	{
		LogTools.screenShot(be);          //截取到我的订单列表当前页面
		be.click(yamlconf.getYamlValue("fmb_cancel_order"));
//		be.click("//tbody[@data-id=orderid]/tr[3]/td[@class='trade-operate']/a[@class='c_999 J_cancel']"); //定位失败
		be.type(yamlconf.getYamlValue("fmb_cancel_order_reason"), cancelreason);
		be.click(yamlconf.getYamlValue("fmb_cancel_confirm"));
	}

}
