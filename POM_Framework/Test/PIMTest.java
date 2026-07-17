package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import Pages.LoginPage;
import Pages.PIMPage;
import Utilities.DriverFactory;

public class PIMTest {

	WebDriver driver;
	LoginPage login;
	PIMPage pim;

	private static final String USERNAME = "Admin";
	private static final String PASSWORD = "admin123";

	@BeforeMethod
	public void setup() {

		driver = DriverFactory.setup();

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		login = new LoginPage(driver);
		pim = new PIMPage(driver);

		login.validLogin(USERNAME, PASSWORD);
	}
	
	

	 @AfterMethod
	public void tearDown() {

		DriverFactory.close();
	} 
	
	@Test
	public void verifyPIMMenuIsDisplayed() {

	    Assert.assertTrue(pim.isPIMMenuDisplayed(),
	            "PIM menu is not displayed after login.");
	}
	
	@Test
	public void verifyclickPIM() {
		pim.clickPIM();
		
		Assert.assertTrue(pim.getCurrentUrl().contains("pim"), "User able to navigate to the PIM page by clicking the PIM menu");

	}
	@Test
public void verifyisDisplayedEmployeeList() {
		
		 pim.clickPIM();
		Assert.assertTrue(
		        pim.isDisplayedEmployeeList(),
		        "Employee List is not displayed."
		);
	} 
	
	@Test
	public void verifyEmployeeTableDisplayed() {
		pim.clickPIM();
		Assert.assertTrue(pim.isDisplayEmployeeTable(), "Employee Table not displayed");
	} 

	@Test
	public void verifyGetRecordsFound() {
		
		pim.clickPIM();
		
		int count = pim.getRecordsCount();
		System.out.println("Records Found: " + count);

		Assert.assertTrue(count > 0, "Employee list is empty.");
	
	} 
	
	@Test
	public void verifyEmployeeRows() {

	    pim.clickPIM();

	    int rows = pim.getEmployeeRowCount();

	    System.out.println("Number of employee rows = " + rows);

	    Assert.assertTrue(rows > 0,
	            "Employee table contains no records.");
	} 
	
	@Test
	public void compareEmployeeCountRowCount() {
		
		pim.clickPIM();
		
		
		Assert.assertEquals(pim.getRecordsCount(), pim.getEmployeeRowCount());
	}
	  
	
	@Test
	public void VerifyNavigatePagination() {

	    pim.clickPIM();
	    System.out.println(driver.getCurrentUrl());

	    Assert.assertTrue(pim.isDisplayPagination());
	    
	    

	    String firstIdPage1 = pim.getFirstEmployeeId();

	    pim.clickNextPage();

	    String firstIdPage2 = pim.getFirstEmployeeId();

	    Assert.assertNotEquals(
	            firstIdPage1,
	            firstIdPage2,
	            "Pagination failed. First employee ID is the same after clicking Next."
	    );
	}
	
	

	
	@Test
	public void addEmployee() {
		pim.clickPIM();
		pim.clickAddButton();
		pim.enterFirstName("yrty");
		pim.enterMidName("Getty");
		pim.enterLastName("yry");
		pim.clickSave();

		Assert.assertTrue(
	            pim.getCurrentUrl().contains("viewPersonalDetails"),
	            "Employee was not added successfully.");

	}

}