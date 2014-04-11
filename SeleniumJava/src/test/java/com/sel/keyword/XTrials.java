/*1st Feb

//*[@id="datepicker"]/div[2]/span[2]/span[2]/span[1]/span[7]/span

20th Feb
//*[@id="datepicker"]/div[2]/span[2]/span[2]/span[4]/span[5]/span
21st Feb
//*[@id="datepicker"]/div[2]/span[2]/span[2]/span[4]/span[6]/span
22nd Feb
//*[@id="datepicker"]/div[2]/span[2]/span[2]/span[4]/span[7]/span
23rd Feb
//*[@id="datepicker"]/div[2]/span[2]/span[2]/span[5]/span[1]/span

//*[@id="datepicker"]/div[3]/span[2]/span[2]/span[4]/span[1]/span
16th mar
//*[@id="datepicker"]/div[3]/span[2]/span[2]/span[4]/span[1]/span

sCurrent 02-23-2014 sCurYear sCurMonth sCurDay
sDepart 05-12-2014 sDepYear sDepMonth sDepDay

Max Month Diff 01-01-2014 to 01-01-2015 = 12
sDepYear - sCurYear = 0 * 12
Month of sCurYear = 2
Remaining Month in sCuryear = 12-2
Month of sDepYear = 5

02-22-2014 - 08-22-2014
2014-2014=0*12
08-02 = 6

03-12-2014 - 05-22-2015
2015-2014 =1
12 + 12 -3 -12 +5 = 14

03-12-2014 - 07-24-2018
2018-2014 = 4 * 12 = 48 +12 -3 -12 + 7
Months = (


2014 = 9
2015 = 12
2016 = 12
2017 = 12
2018 = 7
Total = 52

if (sDepYear - sCurYear==0){
	rem = sDepMonth - sCurMonth
	}
else{
	
	
	}



 * */

package com.sel.keyword;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class XTrials {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//tryGetMonth("01-01-2015");//MMM-DD-YYYY
		WebDriver driver;
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.kayak.com/flights");
		
		setDepartDate(driver,"06-27-2014");
		
	}
	public static void setDepartDate(WebDriver driver,String sDepart){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement weTravelDate = driver.findElement(By.id("travel_dates"));
		weTravelDate.click();
		WebElement weTravelYear = driver.findElement(By.className("r9-datepicker-year"));
		WebElement weMonthFirst = driver.findElement(By.className("r9-datepicker-month-name"));
		String delims = "[-]";
		String[] tokens = sDepart.split(delims);
		int iMonthDiff = 0;
		int i=1;
		int iCurMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
		int iCurYear = Calendar.getInstance().get(Calendar.YEAR);
		int iDepartYear = Integer.parseInt(tokens[2]);
		int iDepartMonth = Integer.parseInt(tokens[0]);
		int iDepartDay = Integer.parseInt(tokens[1]);
		int curYear = Calendar.getInstance().get(Calendar.YEAR);
		iMonthDiff = ((iDepartYear-iCurYear)*12)-iCurMonth+iDepartMonth;
		System.out.println("Month Diff"+iMonthDiff);
		System.out.println("iCurYear "+ iCurYear +" iDepartYear "+iDepartYear+" iCurMonth "+iCurMonth+" iDepartMonth "+iDepartMonth);
		int next=0;
		//loop to click the next month button for the difference in month
		for (next=0;next<iMonthDiff;next++){
			System.out.println("loop count: "+next);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='datepicker']/div[3]/span[1]/a")));
			WebElement weMonthNext = driver.findElement(By.xpath("//*[@id='datepicker']/div[3]/span[1]/a"));
			weMonthNext.click();
			//System.out.println("Month Year "+weMonthFirst.getText()+weTravelYear.getText());
		}
		List<WebElement> weList = driver.findElements(By.className("r9-datepicker-item"));
		System.out.println(weList.size());
		for (WebElement temp : weList) {
			System.out.println(i+" Line: "+temp.getText()+" Length "+temp.getText().length()+"\n");
			if (temp.getText().length()>1){
				int val = Integer.parseInt(temp.findElement(By.tagName("SPAN")).getText());
				if (val==iDepartDay){
					System.out.println("Info Clicked "+iDepartDay);
					temp.click();
					weTravelDate.click();
					break;
				}
			}
			i++;
		}
		
		//Reinitialize Element due to staleElement Exception
		//*[@id="datepicker"]/div[2]/span[2]/span[2]/span[4]/span[6]
		WebElement weHighlighted = driver.findElement(By.cssSelector("r9-datepicker-item.r9-datepicker-enabled.r9-datepicker-item-start.r9-datepicker-item-selected.r9-datepicker-item-highlighted"));
		System.out.println("Highlighted "+weHighlighted.getText());
		weTravelYear = driver.findElement(By.className("r9-datepicker-year"));
		weMonthFirst = driver.findElement(By.className("r9-datepicker-month-name"));
		System.out.println("Month Year "+weMonthFirst.getText()+weTravelYear.getText());
		System.out.println("YEAR "+curYear);
		
		
		/*Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(cal.getTime());
		System.out.println(cal.getWeekYear());

		String formatted = format1.format(cal.getTime());
		System.out.println(formatted);*/
		
	}
	//Intial test commands
	public static void kayaktest(WebDriver driver){
		int i=1;
		WebElement weTravelDate;
		WebElement weMonthFirst;
		WebElement weMonthSecond;
		WebDriverWait wait;
		weTravelDate = driver.findElement(By.id("travel_dates"));
		weTravelDate.click();
		weMonthFirst = driver.findElement(By.className("r9-datepicker-month-name"));
		System.out.println("Month " + weMonthFirst.getText());
		
		List<WebElement> weMonthList = driver.findElements(By.className("r9-datepicker-month-name"));
		for (WebElement temp : weMonthList) {
			System.out.println("Month: "+temp.getText()+" Length "+temp.getText().length()+"\n");
		}
		
		List<WebElement> weList = driver.findElements(By.className("r9-datepicker-item"));
		System.out.println(weList.size());
		for (WebElement temp : weList) {
			System.out.println(i+" Line: "+temp.getText()+" Length "+temp.getText().length()+"\n");
			if (temp.getText().length()>1){
				int val = Integer.parseInt(temp.findElement(By.tagName("SPAN")).getText());
				if (val==25){
					System.out.println("Hey 25!");
					temp.click();
					weTravelDate.click();
				}
			}
			i++;
		}
	}
	public static void tryGetMonth(String sDepart){
		
		//2018-2014 = 4 * 12 = 48 +12 -3 -12 + 7
		int iMonthDiff = 0;
		int iCurMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
		int iCurYear = Calendar.getInstance().get(Calendar.YEAR);
		String delims = "[-]";
		String[] tokens = sDepart.split(delims);
		int iDepartYear = Integer.parseInt(tokens[2]);
		int iDepartMonth = Integer.parseInt(tokens[0]);
		int iDepartDay = Integer.parseInt(tokens[1]);
		System.out.println("Month Num :" + iCurMonth);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");	
		Calendar calendar = new GregorianCalendar(iDepartYear,iDepartMonth,iDepartDay,00,00,00);
		int year       = calendar.get(Calendar.YEAR);
		int month      = calendar.get(Calendar.MONTH);
		iMonthDiff = ((iDepartYear-iCurYear)*12)-iCurMonth+iDepartMonth;
		System.out.println("Month Diff"+iMonthDiff);
		System.out.println("iCurYear "+ iCurYear +" iDepartYear "+iDepartYear+" iCurMonth "+iCurMonth+" iDepartMonth "+iDepartMonth);
		//Month Num :2
		//Month Diff-13
		//iCurYear 2014 iDepartYear 2015 iCurMonth 2 iDepartMonth 1
		
	}

}
