package com.sel.keyword;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Login extends LoadableComponent<Login>{

	private final WebDriver driver;
	
	//Declare Elements
	WebElement weLogin;
	WebElement weUsername;
	WebElement wePassword;
	WebElement weRememberMe;
	WebElement weSignIn;
	WebDriverWait wait;

	
	public Login(WebDriver driver) {
	    this.driver = driver;
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
			username = (p.getProperty("KAYAKUSERNAME"));
		}
		weLogin = driver.findElement(By.id("headersigninlink"));
		weLogin.click();
		try{
			weUsername = driver.findElement(By.name("username"));
			weUsername.sendKeys(username);
		}
		catch(Exception e){
			e.printStackTrace();
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
			password= p.getProperty("KAYAKPASSWORD");
		}
		wePassword = driver.findElement(By.name("passwd"));
		wePassword.sendKeys(password);
	}
	
	public void uncheckRememberMe() {
		weRememberMe = driver.findElement(By.id("ajaxsticky"));
		if(weRememberMe.isSelected()){
			weRememberMe.click();
	    }
	}	

	public void clickSignIn(){
		//weSignIn = driver.findElement(By.id("button"));
		weSignIn = driver.findElement(By.xpath("//*[@id='ajaxloginfields']/div[5]/button/span"));
		
		weSignIn.click();	
	}
	
	public void login(){
    	setUsername("0");//0 to load from properties file
    	setPassword("0");//0 to load from properties file
    	uncheckRememberMe();
    	clickSignIn();
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
		System.out.println("Login -> loading www.kayak.com");
		driver.get("http://www.kayak.com/");
		String url = driver.getCurrentUrl();
		System.out.println(url);
	}

}
