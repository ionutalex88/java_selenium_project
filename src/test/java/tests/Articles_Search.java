package tests;

import aut.AUT;
import utilities.*;

public class Articles_Search {

    public static void main(String[] args) {
        Log.testDescription = "Perform a search and check that all listed headlines contain the searched text";
        Log.createLogAndReport("Articles_Search");
        AUT aut = new AUT(args);

        try {
            Log.step("***Step 1 - Navigate to Homepage***");
            aut.maximizeScreen();
            aut.navigate(Config.getHomepage());

            Log.step("***Step 2 - Search for some words***");
            String searchedText = "San Antonio Spurs";
            aut.homePage.searchForText(searchedText);

            Log.step("***Step 3 - Check results tile***");
            aut.searchResultsPage.checkAllHeadlinesContainText(searchedText);
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
