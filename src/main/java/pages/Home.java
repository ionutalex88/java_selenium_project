package pages;

import objectTypes.Button;
import objectTypes.CommonObject;
import objectTypes.Input;
import org.openqa.selenium.By;

public class Home {
	
	//==========================Objects on page====================
	public Button signInButton = new Button(By.cssSelector("#idcta-username"));
	public CommonObject accountSection = new CommonObject (By.cssSelector("#idcta-statusbar"));

	public Input seachInput = new Input(By.cssSelector("#orb-search-q"));
	public Button searchButton = new Button(By.cssSelector("#orb-search-button"));
	//==========================Methods for this page=============================

	public String getAccountName (){
		return this.accountSection.getText();
	}

	/**
	 * Performs a search for a specific text
	 * @param text
	 */
	public void searchForText(String text){
		this.seachInput.inputText(text);
		this.searchButton.click();
	}

}
