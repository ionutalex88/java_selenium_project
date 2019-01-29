package objectTypes;

import aut.AUT;
import org.openqa.selenium.By;
import utilities.Log;

public class Link extends CommonObject {
	
	//====================Constructor===============
	public Link(By selector) {
		super(selector);
	}
	
	//=====================Methods==================
	/**
	 * Returns the URL indicated by the link
	 * @return String
	 */
	public String getURL(){
		return getAttribute("href");
	}
	
	/**
	 * Returns the URL indicated by the link
	 * @return String
	 */
	public void navigateTo(){
		AUT.driver.navigate().to(getURL());
		Log.action("Navigate to link URL of element "+selector.toString());
	}
	
}
