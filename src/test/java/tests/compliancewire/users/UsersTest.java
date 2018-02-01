package tests.compliancewire.users;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ui.utils.ExcelReader;
import ui.utils.HTTPClient;

public class UsersTest {
	private static final Logger logger = Logger.getLogger(UsersTest.class);
	List<String> failedUsersLst = new ArrayList<String>();

	@DataProvider(name = "usersInfo",parallel=true)
	public Object[] users() {
		ExcelReader data = new ExcelReader("C:\\JNJ.xls");
		List<String> fullUsrsLst = data.getColumnData("Sheet1", "UserName");
		Object[] UsersObjects = fullUsrsLst.toArray();
		return UsersObjects;
		// return Arrays.copyOfRange(UsersObjects,0,100);
	}

	@Test(threadPoolSize=300,alwaysRun = true, dataProvider = "usersInfo", timeOut = 10000)
	public void testUsers(String userId) {
		String response = new HTTPClient().post(
				"https://prodcopy.compliancewire.com/secure/EncryptionLogCheck.ASP", "UserID=" + userId
						+ "&Password1=&Password=Johnson1&CompanyCode=13301US&T1=" + userId + "&T2=Johnson1&T3=13301US",
				"form");
	/*	logger.info("ThreadID: " + Thread.currentThread().getId() + "  " + Thread.currentThread().getName() + " "
				+ userId + "__" + response);*/
		if (response.contains("SSOFailureMsg")) {
			failedUsersLst.add(userId);
		}
	}

	@AfterTest
	public void failedUsers() {
		logger.info("ThreadID: " + Thread.currentThread().getId() + "  " + Thread.currentThread().getName() + " "
				+ failedUsersLst);
	}

}
