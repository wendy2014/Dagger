package com.fmb.pagetest;
/*
 * Author: Shuwen
 * Date:20140821
 */
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import junit.framework.Assert;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.AfterClass;

import com.fmb.common.BrowserEmulator;
//import com.fmb.datadriver.Constant;
//import com.fmb.datadriver.ExcelUtils;
//import com.fmb.page.CommonFun;
import com.fmb.page.Login;
import com.netease.datadriver.ExcelDataProvider;

public class LoginTest 
{
	BrowserEmulator be = null;
	
	@BeforeClass
	  public void beforeClass() 
	  {
		  be = new BrowserEmulator();
	  }
	  
	@Test(dataProvider = "dp")
	public void Login(Map<String,String> data) throws Exception
	{
		Login.openURL(be);
		Login.clickLoginButton(be);
		Login.typeInIframe(be, data.get("email").trim(), data.get("password").trim());
//		Assert.assertTrue(Login.verifyLogin(be, data.get("expect").trim()));
	}
	 
	@DataProvider(name = "dp")
    public Iterator<Object[]> dataFortestMethod(Method method) throws IOException {
        return new ExcelDataProvider(this.getClass().getName(),method.getName());
    }
	 
}
