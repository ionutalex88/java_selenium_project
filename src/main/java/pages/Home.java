package pages;

import objectTypes.Button;
import objectTypes.Input;
import objectTypes.Link;
import objectTypes.Table;
import org.openqa.selenium.By;

public class Home {
	
	//==========================Objects on page====================
	public Table tabel = new Table(By.cssSelector(".reference"));
	public Link women = new Link(By.linkText("WOMEN"));
	public Input email = new Input(By.cssSelector("#LoginForm input[name=\'email\']"));
	public Input password = new Input(By.cssSelector("#LoginForm input[name=\'password\']"));
	public Button submit = new Button(By.cssSelector("#LoginForm .ok"));
	
	//==========================Methods for this page=============================
	public void loginWith(String username, String password){
		email.inputText(username);
		this.password.inputText(password);
		submit.click();
	}
		

}
