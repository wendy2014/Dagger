package com.fmb.common;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class Common 
{
	private WebDriver dr = null;
	public Common(WebDriver dr)
	{
		this.dr = dr;
	}
	public boolean isElementExist(By by)
	{
		try 
		{
			dr.findElement(by);
			return true;
		}
		catch (NoSuchElementException e)
		{
//			System.out.println("元素不存在：【" + by.toString()+ "]");
			return false;
		}
	}
	public void switchToWindow(String windowTitle)
	{
		Set<String> windowHandles = dr.getWindowHandles();
		for(String handler : windowHandles)
		{
			dr.switchTo().window(handler);
			String title = dr.getTitle();
			if(windowTitle.equals(title))
			{
				break;
			}
		}
	}

}
