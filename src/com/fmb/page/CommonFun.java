package com.fmb.page;

import com.fmb.common.BrowserEmulator;
import com.fmb.common.GetYamlFileConfig;
import com.fmb.common.LogTools;

public class CommonFun
{
	public static String tuangou_url;
	static String first_active_title;
	static GetYamlFileConfig yamlconf = new GetYamlFileConfig();
	/*
	 * 打开景点票电子票活动页面
	 */
	public static void openTestJingDianUrl(BrowserEmulator be)
	{
		be.open(yamlconf.getYamlValue("fmb_test_jingdian_dianzi_url"));
	}
	/*
	 * 打开演出票电子票活动页面
	 */
	public static void openTestUYanchuUrl(BrowserEmulator be)
	{
		be.open(yamlconf.getYamlValue("fmb_test_yanchu_dianzi_url"));
	}
	/*
	 * 打开通用票实体票活动页面
	 */
	public static void openTestTongyongUrl(BrowserEmulator be)
	{
		be.open(yamlconf.getYamlValue("fmb_test_tongyong_shiti_url"));
	}
	/*
	 * 打开团购列表页面url
	 */
	public static void openTuanUrl(BrowserEmulator be)
	{
		tuangou_url = yamlconf.getYamlValue("fmb_tuangou_url");
		be.open(tuangou_url);
		be.maxBrowser();
	}
	/*
	 * 点击团购列表页面第一个活动链接
	 */
	public static void clickFirstActiveUrl(BrowserEmulator be)
	{
		be.click(yamlconf.getYamlValue("fmb_tuan_first_active"));
		first_active_title = be.getAttribute(yamlconf.getYamlValue("fmb_tuan_first_active"),"title");
		first_active_title = first_active_title + "-父母邦亲子活动";
	}
	/*
	 * 判断是哪种类型的活动
	 */
	public static void clickActivePage(BrowserEmulator be) throws InterruptedException
	{
		be.switchToWindow(first_active_title);
		Thread.sleep(3000);
		if(be.isElementPresent(yamlconf.getYamlValue("fmb_test_jingdian_ticket_date_Btn"), 3000))
		{
			CartPay.selectJingDianTicketDate(be);
		}
		else if(be.isElementPresent(yamlconf.getYamlValue("fmb_select_ticket"), 3000))
		{
			CartPay.selectYanChuTicketType(be);
		}
	}
	public static void openFmbSchool(BrowserEmulator be)
	{
		be.open(yamlconf.getYamlValue("fmb_shequ_school_url"));
	}
	public static void clickSheQu(BrowserEmulator be)
	{
		be.click(yamlconf.getYamlValue("fmb_shequ"));
	}
	public static void clickXiuChang(BrowserEmulator be)
	{
		be.click(yamlconf.getYamlValue("fmb_shequ_taohuoxq"));
	}
	
	/*
	 * 判断如果未加入圈子，就先加入圈子然后点击发帖，如果已加入圈子就直接点击发帖
	 */
	public static void clickCreateTopicBtn(BrowserEmulator be)
	{
		if(be.isElementPresent(yamlconf.getYamlValue("fmb_shequ_jiaruquanzi"), 5000))
		{
			be.click(yamlconf.getYamlValue("fmb_shequ_jiaruquanzi"));
			be.pause(5000);
			be.click(yamlconf.getYamlValue("fmb_shequ_createtopicbtn"));
		}
		else if(be.isElementPresent(yamlconf.getYamlValue("fmb_shequ_tcquanzi"), 5000))
		{
			be.click(yamlconf.getYamlValue("fmb_shequ_createtopicbtn"));
		}
	}
	public static void typeCreateTopic(BrowserEmulator be, String title, String body)
	{
		be.type(yamlconf.getYamlValue("fmb_shequ_createtopic_title"), title);
		be.enterFrame(yamlconf.getYamlValue("fmb_shequ_createtopic_body_iframe"));;
		be.type(yamlconf.getYamlValue("fmb_shequ_createtopic_body"), body);
		be.leaveFrame();
		be.click(yamlconf.getYamlValue("fmb_shequ_createtopic_submit"));
	}
	public static void clickConfirmTcbox(BrowserEmulator be)
	{
		be.click(yamlconf.getYamlValue("fmb_shequ_createtopic_tcbox"));
	}
	/*
	 * 点击删除帖子
	 */
	public static void clickDeleTopic(BrowserEmulator be)
	{
		be.click(yamlconf.getYamlValue("fmb_shequ_deletopic"));
	}
	/*
	 * 确认删除帖子
	 */
	public static void clickDeleTopicconfirm(BrowserEmulator be)
	{
		be.click(yamlconf.getYamlValue("fmb_shequ_deletopic_confirm"));
	}
	/*
	 * 取消删除帖子
	 */
	public static void clickDeleTopicqx(BrowserEmulator be)
	{
		be.click(yamlconf.getYamlValue("fmb_shequ_deletopic_cancel"));
	}
	/*
	 * 回复帖子
	 */
	public static void replyTopic(BrowserEmulator be, String text) throws InterruptedException
	{
		be.click(yamlconf.getYamlValue("fmb_shequ_reply_topic_btn"));
		be.enterFrame(yamlconf.getYamlValue("fmb_shequ_reply_topic_frame"));
		be.type(yamlconf.getYamlValue("fmb_shequ_reply_topic_content"), text);
		be.leaveFrame();
		be.click(yamlconf.getYamlValue("fmb_shequ_reply_topic_confirm_btn"));
		Thread.sleep(3000);
		LogTools.screenShot(be);
	}
	/*
	 * 退出圈子
	 */
	public static void quitQuanZi(BrowserEmulator be)
	{
		be.click(yamlconf.getYamlValue("fmb_shequ_tcquanzi"));
	}
	

}
