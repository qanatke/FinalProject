package pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getEditProfileBtn() {
		return this.driver.findElement(By.xpath("//*[@id='body']/div/div/div/div[2]/div/div/div[2]/a[2]"));
	}

	public WebElement getFNameInput() {
		return this.driver.findElement(By.xpath("//input[@name='user_first_name']"));
	}

	public WebElement getLNameInput() {
		return this.driver.findElement(By.xpath("//input[@name='user_last_name']"));
	}

	public WebElement getAddressInput() {
		return this.driver.findElement(By.xpath("//input[@name='user_address']"));
	}

	public WebElement getPhoneInput() {
		return this.driver.findElement(By.xpath("//input[@name='user_phone']"));
	}

	public WebElement getZipCodeInput() {
		return this.driver.findElement(By.xpath("//input[@name='user_zip']"));
	}

	public Select getCountrySelect() {
		Select country = new Select(this.driver.findElement(By.xpath("//select[@id='user_country_id']")));
		return country;
	}

	public Select getStateSelect() {
		Select state = new Select(this.driver.findElement(By.xpath("//select[@id='user_state_id']")));
		return state;
	}

	public Select getCitySelect() {
		Select city = new Select(this.driver.findElement(By.xpath("//select[@id='user_city']")));
		return city;
	}

	public WebElement getSubmitBtn() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfoFrm']/div[5]/div/fieldset/input"));
	}

	public WebElement getPhotoUpload() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfo']/div/div[1]/div/a"));
	}

	public void uploadProfilePhoto(String path2file) throws Exception {
		js.executeScript("arguments[0].click()", getPhotoUpload());
		String imgPath = new File(path2file).getCanonicalPath();
		getPhotoUpload().sendKeys(imgPath);
		Thread.sleep(1500);
	}

	public WebElement getRemovePhotoBtn() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfo']/div/div[1]/div/a[2]/i"));
	}

	public void removeProfilePhoto() {
		js.executeScript("arguments[0].click()", getRemovePhotoBtn());
	}

	public void updateProfileInfo(String fname, String lname, String address, String phone, String zipcode,
			String country, String state, String city) throws Exception {
		getEditProfileBtn().click();
		Thread.sleep(2000);

		getFNameInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
		getFNameInput().sendKeys(fname);

		getLNameInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
		getLNameInput().sendKeys(lname);

		getAddressInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
		getAddressInput().sendKeys(address);

		getPhoneInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
		getPhoneInput().sendKeys(phone);

		getZipCodeInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
		getZipCodeInput().sendKeys(zipcode);

		getCountrySelect().selectByVisibleText(country);
		Thread.sleep(2000);

		getStateSelect().selectByVisibleText(state);
		Thread.sleep(2000);

		getCitySelect().selectByVisibleText(city);

		getSubmitBtn().click();
	}
}
