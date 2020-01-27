package com.tgq.TestScripts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.tgq.TGQPageObj.PRegressionWorkFlow;

public class PTGQTestCase extends PRegressionWorkFlow {

	

	private static final Logger log = LogManager.getLogger(PTGQTestCase.class.getName());

	@Test 
	public void TC1() throws InterruptedException {

		log.info("METHOD(TC1) EXECUTION STARTED SUCCESSFULLY");
		try {

			openTGQ();

		} catch (Exception exp) {
			log.error(exp.getMessage());
		}
		log.info("METHOD(IU_TC1) EXECUTED SUCCESSFULLY");
	}
	
	
}
