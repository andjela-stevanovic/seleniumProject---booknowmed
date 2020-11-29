package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.HomePage;

public class SearchTest extends BasicTest{
	
	@Test
	public void searhTest() throws IOException, InterruptedException {
		
		HomePage hp = new HomePage(this.driver, this.waiter);
		
		File file = new File("data/Data (1).xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb.getSheetAt(0);
		
		SoftAssert sa = new SoftAssert();
		
		
		this.driver.navigate().to(baseUrl+"/dialysis-hd?sort=rating_desc");
		this.driver.manage().window().maximize();
		
		this.waiter.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("bnm-search-result-card")));
		
		for(int i = 1; i<=6; i++) {
			XSSFRow row = sheet1.getRow(i);
			
			String treatment = row.getCell(0).getStringCellValue();
			
			String location = row .getCell(1).getStringCellValue();
			
			double num = row.getCell(2).getNumericCellValue();
			
			hp.serch(treatment, location);	
			
			Thread.sleep(2000);
			
			sa.assertEquals(hp.results().size(), (int)num);
			
	    }
		sa.assertAll();
		
		wb.close();
		fis.close();
		
	}
	
	@Test(priority = 5)
	public void sortTest() throws InterruptedException {
		HomePage hp = new HomePage(this.driver, this.waiter);
		
		this.driver.navigate().to("https://bnm.neopixdev.com/dialysis-hd/spain/malaga?sort=price_desc");
		this.waiter.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("bnm-search-result-card")));
		hp.sort("Lowest rating");
		Thread.sleep(2000);
		double firstItemRating = Double.parseDouble(hp.getRatings().get(0).getText());
		double lastItemRating = Double.parseDouble(hp.getRatings().get(hp.getRatings().size()-1).getText());
		
		Assert.assertTrue(firstItemRating <lastItemRating, "ERROR, not sorted");
		
		hp.sort("Highest rating");
		Thread.sleep(2000);
		firstItemRating = Double.parseDouble(hp.getRatings().get(0).getText());
		lastItemRating = Double.parseDouble(hp.getRatings().get(hp.getRatings().size()-1).getText());
		Assert.assertTrue(firstItemRating > lastItemRating, "ERROR, not sorted");
	}
}
