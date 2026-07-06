package Test;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import Pages.LoginPage;
import Utilities.DriverFactory;

public class LoginTest {

	WebDriver driver;
	LoginPage login;
	
	private static final String USERNAME = "Admin";
	private static final String PASSWORD = "admin123";

	@BeforeMethod
	public void setup() {

		driver = DriverFactory.setup();

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		login = new LoginPage(driver);

	}

	// TC-001 - Verify login with valid credentials
	@Test (description = "Verify login with valid credentials")
	public void testvalidLogin() {

		

		login.validLogin(USERNAME, PASSWORD);

		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));

	}
	
	@Test
	public void testlogout() {
	    

	    login.validLogin(USERNAME, PASSWORD);
	    login.logout();
	    
	    Assert.assertTrue(login.getCurrenturl().contains("login"));
	}
	
	// TC-002, TC-017
	@Test(dataProvider = "invalidLoginData")
	public void testinvalidLogin(String user, String pwd) {
		
		login.invalidLogin(user, pwd);
		String error = login.getErrormsg();
		Assert.assertEquals(error, "Invalid credentials");
	}
	

	
	@Test
	public void testemptyFieldValidation() {

	   

	    String message = login.emptyFieldValidation("", "");

	    Assert.assertEquals(message, "Required");
	}
	
	
	@Test
	public void testusernameWithSpacesTest() {

	   

	    login.invalidLogin(" Admin ", "admin123");

	    Assert.assertEquals(login.getErrormsg(), "Invalid credentials");
	}
	
	@Test
	public void testUsernameCaseInsensitivity() {

	    login.validLogin("admin", "admin123");

	    Assert.assertTrue(login.getCurrenturl().contains("dashboard"));
	}
	
	@DataProvider(name = "invalidLoginData")
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
