package objectTypes;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Log;

public class Table extends CommonObject {

	//=====================Constructor=====================
	public Table(By selector) {
		super(selector);
		// TODO Auto-generated constructor stub
	}
	
	//=======================Methods=======================
	
	/**
	 * Returns the WebElement representing the cell positioned at the indicated location by parameters
	 * @param row
	 * @param column
	 * @return WebElement
	 */
	public WebElement getCell(int row, int column){
		return findSelf().findElement(By.cssSelector("tr:nth-of-type("+row+") td:nth-of-type("+column+")"));
	}
	
	
	/**
	 * Returns the text contained by the cell positioned at the indicated location by parameters
	 * @param row
	 * @param column
	 * @return String
	 */
	public String getCellText(int row, int column){
		return getCell(row, column).getText();
	}
	
	/**
	 * Returns the column's header text indicated by parameter
	 * @param column
	 * @return String
	 */
	public String getHeaderText(int column){
		WebElement headerCell = findSelf().findElement(By.cssSelector("th:nth-of-type("+column+")"));
		return headerCell.getText();
	}
	
	/**
	 * Clicks on the center of the cell at index provided by parameters
	 * First cell 1,1
	 * @param row
	 * @param column
	 */
	public void clickCell(int row, int column){
		getCell(row, column).click();
		Log.action("Click on row "+row +" column "+column+" of table "+selector.toString());
	}
	
}
