package tests;

import aut.AUT;
import org.openqa.selenium.By;
import utilities.Config;
import utilities.Log;
import utilities.Verify;

public class IframeTest {
	
	public static void main(String[] args) {
		Log.testDescription = "Test frame source, switching to frame and finding element in frame";
		Log.createLogAndReport("IframeTest");
		AUT aut = new AUT(args);

		try {
			Log.step("***Step 1 - Navigate to Homepage***");
			aut.maximizeScreen();
			aut.navigate(Config.getHomepage());
			
			Log.step("***Step 2 - Print frame source***");
			System.out.println("Iframe: "+aut.withIframe.frame.getSource());
			
			Log.step("***Step 3 - Switch to frame***");
			aut.withIframe.frame.switchTo();
			
			Log.step("***Step 4 - Print element in frame***");
			System.out.println("Image: "+aut.driver.findElement(By.cssSelector("img")).getAttribute("src"));
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
