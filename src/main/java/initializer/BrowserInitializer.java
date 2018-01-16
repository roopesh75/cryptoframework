package initializer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Triple;
import org.apache.log4j.Logger;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.microsoft.tfs.core.TFSTeamProjectCollection;
import com.microsoft.tfs.core.clients.versioncontrol.VersionControlClient;
import com.microsoft.tfs.core.clients.versioncontrol.exceptions.ServerPathFormatException;
import com.microsoft.tfs.core.clients.versioncontrol.soapextensions.Changeset;
import com.microsoft.tfs.core.clients.versioncontrol.soapextensions.RecursionType;
import com.microsoft.tfs.core.clients.versioncontrol.specs.version.DateVersionSpec;
import com.microsoft.tfs.core.clients.versioncontrol.specs.version.LatestVersionSpec;
import com.microsoft.tfs.core.httpclient.Credentials;
import com.microsoft.tfs.core.httpclient.UsernamePasswordCredentials;
import com.microsoft.tfs.core.util.URIUtils;

import ui.BrowserDriver;
import ui.UiBase;
import ui.support.Config;
import ui.utils.CaptureScreenShot;
import ui.utils.ExtentTestManager;
import ui.utils.ScreenShotEditor;
import ui.utils.Tools;
import ui.utils.browser.Browser;

public class BrowserInitializer {
	private static final Logger logger = Logger.getLogger(BrowserInitializer.class);
	protected ITestContext testInfo;
	protected int stepNumber = 1;
	protected Model model;
	private static DateFormat format = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
	private BrowserDriver driver;

	private String stepDescription;
	private HashMap<String, Object> browserCapabilities = new HashMap<String, Object>();
	private Browser browser;
	protected Class testClass;
	public UiBase base = new UiBase();
	List<Thread> threadCollection = new ArrayList<Thread>();

	public BrowserInitializer() {
	}

	public Triple getAuthorInfoTFS() {

		try {
			System.setProperty("com.microsoft.tfs.jni.native.base-directory",
					new File(".").getCanonicalPath() + File.separator + "native");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Credentials cred = new UsernamePasswordCredentials("svc_devapp", "D3vapp!!");
		TFSTeamProjectCollection tpc = new TFSTeamProjectCollection(URIUtils.newURI(Config.getTFSURL()), cred);

		VersionControlClient srcctrl = tpc.getVersionControlClient();
		Changeset[] chngset = new Changeset[1];
		try {
			model = new MavenXpp3Reader().read(new FileReader("pom.xml"));
			chngset = srcctrl.queryHistory(
					Config.getTFS_PROJECT_LOCATION_PREFIX() + model.getArtifactId() + "/src/test/java/"
							+ testClass.toString().replaceAll("\\.", "\\/").split(" ")[1] + ".java",
					LatestVersionSpec.INSTANCE, 0, RecursionType.FULL, null, new DateVersionSpec("6/10/2014"),
					LatestVersionSpec.INSTANCE, Integer.MAX_VALUE, false, true, false, true);
			Set<String> authorsInOrder = new LinkedHashSet<>();
			for (int i = chngset.length - 1; i >= 0; i--) {
				authorsInOrder.add(chngset[i].getOwnerDisplayName());
			}
			String listOfAuthors = authorsInOrder.stream().collect(Collectors.joining(","));

			return Triple.of(chngset.length, listOfAuthors, chngset[chngset.length - 1].getDate().getTime().getTime());
		} catch (ServerPathFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			logger.info(
					"ThreadID: " + Thread.currentThread().getId() + "  " + "ThreadID: " + Thread.currentThread().getId()
							+ "  " + Thread.currentThread().getName() + " " + "no info in tfs");
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (NumberFormatException ne) {
			ne.printStackTrace();
		}

		return Triple.of(0, System.getProperty("user.name"), Calendar.getInstance().getTime().getTime());
	}

	public void initializeDriver(Class testClass) throws Exception {
		logger.info("ThreadID: " + Thread.currentThread().getId() + "  " + "ThreadID: " + Thread.currentThread().getId()
				+ "  " + "ThreadID: " + Thread.currentThread().getId() + "  " + Thread.currentThread().getName() + " "
				+ "Initializing Driver: " + testClass.getName());
		this.testClass = testClass;
		/*
		 * LoggingPreferences logs = new LoggingPreferences();
		 * logs.enable(LogType.BROWSER, Level.ALL); logs.enable(LogType.CLIENT,
		 * Level.ALL); logs.enable(LogType.DRIVER, Level.ALL);
		 * logs.enable(LogType.PERFORMANCE, Level.ALL);
		 * logs.enable(LogType.PROFILER, Level.ALL); logs.enable(LogType.SERVER,
		 * Level.ALL); DesiredCapabilities capabilities = new
		 * DesiredCapabilities();
		 * capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
		 * browser = setDesiredCapabilities(capabilities); //driver = new
		 * BrowserDriverImpl(getWebDriverWithCapabilities(capabilities));
		 */
		driver = DriverFactory.getDriver();
		logger.info("ThreadID: " + Thread.currentThread().getId() + "  " + "ThreadID: " + Thread.currentThread().getId()
				+ "  " + Thread.currentThread().getName() + " " + "Instantiated Driver: " + testClass.getName());
		setTimeOuts();
		base.setDriver(driver);
		driver.manage().window().maximize();
	}

	public void setDriver(BrowserDriver driver) {
		this.driver = driver;
	}

	public BrowserDriver getDriver() {
		return this.driver;
	}

	private void setTimeOuts() {
		driver.manage().timeouts().implicitlyWait(UiBase.getDefaultImplicitTimeout(), TimeUnit.SECONDS);
	}

	public void shutDownBrowser() {
		logger.info("ThreadID: " + Thread.currentThread().getId() + "  " + "ThreadID: " + Thread.currentThread().getId()
				+ "  " + Thread.currentThread().getName() + " " + "COMPLETED RUN FOR : " + this.testClass.getName());
		driver.quit();
	}

	public void Assert(boolean result, String stepDescription) {
		this.stepDescription = stepDescription;
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];// maybe this number needs to be
											// corrected
		String methodName = e.getMethodName();
		try {
			String screenShot = CaptureScreenShot.captureScreen(driver.getWebDriver(),
					CaptureScreenShot.generateFileName(methodName));
			String fileName = screenShot.split("\\\\")[screenShot.split("\\\\").length - 1];
			if (!result) {
				ExtentTestManager.getTest().fail(ScreenShotEditor.markUp(stepDescription, fileName));
				Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
			} else {
				ExtentTestManager.getTest().pass(ScreenShotEditor.markUp(stepDescription, fileName));
			}
			ExtentTestManager.getScreenShotLst().add(screenShot);
			ScreenShotEditor.newInstance().editScreenShot(new File(screenShot), stepDescription.split("::")[0],
					stepNumber);
			stepNumber++;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	
	
	public void AssertTime(String format,String ACTUAL, String EXPECTED, String stepDescription) {
		this.stepDescription = stepDescription;
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];// maybe this number needs to be
											// corrected
		String methodName = e.getMethodName();
		try {
			String screenShot = CaptureScreenShot.captureScreen(driver.getWebDriver(),
					CaptureScreenShot.generateFileName(methodName));
			String fileName = screenShot.split("\\\\")[screenShot.split("\\\\").length - 1];
			
			if (Long.parseLong(Tools.getMilliSecondsForDate(format, EXPECTED))-Long.parseLong(Tools.getMilliSecondsForDate(format, ACTUAL))>30000) {
				ExtentTestManager.getTest().fail(ScreenShotEditor.markUp(
						stepDescription + "" + "<br> EXPECTED -> " + EXPECTED + "<br> ACTUAL ->" + ACTUAL, fileName));
				Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
			} else {
				ExtentTestManager.getTest().pass(ScreenShotEditor.markUp(
						stepDescription + "" + "<br> EXPECTED -> " + EXPECTED + "<br> ACTUAL ->" + ACTUAL, fileName));
			}
			ExtentTestManager.getScreenShotLst().add(screenShot);
			ScreenShotEditor.newInstance().editScreenShot(new File(screenShot), stepDescription.split("::")[0],
					stepNumber);
			stepNumber++;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	public void Assert(String ACTUAL, String EXPECTED, String stepDescription) {
		this.stepDescription = stepDescription;
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];// maybe this number needs to be
											// corrected
		String methodName = e.getMethodName();
		try {
			String screenShot = CaptureScreenShot.captureScreen(driver.getWebDriver(),
					CaptureScreenShot.generateFileName(methodName));
			String fileName = screenShot.split("\\\\")[screenShot.split("\\\\").length - 1];
			if (!ACTUAL.contains(EXPECTED)) {
				ExtentTestManager.getTest().fail(ScreenShotEditor.markUp(
						stepDescription + "" + "<br> EXPECTED -> " + EXPECTED + "<br> ACTUAL ->" + ACTUAL, fileName));
				Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
			} else {
				ExtentTestManager.getTest().pass(ScreenShotEditor.markUp(
						stepDescription + "" + "<br> EXPECTED -> " + EXPECTED + "<br> ACTUAL ->" + ACTUAL, fileName));
			}
			ExtentTestManager.getScreenShotLst().add(screenShot);
			ScreenShotEditor.newInstance().editScreenShot(new File(screenShot), stepDescription.split("::")[0],
					stepNumber);
			stepNumber++;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void AssertMatches(String ACTUAL, String EXPECTED, String stepDescription) {
		this.stepDescription = stepDescription;
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];// maybe this number needs to be
											// corrected
		String methodName = e.getMethodName();
		try {
			String screenShot = CaptureScreenShot.captureScreen(driver.getWebDriver(),
					CaptureScreenShot.generateFileName(methodName));
			String fileName = screenShot.split("\\\\")[screenShot.split("\\\\").length - 1];
			if (!ACTUAL.matches(EXPECTED)) {
				ExtentTestManager.getTest().fail(ScreenShotEditor.markUp(
						stepDescription + "" + "<br> EXPECTED -> " + EXPECTED + "<br> ACTUAL ->" + ACTUAL, fileName));
				Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
			} else {
				ExtentTestManager.getTest().pass(ScreenShotEditor.markUp(
						stepDescription + "" + "<br> EXPECTED -> " + EXPECTED + "<br> ACTUAL ->" + ACTUAL, fileName));
			}
			ExtentTestManager.getScreenShotLst().add(screenShot);
			ScreenShotEditor.newInstance().editScreenShot(new File(screenShot), stepDescription.split("::")[0],
					stepNumber);
			stepNumber++;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void AssertGreater(long ACTUAL, long EXPECTED, String stepDescription) {
		this.stepDescription = stepDescription;
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];// maybe this number needs to be
											// corrected
		String methodName = e.getMethodName();
		try {
			String screenShot = CaptureScreenShot.captureScreen(driver.getWebDriver(),
					CaptureScreenShot.generateFileName(methodName));
			String fileName = screenShot.split("\\\\")[screenShot.split("\\\\").length - 1];
			if (!(ACTUAL > EXPECTED)) {
				ExtentTestManager.getTest().fail(ScreenShotEditor.markUp(stepDescription + "" + "<br> EXPECTED -> "
						+ EXPECTED + " is greater than <br>ACTUAL ->" + ACTUAL, fileName));
				Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
			} else {
				ExtentTestManager.getTest().pass(ScreenShotEditor.markUp(
						stepDescription + "" + "<br> EXPECTED -> " + EXPECTED + " is less than <br>ACTUAL ->" + ACTUAL,
						fileName));
			}
			ExtentTestManager.getScreenShotLst().add(screenShot);
			ScreenShotEditor.newInstance().editScreenShot(new File(screenShot), stepDescription.split("::")[0],
					stepNumber);
			stepNumber++;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void Assert(boolean ACTUAL, boolean EXPECTED, String stepDescription) {
		this.stepDescription = stepDescription;
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];// maybe this number needs to be
											// corrected
		String methodName = e.getMethodName();
		try {
			String screenShot = CaptureScreenShot.captureScreen(driver.getWebDriver(),
					CaptureScreenShot.generateFileName(methodName));
			String fileName = screenShot.split("\\\\")[screenShot.split("\\\\").length - 1];
			if (ACTUAL != EXPECTED) {
				ExtentTestManager.getTest().fail(ScreenShotEditor.markUp(
						stepDescription + "" + "<br> EXPECTED -> " + EXPECTED + "<br> ACTUAL ->" + ACTUAL, fileName));
				Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
			} else {
				ExtentTestManager.getTest().pass(ScreenShotEditor.markUp(
						stepDescription + "" + "<br> EXPECTED -> " + EXPECTED + "<br> ACTUAL ->" + ACTUAL, fileName));
			}
			ExtentTestManager.getScreenShotLst().add(screenShot);
			ScreenShotEditor.newInstance().editScreenShot(new File(screenShot), stepDescription.split("::")[0],
					stepNumber);
			stepNumber++;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void AssertDesktop(boolean result, String stepDescription) {
		this.stepDescription = stepDescription;
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];// maybe this number needs to be
											// corrected
		String methodName = e.getMethodName();
		try {
			String screenShot = CaptureScreenShot.captureDesktop(driver.getWebDriver(),
					CaptureScreenShot.generateFileName(methodName));
			String fileName = screenShot.split("\\\\")[screenShot.split("\\\\").length - 1];
			if (!result) {
				ExtentTestManager.getTest().fail(ScreenShotEditor.markUp(stepDescription, fileName));
				Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
			} else {
				ExtentTestManager.getTest().pass(ScreenShotEditor.markUp(stepDescription, fileName));
			}
			ExtentTestManager.getScreenShotLst().add(screenShot);
			ScreenShotEditor.newInstance().editScreenShot(new File(screenShot), stepDescription, stepNumber);
			stepNumber++;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void AssertDesktop(String ACTUAL, String EXPECTED, String stepDescription) {
		this.stepDescription = stepDescription;
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];// maybe this number needs to be
			// The User Id you entered already exists in this Company								// corrected
			// The User Id you entered already exists in this Company
		String methodName = e.getMethodName();
		try {
			String screenShot = CaptureScreenShot.captureDesktop(driver.getWebDriver(),
					CaptureScreenShot.generateFileName(methodName));
			String fileName = screenShot.split("\\\\")[screenShot.split("\\\\").length - 1];
			if (!ACTUAL.contains(EXPECTED)) {
				ExtentTestManager.getTest().fail(ScreenShotEditor.markUp(stepDescription, fileName));
				Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
			} else {
				ExtentTestManager.getTest().pass(ScreenShotEditor.markUp(stepDescription, fileName));
			}
			ExtentTestManager.getScreenShotLst().add(screenShot);
			ScreenShotEditor.newInstance().editScreenShot(new File(screenShot), stepDescription, stepNumber);
			stepNumber++;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
