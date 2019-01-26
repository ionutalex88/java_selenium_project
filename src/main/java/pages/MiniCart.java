package pages;

import objectTypes.Button;
import objectTypes.CommonObject;
import org.openqa.selenium.By;

public class MiniCart {	

	//==========================Objects on page====================
	public CommonObject arrow = new CommonObject(By.cssSelector(".shopping-arrow"));
	public Button checkout = new Button(By.cssSelector(".button.checkout.left span"));

    //==========================Methods for this page=============================

    
}
