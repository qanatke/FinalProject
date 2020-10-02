package tests;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSistemPage;

public class MealItemTest extends BasicTest {

	@Test(priority = 5)
	public void addMealToCartTest() throws Exception {

		MealPage mealPage = new MealPage(this.driver, this.js, this.waiter);
		LocationPopupPage locationPopupPage = new LocationPopupPage(this.driver, this.js, this.waiter);
		NotificationSistemPage notification = new NotificationSistemPage(this.driver, this.js, this.waiter);

		this.driver.navigate().to(baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.hidePopup();

		mealPage.addMealToCart(3);

		Assert.assertTrue(notification.getMsg().contains("The Following Errors Occurred:"));
		Assert.assertTrue(notification.getMsg().contains("Please Select Location"));
		notification.waitToDisappear();

		locationPopupPage.getLocationForm().click();
		locationPopupPage.setLocation(locationName);

		// waiting for the location to be set
		Thread.sleep(1000);

		mealPage.addMealToCart(2);

		Assert.assertEquals(notification.getMsg(), "Meal Added To Cart");

	}

	@Test(priority = 10)
	public void addMealToFavouriteTest() throws Exception {

		MealPage mealPage = new MealPage(this.driver, this.js, this.waiter);
		LocationPopupPage locationPopupPage = new LocationPopupPage(this.driver, this.js, this.waiter);
		NotificationSistemPage notification = new NotificationSistemPage(this.driver, this.js, this.waiter);
		LoginPage loginPage = new LoginPage(this.driver, this.js, this.waiter);

		this.driver.navigate().to(baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.hidePopup();

		mealPage.addMealToFavourites();
		Assert.assertEquals(notification.getMsg(), "Please login first!");

		this.driver.navigate().to(baseUrl + "/guest-user/login-form");
		loginPage.login(mail, pass);

		this.driver.navigate().to(baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		mealPage.addMealToFavourites();

		Assert.assertEquals(notification.getMsg(), "Product has been added to your favorites.");

	}

	@Test(priority = 15)
	public void clearCartTest() throws Exception {

		LocationPopupPage locationPopupPage = new LocationPopupPage(this.driver, this.js, this.waiter);
		MealPage mealPage = new MealPage(this.driver, this.js, this.waiter);
		NotificationSistemPage notification = new NotificationSistemPage(this.driver, this.js, this.waiter);
		CartSummaryPage cartPage = new CartSummaryPage(this.driver, this.js, this.waiter);
		SoftAssert softAssert = new SoftAssert();

		File meals = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(meals);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet mealsSheet = wb.getSheet("Meals");

		this.driver.navigate().to(baseUrl + "/meals");
		locationPopupPage.setLocation(locationName);
		// waiting for synchronization
		Thread.sleep(2000);

		for (int i = 1; i <= mealsSheet.getLastRowNum(); i++) {
			XSSFRow row = mealsSheet.getRow(i);

			String mealUrl = row.getCell(0).getStringCellValue();
			this.driver.navigate().to(mealUrl);

			double quantity = row.getCell(1).getNumericCellValue();
			mealPage.addMealToCart(quantity);

			softAssert.assertEquals(notification.getMsg(), "Meal Added To Cart");

		}

		// waiting for synchronization
		Thread.sleep(1000);
		cartPage.clearAll();

		Assert.assertEquals(notification.getMsg(), "All meals removed from Cart successfully");
	}

}
