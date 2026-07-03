package Pages;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;
	WebDriverWait wait;

	By username = By.name("username");
	By password = By.name("password");
	By loginButton = By.xpath("//button[@type='submit']");
	
	By userDropDown = By.className("oxd-userdropdown-name");
	By logout = By.linkText("Logout");

	By errorMessage = By.xpath("//p[text()='Invalid credentials']");
	By errorMessage2 = By.xpath("//span[text()='Required']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	public void validLogin(String user, String pwd) {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(username));

		driver.findElement(username).sendKeys(user);

		driver.findElement(password).sendKeys(pwd);

		driver.findElement(loginButton).click();

		wait.until(ExpectedConditions.urlContains("dashboard"));
	}

	public void logout() {

		wait.until(ExpectedConditions.elementToBeClickable(userDropDown));

		driver.findElement(userDropDown).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(logout));

		driver.findElement(logout).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(username));
	}

	public void invalidLogin(String user, String pwd) {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(username));

		driver.findElement(username).sendKeys(user);

		driver.findElement(password).sendKeys(pwd);

		driver.findElement(loginButton).click();

	}
	
	public String getErrormsg() {

	    wait.until(ExpectedConditions.presenceOfElementLocated(errorMessage));

	    return driver.findElement(errorMessage).getText();

	}

	public void emptyFieldValidation(String user, String pwd) {

		driver.findElement(username).sendKeys(user);

		driver.findElement(password).sendKeys(pwd);

		driver.findElement(loginButton).click();

		String message = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage2)).getText();

		if (message.contains("Required")) {
			System.out.println("emptyFieldValidation Test Passed");
		} else {
			System.out.println("emptyFieldValidation Test Failed");
		}
	}

	public void usernameValidation(String user, String pwd) {

		driver.findElement(username).sendKeys(user);

		driver.findElement(password).sendKeys(pwd);

		driver.findElement(loginButton).click();

		try {

			String message = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();

			if (message.equals("Invalid credentials")) {
				System.out.println("usernameValidation Test Passed");
			}
		} catch (Exception e) {

			if (driver.getCurrentUrl().contains("dashboard")) {
				System.out.println("usernameValidation Test Passed (Username spaces ignored)");
				logout();
			} else {
				System.out.println("usernameValidation Test Failed");
			}
		}
	}

	public void passwordValidation(String user, String pwd) {

		driver.findElement(username).sendKeys(user);

		driver.findElement(password).sendKeys(pwd);

		driver.findElement(loginButton).click();

		String message = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();

		if (message.contains("Invalid credentials")) {
			System.out.println("passwordValidation Test Passed");
		} else {
			System.out.println("passwordValidation Test Failed");
		}
	}

	public void usernameCasesensitivity(String user, String pwd) {

		driver.findElement(username).sendKeys(user);

		driver.findElement(password).sendKeys(pwd);

		driver.findElement(loginButton).click();

		try {

			String message = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();

			if (message.equals("Invalid credentials")) {
				System.out.println("TC-012 Passed");
			}

		}

		catch (Exception e) {

			if (driver.getCurrentUrl().contains("dashboard")) {
				System.out.println("TC-012 Passed (Case ignored)");

				logout();
			}

			else {
				System.out.println("TC-012 Failed");
			}
		}
	}

	public void verifyValidLogin(String user, String pwd) {

		driver.findElement(username).sendKeys(user);

		driver.findElement(password).sendKeys(pwd);

		driver.findElement(loginButton).click();

		String currentURL = driver.getCurrentUrl();

		if (currentURL.contains("dashboard")) {
			System.out.println("verifyValidLogin Passed");
		} else {
			System.out.println("verifyValidLogin Failed");
		}
	}

}
