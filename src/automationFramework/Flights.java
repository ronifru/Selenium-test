package automationFramework;


import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Flights {
	
	public static void main (String []args) throws Exception{
		
		File file = new File("flights.txt");
		String row = "";
		WebDriver driver = new ChromeDriver();
		String URL = "http://www.iaa.gov.il/he-IL/airports/BenGurion/Pages/OnlineFlights.aspx";
		
		driver.get(URL);
		while(true){
			if (isPageLoaded(driver)){
				FileWriter fw = new FileWriter("flights.txt", false);
				WebElement baseTable = driver.findElement(By.className("generalTable"));
				List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
				for (int i=1; i < tableRows.size(); i++){
					List<WebElement> rowCells = tableRows.get(i).findElements(By.tagName("td"));
					fw.write("flight number: " + rowCells.get(2).getText() + " from: " + rowCells.get(3).getText() + " scheduled: " + rowCells.get(4).getText() +
							" updated: " +  rowCells.get(5).getText() + " terminal: " + rowCells.get(6).getText() + " status: " + rowCells.get(7).getText() + "\n");
					fw.flush();
				}
				Thread.sleep(60000*5);
				driver.navigate().refresh();	
			}
			else {
				Thread.sleep(1000);
			}
		}
	}
	
	public static boolean isPageLoaded (WebDriver driver){
		return driver.findElements(By.className("odd")).size() > 0; 
	}
}
