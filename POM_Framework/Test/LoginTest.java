package Test;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import Pages.LoginPage;
import Utilities.DriverFactory;

public class LoginTest {

	WebDriver driver;

	@BeforeMethod
	public void setup() {

		driver = DriverFactory.setup();

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

	}

	// TC-001
	@Test
	public void validLogin() {

		LoginPage login = new LoginPage(driver);

		login.validLogin("Admin", "admin123");

		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));

	}
	
	@Test
	public void logout() {
	    LoginPage login = new LoginPage(driver);

	    login.validLogin("Admin","admin123");
	    login.logout();
	}
	
	// TC-002, TC-017
	@Test(dataProvider = "invalidData")
	public void invalidLogin(String user, String pwd) {
		LoginPage login = new LoginPage(driver);
		login.invalidLogin(user, pwd);
		String error = login.getErrormsg();
		Assert.assertTrue(error.contains("Invalid credentials"));
	}
	
	@DataProvider(name = "invalidData")
    public Object[][] invalidData() {

        return new Object[][] {

            {"Admin", "wrongpwd"},
            {"wronguser", "admin123"},
            {"abc", "xyz"},

        };
    }

	@AfterMethod
	public void tearDown() {

		DriverFactory.close();

	}

}
