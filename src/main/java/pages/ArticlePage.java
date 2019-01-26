package pages;

import objectTypes.Button;
import objectTypes.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Verify;

import java.util.ArrayList;
import java.util.List;

public class ArticlePage {

    //==========================Objects on page====================
    public Button shareArticleButton = new Button(By.cssSelector("button[title=\"Share this page\"]"));
    public Link socialMediaOption = new Link(By.cssSelector(".s-b-p-networks a"));

    //==========================Methods for this page=============================

    public void checkForSocialMediaOption(String socialMediaOption){
        ArrayList<String> socialMediaOptionsTitles = new ArrayList<>();
        List<WebElement> options = this.socialMediaOption.findAll();
        for (WebElement option : options) {
            socialMediaOptionsTitles.add(option.getText().trim());
        }
        Verify.contains("Check presence of " + socialMediaOption, socialMediaOption, socialMediaOptionsTitles);
    }

    public void clickOnSocialMediaOption(String socialMediaOption) throws Exception {
        ArrayList<String> socialMediaOptionsTitles = new ArrayList<>();
        List<WebElement> options = this.socialMediaOption.findAll();
        boolean found = false;
        for (WebElement option : options) {
            if(option.getText().trim().equals(socialMediaOption)){
                option.click();
                found = true;
            }
        }
        if (!found){
            throw new Exception("Media option: " + socialMediaOption + " NOT FOUND");
        }
    }

}
