package tests;

import aut.AUT;
import testData.dataObjects.LoginData;
import testData.dataTypes.User;
import utilities.Config;
import utilities.Log;
import utilities.Verify;

public class TestLogin_Invalid {
	
	public static void main(String[] args) {
		String testName = "TestLogin_Invalid";
		AUT aut = new AUT(args);
		Log.createLogAndReport(testName);
		Verify.resetFinalResult();

		try {
			Log.step("***Step 1 - Navigate to Homepage***");
			aut.maximizeScreen();
			aut.navigate(Config.getHomepage());

			Log.step("***Step 2 - Click on Sign In***");
			aut.homePage.signInButton.click();


			Log.step("***Step 3 - Enter Credentials***");
			User testUser = new LoginData().getUserFromFile("Wrong Credentials");
			aut.loginPage.loginWithCredentials(testUser.email, testUser.password);

			Log.step("***Step 3 - Check that user was authenticated***");
			Verify.compare("Check error message",aut.loginPage.getLoginErrorText(), LoginData.invalidCredentialsErrorText);

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
