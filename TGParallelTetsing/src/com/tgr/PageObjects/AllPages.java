package com.tgr.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.tgr.accelerators.Base;

public class AllPages extends Base {

	static WebDriver ldriver;

	public AllPages(WebDriver driver) {
		ldriver = driver;
		PageFactory.initElements(driver, this);
	}


	// ========================= PAGE METHODS ============================

	public MainPage mainPage() {
		//System.out.println("MainPage");
		return new MainPage(ldriver);
	}
	public GeneralInfoPage generalInfoPage() {
		//System.out.println("MainPage");
		return new GeneralInfoPage(ldriver);
	}
	public DriversPage driversPage() {
		//System.out.println("MainPage");
		return new DriversPage(ldriver);
	}
	public VehiclesPage vehiclesPage() {
		//System.out.println("MainPage");
		return new VehiclesPage(ldriver);
	}
	public Quotepage quotePage() {
		//System.out.println("MainPage");
		return new Quotepage(ldriver);
	}
	public Policydetail policydetailPage() {
		//System.out.println("MainPage");
		return new Policydetail(ldriver);
	}
	public AddressPage addressPagePage() {
		//System.out.println("MainPage");
		return new AddressPage(ldriver);
	}
	
	public ApplicationQuestionsPage applicationQuestionsPage() {
		//System.out.println("MainPage");
		return new ApplicationQuestionsPage(ldriver);
	}
	public DriverdetailsPage driverdetailsPage() {
		//System.out.println("MainPage");
		return new DriverdetailsPage(ldriver);
	}
	public VehicleDetailsPage vehicleDetailsPage() {
		//System.out.println("MainPage");
		return new VehicleDetailsPage(ldriver);
	}
	public FinalReviewPage finalReviewPage() {
		//System.out.println("MainPage");
		return new FinalReviewPage(ldriver);
	}
	public OrderReportCreditsPage orderReportCreditsPage() {
		//System.out.println("MainPage");
		return new OrderReportCreditsPage(ldriver);
	}
	public OrderReportsMVRPage orderReportsMVRPage() {
		//System.out.println("MainPage");
		return new OrderReportsMVRPage(ldriver);
	}
	public OrderReportsMVRProcessPage orderReportsMVRProcessPage() {
		//System.out.println("MainPage");
		return new OrderReportsMVRProcessPage(ldriver);
	}
	public ContactinformarionPage contactInformarionPage() {
		//System.out.println("MainPage");
		return new ContactinformarionPage(ldriver);
	}
	public PaymentSelectionpage paymentSelectionpage() {
		//System.out.println("MainPage");
		return new PaymentSelectionpage(ldriver);
	}
	public CreditDebitCardPaymentPage creditDebitCardPaymentPage() {
		//System.out.println("MainPage");
		return new CreditDebitCardPaymentPage(ldriver);
	}
	public GrabPolicyNum grabpolicyNum() {
		//System.out.println("MainPage");
		return new GrabPolicyNum(ldriver);
	}
	public ESign eSign() {
		//System.out.println("MainPage");
		return new ESign(ldriver);
	}
	
}
