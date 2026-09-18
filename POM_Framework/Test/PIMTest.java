package Test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.LoginPage;
import Pages.PIMPage;

public class PIMTest extends BaseTest {

	LoginPage login;
	PIMPage pim;

	private static final String USERNAME = "Admin";
	private static final String PASSWORD = "admin123";

	@BeforeMethod
	public void initializePage() {

		login = new LoginPage(driver);
		pim = new PIMPage(driver);

		login.login(USERNAME, PASSWORD);
	}

	// =========================================================
	// TC-001 - Verify PIM menu is displayed
	// =========================================================

	@Test(description = "Verify PIM menu is displayed after login", enabled = true)
	public void verifyPIMMenuIsDisplayed() {

		Assert.assertTrue(pim.isPIMMenuDisplayed(), "PIM menu is not displayed after login.");
	}

	// =========================================================
	// TC-002 - Verify navigation to PIM
	// =========================================================

	@Test(description = "Verify user can navigate to PIM page", enabled = true)
	public void verifyClickPIM() {

		pim.clickPIM();

		Assert.assertTrue(pim.getCurrentUrl().contains("pim"), "User was not navigated to the PIM page.");
	}

	// =========================================================
	// TC-003 - Verify Employee List
	// =========================================================

	@Test(description = "Verify Employee List is displayed", enabled = true)
	public void verifyEmployeeListDisplayed() {

		pim.clickPIM();

		Assert.assertTrue(pim.isDisplayedEmployeeList(), "Employee List is not displayed.");
	}

	// =========================================================
	// TC-04 - Verify Employee Table
	// =========================================================

	@Test(description = "Verify Employee table is displayed", enabled = true)
	public void verifyEmployeeTableDisplayed() {

		pim.clickPIM();

		Assert.assertTrue(pim.isDisplayEmployeeTable(), "Employee table is not displayed.");
	}

	// =========================================================
	// TC-05 - Verify Records Found count
	// =========================================================

	@Test(description = "Verify employee records count is greater than zero", enabled = true)
	public void verifyRecordsFoundCount() {

		pim.clickPIM();

		int count = pim.getRecordsCount();

		System.out.println("Records Found: " + count);

		Assert.assertTrue(count > 0, "Employee list is empty.");
	}

	// =========================================================
	// TC-06 - Verify employee rows
	// =========================================================

	@Test(description = "Verify employee rows are displayed", enabled = false)
	public void verifyEmployeeRows() {

		pim.clickPIM();

		int rows = pim.getEmployeeRowCount();

		System.out.println("Number of employee rows = " + rows);

		Assert.assertTrue(rows > 0, "Employee table contains no records.");
	}

	// =========================================================
	// TC-07 - Verify pagination navigation
	// =========================================================

	@Test(description = "Verify user can navigate to the next employee page", enabled = true)
	public void verifyPaginationNavigation() {

		pim.clickPIM();
		if (!pim.isNextPageAvailable()) {

			System.out.println("Only one employee page is currently available.");

			System.out.println("Pagination navigation cannot be tested with the current data.");

			return;
		}

		String firstIdPage1 = pim.getFirstEmployeeId();

		System.out.println("First Employee ID on Page 1: " + firstIdPage1);

		pim.clickNextPage();

		String firstIdPage2 = pim.getFirstEmployeeId();

		System.out.println("First Employee ID on Page 2: " + firstIdPage2);

		Assert.assertNotEquals(firstIdPage1, firstIdPage2, "Pagination failed. First employee ID did not change.");
	}

	// =========================================================
	// TC-08 - Verify employee can be added
	// =========================================================

	@Test(description = "Verify employee can be added successfully", enabled = true)
	public void addEmployee() {

		pim.clickPIM();

		pim.clickAddButton();

		pim.enterFirstName("yrty");
		pim.enterMiddleName("Getty");
		pim.enterLastName("yry");

		pim.clickSave();

		Assert.assertTrue(pim.isPersonalDetailsPageDisplayed(), "Employee was not added successfully.");
	}

	// =========================================================
	// TC-09 - Verify employee search by name
	// =========================================================

	@Test(description = "Verify employee search by name", enabled = true)
	public void verifySearchEmployeeByName() {

		String expectedEmployee = "bala kumar";

		pim.clickPIM();

		pim.searchEmployeeByName(expectedEmployee);

		String actualEmployee = pim.getDisplayedEmployeeName();

		System.out.println("Expected Employee Name: " + expectedEmployee);
		System.out.println("Actual Employee Name: " + actualEmployee);

		Assert.assertTrue(actualEmployee.toLowerCase().contains(expectedEmployee.toLowerCase()),
				"Employee search by name failed.");

	}

	// =========================================================
	// TC-010 - Verify employee search by ID
	// =========================================================

	@Test(description = "Verify employee search by Employee ID", enabled = true)
	public void verifySearchEmployeeByID() {

		pim.clickPIM();

		String employeeId = pim.getExistingEmployeeID();

		System.out.println("Employee ID selected for search: " + employeeId);

		pim.searchEmployeeByID(employeeId);

		String actualEmployeeId = pim.getDisplayedEmployeeID();

		System.out.println("Expected Employee ID: " + employeeId);
		System.out.println("Actual Employee ID: " + actualEmployeeId);

		Assert.assertEquals(actualEmployeeId, employeeId, "Employee search by ID failed.");
	}

}