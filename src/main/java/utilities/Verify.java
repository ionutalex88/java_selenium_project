package utilities;

import utilities.suiteUtilities.SuiteEditor;

import java.util.ArrayList;

public class Verify {

	/**
	 * The final result of the script execution
	 */
	public static boolean finalResult;
	
	public static void resetFinalResult(){
		finalResult=true;
	}

	/**
	 * Compares two objects' values of any type and Logs the result
	 * @param message
	 * @param expected
	 * @param actual
	 */
	public static void compare (String message, String expected, String actual){
		System.out.println("::VERIFICATION-> "+message+": expected="+expected+" actual="+actual);
		if (expected.equals(actual)){
			Log.reportPassed(message, expected, actual);
			Log.text("PASSED::VERIFICATION-> "+message+": expected="+expected+" actual="+actual);
		}
		else{
			Log.reportFailure(message, expected, actual);
			Log.text("FAILED::VERIFICATION-> "+message+": expected="+expected+" actual="+actual);
			finalResult = false;
		}
	}
	
	/**
	 * Compares two double values and Logs the result
	 * @param message
	 * @param expected
	 * @param actual
	 */
	public static void compare (String message, double expected, double actual){
		System.out.println("::VERIFICATION-> "+message+": expected="+expected+" actual="+actual);
		if (expected==actual){
			Log.reportPassed(message, Double.toString(expected), Double.toString(actual));
			Log.text("PASSED::VERIFICATION-> "+message+": expected="+expected+" actual="+actual);
		}
		else{
			Log.reportFailure(message, Double.toString(expected), Double.toString(actual));
			Log.text("FAILED::VERIFICATION-> "+message+": expected="+expected+" actual="+actual);
			finalResult = false;
		}
	}
	
	/**
	 * Compares two double values and Logs the result
	 * @param message
	 * @param expected
	 * @param actual
	 */
	public static void compare (String message, boolean expected, boolean actual){
		System.out.println("::VERIFICATION-> "+message+": expected="+expected+" actual="+actual);
		if (expected==actual){
			Log.reportPassed(message, Boolean.toString(expected), Boolean.toString(actual));
			Log.text("PASSED::VERIFICATION-> "+message+": expected="+expected+" actual="+actual);
		}
		else{
			Log.reportFailure(message, Boolean.toString(expected), Boolean.toString(actual));
			Log.text("FAILED::VERIFICATION-> "+message+": expected="+expected+" actual="+actual);
			finalResult = false;
		}
	}
	
	/**
	 * Compares two integer values and Logs the result
	 * @param message
	 * @param expected
	 * @param actual
	 */
	public static void compare (String message, int expected, int actual){
		System.out.println("::VERIFICATION-> "+message+": expected="+expected+" actual="+actual);
		if (expected==actual){
			Log.reportPassed(message, Integer.toString(expected), Integer.toString(actual));
			Log.text("PASSED::VERIFICATION-> "+message+": expected="+expected+" actual="+actual);
		}
		else{
			Log.reportFailure(message, Integer.toString(expected), Integer.toString(actual));
			Log.text("FAILED::VERIFICATION-> "+message+": expected="+expected+" actual="+actual);
			finalResult = false;
		}
	}
	
	/**
	 * Checks if the actual string contains the expected string and Logs the result
	 * @param message
	 * @param expected
	 * @param actual
	 */
	public static void contains (String message, String expected, String actual){
		System.out.println("::VERIFICATION-> "+message+": searched="+expected+" actual="+actual);
		if (actual.contains(expected)){
			Log.reportPassed(message, expected, actual);
			Log.text("PASSED::VERIFICATION-> "+message+": searched="+expected+" actual="+actual);
		}
		else{
			Log.reportFailure(message, expected, actual);
			Log.text("FAILED::VERIFICATION-> "+message+": searched="+expected+" actual="+actual);
			finalResult = false;
		}
	}
	

	/**
	 * Verifies if the expected String is Contained in the Array of Strings given as parameter
	 * @param message
	 * @param expected
	 * @param actual
	 */
	public static void contains (String message, String expected, ArrayList<String> actual){
		System.out.println("::VERIFICATION-> "+message+": expected="+expected+" actual="+actual);
		boolean found = false;
		for (String actualElement : actual) {
			if (actualElement.equals(expected))
				found = true;
		}
		if (found){
			Log.reportPassed(message, "true", String.valueOf(found));
			Log.text("PASSED::VERIFICATION-> "+message+": expected=true"+" actual="+String.valueOf(found));
		}
		else{
			Log.reportFailure(message, "true", String.valueOf(found));
			Log.text("FAILED::VERIFICATION-> "+message+": expected=true"+" actual="+String.valueOf(found));
			finalResult = false;
		}
	}
	
	/**
	 * Logs the final test results in the HTML report and detailed log
	 */
	public static void logFinalResult(){
		Log.reportExecutionTime();
		Log.reportFinalResult(finalResult);
		Log.writeReportJavaScript();
		Log.text("");
		SuiteEditor.reportFinalResult();
		if (finalResult){
			Log.text("==================================== PASSED ==============================");
			System.out.println("================ PASSED ================");
		}
		else{
			Log.text("==================================== FAILED ==============================");
			System.out.println("================ FAILED ================");
		}
	}
	
}
