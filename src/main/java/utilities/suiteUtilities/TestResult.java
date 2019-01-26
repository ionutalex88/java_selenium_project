package utilities.suiteUtilities;


public class TestResult {
	
	public boolean finalResult;
	public String endDate;
	public String endTime;
	public String testName;
	
	public TestResult(boolean finalResult, String endDate, String endTime, String testName){
		this.endDate=endDate;
		this.endTime=endTime;
		this.testName=testName;
		this.finalResult=finalResult;
	}
	
}
