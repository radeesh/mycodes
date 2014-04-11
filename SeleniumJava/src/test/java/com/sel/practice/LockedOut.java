package com.sel.practice;
//https://accounts.google.com/AccountRecoveryOptionsPrompt
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LockedOut extends LoadableComponent<LockedOut>{

	private final WebDriver driver;
	
	WebElement weNoThanks;
	WebElement weDone;
	WebDriverWait wait;
	
	public LockedOut(WebDriver driver) {
	    this.driver = driver;
	    wait = new WebDriverWait(driver,10);

	}
	
	public void clickNoThanks(){
	    try{
	    	weNoThanks = driver.findElement(By.id("cancel"));
	    }
	    catch (NoSuchElementException e){
	    	System.out.println("Account recovery options not displayed.");
	    }

	}
	
	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		String url = driver.getCurrentUrl();
	    Assert.assertTrue(url.startsWith("https://accounts.google.com/AccountRecoveryOptionsPrompt"),"Account Recovery Options Displayed: " + url);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		//driver.get("https://mail.google.com/");
		
	}

}

