package com.sel.keyword;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Flights extends LoadableComponent<Flights>{

	private final WebDriver driver;
	
	//Declare Elements
	WebElement weRoundTrip;
	WebElement weOneWayTrip;
	WebElement weTravelDate;
	WebElement weDepartDate;//Fri Feb 28
	WebElement weReturnDate;//Thu Mar 20
	WebDriverWait wait;

	
	public Flights(WebDriver driver) {
	    this.driver = driver;
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void clickRoundTrip(){
		//weSignIn = driver.findElement(By.id("button"));
		weRoundTrip = driver.findElement(By.id("roundtrip-label"));
		weRoundTrip.click();	
	}

	public void setDepartDate(){
		weTravelDate = driver.findElement(By.id("travel_dates"));
		weTravelDate.click();
		weDepartDate = driver.findElement(By.xpath("//*[@id='travel_dates-start-display']"));
		//*[@id="datepicker"]/div[2]/span[2]/span[2]/span[5]/span[4]/span
		weDepartDate.sendKeys("Fri Feb 28");
	}
	
	public void clickOneWayTrip(){
		//weSignIn = driver.findElement(By.id("button"));
		weOneWayTrip = driver.findElement(By.id("onewaytrip-label"));
		weOneWayTrip.click();	
	}
	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		String url = driver.getCurrentUrl();
	    Assert.assertTrue(url.startsWith("http://www.kayak.com/flights"),"Flights page is loaded: " + url);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		System.out.println("Flights -> loading www.kayak.com");
		driver.get("http://www.kayak.com/flights");
		String url = driver.getCurrentUrl();
		System.out.println(url);
	}

}
