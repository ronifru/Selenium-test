package automationFramework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FlightsTable {

	@FindBy(css= "table.generalTable > tbody > tr")
	List<WebElement> tableRows;
	
	@FindBy(className = "FlightNum ")
	List<WebElement> flightsNumbers;
	
	@FindBy(className = "FlightFrom  ")
	List<WebElement> flightsFrom;
	
	@FindBy(className = "FlightTime  ")
	List<WebElement> flightTimes;
	
	@FindBy(className = "finalTime   ")
	List<WebElement> finalTimes;
	
	@FindBy(className = "localTerminal   ")
	List<WebElement> localTerminals;
	
	@FindBy(className = "status   ")
	List<WebElement> statusList;
	
	WebDriver driver;
	
	public FlightsTable(WebDriver driver){
		this.driver = driver;
	}
	
	public String toString(){
		
		String str="";
		
		for (WebElement row : tableRows ){
				List<WebElement> rowCells = row.findElements(By.tagName("td"));
				str += ("flight number: " + rowCells.get(2).getText() + " from: " + rowCells.get(3).getText() + " scheduled: " + rowCells.get(4).getText() +
						" updated: " +  rowCells.get(5).getText() + " terminal: " + rowCells.get(6).getText() + " status: " + rowCells.get(7).getText() + "\n");
		}
		
		return str;
	}
	
	public String getLine(){
		
		String str = "";
		
		for (int i = 0; i < flightsFrom.size(); i++){
			str += ("flight number: " + flightsNumbers.get(i).getText() + " from: " + flightsFrom.get(i).getText() + " scheduled: " + flightTimes.get(i).getText() +
					" updated: " +  finalTimes.get(i).getText() + " terminal: " + localTerminals.get(i).getText() + " status: " + statusList.get(i).getText() + "\n");
			
		}
		
		return str;
	}
}
