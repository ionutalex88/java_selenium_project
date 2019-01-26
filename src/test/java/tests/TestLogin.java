package tests;

import aut.AUT;
import utilities.Config;
import utilities.Log;
import utilities.Verify;

public class TestLogin {
	
	public static void main(String[] args) {
		String testName = "TestLogin";
		AUT aut = new AUT(args);
		Log.createLogAndReport(testName);
		Verify.resetFinalResult();

		try {
			Log.step("***Step 1 - Navigate to Homepage***");
			aut.maximizeScreen();
			aut.navigate(Config.getHomepage());
			// TODO: Insert actions here
			
			aut.homePage.loginWith("testUser", "testPassword");
			aut.homePage.submit.click();

		}
		//in case any error occurs
		catch (Exception e) {
			Verify.finalResult = false;
			Log.error(e);
			e.printStackTrace();
		}
		//at the end of test execution
		finally {
			Verify.logFinalResult();
			aut.closeBrowser();
		}
	}
}
