package com.sel.keyword;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Homepage extends LoadableComponent<Homepage>{

	private final WebDriver driver;
	
	//Declare Elements
	WebElement weFlightsLink;
	WebDriverWait wait;

	
	public Homepage(WebDriver driver) {
	    this.driver = driver;
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void clickFlights(){
		//weSignIn = driver.findElement(By.id("button"));
		weFlightsLink = driver.findElement(By.id("flights-link"));
		weFlightsLink.click();	
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		String url = driver.getCurrentUrl();
	    Assert.assertTrue(url.startsWith("http://www.kayak.com/"),"Homepage is loaded: " + url);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		System.out.println("Homepage -> loading www.kayak.com");
		driver.get("http://www.kayak.com/");
		String url = driver.getCurrentUrl();
		System.out.println(url);
	}

}
