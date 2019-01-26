package tests;

import aut.AUT;
import utilities.Config;
import utilities.Log;
import utilities.Verify;

public class ExampleTest {
	
	public static void main(String[] args) {
		Log.testDescription = "...insert test description here...";
		Log.createLogAndReport("ExampleTest");
		AUT aut = new AUT(args);

		try {
			Log.step("***Step 1 - Navigate to Homepage***");
			aut.maximizeScreen();
			aut.navigate(Config.getHomepage());
			// TODO: Insert actions here
			
			

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
