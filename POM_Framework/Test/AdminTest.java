package Test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.AdminPage;
import Pages.LoginPage;
import Pages.PIMPage;
import Base.BaseTest;

public class AdminTest extends BaseTest {

	LoginPage login;
	AdminPage admin;
	PIMPage pim;

	private static final String USERNAME = "Admin";
	private static final String PASSWORD = "admin123";

	String empUserName = "avwergefwergwe24235";
	String emppassword = "sfgrdfrt@4354647";
	String confirmPassword = "sfgrdfrt@4354647";
	String employeeName = "bala kumar ravi";

	@BeforeMethod
	public void initializePage() {

		login = new LoginPage(driver);
		pim = new PIMPage(driver);
		admin = new AdminPage(driver);

		login.login(USERNAME, PASSWORD);
	}

	// =========================================================
	// TC-001 - Verify Admin menu is displayed
	// =========================================================

	@Test(description = "Verify Admin menu is displayed after login", groups = "smoke", enabled = false)
	public void verifyAdminMenuisDisplayed() {

		Assert.assertTrue(admin.isDisplayAdminMenu(), "Admin Menu is not displayed");
	}

	// =========================================================
	// TC-002 - Verify navigation to Admin
	// =========================================================

	@Test(description = "Verify user can navigate to Admin page", groups = "smoke", enabled = false)
	public void verifyClickAdmin() {
		admin.clickAdmin();

		Assert.assertTrue(admin.getUrl().contains("/admin/viewSystemUsers"), "Admin page is not opened.");
	}

	// =========================================================
	// TC-003 - Verify admin Table
	// =========================================================

	@Test(description = "Verify Admin table is displayed", groups = "smoke", enabled = false)
	public void verifyAdminTableDisplayed() {

		admin.clickAdmin();

		Assert.assertTrue(admin.isDisplayAdminTable(), "Admin table is not displayed.");
	}

	// =========================================================
	// TC-004 - Verify admin List
	// =========================================================

	@Test(description = "Verify admin List is displayed", groups = "smoke", enabled = false)
	public void verifyAdminListDisplayed() {

		admin.clickAdmin();

		Assert.assertTrue(admin.isDisplayedAdminList(), "Admin List is not displayed.");
	}

	// =========================================================
	// TC-005 - Verify add user fields
	// =========================================================

	@Test(description = "Verify add user fields", groups = "smoke", enabled = false)
	public void verifyAddUserFields() {
		admin.clickAdmin();
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

	// =========================================================
	// TC-006 - Verify add new user
	// =========================================================

	@Test(description = "Verify user can add new admin details Admin page", enabled = false)
	public void verifyUserCreation() {

		System.out.println("Returned Employee Name = " + employeeName);
		admin.clickAdmin();
		admin.openAddUserpage();
		admin.clickUserRoleDropDown();
		admin.selectAdmin();
		admin.enterEmployeeName(employeeName);
		admin.selectEmployeeSuggestion(employeeName);
		admin.clickStatus();
		admin.selectEnabled();

		admin.enterUsernamePassword(empUserName, emppassword);

		admin.confirmPassword(confirmPassword);
		admin.clickSave();

		Assert.assertTrue(admin.getUrl().contains("viewSystemUsers"), "User not added");
	}

	@Test(description = "Verify user can search added admin details", enabled = false)
	public void searchAddedUser() {
		admin.enterUsernamePassword(empUserName, emppassword);
		admin.clickUserRoleDropDown();
		admin.selectAdmin();
		admin.enterEmployeeName(employeeName);
		admin.selectEmployeeSuggestion(employeeName);
		admin.clickStatus();
		admin.selectEnabled();
		admin.clickSearch();
	}
}
