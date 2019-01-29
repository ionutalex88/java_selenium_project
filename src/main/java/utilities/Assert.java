package utilities;

import utilities.suiteUtilities.SuiteEditor;

import java.util.ArrayList;

public class Assert {

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
	public static void stringsMatch(String message, String expected, String actual){
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
	public static void valuesMatch(String message, boolean expected, boolean actual){
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
	public static void valuesMatch(String message, int expected, int actual){
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
	 * Checks if the actual string stringContains the searched string and Logs the result
	 * @param message
	 * @param searched
	 * @param actual
	 */
	public static void stringContains(String message, String searched, String actual){
		System.out.println("::VERIFICATION-> "+message+": searched="+searched+" actual="+actual);
		if (actual.contains(searched)){
			Log.reportPassed(message, searched, actual);
			Log.text("PASSED::VERIFICATION-> "+message+": searched="+searched+" actual="+actual);
		}
		else{
			Log.reportFailure(message, searched, actual);
			Log.text("FAILED::VERIFICATION-> "+message+": searched="+searched+" actual="+actual);
			finalResult = false;
		}
	}
	

	/**
	 * Verifies if the searched String is Contained in the Array of Strings given as parameter
	 * @param message
	 * @param searched
	 * @param actual
	 */
	public static void listContains(String message, String searched, ArrayList<String> actual){
		System.out.println("::VERIFICATION-> "+message+": searched="+searched+" actual="+actual);
		boolean found = false;
		for (String actualElement : actual) {
			if (actualElement.equals(searched)) {
				found = true;
			}
		}
		if (found){
			Log.reportPassed(message, searched, actual.toString());
			Log.text("PASSED::VERIFICATION-> "+message+": searched="+ searched +" actual="+actual.toString());
		}
		else{
			Log.reportFailure(message, searched, actual.toString());
			Log.text("FAILED::VERIFICATION-> "+message+": searched="+ searched +" actual="+actual.toString());
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
