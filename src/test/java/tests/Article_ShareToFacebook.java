package tests;

import aut.AUT;
import testData.dataObjects.SocialMediaData;
import utilities.*;

public class Article_ShareToFacebook {

    public static void main(String[] args) {
        Log.testDescription = "Check that an article can be shared to Facebook";
        Log.createLogAndReport("Article_ShareToFacebook");
        AUT aut = new AUT(args);

        try {
            Log.step("***Step 1 - Navigate to Homepage***");
            aut.maximizeScreen();
            aut.navigate(Config.getHomepage());

            Log.step("***Step 2 - Search for some text and click on the first result***");
            String searchedText = "San Antonio Spurs";
            aut.homePage.searchForText(searchedText);
            aut.searchResultsPage.articleHeadline.click();

            Log.step("***Step 3 - Click on share button and check for Facebook Share option***");
            aut.articlePage.shareArticleButton.click();
            aut.articlePage.checkForSocialMediaOption("Facebook");

            Log.step("***Step 4 - Click on Facebook and check redirect***");
            aut.articlePage.clickOnSocialMediaOption("Facebook");

            String mainWindow = aut.getCurrentWindow();
            aut.switchToLastWindow();
            Assert.stringContains("New window stringContains ", SocialMediaData.facebookLoginMessage, aut.facebookShareLogin.content.getText());

            Log.step("***Step 5 - Close Facebook share window***");
            aut.closeWindow();
            aut.switchToWindow(mainWindow);
        }
        //in case any error occurs
        catch (Exception e) {
            Assert.finalResult = false;
            Log.error(e);
            e.printStackTrace();
        }
        //at the end of test execution
        finally {
            Assert.logFinalResult();
            aut.closeBrowser();
        }
    }
}
