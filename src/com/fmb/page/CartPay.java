package com.fmb.page;

import com.fmb.common.BrowserEmulator;
import com.fmb.common.GetYamlFileConfig;

public class CartPay 
{
	static GetYamlFileConfig yamlconf;
	public static void clickCartBtn(BrowserEmulator be)
	{
		yamlconf = new GetYamlFileConfig();
		be.click(yamlconf.getYamlValue("fmb_cart"));
	}

}
