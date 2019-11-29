package com.tgr.PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tgq.TGQPageObjects.MarsApplicationLoginPage;
import com.tgq.TGQPageObjects.TGQAllPages;
import com.tgr.Utilities.MyOwnException;
import com.tgr.accelerators.Base;

public class WorkFlow extends Base {

	private static final Logger log = LogManager.getLogger(WorkFlow.class.getName());

	public void openTGR() throws InterruptedException, MyOwnException {

		log.info("WORKFLOW(newTrackerListingProcess) EXECUTION STARTED SUCCESSFULLY");
		try {
			trackerspages.mainPage().initiateBrowser();
			driver.navigate().to(configProps.getProperty("TGRURL"));
			trackerspages.mainPage().ClickOnstartQuote();
			trackerspages.generalInfoPage().generalInfo();
			trackerspages.driversPage().driversInfo();
			trackerspages.vehiclesPage().vehiclesInfo();
			trackerspages.quotePage().quoteInfo();
			trackerspages.policydetailPage().policydetail();
			trackerspages.addressPagePage().addressInfo();
			trackerspages.applicationQuestionsPage().applicationQuestions();
			trackerspages.driverdetailsPage().driverDetailsPage();
			trackerspages.vehicleDetailsPage().vehicledetailsPage();
			trackerspages.finalReviewPage().finalreviewPage();
			trackerspages.orderReportCreditsPage().orderreportCreditsPage();
			trackerspages.orderReportsMVRPage().orderReportsMVR();
			trackerspages.orderReportsMVRProcessPage().orderReportsMVRProcess();
			trackerspages.contactInformarionPage().contactinformarionPage();
			trackerspages.paymentSelectionpage().paymentselectionpage();
			trackerspages.creditDebitCardPaymentPage().creditdebitCardPaymentPage();
			trackerspages.grabpolicyNum().grabPolicyNum();

		} catch (Exception exp) {
			log.error(exp.getMessage());

		}
		log.info("WORKFLOW(newTrackerListingProcess) EXECUTED SUCCESSFULLY");
	}
	public void openTGQ() throws InterruptedException, MyOwnException {

		log.info("WORKFLOW(newTrackerListingProcess) EXECUTION STARTED SUCCESSFULLY");
		try {
			tgQTrackerspages.loginPage().initiateTGQBrowser();
			driver.navigate().to(configProps.getProperty("TGQURL"));
			MarsApplicationLoginPage  m=new MarsApplicationLoginPage(driver);
			TGQAllPages allPages=new TGQAllPages(driver);
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
