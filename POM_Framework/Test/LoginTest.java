package Test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.LoginPage;

public class LoginTest extends BaseTest {

	LoginPage login;

	private static final String USERNAME = "Admin";
	private static final String PASSWORD = "admin123";

	// =========================================================
	// Setup
	// =========================================================

	@BeforeMethod
	public void initializePage() {

		System.out.println("===== LoginTest.initializePage() =====");

		login = new LoginPage(driver);
	}

	// =========================================================
	// TC-001 - Verify login with valid credentials
	// =========================================================

	@Test(description = "Verify login with valid credentials")
	public void testValidLogin() {

		login.login(USERNAME, PASSWORD);

		Assert.assertTrue(login.getCurrentUrl().contains("dashboard"), "User was not redirected to Dashboard.");
	}

	// =========================================================
	// TC-002 - Verify login with invalid credentials
	// =========================================================

	@Test(dataProvider = "invalidLoginData", description = "Verify login with invalid credentials")
	public void testInvalidLogin(String user, String pwd) {

		login.login(user, pwd);

		String error = login.getErrorMsg();

		Assert.assertEquals(error, "Invalid credentials", "Invalid credentials error message was not displayed.");
	}

	// =========================================================
	// TC-003 - Verify empty field validation
	// =========================================================

	@Test(description = "Verify validation message for empty username and password")
	public void testEmptyFieldValidation() {

		login.login("", "");

		String message = login.getRequiredMessage();

		Assert.assertEquals(message, "Required", "Required validation message was not displayed.");
	}

	// =========================================================
	// TC-004 - Verify username with spaces
	// =========================================================

	@Test(description = "Verify login with username containing spaces")
	public void testUsernameWithSpaces() {

		login.login(" Admin ", PASSWORD);

		Assert.assertEquals(login.getErrorMsg(), "Invalid credentials",
				"Expected Invalid credentials message was not displayed.");
	}

	// =========================================================
	// TC-005 - Verify username case sensitivity
	// =========================================================

	@Test(description = "Verify username case sensitivity")
	public void testUsernameCaseSensitivity() {

		login.login("admin", PASSWORD);

		Assert.assertTrue(login.getCurrentUrl().contains("dashboard"), "User was not redirected to Dashboard.");
	}

	// =========================================================
	// TC-006 - Verify logout
	// =========================================================

	@Test(description = "Verify user can logout successfully")
	public void testLogout() {

		login.login(USERNAME, PASSWORD);

		login.logout();

		Assert.assertTrue(login.getCurrentUrl().contains("login"),
				"User was not redirected to Login page after logout.");
	}

	// =========================================================
	// Data Provider - Invalid Login
	// =========================================================

	@DataProvider(name = "invalidLoginData")
	public Object[][] invalidData() {

		return new Object[][] {

				{ "Admin", "wrongpwd" }, { "wronguser", "admin123" }, { "abc", "xyz" }

		};
	}
}