package com.sel.keyword;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File; 
import java.io.IOException;
import java.util.Date; 
import jxl.*; 
import jxl.read.biff.BiffException;

public class kayak {
    public static void main(String arg[]){
    	String sSteps="default";
    	WebDriver driver;
    	try {
			Workbook workbook = Workbook.getWorkbook(new File("Tests.xls"));
			Sheet sheet = workbook.getSheet(0);
			System.out.println("Total Steps: "+(sheet.getRows()-1));
			driver = new FirefoxDriver();
	    	for (int i=1;i<=sheet.getRows()-1;i++){
				Cell cellValue = sheet.getCell(1,i);
				sSteps = cellValue.getContents();
				System.out.println("Executing step "+sSteps);
		    	switch (sSteps){
		    		case "Login":
		    	    	driver.get("http://www.kayak.com");
		    	    	Login login = new Login(driver);
		    	    	login.login();
		    			break;
		    		case "Click Flight":
		    			Homepage homepage=new Homepage(driver);
		    			homepage.clickFlights();
		    			break;
		    		case "Click One Way Trip":
		    			Flights flights = new Flights(driver);
		    			flights.clickOneWayTrip();
		    			break;
		    		case "Set Depart Date":
		    			Flights flights1 = new Flights(driver);
		    			flights1.setDepartDate();    			
		    			break;
		    		default:
		    			System.out.println(sSteps + ": Step not defined or registered");
		    			break;
		    	}
	    	}
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    //close main method	
    }
//Close kayak class
}
