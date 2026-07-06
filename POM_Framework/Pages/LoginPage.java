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

	private void login(String user, String pwd) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(username));

        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);

        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pwd);

        driver.findElement(loginButton).click();
    }
	
	public void validLogin(String user, String pwd) {
		
		 login(user, pwd);

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

        login(user, pwd);
    }
	
	public String getErrormsg() {

	    wait.until(ExpectedConditions.presenceOfElementLocated(errorMessage));

	    return driver.findElement(errorMessage).getText();

	}
	
	public String emptyFieldValidation(String user, String pwd) {

		login(user, pwd);
		
		return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage2)).getText();
	
	}
	
	public String getCurrenturl() {

	    return driver.getCurrentUrl();
	}
	

}
