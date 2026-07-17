package Pages;

import java.time.Duration;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PIMPage {

	WebDriver driver;
	WebDriverWait wait;

	public PIMPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	By PIM = By.xpath("//span[text()='PIM']");
	By AddButton = By.xpath("//button[normalize-space()='Add']");
	By FirstName = By.xpath("//input[@name='firstName']");
	By MiddleName = By.xpath("//input[@name='middleName']");
	By LastName = By.xpath("//input[@name='lastName']");
	By Save = By.xpath("//button[normalize-space()='Save']");
	By loadingSpinner = By.className("oxd-loading-spinner-container");
	By personalDetails = By.xpath("//a[text() ='Personal Details']");
	By employeeList = By.xpath("//a[text()='Employee List']");
	By employeeTable = By.xpath("//div[@role='table']");
	By recordsFound = By.xpath("//div[contains(@class,'orangehrm-horizontal-padding')]//span");
	By employeeRows = By.xpath("//div[@class='oxd-table-card']");
	
	By nextButton = By.xpath("//button[.//i[contains(@class,'bi-chevron-right')]]");
	
	
	
	By firstRowEmployeeId = By.xpath("(//div[@role='row'])[2]//div[@role='cell'][2]");
	
	
	
	public boolean isPIMMenuDisplayed() {
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(PIM)).isDisplayed();
	}
	
	public String getCurrentUrl() {
	    return driver.getCurrentUrl();
	}
	
	public void clickPIM() {

	    wait.until(ExpectedConditions.elementToBeClickable(PIM)).click();

	    wait.until(ExpectedConditions.urlContains("pim"));

	    wait.until(ExpectedConditions.visibilityOfElementLocated(employeeList));
	}
	
	public boolean isDisplayedEmployeeList() {
		
		
		return wait.until(ExpectedConditions.visibilityOfElementLocated(employeeList)).isDisplayed();
		
	
	}
	public void clickAddButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddButton));
		driver.findElement(AddButton).click();
	}

	public void enterFirstName(String firstName) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(FirstName));

		driver.findElement(FirstName).sendKeys(firstName);
	}

	public void enterMidName(String MidName) {

		driver.findElement(MiddleName).sendKeys(MidName);
	}

	public void enterLastName(String lastName) {

		driver.findElement(LastName).sendKeys(lastName);
	}
	
	public void clickSave() {

	    wait.until(ExpectedConditions.elementToBeClickable(Save)).click();

	    wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));

	    wait.until(ExpectedConditions.urlContains("viewPersonalDetails"));
	}
	
	public boolean isDisplayEmployeeTable() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(employeeTable)).isDisplayed();
	}
	
	public int getRecordsCount() {

	    String text = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(recordsFound))
	            .getText();

	    // Example: "(231) Records Found"
	    text = text.replaceAll("[^0-9]", "");

	    return Integer.parseInt(text);
	}
	
	public int getEmployeeRowCount() {

	    wait.until(ExpectedConditions.visibilityOfElementLocated(employeeRows));

	    return driver.findElements(employeeRows).size();
	}
	
	public boolean isDisplayPagination() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(nextButton)).isDisplayed();
	}

	
	public String getFirstEmployeeId() {

	    return wait.until(
	            ExpectedConditions.visibilityOfElementLocated(firstRowEmployeeId))
	            .getText()
	            .trim();
	}
	
	public void clickNextPage() {

	    String currentId = getFirstEmployeeId();

	    wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();

	    wait.until(ExpectedConditions.not(
	            ExpectedConditions.textToBePresentInElementLocated(
	                    firstRowEmployeeId, currentId)));
	}
 
	

}
