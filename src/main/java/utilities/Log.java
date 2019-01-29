package utilities;

import aut.AUT;
import org.apache.commons.io.IOUtils;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	
	public static String testName;
	public static String testDescription="";
	public static long startTime;
	private static FileUtil fileUtilities = new FileUtil();
	public static final String logsDir = System.getProperty("user.dir")+"/reports/tests";
	public static String configDir = System.getProperty("user.dir")+"/config";
	public static String screenshotsDir = logsDir+"/screenshots";
	private static SimpleDateFormat time = new SimpleDateFormat ("HH_mm_ss");
	private static SimpleDateFormat time1 = new SimpleDateFormat ("HH:mm:ss");
	private static SimpleDateFormat date = new SimpleDateFormat ("dd_MM_yyyy");
	private static SimpleDateFormat date1 = new SimpleDateFormat ("dd.MM.yyyy");
	
	//====================================Methods used for creating log and report files========================
	/**
	 * 	Deletes any previously created details .txt log file
	 *  Creates the detailed .txt log file
	 */
	public static void createDetailedLog(String testDir){
		fileUtilities.deleteFile(testDir + "/" + testName + "_DetailedLog.txt");
		fileUtilities.createFile(testDir, testName + "_DetailedLog.txt");
		Log.text("******************** Start Test: " + testName + "***********************");
	}
	
	/**
	 *  Deletes any existing html Report for the same test
	 *  Creates the html Report
	 * 	Cleans the folder containing old screenshots
	 */
	public static void createTestReport(String testDir){
		fileUtilities.deleteFile(testDir + "/" + testName + "_Report.html");
		fileUtilities.createFile(testDir, testName + "_Report.html");
		fileUtilities.cleanDirectory(screenshotsDir+"/"+testName);
		Log.writeReportHead();
	}
	
	/** 
	 * Create logs and  report files
	 * INFO: Appends the standard head and css style to the report
	 * INFO: Resets the final result for Tests Suite runs
	 * @param testName
	 */
	public static void createLogAndReport(String testName){
		Log.testName = testName;
		String testDir = Log.logsDir+"/"+testName;
		Log.screenshotsDir = testDir+"/screenshots";
		fileUtilities.cleanDirectory(screenshotsDir);
		
		createDetailedLog(testDir);
		createTestReport(testDir);
		writeReportCSS();
		
		//reset the final result for Suite running
		Assert.resetFinalResult();
	}

	
	//=================================Methods for logging on the detailed.txt log file=============================
	/**
	 * Logs an action in the detailed .txt log file
	 * @param action
	 */
	public static void text(String action){
		try 
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(logsDir + "/" + testName  + "/" + testName + "_DetailedLog.txt",true));
				writer.newLine();
				writer.write(action) ;
				writer.close() ;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	//=========================================General logging methods=============================
	/**
	 * Logs a manual step on detailed log and also on the html  report
	 * @param step
	 */
	public static void step(String step){
		Log.text("");
		Log.text("STEP:\t"+step);
		Log.reportInfo("Step", step);
	}
	
	/**
	 * Logs an information row on detailed log and also on the html  report
	 * @param info
	 */
	public static void info(String info){
		Log.text("INFO:\t"+info);
		Log.reportInfo("Information", info);
	}
	
	/**
	 * Logs an Action in the detailed log
	 * @param action
	 */
	public static void action(String action){
		Log.text("ACTION:\t"+action);
	}
	
	
	
	
	/**
	 * Logs Error on detailed txt  file and also on the html report document
	 * Add a link to screenshot for html document
	 * Logs stack trace of the occurred error
	 * @param e - error
	 */
	public static void error(Exception e){
		StackTraceElement[] trace = e.getStackTrace();
		int i=0;
		while (!trace[i].toString().contains(testName)) {
			i++;
		}
		String stackTrace="";
		for (StackTraceElement element : trace) {
			stackTrace= stackTrace+element.toString()+"\n";
		}
		Log.text("\n!!!!!! ERROR !!!!!!->" + e.getMessage() + "\n\nLocation: " + trace[i].toString());
		Log.text("\n------Stack Trace------\n" + stackTrace);
		Log.reportError(e.getMessage(), stackTrace);
	}
	
	
	//=============================Methods for logging in the html report file===========================
	/**
	 * Appends a given text in the Html Report file
	 * @param text
	 */
	public static void writeOnHtmlReport(String text){
		try 
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(logsDir + "/" + testName + "/" + testName + "_Report.html",true));
				writer.newLine();
				writer.write(text) ;
				writer.close() ;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Append to the html report document the CSS styling
	 * The CSS styling is read from file config/reports/reportCSS.css
	 */
	public static void writeReportCSS(){
		String cssStylefile = configDir+"/reports/reportCSS.css";
		writeOnHtmlReport("<link href=\"" + cssStylefile + "\" rel=\"stylesheet\">");
	}
	
	/**
	 * Append to the html report document the JavaScript content
	 * The JavaScript content is read from file config/reportJavaScript.html
	 */
	public static void writeReportJavaScript(){
		String cssSylefile = configDir+"/reports/reportJavaScript.js";
	    try {
	    	FileInputStream inputStream = new FileInputStream(cssSylefile);
	        String javaScript = IOUtils.toString(inputStream);
	        inputStream.close();
	        Log.writeOnHtmlReport(javaScript);
	    } 
	    catch (Exception e) {
		      System.out.println("Could not read JavaScript \n"+e);
		}
	}
	
	/**
	 * Appends the header of the html report of the executed test
	 */
	public static void writeReportHead(){
		String currentDate = date1.format(new Date(System.currentTimeMillis()));
		String currentTime = time1.format(new Date(System.currentTimeMillis()));
		startTime = System.currentTimeMillis();
		
		String headContent = "<html xmlns =\"http:////www.w3.org//1999//xhtml\">"
							+ "\n<head>"
							+ "\n<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"/>"
							+ "\n<title>"+testName+" report</title>"
							+ "\n<script type=\"text/javascript\" src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.6.3/jquery.min.js\"></script>"
							+ "\n</head>"
							+ "\n<div id=\"report\">"
							+ "\n<div class=\"summary\">"
							+ "\n<div class=\"counters\">"
							+ "\n<p class=\"success-counter\"></p>"
							+ "\n<p class=\"failure-counter\"></p>"
							+ "\n<p class=\"steps-counter\"></p>"
							+ "\n</div>"
							+ "\n</div>"
							+ "\n</head>"
							+ "\n<body>"
							+ "\n<div class=\"testcase\">"
							+ "<p>\r\n"
							+ "	<div class=\"progress\">\r\n"
							+ "		<div class=\"progress-bar progress-bar-success\"><span class=\"sr-only\"></span></div>\r\n"							
							+ "		<div class=\"progress-bar progress-bar-warning\"><span class=\"sr-only\"></span></div>\r\n" 
							+ "		<div class=\"progress-bar progress-bar-danger\"><span class='sr-only'></span></div>\r\n"
							+ "	</div>\r\n"
							+ "<p>"
							+ "\n<ul class=\"tags\">"
							+ "\n</ul>"
							+ "\n<h2>"
							+ "\n<span class=\"keyword\">Test Case: </span>"
							+ "\n<span class=\"title\">"+testName+"</span>"
							+ "\n</h2>"
							+ "\n<div class=\"scenario\">"
							+ "\n<ul class=\"tags\">"
							+ "\n<li>Started on "+currentDate+" at "+currentTime+"</li>"
							+ "\n</ul>"
							+ "\n<h3>"
							+ "\n<span class=\"keyword\">Scenario: </span>"
							+ "\n<span class=\"title\">"+testDescription+"</span>"
							+ "\n</h3>"
							+ "\n<ol>";
		Log.writeOnHtmlReport(headContent);
	}
	
	/**
	 * Appends a "Failed" block to the html Report document
	 * Also a link to a screenshot taken is displayed
	 * @param message -  A description of the verification
	 * @param expected - the expected result
	 * @param actual - the actual result
	 */
	public static void reportFailure(String message, String expected, String actual){
		String currentDate = date.format(new Date(System.currentTimeMillis()));
		String currentTime = time.format(new Date(System.currentTimeMillis()));
		String screenShotLocation = screenshotsDir+"/Failure_"+currentDate+"_"+currentTime+".png";
		new AUT().takeScreenshot(screenShotLocation);
		
		String failureReportContent = "<li class=\"failed alert\">"
									+ "\n<div class=\"step\">"
									+ "\n<span class=\"keyword\">FAILLED - </span>"
									+ "\n<span class=\"text\">"+message+": expected= \"<strong class=\"failed_param\">"+expected+"</strong>\" - actual= \"<strong class=\"failed_param\">"+actual+"</strong>\"</span>"
									+ "\n<span class=\"observation\">"
									+ "\n<a href=\"#\">See screenshot</a>"
									+ "\n</span>"
									+ "\n<span class=\"observation screenshot jq-toggle\">"
									+ "\n<img src=\"" + screenShotLocation + "\">"  
									+ "\n</span>"
									+ "\n</div>"
									+ "\n</li>";
		Log.writeOnHtmlReport(failureReportContent);
	}
	
	/**
	 * Appends a "Passed" block to the html Report document
	 * @param message -  A description of the verification
	 * @param expected - the expected result
	 * @param actual - the actual result
	 */
	public static void reportPassed(String message, String expected, String actual){
		String passedReportContent = "<li class=\"passed alert\">"
									+ "\n<div class=\"step\">"
									+ "\n<span class=\"keyword\">PASSED - </span>"
									+ "\n<span class=\"text\">"+message+": expected= \"<strong class=\"passed_param\">"+expected+"</strong>\" - actual= \"<strong class=\"passed_param\">"+actual+"</strong>\"</span>"
									+ "\n</div>"
									+ "\n</li>";
		Log.writeOnHtmlReport(passedReportContent);
	}
	
	/**
	 * Appends useful information to the report.html file
	 * @param informationType
	 * @param message
	 */
	public static void reportInfo(String informationType, String message){
		String infoReportContent = "<li class=\"info alert\">"
									+ "\n<div class=\"step\">"
									+ "\n<span class=\"keyword\">"+informationType+" - </span>"
									+ "\n<span class=\"text\">"+message+"</span>"
									+ "\n</div>"
									+ "\n</li>";
		Log.writeOnHtmlReport(infoReportContent);
	}
	
	/**
	 * Reports the execution duration on the HTML report file
	 */
	public static void reportExecutionTime(){
		SimpleDateFormat duration = new SimpleDateFormat ("mm' minutes' : ss' seconds'");
		SimpleDateFormat shortDuration = new SimpleDateFormat ("ss' seconds'");
		long executionTimeMillis = (System.currentTimeMillis() - startTime);
		String executionTime;
		if (executionTimeMillis/1000.0 >= 60)
			executionTime = duration.format(new Date(executionTimeMillis));
		else
			executionTime = shortDuration.format(new Date(executionTimeMillis));
		//String executionTime = Long.toString(Math.round((System.currentTimeMillis() - 
		//													startTime)/1000.0)) + " seconds";
		reportInfo("END - Execution time ", executionTime);
	}
	
	/**
	 * Appends the final result of the test to the html report file
	 * @param finalResult
	 */
	public static void reportFinalResult(boolean finalResult){
		if (finalResult){
			String passedReportContent = "<li class=\"passed alert\">"
					+ "\n<div class=\"step\">"
					+ "\n<span class=\"keyword\"><center>PASSED</center></span>"
					+ "\n</div>"
					+ "\n</li>";
			Log.writeOnHtmlReport(passedReportContent);
		}
		else{
			String passedFailureContent = "<li class=\"failed alert\">"
					+ "\n<div class=\"step\">"
					+ "\n<span class=\"keyword\"><center>FAILED</center></span>"
					+ "\n</div>"
					+ "\n</li>";
			Log.writeOnHtmlReport(passedFailureContent);
		}
	}
	
	/**
	 * Appends a fail block to the html report document is care an error has ocured during the execution
	 * Displayed information:
	 * - Error message
	 * - Stack Trace
	 * - Screenshot link
	 * @param message
	 * @param stackTrace
	 */
	public static void reportError(String message, String stackTrace){
		String screenShotLocation = screenshotsDir+"/"+testName+"_error.png";
		new AUT().takeScreenshot(screenShotLocation);
		
//		String error = "abasba %s fsafas %s sfafa %s";
//		String outputString = format(error, message, stackTrace, screenShotLocation);
		
		String errorReportContent = "<li class=\"failed alert\">"
				+ "\n<div class=\"step\">"
				+ "\n<span class=\"keyword\">Unexpcted ERROR - </span>"
				+ "\n<span class=\"text\">"+message+"</span>"
				+ "\n<pre class=\"backtrace\">"+stackTrace
				+ "\n</pre>"
				+ "\n<span class=\"observation\">"
				+ "\n<a href=\"#\">See screenshot</a>"
				+ "\n</span>"
				+ "\n<span class=\"observation screenshot jq-toggle\">"
				+ "\n<img src=\"" + screenShotLocation + "\">"  
				+ "\n</span>"
				+ "\n</div>"
				+ "\n</li>";
		Log.writeOnHtmlReport(errorReportContent);
	}
	
	
}
