package pages;

import aut.AUT;
import objectTypes.Button;
import objectTypes.Input;
import org.openqa.selenium.By;
import utilities.Verify;

public class
        Login {

	//==========================Objects on page====================
	public Input username = new Input(By.cssSelector("#email_address"));
    public Input pass = new Input(By.id("password"));
    
    public Button submitButton = new Button(By.cssSelector("#login-button"));


    //==========================Methods for this page=============================
    /**
     * Performs a login operation using the given parameters
     * @param user
     * @param password
     */
    public void performLogin(String user, String password){
    	AUT aut = new AUT();
        username.inputText(user);
        pass.inputText(password);
        submitButton.click();
        Verify.compare("Login successfully performed", true, aut.commons.accountLinks.isDisplayed());
    }
    
}
