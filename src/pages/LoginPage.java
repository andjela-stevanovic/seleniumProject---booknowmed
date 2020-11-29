package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage{

	public LoginPage(WebDriver driver, WebDriverWait waiter) {
		super(driver, waiter);
	}
	
	public WebElement getEmail() {
		return this.driver.findElement(By.name("username"));
	}
	
	public WebElement getPassword() {
		return this.driver.findElement(By.xpath
				("//bnm-password/bnm-padded-input/input"));
	}
	
	public WebElement getLoginBtn() {
		return this.driver.findElement
				(By.xpath("//form/button[@type='submit']"));
	}
	
	public WebElement errorMsg() {
		return this.driver.findElement(By.tagName("bnm-validation"));
	}
	
	public WebElement getDropdown() {
		return this.driver.findElement(By.xpath("//button[@class = 'dropdown']"));
	}
	
	public WebElement getLogoutBtn() {
		return this.driver.findElement(By.xpath("//button[contains(text(), 'Log out')]"));
	}
	
	public void logIn(String email, String password) {
		this.getEmail().sendKeys(email);
		this.getPassword().sendKeys(password);
		this.getLoginBtn().click();
	}
	
	public void logOut() {
		this.getDropdown().click();
		this.getLogoutBtn().click();
	}

}
