package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ReservationPage extends BasicPage{
	
	protected JavascriptExecutor js;

	public ReservationPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter);
		this.js = js;
	}
	
	public Select getTretman() {
		WebElement tretman = this.driver.findElement(By.name("clinicTreatmentId"));
		Select s = new Select(tretman);
		return s;
	}
	
	public Select getDays() {
		WebElement days = this.driver.findElement(By.name("days"));
		Select s = new Select(days);
		return s;
	}
	
	public WebElement getCheckIn() {
		return this.driver.findElement(By.xpath("//bnm-single-clinic-date-picker/yahtee-popover-container/div/div[2]/button[1]"));
	}
	
	
	public WebElement getMonth() {
		return this.driver.findElement(By.xpath("//bnm-single-clinic-date-picker/yahtee-popover-container/yahtee-popover-content/div/bnm-paper/header/button[2]"));
	}
	
	
	public List<WebElement> dataPicker(){
		return this.driver.findElements(By.tagName("yahtee-date"));
	}
	
	public WebElement getDate(String date) {
		WebElement n = null;
		for(WebElement e : this.dataPicker()) {
			if(e.getAttribute("ng-reflect-date")!= null) {
				if(e.getAttribute("ng-reflect-date").contains(date + " 202")) {
					return e;
				}
			}else {
				continue;
			}
		}
		return n;
	}
	
	
	public WebElement getReserveBtn() {
		return this.driver.findElement(By.xpath("//button[@type = 'submit']"));
	}
	
	public Select getChoosePatient() {
		WebElement e = this.driver.findElement(By.name("patientId"));
		Select s = new Select(e);
		return s;
	}
	
	public WebElement getConfirmBtn() {
		return this.driver.findElement(By.xpath("//div[@class = 'actions']/button"));
	}
	
	public WebElement getSuccesMsg() {
		return this.driver.findElement(By.tagName("bnm-success-message"));
	}
	
	public WebElement getErrorMsg() {
		return this.driver.findElement(By.xpath("//div[@class = 'patient-details']/div/div/bnm-validation"));
	}
	
	public WebElement getCancelResBtn() {
		return this.driver.findElement(By.xpath("//button[contains(text(), 'Cancel reservation')]"));
	}
	
	public WebElement getCancelConfirm() {
		return this.driver.findElement(By.xpath("//bnm-cancel-reservation-dialog/div[@class = 'buttons']/button[1]"));
	}
	
	public WebElement getSuccesCancelMsg() {
		return this.driver.findElement(By.xpath("//p[contains(text(), 'Reservation canceled.')]"));
	}
	
	public void reserve(String tretman, String days, String startDate, String endDate) throws InterruptedException {
		this.getTretman().selectByVisibleText(tretman);
		this.getDays().selectByVisibleText(days);
		this.getCheckIn().click();
		this.getMonth().click();
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", this.getDate(startDate));
		js.executeScript("arguments[0].click();", this.getDate(endDate));
		this.getReserveBtn().click();
	}
	
	public void confirmResrvation(String patientId) {
		this.getChoosePatient().selectByVisibleText(patientId);
		js.executeScript("arguments[0].click();", this.getConfirmBtn());
		
	}
	
	public void cancelReservation() throws InterruptedException {
		this.getCancelResBtn().click();
		this.waiter.until(ExpectedConditions.visibilityOfElementLocated
				(By.tagName("bnm-cancel-reservation-dialog")));
		this.getCancelConfirm().click();
	}
	
}
