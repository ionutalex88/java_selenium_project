package pages;

import objectTypes.Button;
import objectTypes.SelectBox;
import org.openqa.selenium.By;

public class ProductDetails {	

	//==========================Objects on page====================
	public SelectBox size = new SelectBox(By.cssSelector("#attribute156"));
	public SelectBox quantity = new SelectBox(By.cssSelector("#qty"));
	public SelectBox colour = new SelectBox(By.id("chooseColor"));
	
	public Button addToBag = new Button(By.cssSelector(".button btn-cart "));

    //==========================Methods for this page=============================

    
}
