package automationFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Flights {

	public static void main(String[] args) throws Exception {

		WebDriver driver = new ChromeDriver();
		String URL = "http://www.iaa.gov.il/he-IL/airports/BenGurion/Pages/OnlineFlights.aspx";

		driver.get(URL);
		while (true) {
			if (isPageLoaded(driver)) {
				FlightsTable tablePage = new FlightsTable(driver);
				// write the flight into a txt file
				tablePage.clickSortingBySchedulerTime().checkFlightSchedualerTimeInOrder().getAllFlightsDetails();
				System.out.println("done");
				
				//wait 5 minutes
				Thread.sleep(60000*5);
			} else {
				//wait 1 second for page to load
				Thread.sleep(1000);
			}

		}
	}

	public static boolean isPageLoaded(WebDriver driver) {
		return driver.findElements(By.className("even")).size() > 0;
	}
}
