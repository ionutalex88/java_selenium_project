package tests;

import aut.AUT;
import utilities.Config;
import utilities.Log;
import utilities.Verify;

public class SimpleCheckout {
	
	public static void main(String[] args) {
		String testName = "SimpleCheckout";
		AUT aut = new AUT(args);
		Log.createLogAndReport(testName);
		Verify.resetFinalResult();

		try {
			Log.step("***Step 1 - Navigate to Homepage***");
			aut.maximizeScreen();
			aut.navigate(Config.getHomepage());
			
			Log.step("***Step 2 - Login with valid credentials***");
			aut.loginPage.performLogin("itrusca@mailinator.com", "q1w2e3r4");
			
			aut.miniCart.checkout.click();
			
			Log.step("***Step 3 - Navigate to gategory WOMEN");
			aut.homePage.women.click();
			
			Log.step("***Step 4 - Add any product to cart***");
			aut.productsGrid.landrieShoes.click();
			aut.productDetailsPage.size.selectOption("7 B");
			aut.productDetailsPage.quantity.selectOption("1");
			aut.productDetailsPage.addToBag.click();
			aut.sleep(10);
			
			Log.step("***Step 4 - Initiate checkout***");
			aut.miniCart.arrow.click();
			aut.sleep(1);
			aut.miniCart.checkout.click();
			
			Log.step("***Step 5 - Verify the name of the first item added to cart***");
			Verify.compare("First item is Landrie", "LANDRIE", aut.checkout_OrderDetails.firstItemName.getText());
			
			
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


