package pages;

import objectTypes.CommonObject;
import org.openqa.selenium.By;

public class Commons {

	//==========================Common objects found in different places in the application================================
	public CommonObject accountLinks = new CommonObject(By.cssSelector(".customer-link-drop"));
	
	//==========================General methods that are not strictly related to any page=============================
}
