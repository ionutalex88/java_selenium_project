package testSuites;

import tests.ExampleTest;
import tests.Test2;
import tests.TestLogin_Valid;
import utilities.suiteUtilities.SuiteEditor;

public class ExampleSuite {

	public static void main(String[] args) {
		// Create the suite report file
		SuiteEditor.createNewTestSuite("Results");
		
		// Insert tests to be executed
		TestLogin_Valid.main(null);
		ExampleTest.main(null);
		Test2.main(null);

	}

}
