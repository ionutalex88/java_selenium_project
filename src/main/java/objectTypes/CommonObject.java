package objectTypes;

import aut.AUT;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Log;

import java.util.List;

public class CommonObject {
	
	protected By selector;
	
	//=====================Constructor===================
	public CommonObject(By selector){
		this.selector = selector;
	}
	public CommonObject(){}
	//=====================Methods=======================
	/**Returns a WebElement as a result of applying the suitable find method
	 * @return
	 */
	public WebElement findSelf(){
		return AUT.driver.findElement(selector);
	}

	/**Returns a <List>WebElement as a result of applying the suitable find method
	 * @return
	 */
	public List<WebElement> findAll(){
		return AUT.driver.findElements(selector);
	}
	
	/**
	 * Returns the plain text of the selected object
	 * @return text under object
	 */
	public String getText(){
		return findSelf().getText();
	}
	
	/**
	 * Performs a click action on the object
	 */
	public void click(){
		findSelf().click();
		Log.action("Click on element "+selector.toString());
	}
	
	/**Verifies if the object is enabled
	 * @return boolean value
	 */
	public boolean isEnabled(){
		return findSelf().isEnabled();
	}
	
	/**Verifies if the object is visible
	 * @return boolean value
	 */
	public boolean isDisplayed(){
		return findSelf().isDisplayed();
	}
	
	/**
	 * Returns the value of the given attribute
	 * @param attributeName
	 * @return String value of the attribute
	 */
	public String getAttribute(String attributeName){
		return findSelf().getAttribute(attributeName);
	}
	
	 /**
     * Moves the mouse over the selected object
     */
    public void hover(){
    	Actions action = new Actions(AUT.driver);
    	action.moveToElement(findSelf()).perform();
    	Log.action("Hover over element "+selector.toString());
    }
	 
}
