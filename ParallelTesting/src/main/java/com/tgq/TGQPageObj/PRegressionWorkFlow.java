package com.tgq.TGQPageObj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.tgq.TGQPageObj.PTGQAllPages;
import com.tgr.accelerators.PBase;

public class PRegressionWorkFlow extends PBase {

	private static final Logger log = LogManager.getLogger(PRegressionWorkFlow.class.getName());

	public void openTGQ() throws InterruptedException {

		log.info("WORKFLOW(newTrackerListingProcess) EXECUTION STARTED SUCCESSFULLY");
		try {
			ptgQAllpages.loginPage();
			driver.navigate().to(properties.getProperty("TGQURL"));
			PMarsApplicationLoginPage m = new PMarsApplicationLoginPage(driver);
			PTGQAllPages allPages = new PTGQAllPages(driver);
			m.login();
			allPages.homePage().homepage();
			allPages.newquote().newQuote();
			allPages.ratinginfo().ratingInfo();
			allPages.quotedit().quotEdit();
			allPages.driversedit().driversEdit();
			allPages.vehiclesedit().vehiclesEdit();
			allPages.consumerquestions().consumerQuestions();
			allPages.applicationQuestions().applicationquestions();
			allPages.validatequote().validateQuote();
			allPages.paymentTab().paymenttab();
			allPages.policy_page().policyPage();

		} catch (Exception exp) {
			log.error(exp.getMessage());

		}
		log.info("WORKFLOW(newTrackerListingProcess) EXECUTED SUCCESSFULLY");
	}

}
