package com.tgq.TGQPageObj;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.tgr.Utilities.MyOwnException;

public class PMarsApplicationLoginPage extends PTGQAllPages {

	private static final Logger log = LogManager.getLogger(PMarsApplicationLoginPage.class.getName());

	// Page Factory

	@FindBy(how = How.CLASS_NAME, using = "OrangeGradientButton")
	public WebElement signin_button;

	@FindBy(how = How.NAME, using = "agencyid")
	public WebElement agencyId;

	@FindBy(how = How.NAME, using = "pwd")
	public WebElement mars_password;

	WebDriver ldriver;

	public PMarsApplicationLoginPage(WebDriver dr) {
		super(dr);
		this.ldriver = dr;
		PageFactory.initElements(dr, this);
	}

	public void login() throws InterruptedException, MyOwnException {
		log.info("METHOD(login) STARTED SUCCESSFULLY");

		try {
			
			ldriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			System.out.println(ldriver.getTitle());
			myWait.until(ldriver, "ELEMENT_VISIBLE", 90, signin_button);
			mywebEle.enterText(agencyId, currentHash.get("Username"));
			mywebEle.enterText(mars_password, currentHash.get("Password"));
			
			/*
			 * base.screenShot("./Results/Screenshots_" +testCaseNumber+ testRunTimeStamp+
			 * "/" + "Opened MARS.png");
			 * reportVar.logTestCaseStatusWithSnapShot(parentTestCase,
			 * "PASS","Opened MARS Application","./Results/Screenshots_" +testCaseNumber+
			 * testRunTimeStamp + "/" + "Opened MARS.png");
			 */
			 
			signin_button.click();
			System.out.println("Sucessfully logged in to MARS Application");
			log.info("METHOD(login) EXECUTED SUCCESSFULLY");
		} catch (Exception e) {

			/*
			 * base.screenShot("./Results/Screenshots_" +testCaseNumber+ testRunTimeStamp +
			 * "/" + "Error in Opening MARS Application.png");
			 * reportVar.logTestCaseStatusWithSnapShot(parentTestCase, "FAIL",
			 * "<font color=red><b>Error while Opening MARS application: </b></font><br />"
			 * + e.getMessage() + "<br />", "./Results/Screenshots_"+ testCaseNumber+
			 * testRunTimeStamp + "/" + "Error in Opening MARS Application.png");
			 */
			throwException("Unable To open the  MARS application \n" + e.getMessage() + "\n");
			e.printStackTrace();
		}

	}

}
