package Test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.AdminPage;
import Pages.LoginPage;
import Utilities.DriverFactory;

public class AdminTest {

	WebDriver driver;
	LoginPage login;
	AdminPage admin;

	private static final String USERNAME = "Admin";
	private static final String PASSWORD = "admin123";

	@BeforeMethod
	public void setup() {

		driver = DriverFactory.setup();

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		login = new LoginPage(driver);
		admin = new AdminPage(driver);

		login.validLogin(USERNAME, PASSWORD);
	}

	@AfterMethod
	public void tearDown() {

		DriverFactory.close();
	}
	/*
	 * @Test public void verifyAdminMenuisDisplayed() {
	 * 
	 * Assert.assertTrue(admin.isDisplayAdminMenu(), "Admin Menu is not displayed");
	 * 
	 * }
	 * 
	 * @Test public void verifyOpenAdminMenu() { admin.openAdminMenu();
	 * 
	 * 
	 * Assert.assertTrue(admin.getUrl().contains("/admin/viewSystemUsers"),
	 * "Admin page is not opened."); }
	 * 
	 * @Test public void verifyUserManagementDisplayed() { admin.openAdminMenu();
	 * Assert.assertTrue(admin.isUserManagementDisplayed(),
	 * "user managemnet is not dispalyed"); }
	 */

	@Test
	public void verifyDisplayAddButton() {
		admin.openAdminMenu();
		Assert.assertTrue(admin.isDisplayAddButton(), "add button is not dispalyed");
	}

	@Test
	public void verifyOpenAddUserpage() {
		admin.openAdminMenu();
		admin.openAddUserpage();

		Assert.assertTrue(admin.getUrl().contains("/admin/saveSystemUser"), "Add User page is not opened.");
	}

	@Test
	public void verifyAddUserFields() {
		admin.openAdminMenu();
		admin.openAddUserpage();

		Assert.assertTrue(admin.isUserRoleDisplayed(), "user role field is not dispalyed");
		Assert.assertTrue(admin.isEmployeeNameDisplayed(), "employee nmae field is not dispalyed");
		Assert.assertTrue(admin.isStatusDisplayed(), "status field is not dispalyed");
		Assert.assertTrue(admin.isUsernameDisplayed(), "user name field is not dispalyed");
		Assert.assertTrue(admin.isPasswordDisplayed(), "password field is not dispalyed");
		Assert.assertTrue(admin.isDisplayConfirmPassword(), "confirm password field is not dispalyed");
		Assert.assertTrue(admin.isDisplayCancelButton(), "cancel button is not dispalyed");
		Assert.assertTrue(admin.isSaveButtonDisplayed(), "save button is not dispalyed");
	}
}
