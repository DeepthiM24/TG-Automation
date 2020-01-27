package com.tgr.accelerators;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.tgq.TGQPageObj.PTGQAllPages;
import com.tgr.Utilities.ConfigProperties;
import com.tgr.Utilities.Excel;
import com.tgr.Utilities.MyOwnException;
import com.tgr.Utilities.Report;

import wrapper.classes.methods.MyWait;
import wrapper.classes.methods.MyWebElement;

public class PBase {
	private static final Logger log = LogManager.getLogger(PBase.class.getName());
	public static String testRunTimeStamp;
	public static PBase base;
	public static MyWait myWait;
	public ConfigProperties configPropers;
	public static MyWebElement mywebEle ;
	public static Excel excel;
	public static Report reportVar;
	public String extentReportFileName = "";
	public String screenShotFolderPath;
	public  EventFiringWebDriver driver;
	public String sBrowserType = "";
	private static final String Name_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String Addres_Number_STRING = "1234567890";
	public String sBrowser;
	
	public static String quoteNumber;
	public String testDataFilename;
	public String relatedToATrustIssuerGlobal;
	public String withOrWithoutCrossGlobal;
	public String datepattern = "dd/MM/yyyy";
	public String dateInString = new SimpleDateFormat(datepattern).format(new Date());
	public static String testCaseNumber ;
	public PTGQAllPages ptgQAllpages;
	public String revisedfilename = "";
	public boolean result;
	public Actions action;
	public String fileFormat = "";
	public File file;
	public FileInputStream fs;
	public FileOutputStream os;
	public Properties properties;
	public Properties configProps;
	public static String documentPath;
	public static ExtentReports report;
	public static ExtentTest parentTestCase;
	private static Workbook wb;
	private static Sheet sh;
	public static HashMap<String, String> currentHash = new HashMap<String, String>();
	public static List<HashMap<String, String>> mydata = new ArrayList<HashMap<String, String>>();
	public static Map<String, String> IULoginMap = null;

	public PBase() {
		File file = new File("./config.properties");

		try {
			FileInputStream fis = new FileInputStream(file);
			properties = new Properties();
			properties.load(fis);

		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	@BeforeClass
	@Parameters({ "testCaseId", "testDataFileName" })
	public void setup(String testCaseId, String testDataFileName) throws Exception {
		base =new PBase();
		configPropers=new ConfigProperties();
		configProps = configPropers.loadFrom(System.getProperty("user.dir") + "\\", "config.properties");
		mywebEle=new MyWebElement();
		 myWait=new MyWait();
		 excel=new Excel();
		 reportVar=new Report();
		 WebDriver driver1 = BrowserFactory.getInstance().getDriver();
		 driver1.manage().window().maximize();
		driver = new EventFiringWebDriver(driver1);
		driver.manage().deleteAllCookies();
		ptgQAllpages = new PTGQAllPages(driver1);
		java.util.Date today = new java.util.Date();
		testRunTimeStamp = new java.sql.Timestamp(today.getTime()).toString();
		testRunTimeStamp = testRunTimeStamp.replace(" ", "_").replace(":", "_").replace(".", "_");
		System.out.println(testCaseId);
		testCaseNumber=testCaseId;
		testDataFilename = testDataFileName;
		readSpecificTestData(testCaseId, testDataFileName);
		initiateReport(testCaseId,testRunTimeStamp);
	}
	public  String randomAddressNumberString(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * Addres_Number_STRING.length());
			builder.append(Addres_Number_STRING.charAt(character));
		}
		return builder.toString();
	}

	public  boolean fileFormatIs(String expectedExtension) {

		log.info("INTENDED TO CHECK IF THE FILE BELONGS TO THE GIVEN FORMAT");
		if (fileFormat.equalsIgnoreCase(expectedExtension)) {
			log.info("EXPECTED AND ACTUAL FILE FORMATS ARE THE SAME");
			result = true;
		} else {
			log.error("EXPECTED AND ACTUAL FILE FORMATS ARE DIFFERENT");
			result = false;
		}
		log.info("SUCCESSFULLY CHECKED IF THE FILE BELONGS TO THE GIVEN FORMAT");
		return result;
	}

	public  boolean notEmpty(String string) {

		log.info("INTENDED TO CHECK IF THE STRING IS EMPTY");
		if (string != "") {
			log.info("STRING IS NOT EMPTY");
			result = true;
		} else {
			log.error("STRING IS EMPTY");
			result = false;
		}
		log.info("SUCCESSFULLY CHECKED IF THE STRING IS EMPTY");
		return result;
	}

	public  boolean formatGiven(String fileName) {

		log.info("INTENDED TO CHECK IF THE FILE NAME HAS THE FILE EXTENSION");
		int position = fileName.indexOf(".");
		if (position >= 0) {
			log.info("FILE FORMAT IS PROVIDED WITH THE FILE NAME(" + fileName + ")");
			result = true;
		} else {
			log.error("FILE FORMAT IS NOT PROVIDED WITH THE FILE NAME(" + fileName + ")");
			result = false;
		}
		log.info("SUCCESSFULLY CHECKED IF THE FILE NAME HAS THE FILE EXTENSION");
		return result;
	}

	public  String getFileFormat(String fileName) throws MyOwnException {

		log.info("INTENDED TO RETRIEVE THE FORMAT OF THE FILE");
		try {
			fileFormat = fileName.substring(fileName.indexOf("."));
			log.info(
					"SUCCESSFULLY GOT THE FILE FORMAT(" + fileFormat + ") FROM THE FILE NAME(" + fileName + ")" + "\n");
		} catch (Exception e) {
			log.error("UNABLE TO GET THE FILE FORMAT(" + fileFormat + ") FROM THE FILE NAME(" + fileName + ")");
			throwException(
					"UNABLE TO GET THE FILE FORMAT(" + fileFormat + ") FROM THE FILE NAME(" + fileName + ")" + "\n");
		}
		log.info("SUCCESSFULLY RETRIEVED THE FORMAT OF THE FILE");
		return fileFormat;
	}

	public  FileInputStream readFile(File file) throws MyOwnException {

		log.info("INTENDED TO READ THE GIVEN FILE");
		try {
			fs = new FileInputStream(file);
			log.info("SUCCESSFULLY READ THE FILE(" + file + ") SPECIFIED");
		} catch (FileNotFoundException e) {
			log.error("UNABLE TO READ THE FILE(" + file + ") SPECIFIED, PLEASE CHECK NAME OR LOCATION OR EXTENSION");
			throwException("UNABLE TO READ THE FILE(" + file + ") SPECIFIED, PLEASE CHECK NAME OR LOCATION OR EXTENSION"
					+ "\n");
		}
		log.info("READING THE FILE IS SUCCESSFUL");
		return fs;
	}

	public  File createFileInstance(String name) throws MyOwnException {

		log.info("INTENDED TO CREATE THE FILE INSTANCE");
		try {
			file = new File(name);
			log.info("FILE INSTANCE CREATED");
		} catch (Exception e) {
			log.error("UNABLE TO CREATE AN INSTANCE OF THE FILE(" + name + ")");
			throwException("UNABLE TO CREATE AN INSTANCE OF THE FILE(" + name + ")" + "\n");
		}
		log.info("SUCCESSFULLY CREATED THE FILE INSTANCE");
		return file;
	}

	public  List<HashMap<String, String>> readSpecificTestData(String testCaseId, String testDataFileName)
			throws Exception {
		String fileName = System.getProperty("user.dir") + "\\TestData\\" + testDataFileName + ".xlsx";
		String sheetName = configProps.getProperty("InitAppSheetName");
		InputStream file = new FileInputStream(fileName);
		String value = null;
		wb = WorkbookFactory.create(file);

		sh = wb.getSheet(sheetName);

		Row HeaderRow = sh.getRow(0);

		for (int i = 1; i < sh.getPhysicalNumberOfRows(); i++) {
			Row currentRow = sh.getRow(i);

			if (testCaseId.equalsIgnoreCase(currentRow.getCell(0).getStringCellValue())) {

				for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) {
					Cell currentCell = currentRow.getCell(j);
					switch (currentCell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (String.valueOf(currentCell.getNumericCellValue()).contains("."))
							value = splitData(String.valueOf(currentCell.getNumericCellValue()));
						currentHash.put(HeaderRow.getCell(j).getStringCellValue(), value);
						break;
					}

				}
				mydata.add(currentHash);
				System.out.println(mydata);
			}
		}
		return mydata;
	}

//converts the excel sheet numeric data into text
	public  String splitData(String data) {
		String value = null;
		Pattern p = Pattern.compile("[.]");
		String[] s = p.split(data);
		String st = s[0] + s[1];
		if (s[1].contains("E8")) {
			p = Pattern.compile("E8");
			s = p.split(st);
			value = s[0];
		} else
			value = s[0];

		return value;

	}

	// provides commas based on numeric values
	public  String numValue(String data) {
		int len = data.length();
		if (len == 5) {
			data = new StringBuffer(data).insert(2, ",").toString();
		} else if (len == 4) {
			data = new StringBuffer(data).insert(1, ",").toString();
		} else if (len == 3) {
			return data;
		}
		return data;

	}

	public  String randomNameString(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * Name_STRING.length());
			builder.append(Name_STRING.charAt(character));
		}
		return builder.toString();
	}

	public  void throwException(String message) throws MyOwnException {
		throw new MyOwnException(message);
	}

	public  void screenShot(String fileName) throws MyOwnException, InterruptedException {
		Thread.sleep(1000);
		BrowserFactory browserFactory = BrowserFactory.getInstance();
			File scrFile = ((TakesScreenshot)  browserFactory.getDriver()).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(fileName));
			} catch (IOException exp) {

				throwException("UNABLE TO TAKE THE SCREEN SHOT OF THE SCREEN FROM THE METHOD screenShot.\n"
						+ exp.getMessage() + "\n");
			}
		

	}
	public  void initiateReport(String Reportname,String timeStamp) throws MyOwnException {

		
		screenShotFolderPath = System.getProperty("user.dir") + "\\Results\\Screenshots" + "_"
				+ testRunTimeStamp;	
		extentReportFileName="TG_Extent_Report_" + Reportname+".html";
		report = reportVar.initialize(extentReportFileName, false);
		reportVar.recordSystemInfo(report, "Operating System", "WINDOWS OS");
		reportVar.recordSystemInfo(report, "Java", "1.8");
		reportVar.recordSystemInfo(report, "Selenium Version", "3.4");

	}

	@AfterClass(alwaysRun = true)
	public  void close(ITestContext itx) throws Throwable {
		try {
			
			
			BrowserFactory browserFactory = BrowserFactory.getInstance();
			browserFactory.getDriver().quit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
