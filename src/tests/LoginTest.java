package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;

public class LoginTest extends BasicTest{
	
	@Test(priority = 0)
	public void invalidlLogin() {
		LoginPage lp = new LoginPage(this.driver, this.waiter);
		
		this.driver.navigate().to(baseUrl+"/dialysis/login");
		this.driver.manage().window().maximize();
		lp.logIn("invalidEmail", "invalidPassword");
		
		Assert.assertEquals(lp.errorMsg().isDisplayed(), true, "ERROR, error message isn't displayed");
		Assert.assertEquals(lp.errorMsg().getText(), "Invalid email/password combination.", "ERROR wrong message");
		
	}
	
	@Test(priority = 5)
	public void passwordEmpty() {
		LoginPage lp = new LoginPage(this.driver, this.waiter);
		
		this.driver.navigate().to( baseUrl+"/dialysis/login");
		this.driver.manage().window().maximize();
		lp.logIn("invalidEmail", "");
		Assert.assertEquals(lp.errorMsg().isDisplayed(), true, "ERROR, error message isn't displayed");
		Assert.assertEquals(lp.errorMsg().getText(), "Password is required.", "ERROR wrong message");
	}
	
	@Test(priority = 10)
	public void happyPath() throws InterruptedException {
		LoginPage lp = new LoginPage(this.driver, this.waiter);
		
		this.driver.navigate().to(baseUrl +"/dialysis/login");
		lp.logIn(email, password);
		
		Thread.sleep(2000);
		boolean isThere = false;
		List<WebElement> results = this.driver.findElements(By.tagName("bnm-search-result-card"));
		if(results.size() != 0) {
			isThere = true;
		}
		
		Assert.assertEquals(isThere, true, "ERROR not log in");
	}
	
	@Test(priority = 15)
	public void logOut() throws InterruptedException {
		LoginPage lp = new LoginPage(this.driver, this.waiter);
		
		this.driver.navigate().to(baseUrl+"/dialysis-hd?sort=rating_desc");
		
		lp.logOut();
		
		Thread.sleep(2000);
		
		Assert.assertEquals(driver.getCurrentUrl(), baseUrl+"/dialysis", "ERROR, not log out");
	}
}
