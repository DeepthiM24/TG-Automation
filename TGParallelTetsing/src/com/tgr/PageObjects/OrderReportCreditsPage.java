package com.tgr.PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.tgr.Utilities.MyOwnException;

import wrapper.classes.methods.MyWait;
import wrapper.classes.methods.MyWebElement;

public class OrderReportCreditsPage extends CommonPage {

	private static final Logger log = LogManager.getLogger(OrderReportCreditsPage.class.getName());

	WebDriver ldriver;
	ExtentTest testCase;

	public OrderReportCreditsPage(WebDriver driver) {
		super(driver);

		this.ldriver = driver;
		PageFactory.initElements(driver, this);
	}

	// ===================== PAGE OBJECTS ======================

	@FindBy(how = How.ID, using = "emailAddress")
	public WebElement emailAddress;
	@FindBy(how = How.ID, using = "emailAddress2")
	public WebElement confirmEmailAddress;
	@FindBy(how = How.ID, using = "password1")
	public WebElement password;
	@FindBy(how = How.LINK_TEXT, using = "Continue")
	public WebElement continueLink;

	// ===================== PAGE METHODS ======================

	public void orderreportCreditsPage() throws InterruptedException, MyOwnException {

		log.info("METHOD(login) EXECUTION STARTED SUCCESSFULLY");
		try {
			MyWait.until(ldriver, "ELEMENT_VISIBLE", 90, continueLink);

			continueLink.click();

		} catch (AssertionError exp) {
			log.error(exp.getMessage());

			throwException("UNABLE TO open INTO THE TGR APPLICATION FROM THE METHOD login\n" + exp.getMessage() + "\n");
		} catch (Exception exp) {
			log.error(exp.getMessage());

			throwException("UNABLE TO open INTO THE TGR APPLICATION FROM THE METHOD login\n" + exp.getMessage() + "\n");
		}
		log.info("METHOD(login) EXECUTED SUCCESSFULLY");
	}

}
