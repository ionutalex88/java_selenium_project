package aut;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import pages.*;
import utilities.Config;
import utilities.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AUT {
	
	 public static WebDriver driver;
	 
	 //===========================Constructor======================
	 /**
	  * Constructor used at the beginning of the script
	  * Initializes browser depending on configuration file properties
	 * @param - ProfileName
	 */
	public AUT(String[] arguments){
		Config.setTestProfile(arguments);
		
		if (Config.browser.equals("Chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/config/chromedriver.exe");
			driver = new ChromeDriver();
		}
		if (Config.browser.equals("Firefox"))
			driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	 
	
	 /**
	 * Constructor used in initializing AUT in tasks
	 */
	public AUT(){
	 }
	
	 //===============================Pages===========================
	public Login loginPage = new Login();
	public Home homePage = new Home();
	public SearchResultsPage searchResultsPage = new SearchResultsPage();
	public ArticlePage articlePage = new ArticlePage();

	 //===============================Methods=========================
	 /**
	  * Navigates to the indicated URL
	 * @param URL
	 */
	public void navigate (String URL){
		 driver.get(URL);
		 Log.action("Navigate to :"+URL);
	 }
	 
	 /**
	 * Maximizes WebDriver screen
	 */
	public void maximizeScreen(){
		 driver.manage().window().maximize();
		 Log.action("Maximize Screen");
	 }
	 
	 /**
	 * Closes driver instance
	 */
	public void closeBrowser(){
		 driver.close();
		 driver.quit();
	 }
	
	/**
	 * @param text
	 * @return true if page contains the given text
	 */
	public boolean pageContains(String text){
		return driver.getPageSource().contains(text);
	}
	
	/**
	 * Waits for a given amount of seconds
	 * @param seconds
	 */
	public void sleep(long seconds){
		try {
		    TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
		    //Handle exception
		}
		Log.action("wait for " + seconds + " seconds");
	}
	
	
	/**
	 * Takes a screenshot and store it in the indicated output file
	 * @param outputFile
	 */
	public void takeScreenshot(String outputFile){
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(outputFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Send keys to browser
	 * @param keysToSend
	 */
	public void sendKeys(String keysToSend){
		Actions action = new Actions(driver);
		action.sendKeys(keysToSend);
		Log.action("Insert keys: "+keysToSend);
	}
	
	/**
	 * Navigates back
	 */
	public void navigateBack(){
		driver.navigate().back();
		Log.action("Navigate back");
	}
	
	/**
	 * Navigates forward
	 */
	public void navigateForward(){
		driver.navigate().forward();
		Log.action("Navigate forward");
	}
	
	/**
	 * Refresh page
	 */
	public void refreshPage(){
		driver.navigate().refresh();
		Log.action("Refresh page");
	}
	
	public void setTimeout(long seconds){
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
	

}
