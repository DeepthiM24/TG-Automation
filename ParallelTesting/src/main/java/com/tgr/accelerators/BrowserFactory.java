package com.tgr.accelerators;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserFactory {
	private BrowserFactory()
	   {
	      //Do-nothing..Do not allow to initialize this class from outside
	   }
	   private static BrowserFactory instance = new BrowserFactory();

	   public static BrowserFactory getInstance()
	   {
	      return instance;
	   }

	   ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() // thread local driver object for webdriver
	   {
	      @Override
	      protected WebDriver initialValue()
	      {
	    	  DesiredCapabilities caps = null;
	    	  caps = DesiredCapabilities.chrome();
				ChromeOptions chOptions = new ChromeOptions();
				Map<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("credentials_enable_service", false);
				chOptions.setExperimentalOption("prefs", chromePrefs);
				chOptions.addArguments("--disable-plugins", "--disable-extensions",
						"--disable-popup-blocking");
				caps.setCapability(ChromeOptions.CAPABILITY, chOptions);
				caps.setCapability("applicationCacheEnabled", false);
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
	         return new ChromeDriver(); // can be replaced with other browser drivers
	      }
	   };

	   public WebDriver getDriver() // call this method to get the driver object and launch the browser
	   {
	      return driver.get();
	   }

	   public void removeDriver() // Quits the driver and closes the browser
	   {
	      driver.get().quit();
	      driver.remove();
	   }
}
