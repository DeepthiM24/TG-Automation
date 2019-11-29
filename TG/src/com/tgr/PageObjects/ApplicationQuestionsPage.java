package com.tgr.PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class ApplicationQuestionsPage extends CommonPage {

	private static final Logger log = LogManager.getLogger(ApplicationQuestionsPage.class.getName());

	WebDriver ldriver;
	ExtentTest testCase;

	public ApplicationQuestionsPage(WebDriver driver) {
		super(driver);

		this.ldriver = driver;
		PageFactory.initElements(driver, this);
	}

	// ===================== PAGE OBJECTS ======================

	@FindBy(how = How.XPATH, using = "//*[@id=\"content_table\"]/div[3]/div/div[1]/div[2]/div[1]/div/label[2]")
	public WebElement appQue1;
	@FindBy(how = How.XPATH, using = "//*[@id=\"content_table\"]/div[3]/div/div[1]/div[3]/div[1]/div/label[2]")
	public WebElement appQue2;
	@FindBy(how = How.XPATH, using = "//*[@id=\"content_table\"]/div[3]/div/div[1]/div[4]/div[1]/div/label[2]")
	public WebElement appQue3;
	@FindBy(how = How.XPATH, using = "//*[@id=\"content_table\"]/div[3]/div/div[1]/div[5]/div[1]/div/label[2]")
	public WebElement appQue4;
	@FindBy(how = How.XPATH, using = "//*[@id=\"content_table\"]/div[3]/div/div[1]/div[6]/div[1]/div/label[1]")
	public WebElement appQue5;
	@FindBy(how = How.XPATH, using = "//*[@id=\"content_table\"]/div[3]/div/div[1]/div[7]/div[1]/div/label[1]")
	public WebElement appQue6;
	@FindBy(how = How.XPATH, using = "//*[@id=\"content_table\"]/div[3]/div/div[1]/div[4]/div[1]/div/label[1]")
	public WebElement appQueFor3;
	@FindBy(how = How.XPATH, using = "//*[@id=\"content_table\"]/div[3]/div/div[1]/div[5]/div[1]/div/label[1]")
	public WebElement appQueFor4;

	@FindBy(how = How.LINK_TEXT, using = "Continue")
	public WebElement continueLink;

	// ===================== PAGE METHODS ======================

	public void applicationQuestions() throws InterruptedException, MyOwnException {

		log.info("METHOD(login) EXECUTION STARTED SUCCESSFULLY");
		try {
			MyWait.until(ldriver, "ELEMENT_VISIBLE", 90, continueLink);
			if (currentHash.get("state").equals("NC")) {
				appQue1.click();
				appQue2.click();
				appQueFor3.click();
				appQueFor4.click();

			} else {
				appQue1.click();
				appQue2.click();
				appQue3.click();
				appQue4.click();
				appQue5.click();
				appQue6.click();
			}
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
