package tests;

import org.testng.Assert;
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
		Assert.assertEquals(notification.getMsg(), "Login Successfull", "[ERROR] Login failed.");

		this.driver.navigate().to(baseUrl + "/member/profile");

		profilePage.updateProfileInfo("ime", "prezime", "Some Adress", "7418-529", "17935", "United Kingdom",
				"Northants", "Brackley");
		Assert.assertEquals(notification.getMsg(), "Setup Successful", "[ERROR] Setup failed.");

		// waiting for update data
		Thread.sleep(2000);

		authPage.userLogout();
		Assert.assertEquals(notification.getMsg(), "Logout Successfull!", "[ERROR] Logout failed.");

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
		Assert.assertEquals(notification.getMsg(), "Login Successfull", "[ERROR] Login failed.");

		this.driver.navigate().to(baseUrl + "/member/profile");

		profilePage.uploadProfilePhoto(pathToFile);
		Assert.assertEquals(notification.getMsg(), "Profile Image Uploaded Successfully", "[ERROR] Profile image upload failed.");
		notification.waitToDisappear();

		profilePage.removeProfilePhoto();
		Assert.assertEquals(notification.getMsg(), "Profile Image Deleted Successfully", "[ERROR] Profile image delete failed.");
		notification.waitToDisappear();

		authPage.userLogout();
		Assert.assertEquals(notification.getMsg(), "Logout Successfull!", "[ERROR] Logout failed.");
		notification.waitToDisappear();

	}

}
