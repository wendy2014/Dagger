package com.fmb.pagetest;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fmb.common.BrowserEmulator;
import com.fmb.datadriver.Constant;
import com.fmb.datadriver.ExcelUtils;
import com.fmb.page.CommonFun;
import com.fmb.page.Login;

public class DemoTest1 
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
	  public void opentaohuoxiuchang()
	 {
		 CommonFun.clickSheQu(be);
		 CommonFun.clickXiuChang(be);
	 }
	 @Test(dependsOnMethods="opentaohuoxiuchang")
	 public void createTopic() throws Exception
	 {
		 be.selectWindow("淘货秀场-父母邦亲子社区");
		 CommonFun.clickCreateTopicBtn(be);
		 ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "creatTopic");
		 CommonFun.typeCreateTopic(be, ExcelUtils.getCellData(1, 1), ExcelUtils.getCellData(1, 2));
		 CommonFun.clickConfirmTcbox(be);
		 CommonFun.clickDeleTopic(be);
		 CommonFun.clickDeleTopicconfirm(be);
		 
	 }

}
