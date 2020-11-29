package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ReservationPage;
import pages.LoginPage;

public class ReservationTest extends BasicTest{
	
	@Test(priority = 0)
	public void resrve() throws InterruptedException {
		LoginPage lp = new LoginPage(this.driver, this.waiter);
		ReservationPage rp = new ReservationPage(this.driver, this.waiter, this.js);
		
		this.driver.navigate().to(baseUrl+"/496-dialysis-center-dialhelp-eood;treatment=dialysis-hd;arrivalDate=null;departureDate=null");
		this.driver.manage().window().maximize();
		
		rp.reserve("Dialysis HD", "Monday/Wednesday/Friday" ,"08", "16");
		
		this.waiter.until(ExpectedConditions.presenceOfElementLocated(By.tagName("bnm-login")));
		
		lp.logIn(email, password);
		
		this.waiter.until(ExpectedConditions.presenceOfElementLocated(By.className("reservation-details")));
		
		rp.confirmResrvation("12122 3313");
		
		Thread.sleep(2000);
		
		Assert.assertEquals(rp.getSuccesMsg().isDisplayed(), true, "ERROR, not reserved");
	}
	
	@Test (priority = 5)
	public void reserveSame() throws InterruptedException {

		ReservationPage rp = new ReservationPage(this.driver, this.waiter, this.js);
		
		this.driver.navigate().to(baseUrl +"/496-dialysis-center-dialhelp-eood;treatment=dialysis-hd;arrivalDate=null;departureDate=null");
		rp.reserve("Dialysis HD", "Monday/Wednesday/Friday" ,"15", "25");
		this.waiter.until(ExpectedConditions.presenceOfElementLocated(By.className("reservation-details")));
		rp.confirmResrvation("12122 3313");
		
		Assert.assertEquals(rp.getErrorMsg().isDisplayed(), true, "ERROR, expect error message");
		
	}
	
	@Test(priority = 10)
	public void cancelReservatoion() throws InterruptedException {
		ReservationPage rp = new ReservationPage(this.driver, this.waiter, this.js);
		
		this.driver.navigate().to(baseUrl+"/profile/reservations/pending");
		Thread.sleep(2000);
		rp.cancelReservation();
		Thread.sleep(1000);
		Assert.assertEquals(rp.getSuccesCancelMsg().isDisplayed(), true, "ERROR, reservation not deleted");
		Thread.sleep(3000);
	}
}
