package automationFramework;


import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.jboss.netty.util.internal.SystemPropertyUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class Flights {
	
	public static void main (String []args) throws Exception{
		
		FileWriter fw = new FileWriter("flights.txt", false);
		
		WebDriver driver = new ChromeDriver();
		String URL = "http://www.iaa.gov.il/he-IL/airports/BenGurion/Pages/OnlineFlights.aspx";
		
		driver.get(URL);
		while(true){
			if (isPageLoaded(driver)){
				FlightsTable flightsTable = PageFactory.initElements(driver, FlightsTable.class);
				fw.write(flightsTable.getLine());
				fw.flush();
				Thread.sleep(60000*5);
				driver.navigate().refresh();	
			}else {
				Thread.sleep(1000);
			}
		}
	}
	
	public static boolean isPageLoaded (WebDriver driver){
		return driver.findElements(By.className("odd")).size() > 0; 
	}
}
