package com.fmb.page;

import org.testng.Assert;

import com.fmb.common.BrowserEmulator;

public class CartPay 
{
	static String cart_num;
	static String buy_num;
	static String active_title;
	static String money_sum;
	static String ticket_type_jingdian;
	static String ticket_type_yanchu;
	static String ticket_type_tongyong_property1;
	static String ticket_type_tongyong_property2;
	static String cartpage_active_title;
	static String cartpage_money_sum;
	static String cartpage_ticket_type;
	/*
	 * 点击购物车按钮
	 */
	public static void clickCartBtn(BrowserEmulator be)
	{
		be.click(CommonFun.yamlconf.getYamlValue("fmb_cart"));
	}
	/*
	 * 选择景点票时间，为当前第一个可选日期
	 */
	public static void selectJingDianTicketDate(BrowserEmulator be)
	{
		be.click(CommonFun.yamlconf.getYamlValue("fmb_test_jingdian_ticket_date_Btn"));
		be.click(CommonFun.yamlconf.getYamlValue("fmb_test_jingdian_ticket_date"));
	}
	/*
	 * 选择演出票商品种类
	 */
	public static void selectYanChuTicketType(BrowserEmulator be) throws InterruptedException
	{
		Thread.sleep(3000);
		be.click(CommonFun.yamlconf.getYamlValue("fmb_select_ticket"));         //选择票务种类
	}
	/*
	 * 选择通用票商品种类
	 */
	public static void selectTongYongTicketType(BrowserEmulator be) throws InterruptedException
	{
		Thread.sleep(3000);
		be.click(CommonFun.yamlconf.getYamlValue("fmb_test_tongyong_ticket_property2"));         //选择票务种类
	}
	/*
	 * 购买数量+1
	 */
	public static void selectNumPls(BrowserEmulator be)
	{
		be.click(CommonFun.yamlconf.getYamlValue("fmb_test_jingdian_ticket_num"));
	}
	/*
	 * 点击加入购物车
	 */
	public static void clickJoinCart(BrowserEmulator be)
	{
		be.click(CommonFun.yamlconf.getYamlValue("fmb_join_cart"));
	}
	/*
	 * 点击继续购物
	 */
	public static void clickContinueShop(BrowserEmulator be)
	{
		be.click(CommonFun.yamlconf.getYamlValue("fmb_continue_shop_Btn"));
	}
	/*
	 * 点击去购物车
	 */
	public static void clickGoToCart(BrowserEmulator be)
	{
		be.click(CommonFun.yamlconf.getYamlValue("fmb_go_to_shopcart"));
	}
	/*
	 * 首页页面上方购物车数量
	 */
	public static void getCartNum(BrowserEmulator be)
	{
		cart_num = be.getText(CommonFun.yamlconf.getYamlValue("fmb_cart_num"));
		cart_num = cart_num.substring(1, 2);
	}
	/*
	 * 获取到景点票商品信息页面的各项检查内容
	 */
	public static void getJingDianPageContent(BrowserEmulator be)
	{
		active_title = be.getText(CommonFun.yamlconf.getYamlValue("fmb_test_active_title")); //获取到当前活动标题
		active_title = active_title.trim();
		ticket_type_jingdian = be.getText(CommonFun.yamlconf.getYamlValue("fmb_test_jingdian_ticket_type"));  //获取到当前票种类型
		buy_num = be.getText(CommonFun.yamlconf.getYamlValue("fmb_buy_num"));  //获取到当前购买的数量
		money_sum = be.getText(CommonFun.yamlconf.getYamlValue("fmb_test_money_sum"));  //获取到购买金额
	}
	/*
	 * 获取到演出票商品信息页面的各项检查内容
	 */
	public static void getYanChuPageContent(BrowserEmulator be)
	{
		active_title = be.getText(CommonFun.yamlconf.getYamlValue("fmb_test_active_title")); //获取到当前活动标题
		active_title = active_title.trim();
		ticket_type_yanchu = be.getText(CommonFun.yamlconf.getYamlValue("fmb_test_yanchu_ticket_type"));  //获取到当前票种类型
		buy_num = be.getText(CommonFun.yamlconf.getYamlValue("fmb_buy_num"));  //获取到当前购买的数量
		money_sum = be.getText(CommonFun.yamlconf.getYamlValue("fmb_test_money_sum"));  //获取到购买金额
	}
	/*
	 * 获取到通用商品信息页面的各项检查内容
	 */
	public static void getTongYongPageContent(BrowserEmulator be)
	{
		active_title = be.getText(CommonFun.yamlconf.getYamlValue("fmb_test_active_title")); //获取到当前活动标题
		active_title = active_title.trim();
		ticket_type_tongyong_property1 = be.getAttribute(CommonFun.yamlconf.getYamlValue("fmb_test_tongyong_ticket_property1"), "date");
		ticket_type_tongyong_property2 = be.getText(CommonFun.yamlconf.getYamlValue("fmb_test_tongyong_ticket_property2"));  //获取到当前票种类型
		buy_num = be.getText(CommonFun.yamlconf.getYamlValue("fmb_buy_num"));  //获取到当前购买的数量
		money_sum = be.getText(CommonFun.yamlconf.getYamlValue("fmb_test_money_sum"));  //获取到购买金额
	}
	/*
	 * 获取到购物车页面的各项检查内容
	 */
	public static void getCartPageContent(BrowserEmulator be)
	{
		cartpage_active_title = be.getText(CommonFun.yamlconf.getYamlValue("fmb_cartpage_active_title"));
		cartpage_active_title = cartpage_active_title.trim();
		cartpage_ticket_type = be.getText(CommonFun.yamlconf.getYamlValue("fmb_cartpage_active_ticket_type"));
		cartpage_ticket_type = cartpage_ticket_type.substring(3);
		cartpage_money_sum = be.getText(CommonFun.yamlconf.getYamlValue("fmb_cartpage_money_sum"));
	}
	/*
	 * 判断景点票信息是否一致
	 */
	public static void assertJingDianContent()
	{
		Assert.assertEquals(cartpage_active_title, active_title);
		Assert.assertEquals(cartpage_ticket_type, ticket_type_jingdian);
		Assert.assertEquals(cartpage_money_sum, money_sum);
	}
	/*
	 * 判断演出票信息是否一致
	 */
	public static void assertYanChuContent()
	{
		Assert.assertEquals(cartpage_active_title, active_title);
		Assert.assertEquals(cartpage_ticket_type, ticket_type_yanchu);
		Assert.assertEquals(cartpage_money_sum, money_sum);
	}
	/*
	 * 判断通用票信息是否一致
	 */
	public static void assertTongYongContent()
	{
		Assert.assertEquals(cartpage_active_title, active_title);
		Assert.assertEquals(cartpage_ticket_type, ticket_type_tongyong_property1);
		Assert.assertEquals(cartpage_money_sum, money_sum);
	}
	/*
	 * 点击去结算
	 */
	public static void clickGoToPay(BrowserEmulator be)
	{
		be.click(CommonFun.yamlconf.getYamlValue("fmb_cartpage_goto_pay"));
	}

}
