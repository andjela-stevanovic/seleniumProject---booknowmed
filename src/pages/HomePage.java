package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasicPage{
	
	public HomePage(WebDriver driver, WebDriverWait waiter) {
		super(driver, waiter);
	}
	
	public List<WebElement> results(){
		return this.driver.findElements(By.tagName("bnm-search-result-card"));
	}
	
	public Select getSort() {
		WebElement sort = this.driver.findElement
				(By.xpath("//div[@class = 'sort-button-wrapper']/bnm-select/select"));
		Select s = new Select(sort);
		return s;
	}
	
	public WebElement getTrtmen() {
		return this.driver.findElement
				(By.xpath("//bnm-platform-search/div[@class = 'group']/bnm-custom-select/div[1]"));
	}
	
	public WebElement getChosenTretman(String tretman) {
		return this.driver.findElement(By.xpath("//div[contains(text(),'"+ tretman +"')]"));
	}
	
	public WebElement getLocationInput() {
		return this.driver.findElement
				(By.xpath("//div[@class = 'location']/bnm-location-picker/div/input"));
	}
	
	public WebElement getSearchBtn() {
		return this.driver.findElement
				(By.xpath("//div[@class = 'search']/button"));
	}
	
	public List<WebElement> getRatings(){
		return this.driver.findElements(By.xpath("//div[@class='info']/div[2]/span"));
	}
	
	public void serch(String tretman, String location) throws InterruptedException {
		this.getTrtmen().click();
		this.getChosenTretman(tretman).click();
		this.getLocationInput().clear();
		this.getLocationInput().sendKeys(location);
		Thread.sleep(2000);
		this.getLocationInput().sendKeys(Keys.ENTER);
		this.getSearchBtn().click();
	}
	
	public void sort(String sortBy) {
		this.getSort().selectByVisibleText(sortBy);
	}

}
