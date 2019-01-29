package objectTypes;

import org.openqa.selenium.By;
import utilities.Log;

public class Input extends CommonObject {
	
	//===================Constructor==================
	public Input(By selector) {
		super(selector);
	}
	public Input(){}

	//====================Methods=====================
	/**
	 * Fills the input field with the given text
	 * @param text
	 */
	public void inputText(String text){
	    findSelf().sendKeys(text);
	    Log.action("Set text "+text+" on field "+selector.toString());
	}
	
	/**
	 * Clears all text in the input filed
	 */
	public void clearText(){
	    findSelf().clear();
	    Log.action("Clear all text on filed"+selector.toString());
	}
	


}
