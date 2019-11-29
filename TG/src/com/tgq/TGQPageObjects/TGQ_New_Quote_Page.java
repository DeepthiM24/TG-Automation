package com.tgq.TGQPageObjects;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.tgr.accelerators.Base;
import com.tgr.Utilities.MyOwnException;
import wrapper.classes.methods.MyWait;
import wrapper.classes.methods.MyWebElement;

public class TGQ_New_Quote_Page extends TGQAllPages {

	private static final Logger log = LogManager.getLogger(TGQ_New_Quote_Page.class.getName());

	// Page Factory
	@FindBy(xpath = "//*[@name='newQuoteBean.allowableStates.realValue']")
	public WebElement tgq_state;
	@FindBy(how = How.ID, using = "newQuoteBean.policyTypeHelper.value")
	public WebElement tgq_policy_type;
	@FindBy(how = How.ID, using = "referralLocationCodeInputField")
	public WebElement tgq_zip_code;
	@FindBy(how = How.LINK_TEXT, using = "Next")
	public WebElement tgq_next;
	@FindBy(how = How.XPATH, using = "//*[@id=\"zipInGarage\"]")
	public WebElement zipInGarage;
	@FindBy(how = How.ID, using = "enhancedContinueInsured")
	public WebElement enhancedContinue;
	@FindBy(how = How.ID, using = "homeownerDiscount")
	public WebElement homeownerDisc;
	@FindBy(how = How.ID, using = "roadside")
	public WebElement roadsideAsst;

	WebDriver ldriver;

	public TGQ_New_Quote_Page(WebDriver dr) {
		super(dr);
		this.ldriver = dr;
		PageFactory.initElements(dr, this);
	}

	public void newQuote() throws MyOwnException, InterruptedException {
		log.info("METHOD(login) STARTED SUCCESSFULLY");
		try {
			for (String handle1 : ldriver.getWindowHandles()) {
				ldriver.switchTo().window(handle1);
				ldriver.getTitle();
				ldriver.manage().window().maximize();
			}
			Thread.sleep(3000);
			Select state = new Select(tgq_state);
			state.selectByVisibleText(currentHash.get("QuoteState"));
			Select policy_type = new Select(tgq_policy_type);
			policy_type.selectByVisibleText(currentHash.get("PolicyType"));
			if (MyWebElement.isElementExistwithid("referralLocationCodeInputField"))
				MyWebElement.enterText(tgq_zip_code, currentHash.get("ZipCode"));
			else {
				MyWebElement.enterText(zipInGarage, currentHash.get("ZipCode"));
				Select enhance = new Select(enhancedContinue);
				enhance.selectByVisibleText("No: Other");
				Select homeownerDiscount = new Select(homeownerDisc);
				homeownerDiscount.selectByVisibleText(currentHash.get("HomeOwnerDisc"));
				Select roadsideAst = new Select(roadsideAsst);
				roadsideAst.selectByVisibleText("No");
			}

			MyWait.until(driver, "ELEMENT_CLICKABLE", 30, tgq_next);
			MyWebElement.clickOn(tgq_next);

		} catch (Exception exp) {
			log.error(exp.getMessage());

			throwException("Unable To Create New Quote application \n" + exp.getMessage() + "\n");
		}
		log.info("METHOD(login) EXECUTED SUCCESSFULLY");

	}

}
