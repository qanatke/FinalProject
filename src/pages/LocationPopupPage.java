package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasicPage {
	
	public LocationPopupPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getLocationForm () {
		return this.driver.findElement(By.xpath("//*[@id='header']/div[1]/div/div/div[1]/div/a"));
	}
	
	public WebElement getLocationFormButton () {
		return this.driver.findElement(By.xpath("//*[@id='location-popup']/div/div/div/div/a"));
	}
	
	private WebElement getKeyword() {
		return this.driver.findElement(By.xpath("//*[@id='locality_keyword']"));
	}
	
	private WebElement getLocationItem (String locationName) {
		return this.driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}
	
	private WebElement getLocationInput() {
		return this.driver.findElement(By.xpath("//*[@id='location_id']"));
	}
	
	private WebElement getSubmit () {
		return this.driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}
	
	public void openLocationForm () {
		getLocationForm().click();
	}
	
	public void setLocation (String locationName) {
		getKeyword().click();
		
		String locationValue = getLocationItem(locationName).getAttribute("data-value");
		
		js.executeScript("arguments[0].value=arguments[1]", getLocationInput(), locationValue);
		js.executeScript("arguments[0].click()", getSubmit());	
	}
	
	public void closeLocationForm () {
		getLocationFormButton().click();
	}
	

}
