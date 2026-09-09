package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Base.BasePage;

public class LoginPage extends BasePage {

	// =========================================================
	// Locators
	// =========================================================

	// Login fields
	private final By username = By.name("username");

	private final By password = By.name("password");

	private final By loginButton = By.xpath("//button[@type='submit']");

	// Login validation messages
	private final By errorMessage = By.xpath("//p[text()='Invalid credentials']");

	private final By requiredMessage = By.xpath("//span[text()='Required']");

	// User menu and Logout
	private final By userDropDown = By.className("oxd-userdropdown-name");

	private final By logout = By.linkText("Logout");

	// =========================================================
	// Constructor
	// =========================================================

	public LoginPage(WebDriver driver) {

		super(driver);
	}

	// =========================================================
	// Login
	// =========================================================

	public void login(String user, String pwd) {

		System.out.println("Login page URL = " + driver.getCurrentUrl());

		wait.until(ExpectedConditions.visibilityOfElementLocated(username));

		driver.findElement(username).clear();
		driver.findElement(username).sendKeys(user);

		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(pwd);

		driver.findElement(loginButton).click();
	}

	// =========================================================
	// Login Validation Messages
	// =========================================================

	public String getErrorMsg() {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
	}

	public String getRequiredMessage() {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(requiredMessage)).getText();
	}

	// =========================================================
	// URL
	// =========================================================

	public String getCurrentUrl() {

		return driver.getCurrentUrl();
	}

	// =========================================================
	// Logout
	// =========================================================

	public void logout() {

		wait.until(ExpectedConditions.elementToBeClickable(userDropDown));

		driver.findElement(userDropDown).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(logout));

		driver.findElement(logout).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(username));
	}
}