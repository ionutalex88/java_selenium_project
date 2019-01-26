package utilities;

import aut.AUT;

public class Dialog {
	
	public static void accept(){
		AUT.driver.switchTo().alert().accept();
	}
	
	public  static void cancel(){
		AUT.driver.switchTo().alert().dismiss();
	}
	
	public static void sendKeys(String keysToSend){
		AUT.driver.switchTo().alert().sendKeys(keysToSend);
	}
	
	public static String getText(){
		return AUT.driver.switchTo().alert().getText();
	}
	
}
