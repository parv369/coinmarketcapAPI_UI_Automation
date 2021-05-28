package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class coinmarketcapLandingPage {
	
	private WebDriver driver;
	
	//1. By locators

	private By showRowsDDL = By.xpath("//body/div[@id='__next']//div[@class='cmc-homepage']/div[1]/div[2]/div[3]/div[1]/div");
	private By tableRows = By.xpath("//tbody/tr");
	private By filterButton = By.xpath("(//body/div[@id='__next']//button[text()='Filters'])[2]");
	private By addFilter = By.xpath("(//body[1]//ul[1]//button)[6]");
	private By subFilterMarketCap = By.xpath("(//body[1]//div[@class='popup-content ']//button)[2]");
	private By subFilterCapRange = By.xpath("//button[contains(text(),'$1B - $10B')]");
	private By subFilterPrice = By.xpath("(//body[1]//div[@class='popup-content ']//button)[3]");
	private By subFilterPriceRange = By.xpath("//button[contains(text(),'$101 - $1,000')]");
	private By applyFilter = By.xpath("//button[contains(text(),'Apply Filter')]");
	private By showResult = By.xpath("//button[contains(text(),'Show results')]");
	private By tableHeaders = By.xpath("//thead//tr/th");
	private By pricesList = By.xpath("//tbody/tr");
	private By filterValue = By.xpath("//button[contains(text(),'100')]");


	//2. Constructor of the Page Class
	public coinmarketcapLandingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//3. Page Actions
	
	public String getLoginPageTitle()	{
		return driver.getTitle();
	}

	public void selectShowRowValue() throws InterruptedException {
		Boolean Display = driver.findElement(showRowsDDL).isDisplayed();
		Thread.sleep(1000);
		WebElement element = driver.findElement(showRowsDDL);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);
		driver.findElement(filterValue).click();
}

	public void validateFilter() throws InterruptedException {
		Thread.sleep(10000);
		List<WebElement> headers = driver.findElements(tableHeaders);
		for(int i=1; i<=headers.size();i++){
			String hValue = null;
			hValue = driver.findElement(By.xpath("//thead//tr/th[" + i + "]")).getText();
			if(hValue.equalsIgnoreCase("Price")){
				List<WebElement> prices = driver.findElements(pricesList);
				for (int j=1;j<=prices.size();j++){
					String pValue = driver.findElement(By.xpath("//tbody/tr[" + j + "]/td[" + i + "]")).getText().replace("$","");
					Double price = Double.parseDouble(pValue);
					System.out.println("Price : "+price);
					assertTrue(price>=101 && price<=1000);
				}
			} else if(hValue.equalsIgnoreCase("Market Cap")){
				List<WebElement> prices = driver.findElements(pricesList);
				Long maxMarketCap = 10000000000L;
				Long minMarketCap = 1000000000L;
				for (int j=1;j<=prices.size();j++){
					String mValue = driver.findElement(By.xpath("//tbody/tr[" + j + "]/td[" + i + "]")).getText().replace("$","").replaceAll(",","");
					Long marketCap = Long.parseLong(mValue);
					assertTrue(marketCap>=minMarketCap && marketCap<=maxMarketCap);
					System.out.println("Market Cap : "+marketCap);
				}
			}
		}
	}

	public void applyFilter() throws InterruptedException {
		assertTrue(driver.findElement(filterButton).isDisplayed());
		Thread.sleep(1000);
		WebElement element = driver.findElement(filterButton);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		driver.findElement(addFilter).click();
		WebElement element1 = driver.findElement(subFilterMarketCap);
		JavascriptExecutor executor1 = (JavascriptExecutor)driver;
		executor1.executeScript("arguments[0].click();", element1);
		Thread.sleep(2000);
		driver.findElement(subFilterCapRange).click();
		driver.findElement(applyFilter).click();
		WebElement element2 = driver.findElement(subFilterPrice);
		JavascriptExecutor executor2 = (JavascriptExecutor)driver;
		executor1.executeScript("arguments[0].click();", element2);
		Thread.sleep(2000);
		driver.findElement(subFilterPriceRange).click();
		driver.findElement(applyFilter).click();
		driver.findElement(showResult).click();
	}

	public void validateRows(String expectedRows) throws InterruptedException {
		List<WebElement> actualNumberOfRows = driver.findElements(tableRows);
		System.out.println("Total number of rows dispalys is: "+actualNumberOfRows.size());
		assertEquals(expectedRows,String.valueOf(actualNumberOfRows.size()));
	}

	public String getHomePageTitle() {
		return driver.getTitle();
	}

}
