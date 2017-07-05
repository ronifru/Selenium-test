package automationFramework;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.google.common.collect.Ordering;
import com.steadystate.css.parser.TokenMgrError;

public class FlightsTable {

	By flightTimeList = By.className("FlightTime ");
	By sortFlightBySchedulerTime = By.cssSelector("span[data-sortexpression='SchedulerTime']");
	By sortFilghtBySchedualerTimetIsUp = By.cssSelector("span.divSortTable.sortCurrent.sortUp");

	By tableRow = By.cssSelector("tbody > tr");

	WebDriver driver;

	/**
	 * constructor
	 * @param driver
	 */
	public FlightsTable(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Clicks the sorting button of scheduler time
	 * 
	 * @return
	 * @throws Exception
	 */
	public FlightsTable clickSortingBySchedulerTime() throws Exception {
		driver.findElement(sortFlightBySchedulerTime).click();
		Thread.sleep(1500);
		return this;
	}
	
	/**
	 * check if sorted properly
	 * @return
	 * @throws Exception
	 */
	public FlightsTable checkFlightSchedualerTimeInOrder() throws Exception {

		if (!(driver.findElements(sortFilghtBySchedualerTimetIsUp).size() > 0)) {
			clickSortingBySchedulerTime();
		}

		List<WebElement> listOfSortedFlightSchedualerTime = driver.findElements(flightTimeList);
		List<String> flightTime = new ArrayList<>();
		for (WebElement flight : listOfSortedFlightSchedualerTime)
			flightTime.add(flight.getText());
		Assert.assertTrue("The flight time is not in order.", Ordering.natural().isOrdered(flightTime));
		return this;
	}
	
	/**
	 * create flights details string
	 * @return
	 * @throws Exception
	 */
	public FlightsTable getAllFlightsDetails() throws Exception {
		List<WebElement> listOfAllFlights = driver.findElements(tableRow);
		String str = "";
		for (WebElement row : listOfAllFlights) {
			str += row.getText().replaceAll("\n", " ") + "\n";
		}
		printFlightDetailsToFile(str);
		return this;
	}
	
	/**
	 * write flights details {@link TokenMgrError} a file
	 * @param flightList
	 * @return
	 * @throws Exception
	 */
	private FlightsTable printFlightDetailsToFile(String flightList) throws Exception {
		FileWriter fw = new FileWriter("flights.txt", false);
		fw.write(flightList);
		fw.flush();
		return this;
	}

}
