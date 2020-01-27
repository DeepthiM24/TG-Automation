package com.tgq.TGQPageObj;



import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.tgr.accelerators.BrowserFactory;
import com.tgr.accelerators.PBase;

public class MyWebElement extends PBase {
	  BrowserFactory browserFactory = BrowserFactory.getInstance();
	private static final Logger log = LogManager.getLogger(MyWebElement.class.getName());
	public   boolean isElementExistwithid(String id) {

		List<WebElement> ele =  browserFactory.getDriver().findElements(By.id(id));
		if (ele.size() > 0) {
			return true;
		} else
			return false;
	}
	public   boolean isElementExist(String linkText) {

		List<WebElement> ele =  browserFactory.getDriver().findElements(By.linkText(linkText));
		if (ele.size() > 0) {
			return true;
		} else
			return false;
	}
	public  boolean isCloseButtonExist(String xpath) {
		List<WebElement> ele =  browserFactory.getDriver().findElements(By.xpath(xpath));
		if (ele.size() > 0) {
			return true;
		} else
			return false;
	}

	public  boolean isAlertPresent(String className) {
		List<WebElement> ele =  browserFactory.getDriver().findElements(By.className(className));
		if (ele.size() > 0) {
			return true;
		} else
			return false;
	}
	public  boolean isButtonExist(String xpath) {
		List<WebElement> ele =  browserFactory.getDriver().findElements(By.xpath(xpath));
		if (ele.size() > 0) {
			return true;
		} else
			return false;
	}
	public  boolean isDropdownExist(String name) {
		List<WebElement> ele =  browserFactory.getDriver().findElements(By.name(name));
		if (ele.size() > 0) {
			return true;
		} else
			return false;
	}



	

	


	

	






	
}