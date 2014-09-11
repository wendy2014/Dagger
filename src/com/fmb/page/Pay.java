package com.fmb.page;
/*
 * Author: Shuwen
 * Date:20140821
 */
import org.openqa.selenium.By;
import org.testng.Assert;

import com.fmb.common.BrowserEmulator;
import com.fmb.common.LogTools;

public class Pay 
{
	static String confirmpage_active_title;   //购物车内有1个商品的信息
	static String confirmpage_money_sum;
	static String confirmpage_ticket_type;
	static String confirmpage_active_title_1;  //购物车有多个商品的第一个商品
	static String confirmpage_money_sum_1;     //购物车有多个商品的第一个商品
	static String confirmpage_ticket_type_1;
	static String confirmpage_active_title_2;   //购物车有多个商品的第2个商品
	static String confirmpage_money_sum_2;
	static String confirmpage_ticket_type_2;
	static String confirmpage_active_title_3;   //购物车有多个商品的第3个商品
	static String confirmpage_money_sum_3;
	static String confirmpage_ticket_type_3;
	static String confirmpage_money_total;   //确认订单信息页面应付总额
	static String orderid;
    static String ordersum;
    static String orderid_alipay;
    static String ordersum_alipay;
    static String ordersum_yinlian;
    static String orderid_yinlian;
	/*
	 * 点击立即购买
	 */
	public static void clickBuyNow(BrowserEmulator be) throws InterruptedException
	{
		Thread.sleep(3000);
		be.click(CommonFun.yamlconf.getYamlValue("fmb_buy"));    //点击立即购买
	}
	/*
	 * 获取到确认订单信息页面检查内容
	 */
	public static void getConfirmOrderContent(BrowserEmulator be)
	{
		confirmpage_active_title = be.getText(CommonFun.yamlconf.getYamlValue("fmb_confirmorder_active_title"));
		confirmpage_active_title = confirmpage_active_title.trim();
		confirmpage_ticket_type = be.getText(CommonFun.yamlconf.getYamlValue("fmb_confirmorder_active_ticket_type"));
		confirmpage_ticket_type = confirmpage_ticket_type.substring(3);
		confirmpage_money_sum = be.getText(CommonFun.yamlconf.getYamlValue("fmb_confirmorder_money_sum"));
	}
	/*
	 * 获取到确认订单信息页面所有票务检查内容,景点票、演出票、通用票
	 */
	public static void getConfirmOrderAllContent(BrowserEmulator be)
	{
		confirmpage_active_title_1 = be.getTableCellText(1, CommonFun.yamlconf.getYamlValue("fmb_confirmorder_active_title_jingdian"));
		confirmpage_active_title_1 = confirmpage_active_title_1.trim();
		confirmpage_ticket_type_1 = be.getTableCellText(1, CommonFun.yamlconf.getYamlValue("fmb_confirmorder_active_ticket_type_jingdian"));
		confirmpage_ticket_type_1 = confirmpage_ticket_type_1.substring(3);
		confirmpage_money_sum_1 = be.getTableCellText(1, CommonFun.yamlconf.getYamlValue("fmb_confirmorder_money_sum_jingdian"));
		
		confirmpage_active_title_2 = be.getTableCellText(2, CommonFun.yamlconf.getYamlValue("fmb_confirmorder_active_title_yanchu"));
		confirmpage_active_title_2 = confirmpage_active_title_2.trim();
		confirmpage_ticket_type_2 = be.getTableCellText(2, CommonFun.yamlconf.getYamlValue("fmb_confirmorder_active_ticket_type_yanchu"));
		confirmpage_ticket_type_2 = confirmpage_ticket_type_2.substring(3);
		confirmpage_money_sum_2 = be.getTableCellText(2, CommonFun.yamlconf.getYamlValue("fmb_confirmorder_money_sum_yanchu"));
		
		confirmpage_active_title_3 = be.getTableCellText(3, CommonFun.yamlconf.getYamlValue("fmb_confirmorder_active_title_tongyong"));
		confirmpage_active_title_3 = confirmpage_active_title_3.trim();
		confirmpage_ticket_type_3 = be.getTableCellText(3, CommonFun.yamlconf.getYamlValue("fmb_confirmorder_active_ticket_type_tongyong"));
		confirmpage_ticket_type_3 = confirmpage_ticket_type_3.substring(3);
		confirmpage_money_sum_3 = be.getTableCellText(3, CommonFun.yamlconf.getYamlValue("fmb_confirmorder_money_sum_tongyong"));
		
		confirmpage_money_total = be.getText(CommonFun.yamlconf.getYamlValue("fmb_confirmorder_money_total"));
		
	}
	/*
	 * 判断确认订单信息页面内容是否一致
	 */
	public static void assertConfirmOrderContent()
	{
		Assert.assertEquals(confirmpage_active_title, CartPay.cartpage_active_title);
		Assert.assertEquals(confirmpage_ticket_type, CartPay.cartpage_ticket_type);
		Assert.assertEquals(confirmpage_money_sum, CartPay.cartpage_money_sum);
	}
	/*
	 * 判断确认订单信息页面内容是否一致
	 */
	public static void assertConfirmOrderAllContent()
	{
		Assert.assertEquals(confirmpage_active_title_1, CartPay.cartpage_active_title_1);
		Assert.assertEquals(confirmpage_ticket_type_1, CartPay.cartpage_ticket_type_1);
		Assert.assertEquals(confirmpage_money_sum_1, CartPay.cartpage_money_sum_1);
		
		Assert.assertEquals(confirmpage_active_title_2, CartPay.cartpage_active_title_2);
		Assert.assertEquals(confirmpage_ticket_type_2, CartPay.cartpage_ticket_type_2);
		Assert.assertEquals(confirmpage_money_sum_2, CartPay.cartpage_money_sum_2);
		
		Assert.assertEquals(confirmpage_active_title_3, CartPay.cartpage_active_title_3);
		Assert.assertEquals(confirmpage_ticket_type_3, CartPay.cartpage_ticket_type_3);
		Assert.assertEquals(confirmpage_money_sum_3, CartPay.cartpage_money_sum_3);
		
		Assert.assertEquals(confirmpage_money_total, CartPay.cartpage_money_total);
	}
	/*
	 * 提交订单
	 */
	public static void submitOrder(BrowserEmulator be) throws InterruptedException
	{
		Thread.sleep(3000);
		be.click(CommonFun.yamlconf.getYamlValue("fmb_submit_order"));              //点击提交订单
		Thread.sleep(3000);
	}
	/*
	 * 提交订单成功后，支付宝支付，判断订单号和应付总额
	 */
	public static void payOrderAlipay(BrowserEmulator be) throws InterruptedException
	{
		orderid = be.getText(CommonFun.yamlconf.getYamlValue("fmb_order_id"));    //找到订单号
		ordersum = be.getText(CommonFun.yamlconf.getYamlValue("fmb_order_should_pay_sum"));      //找到订单应付总额
		ordersum = ordersum.substring(1);
//		System.out.println(orderid);
//		System.out.println(ordersum);
		be.click(CommonFun.yamlconf.getYamlValue("fmb_pay_order"));   //点击去支付按钮
		Thread.sleep(3000);
		LogTools.screenShot(be);            //截取支付宝当前页面
		orderid_alipay = be.getText(CommonFun.yamlconf.getYamlValue("fmb_order_alipay_order_id"));  //支付宝页面获取到的订单id
		ordersum_alipay = be.getText(CommonFun.yamlconf.getYamlValue("fmb_order_alipay_pay_sum"));  //支付宝页面获取到的订单金额
//		System.out.println(orderid);
//		System.out.println(ordersum);
//		Assert.assertEquals(orderid_alipay, orderid);
		Assert.assertEquals(ordersum_alipay, ordersum);
	}
	/*
	 * 提交订单成功后，银联支付
	 */
	public static void payOrderYinlian(BrowserEmulator be) throws InterruptedException
	{
		be.click(CommonFun.yamlconf.getYamlValue("fmb_pay_yinlian"));          //选择银联支付
		be.click(CommonFun.yamlconf.getYamlValue("fmb_pay_order"));           //点击去支付按钮
		Thread.sleep(3000);
		LogTools.screenShot(be);             //截取银联当前页面
		orderid_yinlian = be.getText(CommonFun.yamlconf.getYamlValue("fmb_order_yinlian_order_id"));    //银联页面获取到的订单id
		orderid_yinlian = orderid_yinlian.substring(5);                               
		ordersum_yinlian = be.getText(CommonFun.yamlconf.getYamlValue("fmb_order_yinlian_pay_sum"));  //银联页面获取到的订单金额
//		System.out.println(orderid_yinlian + ordersum_yinlian);
		Assert.assertEquals(orderid_alipay, orderid);
		Assert.assertEquals(ordersum_alipay, ordersum);
	}
	/*
	 * 在我的订单页面点击  去支付  按钮
	 */
	public static void clickMyOrderPayBtn(BrowserEmulator be)
	{
		be.click(CommonFun.yamlconf.getYamlValue("fmb_myorder_payorder"));
	}
	public static void openMyOrderPage(BrowserEmulator be)
	{
		be.open(CommonFun.yamlconf.getYamlValue("fmb_myorder_url"));
	}
	public static void cancelOrder(BrowserEmulator be, String cancelreason) throws InterruptedException
	{
		Thread.sleep(3000);
		LogTools.screenShot(be);          //截取到我的订单列表当前页面
		be.click(CommonFun.yamlconf.getYamlValue("fmb_cancel_order"));
//		be.click("//tbody[@data-id=orderid]/tr[3]/td[@class='trade-operate']/a[@class='c_999 J_cancel']"); //定位失败
		be.type(CommonFun.yamlconf.getYamlValue("fmb_cancel_order_reason"), cancelreason);
		be.click(CommonFun.yamlconf.getYamlValue("fmb_cancel_confirm"));
		Thread.sleep(3000);
	}
	public static void cancelOrders(BrowserEmulator be, String cancelreason) throws InterruptedException
	{
		System.out.println(be.getSize(CommonFun.yamlconf.getYamlValue("fmb_cancel_order")));
		for(int i=0; i<be.getSize(CommonFun.yamlconf.getYamlValue("fmb_cancel_order")); i++)
		{
//			Pay.cancelOrder(be, cancelreason);
			be.click(CommonFun.yamlconf.getYamlValue("fmb_cancel_order"));
			be.type(CommonFun.yamlconf.getYamlValue("fmb_cancel_order_reason"), cancelreason);
			be.click(CommonFun.yamlconf.getYamlValue("fmb_cancel_confirm"));
			Thread.sleep(3000);
		}
		
	}

}
