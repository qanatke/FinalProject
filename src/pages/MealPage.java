package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getFavouriteBtn() {
		return this.driver.findElement(By.xpath("//*[contains(@class, 'favourite')]"));
	}

	public WebElement getQuantityInput() {
		return this.driver.findElement(By.xpath("//input[@type='number']"));
	}

	public WebElement getAdd2CartBtn() {
		return this.driver.findElement(By.xpath("//*[contains(@class, 'btn--primary')]"));
	}

	public void addMeal2cart(int quantity) {
		getQuantityInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
		getQuantityInput().sendKeys(quantity + "");
		getAdd2CartBtn().click();
	}

	public void addMeal2favourites() {
		getFavouriteBtn().click();
	}

}
