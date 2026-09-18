package Base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Utilities.DriverFactory;

public class BaseTest {

	protected WebDriver driver;

	@BeforeMethod
	public void setup() {

		System.out.println("===== BaseTest.setup() =====");

		driver = DriverFactory.setup();

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		System.out.println(driver.getCurrentUrl());
	}

	@AfterMethod
	public void tearDown() {

		DriverFactory.close();
	}
}
