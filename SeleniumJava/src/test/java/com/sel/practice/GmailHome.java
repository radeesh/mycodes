package com.sel.practice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class GmailHome extends LoadableComponent<GmailHome>{

	private final WebDriver driver;
	
	WebElement weCompose;
	//List<WebElement> weDiv;
	WebElement weInbox;
	WebDriverWait wait;
	WebElement weSubjectBox;
	WebElement weSend;
	WebElement weErrorAlert;
	WebElement weTo;
	WebElement weNewMail;
	List<WebElement> weNewMailsList;
	//WebElement weHijackPic = driver.findElement(By.className("hijack-pic"));
	//Boolean isPresent = driver.findElements(By.className("hijack-pic")).size()<0;
	//System.out.println("Hijack Image is present "+isPresent);
	public GmailHome(WebDriver driver) {
	    this.driver = driver;
	    wait = new WebDriverWait(driver,10);

	}
	
	public void inboxCount(){
		wait.until(ExpectedConditions.elementToBeClickable(By.className("J-Ke")));
		weInbox = driver.findElement(By.className("J-Ke"));
		System.out.println(weInbox.getText());
		String sNewMails = weInbox.getText();
		String[] sInbox= sNewMails.split("\\D+");
		System.out.println("New mails "+sInbox[1]);
	}

	public void logout(){
		wait.until(ExpectedConditions.elementToBeClickable(By.className("gb_K")));
		WebElement userPic = driver.findElement(By.className("gb_K"));
		userPic.click();
		WebElement logoutBtn = driver.findElement(By.id("gb_71"));
		logoutBtn.click();		
	}

	public void clickCompose(){
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='button' and .='COMPOSE']")));
	    weSend = driver.findElement(By.xpath("//div[@role='button' and .='COMPOSE']"));
		weSend.click();	
	}
	
	public void setSubject(String sSubject){
		wait.until(ExpectedConditions.elementToBeClickable(By.name("subjectbox")));
		weSubjectBox = driver.findElement(By.name("subjectbox"));
		weSubjectBox.sendKeys(sSubject);
	}
	
	public void setTo(String sTo){
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@aria-label='Address' and @name='to']")));
		//wait.until(ExpectedConditions.elementToBeClickable(By.name("to")));
		//weTo = driver.findElement(By.name("to"));
		weTo = driver.findElement(By.xpath("//textarea[@aria-label='Address' and @name='to']"));
		weTo.sendKeys(sTo);
	}
	
	public void clickSend(){
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='button' and .='Send']")));
	    weSend = driver.findElement(By.xpath("//div[@role='button' and .='Send']"));
		weSend.click();
	}
	
	public void verifyErrorAlert(){
		try{
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='alertdialog']")));
		weErrorAlert = driver.findElement(By.xpath("//div[@role='alertdialog']"));
		System.out.println(weErrorAlert.getText());
		}
		catch (Exception e){
			System.out.println("No error messages.");
		}
	}
	
	public void newMails(){
		weNewMail = driver.findElement(By.xpath("//div[@id='Cp']"));
		weNewMailsList = driver.findElements(By.xpath("//div[@id='Cp'/tbody/tr]"));
		System.out.println(weErrorAlert.getText());
	}
	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		String url = driver.getCurrentUrl();
	    Assert.assertTrue(url.startsWith("https://mail.google.com/"),"Gmail homepage is loaded: " + url);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		driver.get("https://mail.google.com/");
		
	}

}
