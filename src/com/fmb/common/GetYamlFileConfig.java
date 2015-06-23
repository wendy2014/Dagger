package com.fmb.common;
/*
 * Author: Shuwen
 * Date:20140821
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;


import org.ho.yaml.Yaml;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * 配置Yaml文件读取
 */
public class GetYamlFileConfig 
{
	private String yamlFile;
	private HashMap<String, HashMap<String, String>> ml;
	public WebDriver dr;
	
	public GetYamlFileConfig()
	{
		yamlFile = "fmb";
		this.getYamlFile();
	}
	/*
	 * 解析yaml文件
	 */
	public void getYamlFile()
	{
		File f = new File("data/" + yamlFile + ".yaml");
		try 
		{
			ml = Yaml.loadType(new FileInputStream(f.getAbsolutePath()), HashMap.class);    //？？？？
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	/*
	 * 设置元素按照何种type来定位
	 */
	private By getBy(String type, String value)
	{
		By by = null;
		if(type.equals("id"))
		{
			by = By.id(value);
		}
		if(type.equals("name"))
		{
			by = By.name(value);
		}
		if(type.equals("xpath"))
		{
			by = By.xpath(value);
		}
		if(type.equals("classname"))
		{
			by = By.className(value);
		}
		if(type.equals("linktext"))
		{
			by = By.linkText(value);
		}
		return by;
	}
	/*
	 * 设置页面等待时间，WebDriverWait和ExpectedCondition都是源码包
	 */
	private WebElement waitForElement(final By by)
	{
		WebElement ele = null; 
		int waitTime = Integer.parseInt("5");              //5s超时
		try
		{
		ele = new WebDriverWait(dr, waitTime).until(new ExpectedCondition<WebElement>()
				{
			       public WebElement apply(WebDriver d)
			          {
				         return d.findElement(by);
			          }
				});
		}
		catch(Exception e)
		{
			System.out.println(by.toString() + " is not exist until " + waitTime);
		}
		return ele;
	}
	/*
	 * 只显示displayed的元素对象
	 */
	private boolean waitElementToBeDisplayed(final WebElement ele)
	{
		boolean wait = false;
		if(ele == null)
		{
			return wait;
		}
		try
		{
		wait = new WebDriverWait(dr,5).until(new ExpectedCondition<Boolean>()
				{
			        public Boolean apply(WebDriver d)
			        {
			        	return ele.isDisplayed();
			        }
				});
		}
		catch(Exception e)
		{
			System.out.println(ele.toString() + "is not displayed");
		}
		return wait;
	}
	/*
	 * 等待元素消失
	 */
	public boolean waitElementToBeNonDisplayed(final WebElement ele)
	{
		boolean wait = false;
		if(ele == null)
		{
			return wait;
		}
		try
		{
		wait = new WebDriverWait(dr, 5).until(new ExpectedCondition<Boolean>()
				{
			        public Boolean apply(WebDriver d)
			        {
			        	return !ele.isDisplayed();
			        }
				});
		}
		catch(Exception e)
		{
			System.out.println("Locator[" + ele.toString() + "] is also displayed ");
		}
		return wait;
	}
	/*
	 * 从yaml文件取key对应的type和value
	 */
	private WebElement getLocator(String key, boolean wait)
	{
		WebElement ele = null;
		if(ml.containsKey(key))
		{
			HashMap<String, String> m = ml.get(key);
			String type = m.get("type");
			String value = m.get("value");
			By by = this.getBy(type, value);
			if(wait)
			{
				ele = this.waitForElement(by);
				boolean flag = this.waitElementToBeDisplayed(ele);
				if(!flag)
				{
					ele = null;
				}
				else
				{
					try
					{
						ele = dr.findElement(by);
					}
					catch(Exception e)
					{
						ele = null;
					}
				}
			}
			else
			{
				System.out.println("Locator " + key + " is not exist in " + yamlFile + ".yaml");
			}
		}
		return ele;
	}
	/*
	 * 验证一个元素对象在页面上
	 */
	public WebElement getElement(String key)
	{
//		String type = ml.get(key).get("type");
//		String value = ml.get(key).get("value");
////		return dr.findElement(this.getBy(type, value));
////		return this.waitForElement(this.getBy(type, value));
//		WebElement ele = this.waitForElement(this.getBy(type, value));
//		if(!this.waitElementToBeDisplayed(ele))
//		{
//			ele = null;
//		}
//		return ele;
		
		return this.getLocator(key, true);
	}
	/*
	 * 验证一个元素对象不出现在页面上
	 */
	public WebElement getElementNoWait(String key)
	{
		return this.getLocator(key, false);
	}
	/*
	 * 获取到key对应的value值
	 */
	public String getYamlValue(String key)
	{
		String value = ml.get(key).get("value");
		return value;
	}

}
