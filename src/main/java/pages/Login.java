package pages;

import objectTypes.Button;
import objectTypes.CommonObject;
import objectTypes.Input;
import org.openqa.selenium.By;

public class Login {

	//==========================Objects on page====================
	public Input email = new Input(By.cssSelector("#user-identifier-input"));
    public Input password = new Input(By.cssSelector("#password-input"));
    
    public Button submitButton = new Button(By.cssSelector("#submit-button"));

    public CommonObject loginErrorText = new CommonObject(By.cssSelector("#form-message-username"));

    //==========================Methods for this page=============================
    /**
     * Performs a login operation using the given parameters
     * @param email
     * @param password
     */
    public void loginWithCredentials(String email, String password){
        this.email.inputText(email);
        this.password.inputText(password);
        submitButton.click();
    }

    public String getLoginErrorText(){
        return this.loginErrorText.getText();
    }
    
}
