package objectTypes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utilities.Log;

import java.util.ArrayList;
import java.util.List;

public class SelectBox extends CommonObject{

	//=====================Constructor=====================
	public SelectBox(By selector) {
		super(selector);
		// TODO Auto-generated constructor stub
	}
	
	//=====================Methods==========================
	 /**
	  * Selects an option by text
	 * @param optionText
	 */
	 public void selectOption(String optionText){
        Select selectBox = new Select(findSelf());
        selectBox.selectByVisibleText(optionText);
        Log.action("Select option "+optionText+" from element "+selector.toString());
	 }
	 
	 /**
	  * Selects an option by index
	 * @param index
	 */
	 public void selectOption(int index){
        Select selectBox = new Select(findSelf());
        selectBox.selectByIndex(index);
        Log.action("Select option at index "+index+" from element "+selector.toString());
	 }
	 
	 
	 /**
	  * Returns the selected option of the SelectBox
	 * @return String
	 */
	public String getSelectedOption(){
        Select selectBox = new Select(findSelf());
        return selectBox.getFirstSelectedOption().getText();
	 }
	 
	 /**
	  * Selects an option by value
	 * @param value
	 */
	public void selectByValue(String value){
        Select selectBox = new Select(findSelf());
        selectBox.selectByValue(value);
        Log.action("Select option with value "+value+" from element "+selector.toString());
	 }
	 
	 /**
	  * Returns all the available options
	 * @return text
	 */
	@SuppressWarnings("null")
	public ArrayList<String> getOptions(){
		 Select selectBox = new Select(findSelf());
	     List<WebElement> elements = selectBox.getOptions();
	     ArrayList<String> elementsText = null;
	     for (int i=0;i<elements.size();i++)
	    	 elementsText.add(elements.get(i).getText());
	     return elementsText;
	 }
		 
		 

}
