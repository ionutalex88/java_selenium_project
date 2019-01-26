package objectTypes;

import aut.AUT;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import utilities.Log;

public class Button extends CommonObject {
	
	//=======================Constructor===================
    public Button(By selector) {
		super(selector);
	}
    
    //=======================Methods=======================
    
    /**
     * Performs a double click action on the object
     */
    public void doubleClick(){
    	Actions action = new Actions(AUT.driver);
    	action.doubleClick(findSelf()).perform();
    	Log.action("Double click on element "+selector.toString());
    }
     
    /**
     * Performs a right click action on the object
     */
    public void rightClick(){
    	Actions action = new Actions(AUT.driver);
    	action.contextClick(findSelf()).perform();
    	Log.action("Right click on element "+selector.toString());
    }


}
