package utilities.suiteUtilities;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import utilities.FileUtil;
import utilities.Log;
import utilities.Verify;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SuiteEditor {
	
	private static FileUtil fileUtilities = new FileUtil();
	
	public static String suitesDir = System.getProperty("user.dir")+"/reports/suites";
	public static String batchReportFile=suitesDir+"/Results.html";
	public static String reportName = FilenameUtils.getName(batchReportFile);
	public static String configDir = System.getProperty("user.dir")+"/config";
	public static String configFile = "config.ini";
	public static String configFilePath = configDir+"/"+configFile;
	
	private static SimpleDateFormat time = new SimpleDateFormat ("HH_mm_ss");
	private static SimpleDateFormat time1 = new SimpleDateFormat ("HH:mm:ss");
	private static SimpleDateFormat date = new SimpleDateFormat ("dd_MM_yyyy");
	private static SimpleDateFormat date1 = new SimpleDateFormat ("dd.MM.yyyy");
	
	public static void setSuitePathToDefault(){
		batchReportFile = suitesDir+"/DefaultSuite.html";
		reportName = FilenameUtils.getName(batchReportFile);
	}
	
	public static void setSuiteName(String Name){
		batchReportFile = suitesDir+"/" + Name + ".html";
		reportName = FilenameUtils.getName(batchReportFile);
	}
	
	public static void setRandomSuitePath(){
		String currentDate = date.format(new Date(System.currentTimeMillis()));
		String currentTime = time.format(new Date(System.currentTimeMillis()));
		batchReportFile = suitesDir+"/"+currentDate+"_at_"+currentTime+".html";
		reportName = FilenameUtils.getName(batchReportFile);
	}
	
	
	
	
	
	/**
	 * 	Deletes the previously suite report 
	 *  Resets the HTML batch report
	 */
	public static void createSuiteReport(){ 
		String path = batchReportFile.replace(reportName, "");
		//String path = path1.substring(0, path1.length()-2);
		System.out.println("Suite path: "+path);
		System.out.println("Suite file: "+reportName);
		fileUtilities.deleteFile(batchReportFile);
		fileUtilities.createFile(path, reportName);
	}
	
	/**
	 * Appends a given text in the Html Batch Report file
	 * @param text
	 */
	public static void writeOnHtmlReport(String text){
		try 
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(batchReportFile,true));
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
	 * Appends the header of the html report of the executed test
	 */
	public static void writeSuiteReportHead(){
		String currentDate = date1.format(new Date(System.currentTimeMillis()));
		String currentTime = time1.format(new Date(System.currentTimeMillis()));
		
		String headContent = "<html xmlns =\"http:////www.w3.org//1999//xhtml\">"
							+ "\n<head>"
							+ "\n<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"/>"
							+ "\n<title>"+reportName+" report</title>"
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
							+ "\n<span class=\"keyword\">Suite: </span>"
							+ "\n<span class=\"title\">"+reportName+"</span>"
							+ "\n</h2>"
							+ "\n<div class=\"scenario\">"
							+ "\n<ul class=\"tags\">"
							+ "\n<li>Started on "+currentDate+" at "+currentTime+"</li>"
							+ "\n</ul>"
							+ "\n<h3>"
							+ "\n<span class=\"title\">"+"Test Suite Results"+"</span>"
							+ "\n</h3>"
							+ "\n<ol>";
		writeOnHtmlReport(headContent);
	}
	
	/**
	 * Append to the html report document the CSS styling
	 * The CSS styling is read from file config/reports/suiteReportCSS.css
	 */
	public static void writeSuiteReportCSS(){
		String cssStylefile = configDir+"/reports/suiteReportCSS.css";
		writeOnHtmlReport("<link href=\"" + cssStylefile + "\" rel=\"stylesheet\">");
	}
	
	/**
	 * Append to the html report document the JavaScript content
	 * The JavaScript content is read from file /reports/config/suiteReportJavaScript.js
	 */
	public static void writeSuiteReportJavaScript(){
		String javaScriptFile = configDir+"/reports/suiteReportJavaScript.js";
	    try {
	    	FileInputStream inputStream = new FileInputStream(javaScriptFile);
	        String javaScript = IOUtils.toString(inputStream);
	        inputStream.close();
	        writeOnHtmlReport(javaScript);
	    } 
	    catch (Exception e) {
		      System.out.println("Could not read JavaScript \n"+e);
		}
	}
	
	/**
	 * Appends a "Failed" script to the html Batch Report document
	 * Offers links to specific reports and detailed logs
	 */
	public static void reportFailedScript(){
		SimpleDateFormat duration = new SimpleDateFormat ("mm'm' : ss's'");
		SimpleDateFormat shortDuration = new SimpleDateFormat ("ss' seconds'");
		long executionTimeMillis = (System.currentTimeMillis() - Log.startTime);
		String executionTime;
		if (executionTimeMillis/1000.0 >= 60)
			executionTime = duration.format(new Date(executionTimeMillis));
		else
			executionTime = shortDuration.format(new Date(executionTimeMillis));
		
		String failureReportContent = "<li class=\"failed alert\">"
									+ "\n<div class=\"step\">"
									+ "\n<span class=\"keyword\">FAILLED - </span>"
									+ "\n<span class=\"text\">"+Log.testName+"</span>"
									+ "\n<span class=\"observation\">"
									+ "\n<span>"+executionTime+"  </span>"
									+ "\t<a href=\""+Log.logsDir+"/"+Log.testName+"/"+Log.testName+"_Report.html"+"\">See Test Report</a>"
									+ "\t<a href=\""+Log.logsDir+"/"+Log.testName+"/"+Log.testName+"_DetailedLog.txt"+"\"> Detailed log</a>"
									+ "\n</span>"
									+ "\n</div>"
									+ "\n</li>";
		writeOnHtmlReport(failureReportContent);
	}
	
	/**
	 * Appends a "Passed" script to the html Batch Report document
	 * Offers links to specific reports and detailed logs
	 */
	public static void reportPassedScript(){
		SimpleDateFormat duration = new SimpleDateFormat ("mm'm' : ss's'");
		SimpleDateFormat shortDuration = new SimpleDateFormat ("ss' seconds'");
		long executionTimeMillis = (System.currentTimeMillis() - Log.startTime);
		String executionTime;
		if (executionTimeMillis/1000.0 >= 60)
			executionTime = duration.format(new Date(executionTimeMillis));
		else
			executionTime = shortDuration.format(new Date(executionTimeMillis));
		
		String passedTestContent = "<li class=\"passed alert\">"
									+ "\n<div class=\"step\">"
									+ "\n<span class=\"keyword\">PASSED - </span>"
									+ "\n<span class=\"text\">"+Log.testName+"</span>"
									+ "\n<span class=\"observation\">"
									+ "\n<span>"+executionTime+"  </span>"
									+ "\t<a href=\""+Log.logsDir+"/"+Log.testName+"/"+Log.testName+"_Report.html"+"\">See Test Report</a>"
									+ "\t<a href=\""+Log.logsDir+"/"+Log.testName+"/"+Log.testName+"_DetailedLog.txt"+"\"> Detailed log</a>"
									+ "\n</span>"
									+ "\n</div>"
									+ "\n</li>";
		writeOnHtmlReport(passedTestContent);
	}
	
	/**
	 * Creates a new tests suite
	 * Report gets name as current date and time
	 */
	public static void createNewTestSuite(){
		//Because no name is given as parameter, sets the suite name using current date and time
		setRandomSuitePath();
		
		createSuiteReport();
		writeSuiteReportHead();
		writeSuiteReportCSS();
		writeSuiteReportJavaScript();
	}
	
	/**
	 * Creates a new tests suite with name given as parameter
	 * @param suiteName
	 */
	public static void createNewTestSuite(String suiteName){
		setSuiteName(suiteName);
		
		createSuiteReport();
		writeSuiteReportHead();
		writeSuiteReportCSS();
		writeSuiteReportJavaScript();
	}
	
	/**
	 * Appends the final result in the Batch Report .html file
	 */
	public static void reportFinalResult(){
		if (Verify.finalResult)
			reportPassedScript();
		else
			reportFailedScript();
	}
	

}
