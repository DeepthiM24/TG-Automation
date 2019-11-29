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
import com.tgr.Utilities.Excel;
import com.tgr.Utilities.MyOwnException;
import com.tgr.accelerators.Base;

import wrapper.classes.methods.MyWait;
import wrapper.classes.methods.MyWebElement;

public class Quotepage extends CommonPage {

	private static final Logger log = LogManager.getLogger(Quotepage.class.getName());

	WebDriver ldriver;
	ExtentTest testCase;

	public Quotepage(WebDriver driver) {
		super(driver);

		this.ldriver = driver;
		PageFactory.initElements(driver, this);
	}

	// ===================== PAGE OBJECTS ======================

	@FindBy(how = How.CLASS_NAME, using = "jumpout")
	public WebElement quoteNumber;

	@FindBy(how = How.LINK_TEXT, using = "Continue")
	public WebElement continueLink;
	// ===================== PAGE METHODS ======================

	public void quoteInfo() throws InterruptedException, MyOwnException {

		log.info("METHOD(login) EXECUTION STARTED SUCCESSFULLY");
		try {
			MyWait.until(ldriver, "ELEMENT_VISIBLE", 90, continueLink);
			// System.out.println("quoteInfo");
			System.out.println(quoteNumber.getAttribute("innerHTML"));
			Base.quoteNumber = quoteNumber.getAttribute("innerHTML");
			// Excel.writeToExcelSheet(policyNumber, quoteNUmber)writeToExcelSheet
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
