package objectTypes;

import org.openqa.selenium.By;
import utilities.Log;

public class CheckBox extends CommonObject {
	
	//===================Constructor================
	public CheckBox(By selector) {
		super(selector);
		// TODO Auto-generated constructor stub
	}
	
	//===================Methods====================
	/**
	 * Will return true if the check-box is selected
	 * @return boolean value
	 */
	public boolean isSelected(){
		return findSelf().isSelected();
	}
	
	/**
	 * Sets the value of the check-box to "checked"
	 */
	public void setChecked(){
		if (!isSelected())
			click();
		Log.action("Set element as checked "+selector.toString());
	}
	
	/**
	 * Sets the value of the check-box to "unchecked"
	 */
	public void setUnchecked(){
		if (isSelected())
			click();
		Log.action("Set element as unchecked "+selector.toString());
	}

}
