package com.fmb.page;

import com.fmb.common.BrowserEmulator;
import com.fmb.common.GetYamlFileConfig;
import com.fmb.datadriver.ExcelUtils;

public class Login 
{
	public static String url;
	static GetYamlFileConfig yamlconf;
	public static void openURL(BrowserEmulator be) 
	{
		yamlconf = new GetYamlFileConfig();
		url = yamlconf.getYamlValue("fmb_login_url");
		be.open(url);
		be.maxBrowser();
	}
	public static void clickLoginButton(BrowserEmulator be)
	{
		
		be.click(yamlconf.getYamlValue("fmb_login_btn"));
	}
	/*
	 * 在登录的iframe中输入文本
	 */
	public static void typeInIframe(BrowserEmulator be, String email, String password) 
	{		
		be.enterFrame(yamlconf.getYamlValue("fmb_login_iframe"));
		be.type(yamlconf.getYamlValue("fmb_login_email"), email);
		be.type(yamlconf.getYamlValue("fmb_login_password"), password);
		be.click(yamlconf.getYamlValue("fmb_login_submit"));
		be.expectElementExistOrNot(false, yamlconf.getYamlValue("fmb_login_iframe") + "login button" +"')]", 5000);
		
	}
	/*
	 * 判断是否登录成功
	 */
//	public static boolean verifyLogin(BrowserEmulator be, String expect) throws Exception
//	{
//		String actual;
//		actual = be.getText("//label[@style='']");
//		System.out.println(actual);
//		if(expect.equals(actual))
//		{
//			ExcelUtils.setCellData("Pass", 1, 4);
//			return true;
//		}
//		else 
//		{
//			ExcelUtils.setCellData("Failed" + "expect is: " + expect + ", actual is: " + actual, 1, 4);
//			return false;
//		}
//	}

}
