package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPage {

	WebDriver driver;
	WebDriverWait wait;

	public AdminPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	By adminMenu = By.xpath("//span[text() ='Admin']");
	By userManagement = By.xpath("//span[text()='User Management ']");
	By addButton = By.xpath("//button[text()=' Add ']");
	By userRoleText = By.xpath("//label[normalize-space()='User Role']");
	By employeeNameText = By.xpath("//label[normalize-space()='Employee Name']");
	By statusText = By.xpath("//label[normalize-space()='Status']");
	By userNametext = By.xpath("//label[normalize-space()='Username']");
	By passwordText = By.xpath("//label[normalize-space()='Password']");
	By confirmPasswordText = By.xpath("//label[normalize-space() = 'Confirm Password']");
	By cancelButton = By.xpath("//button[normalize-space()='Cancel']");
	By saveButton = By.xpath("//button[normalize-space()='Save']");

	public String getUrl() {
		return driver.getCurrentUrl();
	}

	public boolean isDisplayAdminMenu() {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(adminMenu)).isDisplayed();
	}

	public void openAdminMenu() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(adminMenu)).click();

	}
	
	public boolean isUserManagementDisplayed() {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(userManagement)).isDisplayed();

	}
	
	public boolean isDisplayAddButton() {
		
		return wait.until(ExpectedConditions.visibilityOfElementLocated(addButton)).isDisplayed();
	}
	
	public void openAddUserpage() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(addButton)).click();

	}
	
public boolean isUserRoleDisplayed() {
		
		return wait.until(ExpectedConditions.visibilityOfElementLocated(userRoleText)).isDisplayed();
	}

public boolean isEmployeeNameDisplayed() {
	
	return wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameText)).isDisplayed();
}

public boolean isStatusDisplayed() {
	
	return wait.until(ExpectedConditions.visibilityOfElementLocated(statusText)).isDisplayed();
}

public boolean isUsernameDisplayed() {
	
	return wait.until(ExpectedConditions.visibilityOfElementLocated(userNametext)).isDisplayed();
}

public boolean isPasswordDisplayed() {
	
	return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordText)).isDisplayed();
}

public boolean isDisplayConfirmPassword() {
	
	return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordText)).isDisplayed();
}
public boolean isDisplayCancelButton() {
	
	return wait.until(ExpectedConditions.visibilityOfElementLocated(cancelButton)).isDisplayed();
}

public boolean isSaveButtonDisplayed() {
	
	return wait.until(ExpectedConditions.visibilityOfElementLocated(saveButton)).isDisplayed();
}
}

