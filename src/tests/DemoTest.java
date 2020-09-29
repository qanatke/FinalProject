package tests;

import org.testng.annotations.Test;

import pages.LocationPopupPage;
import pages.LoginPage;
import pages.NotificationSistemPage;
import pages.ProfilePage;

public class DemoTest extends BasicTest {
	
	@Test
	public void locationTest () throws Exception {
		this.driver.navigate().to("http://demo.yo-meals.com/guest-user/login-form");
		LocationPopupPage lpp = new LocationPopupPage(driver, js, waiter);
		LoginPage lp = new LoginPage(driver, js, waiter);
		NotificationSistemPage nsp = new NotificationSistemPage(driver, js, waiter);
		ProfilePage pp = new ProfilePage(driver, js, waiter);
		
		Thread.sleep(2000);
		
//		lpp.setLocation(locationName);
		lpp.closeLocationForm();
		
		Thread.sleep(2000);
		lp.login(mail, pass);
		
//		System.out.println(nsp.getMsg());
//		nsp.wait2disappear();
		
		this.driver.navigate().to("http://demo.yo-meals.com/member/profile");
		Thread.sleep(2000);
		pp.updateProfileInfo("ime", "prezime", "adresa", "1236-9547", "4659", "India", "Bihar", "Ara");
		
		pp.uploadProfilePhoto(path2file);
		
	}

}
