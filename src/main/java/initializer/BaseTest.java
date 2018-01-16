package initializer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.itextpdf.text.DocumentException;

import ui.BrowserDriver;
import ui.support.Config;
import ui.utils.ExtentManager;
import ui.utils.ExtentTestManager;
import ui.utils.MySQLAccess;
import ui.utils.PDFGenerator;

public abstract class BaseTest extends PageInitializer {
	ITestResult result;
	boolean possibleFailure = true;
	private static final Logger logger = Logger.getLogger(BaseTest.class);
	protected final String fileName = getClass().getSimpleName();
	private Map<String, String> localStorage;

	public void setUpPageInitializer(BrowserDriver driver) {
		initializePages(driver, this.getClass());
		localStorage = new HashMap<>();
		logger.info("ThreadID: " + Thread.currentThread().getId() + "  " + Thread.currentThread().getName() + " "
				+ "Initialized Pages and Setup Local Storage");

	}

	public void refreshCurrentPage() {
		getDriver().navigate().refresh();
	}

	public String getSecurityRoleSeq() throws Exception {
		return new MySQLAccess().getSecurityRoleSeq();
	}

	public String getRandomEntityID() {
		return UUID.randomUUID().toString();
	}

	public static String getData(String key) throws Exception {
		return Data.getData(key);
	}

	public void addToStorage(String key, String value) {
		localStorage.put(key, value);
	}

	public String getFromStorage(String key) {
		return localStorage.get(key);
	}

	@BeforeClass(alwaysRun = true)
	public void setUp(ITestContext testInfo) throws Exception {

		String projectName = testInfo.getName();
		if (testInfo.getIncludedGroups().length > 0) {
			projectName = testInfo.getIncludedGroups()[0];
		}
		logger.info("ThreadID: " + Thread.currentThread().getId() + "  " + Thread.currentThread().getName() + " "
				+ "Create Report HTML");

		if (ExtentManager.getInstance() == null) {
			ExtentManager.createInstance(
					System.getProperty("user.dir") + File.separator + "target" + File.separator + "TestReport.html",
					projectName);
			logger.info("ThreadID: " + Thread.currentThread().getId() + "  " + Thread.currentThread().getName() + " "
					+ Thread.currentThread().getName() + " Created instance for report");
		}

		this.testInfo = testInfo;
		initializeDriver(this.getClass());
		getDriver().get(Config.getApplicationUrl());
	}

	@BeforeMethod(alwaysRun = true)
	public void initializeMethod(ITestResult result) throws IOException {

		ExtentTestManager.createTest(this.getClass().toString(),
				testInfo.getSuite().getAllMethods().get(0).getMethodName());
		ExtentTestManager.setAuthorInfo(getAuthorInfoTFS());
		setUpPageInitializer(getDriver());
		logger.info("ThreadID: " + Thread.currentThread().getId() + "  " + Thread.currentThread().getName() + " "
				+ "Back in Initialized Method: " + "CURRENT RESULT STATUS: " + result.getStatus());

	}

	@AfterMethod(alwaysRun = true)
	public void generatePdfReport(ITestResult result) {
		this.possibleFailure = false;

		this.result = result;
		logger.info("ThreadID: " + Thread.currentThread().getId() + "  " + Thread.currentThread().getName() + " "
				+ "FINAL RESULT STATUS" + " for " + result.getStatus());
		if (result.getStatus() == ITestResult.FAILURE) {
			if (result.getThrowable() != null) {
				ExtentTestManager.getTest().fail(MarkupHelper.createLabel(
						result.getName() + "<br>" + result.getThrowable() + "<br>Test Case Failed", ExtentColor.RED));
			} else {
				ExtentTestManager.getTest()
						.fail(MarkupHelper.createLabel(result.getName() + "<br>Test Case Failed", ExtentColor.RED));
			}

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			ExtentTestManager.getTest()
					.pass(MarkupHelper.createLabel(result.getName() + "Test Case Passed", ExtentColor.GREEN));
		}

		ExtentManager.getInstance().flush();
		logger.info("ThreadID: " + Thread.currentThread().getId() + "  " + Thread.currentThread().getName() + " "
				+ "PDF ABOUT TO BE GENERATED" + " for " + result.getName());
		try {
			PDFGenerator.generatePDF(ExtentTestManager.getScreenShotLst(),
					System.getProperty("user.dir") + File.separator + "target" + File.separator
							+ testClass.getSimpleName() + "___" + result.getMethod().getMethodName() + ".pdf");
		} catch (IOException io) {
			logger.info("ThreadID: " + Thread.currentThread().getId() + "  " + Thread.currentThread().getName() + " "
					+ "ERROR PDF CORRUPT");
		} catch (DocumentException de) {
			logger.info("ThreadID: " + Thread.currentThread().getId() + "  " + Thread.currentThread().getName() + " "
					+ "ERROR PDF CORRUPT");
		}

		logger.info("ThreadID: " + Thread.currentThread().getId() + "  " + Thread.currentThread().getName() + " "
				+ "PDF GENERATED");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		if (result != null) {
			if (possibleFailure) {
				result.setStatus(ITestResult.FAILURE);
				logger.info("ThreadID: " + Thread.currentThread().getId() + "  " + Thread.currentThread().getName()
						+ " " + "RESULT IS SET FOR FAILURE EXPLICITLY BECAUSE PDF CODE NOT PARSED");
			}
		} else {
			logger.info("ThreadID: " + Thread.currentThread().getId() + "  " + Thread.currentThread().getName() + " "
					+ "RESULT IS NULL");
		}

		/*
		 * logger.info("ThreadID: " + Thread.currentThread().getId() + "  " +
		 * Thread.currentThread().getName() + " " + "TEAR DOWN RESULT STATUS" +
		 * " for " + result.getStatus());
		 */

		shutDownBrowser();
	}
}