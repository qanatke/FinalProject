package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getUserMenu() {
		return this.driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div[2]/div[2]/ul/li/a"));
	}

	public WebElement getMyAccountBtn() {
		return this.driver.findElement(By.xpath("//a[@href='/member']"));
	}

	public WebElement getLogoutBtn() {
		return this.driver.findElement(By.xpath("//a[@href='/guest-user/logout']"));
	}

	public void userLogout() {
		getUserMenu().click();
		getLogoutBtn().click();
	}

}
