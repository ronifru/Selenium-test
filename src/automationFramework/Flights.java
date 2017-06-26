package automationFramework;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Flights {
	
	public static void main (String []args) throws IOException{
		
		File file = new File("flights.txt");
		file.createNewFile();
		FileWriter fw = new FileWriter(file);
		String row = "";
		
		WebDriver driver = new ChromeDriver();
		String URL = "http://www.iaa.gov.il/he-IL/airports/BenGurion/Pages/OnlineFlights.aspx";
		driver.get(URL);
		
		WebElement baseTable = driver.findElement(By.className("generalTable"));
		List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
		for (int i=1; i < tableRows.size(); i++){
			row = tableRows.get(i).getText();
			fw.write(row);
			fw.flush();
			System.out.println(row);
			
		}
		
	}

}
