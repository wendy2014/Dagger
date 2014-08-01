package com.fmb.pagetest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fmb.common.BrowserEmulator;
import com.fmb.datadriver.Constant;
import com.fmb.datadriver.ExcelUtils;
import com.fmb.page.CommonFun;
import com.fmb.page.Login;

public class TopicTest 
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
	public void createTopic() throws Exception
	{
//		System.out.println(be.getCurrentTitle());
		CommonFun.openFmbSchool(be);
//		System.out.println(be.getCurrentTitle());
		CommonFun.clickCreateTopicBtn(be);
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "createTopic");
		CommonFun.typeCreateTopic(be, ExcelUtils.getCellData(1, 1), ExcelUtils.getCellData(1, 2));
		CommonFun.clickConfirmTcbox(be);
	}
	@Test(dependsOnMethods="createTopic")
	public void replyTopic() throws Exception
	{
		CommonFun.replyTopic(be, ExcelUtils.getCellData(2, 1));
	}
	@Test(dependsOnMethods="replyTopic")
	public void deleTopic()
	{
		CommonFun.clickDeleTopic(be);
		CommonFun.clickDeleTopicconfirm(be);
		CommonFun.quitQuanZi(be);
	}
	@AfterClass
	public void quit()
	{
		be.quit();
	}

}
