package automation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Scenario {
	// Centralized location
	private static String baseURL = "https://www.securian.com/insights-tools/retirement-calculator.html";
	private static String currentAge = "40";
	private static String Retirementage = "68";
	private static String currentannualincome = "100000";
	private static String spousalannualincome = "75000";
	private static String currentretirementsavings = "500000";
	private static String currentretirementcont = "10";
	private static String Annualretirementcont = "2";
	private static String socialsecamount = "4000";
	private static String otherincomeamt = "500";
	private static String retireduration = "20";
	private static String percentfinalamt = "75";
	private static String preretireamt = "8";
	private static String postretireamt = "5";
	// locators
	private static String loc_txtcurrentage = "//*[@id='current-age']";
	private static String loc_txtretireage = "//*[@id='retirement-age']";
	private static String loc_txtcuranninc = "//*[@id='current-income']";
	private static String loc_txtspsinc = "//*[@id='spouse-income']";
	private static String loc_txtcurrtotsav = "//*[@id='current-total-savings']";
	private static String loc_txtcurranusav = "//*[@id='current-annual-savings']";
	private static String loc_txtsavincrate = "//*[@id='savings-increase-rate']";
	private static String loc_btncalculate = "(//*[@class='dsg-btn-primary btn-block'])[1]";
	private static String loc_rdoyessb = "//*[@id='yes-social-benefits']/following-sibling::label";
	private static String loc_rdonossb = "//*[@id='no-social-benefits']/following-sibling::label";
	private static String loc_lblmaritalstatus = "//*[@id='marital-status-toggle-group']";
	private static String loc_lblssofield = "//*[@id='social-security-override-container']";
	private static String loc_lblmarstatsingle = "//*[@id='single']/following-sibling::label";
	private static String loc_lblmarstatmarried = "//*[@id='married']/following-sibling::label";
	private static String loc_txtsocsecoveride = "//*[@id='social-security-override']";
	private static String loc_lnkdefaultvalue = "Adjust default values";
	private static String loc_txtothincome = "//*[@id='additional-income']";
	private static String loc_txtretduration = "//*[@id='retirement-duration']";
	private static String loc_rdoyespostret = "//*[@id='include-inflation']/following-sibling::label";
	private static String loc_txtretanninc = "//*[@id='retirement-annual-income']";
	private static String loc_txtpreretire = "//*[@id='pre-retirement-roi']";
	private static String loc_txtpostretire = "//*[@id='post-retirement-roi']";
	private static String loc_btnsavechanges = "(//*[@class='dsg-btn-primary btn-block'])[2]";
	private WebDriver driver;


	@BeforeTest
	// Before every Test Chrome Driver will open and open Securian -Retirement
	// calculator page
	private void startchromebrowser() {
		try {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(baseURL);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// TC1_User should be able to submit form with all required fields filled in
	@Test
	private void submitwithallreqfields() {
		try {
			driver.findElement(By.xpath(loc_txtcurrentage)).sendKeys(currentAge);
			driver.findElement(By.xpath(loc_txtretireage)).sendKeys(Retirementage);
			driver.findElement(By.xpath(loc_txtcuranninc)).click();
			driver.findElement(By.xpath(loc_txtcuranninc)).sendKeys(currentannualincome);
			driver.findElement(By.xpath(loc_txtcurrtotsav)).click();
			driver.findElement(By.xpath(loc_txtcurrtotsav)).sendKeys(currentretirementsavings);
			driver.findElement(By.xpath(loc_txtcurranusav)).sendKeys(currentretirementcont);
			driver.findElement(By.xpath(loc_txtsavincrate)).sendKeys(Annualretirementcont);
			driver.findElement(By.xpath(loc_btncalculate)).click();
		} catch (Exception e) {
			System.out.println("Error occured at :: submitwithallreqfields :: " + e.getMessage());
		}
	}

//	TC2_Verify Additional Social Security fields should display on clicking yes on Social Security benefits toggle
	@Test
	private void fieldsaredisplayedonyes() {
		try {
			driver.findElement(By.xpath(loc_rdoyessb)).click();
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc_lblmaritalstatus)));
			WebElement maritalstatus = driver.findElement(By.xpath(loc_lblmaritalstatus));
			WebElement socialsecurity = driver.findElement(By.xpath(loc_lblssofield));
			if (maritalstatus.isDisplayed() && socialsecurity.isDisplayed()) {
				Assert.assertEquals(true, true);
				System.out.println("Fields are displayed");
			} else {
				Assert.assertEquals(true, false);
				System.out.println("Fields are not displayed");
			}
		} catch (Exception e) {
			System.out.println("Error occured at :: fieldsaredisplayedonyes :: " + e.getMessage());
		}
	}

//	TC3_Verify Additional Social Security fields should not be displayed on clicking no on Social Security benefits toggle
	@Test
	private void fieldsarenotdisplayedonno() {
		try {
			driver.findElement(By.xpath(loc_rdonossb)).click();
			WebElement maritalstatus = driver.findElement(By.xpath(loc_lblmaritalstatus));
			WebElement socialsecurity = driver.findElement(By.xpath(loc_lblssofield));
			if (!maritalstatus.isDisplayed() && !socialsecurity.isDisplayed()) {
				Assert.assertEquals(true, true);
				System.out.println("Fields are not displayed");
			} else {
				Assert.assertEquals(true, false);
				System.out.println("Fields are displayed");
			}
		} catch (Exception e) {
			System.out.println("Error occured at :: fieldsarenotdisplayedonno :: " + e.getMessage());
		}
	}

	// TC4_User should be able to submit form with all fields filled in
	@Test
	private void submitwithallfields() {
		try {
			driver.findElement(By.xpath(loc_txtcurrentage)).sendKeys(currentAge);
			driver.findElement(By.xpath(loc_txtretireage)).sendKeys(Retirementage);
			driver.findElement(By.xpath(loc_txtcuranninc)).click();
			driver.findElement(By.xpath(loc_txtcuranninc)).sendKeys(currentannualincome);
			driver.findElement(By.xpath(loc_txtspsinc)).click();
			driver.findElement(By.xpath(loc_txtspsinc)).sendKeys(spousalannualincome);
			driver.findElement(By.xpath(loc_txtcurrtotsav)).click();
			driver.findElement(By.xpath(loc_txtcurrtotsav)).sendKeys(currentretirementsavings);
			driver.findElement(By.xpath(loc_txtcurranusav)).sendKeys(currentretirementcont);
			driver.findElement(By.xpath(loc_txtsavincrate)).sendKeys(Annualretirementcont);
			driver.findElement(By.xpath(loc_rdoyessb)).click();
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc_lblmarstatmarried)));
			driver.findElement(By.xpath(loc_lblmarstatmarried)).click();
			driver.findElement(By.xpath(loc_txtsocsecoveride)).click();
			driver.findElement(By.xpath(loc_txtsocsecoveride)).sendKeys(socialsecamount);
			driver.findElement(By.xpath(loc_btncalculate)).click();
		} catch (Exception e) {
			System.out.println("Error occured at :: submitwithallfields :: " + e.getMessage());
		}
	}

	// TC5_User should be able to update calculator values and submit data
	@Test
	private void userupdatecalvalues() {
		try {
			driver.findElement(By.xpath(loc_txtcurrentage)).sendKeys(currentAge);
			driver.findElement(By.xpath(loc_txtretireage)).sendKeys(Retirementage);
			driver.findElement(By.xpath(loc_txtcuranninc)).click();
			driver.findElement(By.xpath(loc_txtcuranninc)).sendKeys(currentannualincome);
			driver.findElement(By.xpath(loc_txtspsinc)).click();
			driver.findElement(By.xpath(loc_txtspsinc)).sendKeys(spousalannualincome);
			driver.findElement(By.xpath(loc_txtcurrtotsav)).click();
			driver.findElement(By.xpath(loc_txtcurrtotsav)).sendKeys(currentretirementsavings);
			driver.findElement(By.xpath(loc_txtcurranusav)).sendKeys(currentretirementcont);
			driver.findElement(By.xpath(loc_txtsavincrate)).sendKeys(Annualretirementcont);
			driver.findElement(By.xpath(loc_rdoyessb)).click();
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc_lblmarstatmarried)));
			driver.findElement(By.xpath(loc_lblmarstatmarried)).click();
			driver.findElement(By.xpath(loc_txtsocsecoveride)).click();
			driver.findElement(By.xpath(loc_txtsocsecoveride)).sendKeys(socialsecamount);
			driver.findElement(By.linkText(loc_lnkdefaultvalue)).click();
			driver.findElement(By.xpath(loc_txtothincome)).click();
			driver.findElement(By.xpath(loc_txtothincome)).sendKeys(otherincomeamt);
			driver.findElement(By.xpath(loc_txtretduration)).sendKeys(retireduration);
			driver.findElement(By.xpath(loc_rdoyespostret)).click();
			driver.findElement(By.xpath(loc_txtretanninc)).sendKeys(percentfinalamt);
			driver.findElement(By.xpath(loc_txtpreretire)).sendKeys(preretireamt);
			driver.findElement(By.xpath(loc_txtpostretire)).sendKeys(postretireamt);
			driver.findElement(By.xpath(loc_btnsavechanges)).click();
			WebDriverWait wait2 = new WebDriverWait(driver, 30);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc_btncalculate)));
			driver.findElement(By.xpath(loc_btncalculate)).click();
		} catch (Exception e) {
			System.out.println("Error occured at :: submitwithallfields :: " + e.getMessage());
		}
	}

//
	@AfterTest
	private void closebrowser() {
		try {
			driver.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}
}
