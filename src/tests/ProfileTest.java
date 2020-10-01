package tests;

import org.testng.annotations.Test;

import pages.AuthPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.NotificationSistemPage;
import pages.ProfilePage;

public class ProfileTest extends BasicTest {

	@Test(priority = 5)
	public void editProfileTest() throws Exception {

		LocationPopupPage locationPopupPage = new LocationPopupPage(this.driver, this.js, this.waiter);
		LoginPage loginPage = new LoginPage(this.driver, this.js, this.waiter);
		NotificationSistemPage notification = new NotificationSistemPage(this.driver, this.js, this.waiter);
		ProfilePage profilePage = new ProfilePage(this.driver, this.js, this.waiter);
		AuthPage authPage = new AuthPage(this.driver, this.js, this.waiter);

		this.driver.navigate().to(baseUrl + "/guest-user/login-form");

		locationPopupPage.hidePopup();

		loginPage.login(this.mail, this.pass);
		softAssert.assertEquals(notification.getMsg(), "Login Successfull");

		this.driver.navigate().to(baseUrl + "/member/profile");

		profilePage.updateProfileInfo("Natalia", "Peychev", "Some Adress", "7418-529", "17935", "United Kingdom",
				"Northants", "Brackley");
		// waiting to "processing..." msg to change
		Thread.sleep(500);
		softAssert.assertEquals(notification.getMsg(), "Setup Successful");

		// waiting for update data
		Thread.sleep(2000);

		authPage.userLogout();
		softAssert.assertEquals(notification.getMsg(), "Logout Successfull!");

		softAssert.assertAll();
		
	}

	@Test(priority = 10)
	public void changeProfileImageTest() throws Exception {

		LocationPopupPage locationPopupPage = new LocationPopupPage(this.driver, this.js, this.waiter);
		LoginPage loginPage = new LoginPage(this.driver, this.js, this.waiter);
		NotificationSistemPage notification = new NotificationSistemPage(this.driver, this.js, this.waiter);
		ProfilePage profilePage = new ProfilePage(this.driver, this.js, this.waiter);
		AuthPage authPage = new AuthPage(this.driver, this.js, this.waiter);

		this.driver.navigate().to(baseUrl + "/guest-user/login-form");

		locationPopupPage.hidePopup();

		loginPage.login(this.mail, this.pass);
		softAssert.assertEquals(notification.getMsg(), "Login Successfull");

		this.driver.navigate().to(baseUrl + "/member/profile");

		profilePage.uploadProfilePhoto(pathToFile);
		softAssert.assertEquals(notification.getMsg(), "Profile Image Uploaded Successfully");
		notification.waitToDisappear();

		profilePage.removeProfilePhoto();
		// waiting to "processing..." msg to change
		Thread.sleep(500);
		softAssert.assertEquals(notification.getMsg(), "Profile Image Deleted Successfully");
		notification.waitToDisappear();

		authPage.userLogout();
		softAssert.assertEquals(notification.getMsg(), "Logout Successfull!");
		notification.waitToDisappear();

		softAssert.assertAll();

	}

}
