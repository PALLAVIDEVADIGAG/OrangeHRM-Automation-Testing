package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

	private static  WebDriver driver;

	public static WebDriver setup() {

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		return driver;
	}

	public static void close() {

		if (driver != null) {
            driver.quit();
        }
	}

}
