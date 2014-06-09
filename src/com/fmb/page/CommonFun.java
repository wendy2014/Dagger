package com.fmb.page;

import com.fmb.common.BrowserEmulator;
import com.fmb.common.GetYamlFileConfig;
import com.fmb.page.Login;

public class CommonFun
{
	static GetYamlFileConfig yamlconf;
	public static void clickSheQu(BrowserEmulator be)
	{
		yamlconf = new GetYamlFileConfig();
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
	

}
