package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getLoginForm() {
		return this.driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div[2]/div[2]/ul/li[2]/a"));
	}

	public WebElement getEmailInput() {
		return this.driver.findElement(By.xpath("//*[@id='frm_fat_id_frmLogin']/fieldset[1]/input"));
	}

	public WebElement getPassInput() {
		return this.driver.findElement(By.xpath("//*[@id='frm_fat_id_frmLogin']/fieldset[2]/input"));
	}

	public WebElement getLoginButton() {
		return this.driver.findElement(By.xpath("//*[@id='frm_fat_id_frmLogin']/fieldset[4]/input"));
	}

	public void login(String mail, String pass) {
		getEmailInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
		getEmailInput().sendKeys(mail);

		getPassInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
		getPassInput().sendKeys(pass);

		getLoginButton().click();
	}

}
