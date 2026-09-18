package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Base.BasePage;

public class AdminPage extends BasePage {

	public AdminPage(WebDriver driver) {
		super(driver);
	}

	private final By adminMenu = By.xpath("//span[text() ='Admin']");
	private final By adminTable = By.xpath("//div[@role='table']");
	private final By adminList = By.xpath("//div[@role='rowgroup']");
	private final By addButton = By.xpath("//button[text()=' Add ']");
	private final By userRoleText = By.xpath("//label[normalize-space()='User Role']");
	private final By employeeNameText = By.xpath("//label[normalize-space()='Employee Name']");
	private final By statusText = By.xpath("//label[normalize-space()='Status']");
	private final By userNametext = By.xpath("//label[normalize-space()='Username']");
	private final By passwordText = By.xpath("//label[normalize-space()='Password']");
	private final By confirmPasswordText = By.xpath("//label[normalize-space() = 'Confirm Password']");
	private final By cancelButton = By.xpath("//button[normalize-space()='Cancel']");
	private final By saveButton = By.xpath("//button[normalize-space()='Save']");
	private final By userRollDropDown = By.xpath("(//div[@class = 'oxd-select-text oxd-select-text--active'])[1]");
	private final By adminOption = By.xpath("//div[@role='option']//span[text()='Admin']");
	private final By enterEmployeeName = By.xpath("//input[@placeholder='Type for hints...']");
	private final By statusDropDown = By.xpath("(//div[@class = 'oxd-select-text oxd-select-text--active'])[2]");
	private final By enabledOption = By.xpath("//div[@role='option']//span[text()='Enabled']");
	private final By enterUserName = By.xpath("//label[text()='Username']/following::input[1]");
	private final By enterPassword = By.xpath("//label[text()='Password']/following::input[1]");
	private final By confirmPassword = By.xpath("//label[text()='Confirm Password']/following::input[1]");
	private final By searchButton = By.xpath("//button[normalize-space()='Search']");

	public boolean isDisplayAdminMenu() {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(adminMenu)).isDisplayed();
	}

	public void clickAdmin() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(adminMenu)).click();

	}

	public String getUrl() {
		return driver.getCurrentUrl();
	}

	public boolean isDisplayAdminTable() {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(adminTable)).isDisplayed();
	}

	public boolean isDisplayedAdminList() {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(adminList)).isDisplayed();
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

	public void clickUserRoleDropDown() {

		wait.until(ExpectedConditions.elementToBeClickable(userRollDropDown)).click();

	}

	public void selectAdmin() {

		wait.until(ExpectedConditions.elementToBeClickable(adminOption)).click();

	}

	public void enterEmployeeName(String empName) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(enterEmployeeName)).sendKeys(empName);

	}

	public void clickStatus() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(statusDropDown)).click();

	}

	public void selectEnabled() {

		wait.until(ExpectedConditions.elementToBeClickable(enabledOption)).click();

	}

	public void enterUsernamePassword(String userName, String password) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(enterUserName)).sendKeys(userName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(enterPassword)).sendKeys(password);

	}

	public void confirmPassword(String password) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPassword)).sendKeys(password);

	}

	public void clickSave() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(saveButton)).click();
		wait.until(ExpectedConditions.urlContains("saveSystemUsers"));

	}

	public void selectEmployeeSuggestion(String employeeName) {

		List<WebElement> suggestions = wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'oxd-autocomplete-option')]//span")));

		for (WebElement suggestion : suggestions) {
			if (suggestion.getText().trim().equals(employeeName)) {
				suggestion.click();
				break;
			}
		}
	}

	public void clickSearch() {

		driver.findElement(searchButton).click();
	}

}
