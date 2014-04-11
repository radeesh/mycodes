package com.sel.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class Login extends LoadableComponent<Login>{

	private final WebDriver driver;
	
	WebElement weEmail;
	WebElement wePassword;
	WebElement weRememberMe;
	WebElement weSignIn;

	
	public Login(WebDriver driver) {
	    this.driver = driver;
	}
	public void setUsername(String username) {
		if (username=="0"){
			Properties p=new Properties();
			try {
				FileInputStream  fis=new FileInputStream("./prop.properties");
				p.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			weEmail = driver.findElement(By.id("Email"));
			weEmail.sendKeys(p.getProperty("USERNAME"));
		}
		else {
			weEmail = driver.findElement(By.id("Email"));
			weEmail.sendKeys(username);
		}		
	}

	public void setPassword(String password) {
		if (password=="0"){
			Properties p=new Properties();
			try {
				FileInputStream  fis=new FileInputStream("./prop.properties");
				p.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			wePassword = driver.findElement(By.id("Passwd"));
			wePassword.sendKeys(p.getProperty("PASSWORD"));
		}
		else {
			wePassword = driver.findElement(By.id("Passwd"));
			wePassword.sendKeys(password);
		}
	}
	
	public void uncheckRememberMe() {
		weRememberMe = driver.findElement(By.id("PersistentCookie"));
		if(weRememberMe.isSelected()){
			weRememberMe.click();
	    }
	}	

	public void clickSignIn(){
		weSignIn = driver.findElement(By.id("signIn"));
		weSignIn.click();	
	}
	
	public void login(){
    	setUsername("0");
    	setPassword("0");
    	uncheckRememberMe();
    	clickSignIn();
	}
	

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		String url = driver.getCurrentUrl();
	    Assert.assertTrue(url.startsWith("https://accounts.google.com/"),"Login page is loaded: " + url);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		System.out.println("Login -> loading www.gmail.com");
		driver.get("https://mail.google.com");
		String url = driver.getCurrentUrl();
		System.out.println(url);
	}

}
