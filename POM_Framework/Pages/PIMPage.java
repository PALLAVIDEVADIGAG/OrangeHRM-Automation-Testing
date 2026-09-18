package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Base.BasePage;

public class PIMPage extends BasePage {

	public PIMPage(WebDriver driver) {
		super(driver);
	}

	private final By pim = By.xpath("//span[text()='PIM']");

	private final By addButton = By.xpath("//button[normalize-space()='Add']");

	private final By firstName = By.xpath("//input[@name='firstName']");

	private final By middleName = By.xpath("//input[@name='middleName']");

	private final By lastName = By.xpath("//input[@name='lastName']");

	private final By save = By.xpath("//button[normalize-space()='Save']");

	private final By employeeList = By.xpath("//a[text()='Employee List']");

	private final By employeeTable = By.xpath("//div[@role='table']");

	private final By recordsFound = By.xpath("//div[contains(@class,'orangehrm-horizontal-padding')]//span");

	private final By employeeRows = By.xpath("//div[contains(@class,'oxd-table-card')]");

	private final By nextButton = By.xpath("//button[.//i[contains(@class,'bi-chevron-right')]]");

	private final By firstRowEmployeeId = By
			.xpath("(//div[contains(@class,'oxd-table-card')])[1]//div[@role='cell'][2]");

	private final By employeeName = By.xpath("(//input[@placeholder='Type for hints...'])[1]");

	private final By searchButton = By.xpath("//button[@type='submit']");

	private final By employeeNameResult = By
			.xpath("(//div[contains(@class,'oxd-table-card')])[1]//div[@role='cell'][3]");

	private final By employeeID = By.xpath("//label[normalize-space()='Employee Id']/following::input[1]");

	private final By noRecordsFound = By.xpath("//*[contains(normalize-space(),'No Records Found')]");

	public boolean isPIMMenuDisplayed() {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(pim)).isDisplayed();
	}

	public void clickPIM() {

		wait.until(ExpectedConditions.elementToBeClickable(pim)).click();

		wait.until(ExpectedConditions.urlContains("pim"));

		wait.until(ExpectedConditions.visibilityOfElementLocated(employeeList));

		wait.until(ExpectedConditions.visibilityOfElementLocated(employeeTable));
	}

	public String getCurrentUrl() {

		return driver.getCurrentUrl();
	}

	public boolean isDisplayedEmployeeList() {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(employeeList)).isDisplayed();
	}

	public boolean isDisplayEmployeeTable() {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(employeeTable)).isDisplayed();
	}

	public int getRecordsCount() {

		String text = wait.until(ExpectedConditions.visibilityOfElementLocated(recordsFound)).getText();

		// Example: "(231) Records Found"
		text = text.replaceAll("[^0-9]", "");

		return Integer.parseInt(text);
	}

	public int getEmployeeRowCount() {

		return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(employeeRows, 0)).size();
	}

	public boolean isNextPageAvailable() {

		List<WebElement> buttons = driver.findElements(nextButton);

		if (buttons.isEmpty()) {
			return false;
		}

		WebElement button = buttons.get(0);

		return button.isDisplayed() && button.isEnabled();
	}

	public String getFirstEmployeeId() {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(firstRowEmployeeId)).getText().trim();
	}

	public void clickNextPage() {

		String currentId = getFirstEmployeeId();

		WebElement next = wait.until(ExpectedConditions.elementToBeClickable(nextButton));

		next.click();

		wait.until(ExpectedConditions
				.not(ExpectedConditions.textToBePresentInElementLocated(firstRowEmployeeId, currentId)));
	}

	// =========================
	// Add Employee
	// =========================

	public void clickAddButton() {

		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
	}

	public void enterFirstName(String firstNameValue) {

		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));

		field.clear();
		field.sendKeys(firstNameValue);
	}

	public void enterMiddleName(String middleNameValue) {

		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(middleName));

		field.clear();
		field.sendKeys(middleNameValue);
	}

	public void enterLastName(String lastNameValue) {

		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));

		field.clear();
		field.sendKeys(lastNameValue);
	}

	public void clickSave() {

		wait.until(ExpectedConditions.elementToBeClickable(save)).click();
	}

	public boolean isPersonalDetailsPageDisplayed() {

		return wait.until(ExpectedConditions.urlContains("viewPersonalDetails"));
	}

	// =========================
	// Employee Search
	// =========================

	public void searchEmployeeByName(String employeeNameValue) {

		WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeName));

		nameField.clear();
		nameField.sendKeys(employeeNameValue);

		wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(employeeRows));
	}

	public String getDisplayedEmployeeName() {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameResult)).getText().trim();
	}

	public boolean areEmployeeRowsDisplayed() {

		return !driver.findElements(employeeRows).isEmpty();
	}

	public String getExistingEmployeeID() {

		WebElement firstRow = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'oxd-table-card')])[1]")));

		WebElement employeeIdCell = firstRow.findElement(By.xpath(".//div[@role='cell'][2]"));

		return employeeIdCell.getText().trim();
	}

	public void searchEmployeeByID(String employeeIdValue) {

		WebElement idField = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeID));

		idField.clear();
		idField.sendKeys(employeeIdValue);

		wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

		wait.until(driver -> !driver.findElements(employeeRows).isEmpty()
				|| !driver.findElements(noRecordsFound).isEmpty());
	}

	public String getDisplayedEmployeeID() {
		WebElement row = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'oxd-table-card')])[1]")));

		WebElement employeeIdCell = row.findElement(By.xpath(".//div[@role='cell'][2]"));

		return employeeIdCell.getText().trim();
	}

}