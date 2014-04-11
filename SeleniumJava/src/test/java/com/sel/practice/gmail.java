package com.sel.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class gmail {
    public static void main(String arg[]){
    	WebDriver driver = new FirefoxDriver();
    	driver.get("http://www.gmail.com");
    	Login login = new Login(driver);
    	login.get();
    	login.login();
    	LockedOut lockedout = new LockedOut(driver);
    	lockedout.clickNoThanks();
    	GmailHome gmailhome = new GmailHome(driver);
    	gmailhome.inboxCount();
    	gmailhome.clickCompose();
    	gmailhome.setTo("anygmailidtosendmailto@gmail.com");
    	gmailhome.setSubject("Automated mail");
    	gmailhome.clickSend();
    	gmailhome.verifyErrorAlert();
    	gmailhome.logout();
    }
}
