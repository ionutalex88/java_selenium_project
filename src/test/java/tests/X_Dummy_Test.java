package tests;

import aut.AUT;
import utilities.Config;
import utilities.Log;
import utilities.Assert;

public class X_Dummy_Test {

    public static void main(String[] args) {
        Log.createLogAndReport("X_Dummy_Test");
        AUT aut = new AUT(args);
        try {
            aut.navigate(Config.getHomepage());
            aut.maximizeScreen();

            Log.step("********Step 1*********** Fac si eu niste verificari");
            // TODO: Insert actions here
            Assert.valuesMatch("Bla bla", 2, 3);
            Assert.valuesMatch("Portugalia portugalia...Nananana", 3.14, 3.14);
            Assert.valuesMatch("Nebunia lu Salam", 2, 2);

            Log.step("********Step 2*********** O ard la caterinca");
            String cantec = "Ole ole ola cine tine cu oaia...";
            Assert.stringsMatch("Cantec", "Ole ole ola cine tine cu oaia...", cantec);

            //huihi
            Assert.valuesMatch("fsafsa", 0, 0);


        } catch (Exception e) {
            Assert.finalResult = false;
            Log.error(e);
            e.printStackTrace();
        } finally {
            Assert.logFinalResult();
            aut.closeBrowser();
        }

    }

}
