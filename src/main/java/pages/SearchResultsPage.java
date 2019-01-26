package pages;

import objectTypes.CommonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Verify;

import java.util.List;

public class SearchResultsPage {

    //==========================Objects on page====================
    public CommonObject articleHeadline = new CommonObject(By.cssSelector("article [itemprop=\"headline\"]"));

    //==========================Methods for this page=============================

    /**
     * Checks that all listed articles contain text
     * @param expectedText
     */
    public void checkAllHeadlinesContainText(String expectedText){
        List<WebElement> articles = this.articleHeadline.findAll();
        for (WebElement article : articles) {
            Verify.contains("Title check", expectedText, article.getText());
        }
    }

}
