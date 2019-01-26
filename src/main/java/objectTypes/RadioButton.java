package objectTypes;

import org.openqa.selenium.By;
import utilities.Log;

public class RadioButton extends CommonObject {
	
	//===================Constructor================
	public RadioButton(By selector) {
		super(selector);
		// TODO Auto-generated constructor stub
	}
	
	//===================Methods====================
	/**
	 * Will return true if the radio button is selected
	 * @return boolean value
	 */
	public boolean isSelected(){
		return findSelf().isSelected();
	}
	
	/**
	 * Sets the value of the radio button to "checked"
	 */
	public void select(){
		click();
		Log.action("Click Radio selection "+selector.toString());
	}
	

}