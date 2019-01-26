package tests;

import aut.AUT;
import utilities.Config;
import utilities.Log;
import utilities.Verify;

public class Test2 {

    public static void main(String[] args) {
        Log.createLogAndReport("Test2");
        AUT aut = new AUT(args);
        try {
            aut.navigate(Config.getHomepage());
            aut.maximizeScreen();

            Log.step("********Step 1*********** Fac si eu niste verificari");
            // TODO: Insert actions here
            Verify.compare("Bla bla", 2, 3);
            Verify.compare("Portugalia portugalia...Nananana", 3.14, 3.14);
            Verify.compare("Nebunia lu Salam", 2, 2);

            Log.step("********Step 2*********** O ard la caterinca");
            String cantec = "Ole ole ola cine tine cu oaia...";
            Verify.compare("Cantec", "Ole ole ola cine tine cu oaia...", cantec);

            //huihi
            Verify.compare("fsafsa", 0, 0);


        } catch (Exception e) {
            Verify.finalResult = false;
            Log.error(e);
            e.printStackTrace();
        } finally {
            Verify.logFinalResult();
            aut.closeBrowser();
        }

    }

}
