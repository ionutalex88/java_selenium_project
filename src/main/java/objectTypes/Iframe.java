package objectTypes;

import aut.AUT;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import utilities.Log;

public class Iframe extends CommonObject {
	public By getSelector(){
		return selector;
	}
	
	public Iframe(By selector) {
		super(selector);
	}
	
	public void switchTo (){
		try {
			AUT.driver.switchTo().frame(this.findSelf());
		}
		catch (NoSuchElementException e1) {
			Log.error(e1);
		}		
	}
	public String getSource (){
		return this.findSelf().getAttribute("src");
	}
}
