package tests;

import aut.AUT;
import utilities.Config;
import utilities.Log;
import utilities.Verify;

public class MyTestCase {
	public static void main(String[] args) {
		String testName = "MyTestCase";
		Log.createLogAndReport(testName);
		Verify.resetFinalResult();
		AUT aut = new AUT(args);

		try {
			Log.step("***Step 1 - Navigate to Homepage***");
			aut.maximizeScreen();
			aut.navigate(Config.getHomepage());
			// TODO: Insert actions here

		}

		catch (Exception e) {
			Verify.finalResult = false;
			Log.error(e);
			e.printStackTrace();
		}

		finally {
			Verify.logFinalResult();
			aut.closeBrowser();
		}
	}
}


