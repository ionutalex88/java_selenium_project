package tests;

import aut.AUT;
import testData.dataObjects.LoginData;
import testData.dataTypes.User;
import utilities.Config;
import utilities.Log;
import utilities.Assert;

public class TestLogin_Valid {
	
	public static void main(String[] args) {
		String testName = "TestLogin_Valid";
		AUT aut = new AUT(args);
		Log.createLogAndReport(testName);
		Assert.resetFinalResult();

		try {
			Log.step("***Step 1 - Navigate to Homepage***");
			aut.maximizeScreen();
			aut.navigate(Config.getHomepage());

			Log.step("***Step 2 - Click on Sign In***");
			aut.homePage.signInButton.click();


			Log.step("***Step 3 - Enter Credentials***");
			User testUser = new LoginData().getUserFromFile("Right Credentials");
			aut.loginPage.loginWithCredentials(testUser.email, testUser.password);

			Log.step("***Step 3 - Check that user was authenticated***");
			Assert.stringsMatch("User Name Check",aut.homePage.getAccountName(), testUser.firstName + " " + testUser.lastName);

		}
		//in case any error occurs
		catch (Exception e) {
			Assert.finalResult = false;
			Log.error(e);
			e.printStackTrace();
		}
		//at the end of test execution
		finally {
			Assert.logFinalResult();
			aut.closeBrowser();
		}
	}
}
