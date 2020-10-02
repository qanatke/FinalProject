package tests;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.LocationPopupPage;
import pages.MealPage;
import pages.SearchResultPage;

public class SearchTest extends BasicTest {

	@Test
	public void searchResultsTest() throws Exception {

		LocationPopupPage locationPopup = new LocationPopupPage(this.driver, this.js, this.waiter);
		MealPage mealPage = new MealPage(this.driver, this.js, this.waiter);
		SearchResultPage searchResults = new SearchResultPage(this.driver, this.js, this.waiter);
		SoftAssert softAssert = new SoftAssert();

		File meals = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(meals);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet mealsSearchheet = wb.getSheet("Meal Search Results");

		this.driver.navigate().to(baseUrl + "/meals");

		locationPopup.setLocation(locationName);
		// waiting for synchronization
		Thread.sleep(2000);

		for (int i = 1; i <= mealsSearchheet.getLastRowNum(); i++) {
			XSSFRow row = mealsSearchheet.getRow(i);

			String mealUrl = row.getCell(1).getStringCellValue();
			// waiting for synchronization
			Thread.sleep(2000);
			this.driver.navigate().to(mealUrl);

			locationPopup.getLocationForm().click();
			locationPopup.setLocation(row.getCell(0).getStringCellValue());

			softAssert.assertEquals(searchResults.mealsCount(), row.getCell(2).getNumericCellValue());

			for (int j = 3; j < mealsSearchheet.getRow(i).getCell(2).getNumericCellValue() + 3; j++) {
				String expected = mealsSearchheet.getRow(i).getCell(j).getStringCellValue();
				String actual = searchResults.mealNames().get(j - 3);
				softAssert.assertEquals(actual, expected, " [ERROR] Meal does not exist");
			}
		}
	}
}
