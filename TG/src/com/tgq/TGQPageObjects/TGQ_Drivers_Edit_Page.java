package com.tgq.TGQPageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.tgr.accelerators.Base;
import com.tgr.Utilities.MyOwnException;
import wrapper.classes.methods.MyWait;
import wrapper.classes.methods.MyWebElement;

public class TGQ_Drivers_Edit_Page extends TGQAllPages {

	private static final Logger log = LogManager.getLogger(TGQ_Drivers_Edit_Page.class.getName());

	// Page Factory

	@FindBy(how = How.ID, using = "drivers[0].stateLicensed.writableValue")
	public WebElement state_lic;
	@FindBy(how = How.ID, using = "drivers[0].socialSecurityNumber.value")
	public WebElement ssn;
	@FindBy(how = How.ID, using = "drivers[0].driverLicenseNumber.value")
	public WebElement dl;
	@FindBy(how = How.ID, using = "drivers[0].occupation.writableValue")
	public WebElement occupation;

	@FindBy(how = How.LINK_TEXT, using = "Recalculate")
	public WebElement recalculate_button;
	@FindBy(how = How.LINK_TEXT, using = "Next")
	public WebElement next_button;

	WebDriver ldriver;

	public TGQ_Drivers_Edit_Page(WebDriver dr) {
		super(dr);
		this.ldriver = dr;
		PageFactory.initElements(dr, this);
	}

	public void driversEdit() throws MyOwnException, InterruptedException {
		log.info("METHOD(login) STARTED SUCCESSFULLY");
		try {

			Select state_lic_dr = new Select(state_lic);
			state_lic_dr.selectByVisibleText(currentHash.get("QuoteState"));
			MyWebElement.enterText(ssn, currentHash.get("SSN"));
			MyWebElement.enterText(dl, currentHash.get("DL"));
		//	System.out.println(occupation.getAttribute("style")+"fdhfdhfdhfdhfdhfdh");
			if (MyWebElement.isElementExistwithid("drivers[0].occupation.writableValue")&& !(occupation.getAttribute("style").equals("display: none;"))) {
				Select Occupation = new Select(occupation);
				Occupation.selectByVisibleText("Accountant/Auditor");
				
			}

			recalculate_button.click();

			next_button.click();
			Thread.sleep(4000);
		} catch (Exception exp) {
			log.error(exp.getMessage());

			throwException("Unable To Edit Driver/s application \n" + exp.getMessage() + "\n");
		}
		log.info("METHOD(login) EXECUTED SUCCESSFULLY");

	}

}
