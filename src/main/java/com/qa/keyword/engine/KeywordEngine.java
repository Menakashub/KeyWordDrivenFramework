package com.qa.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.keyword.base.Base;

public class KeywordEngine {
	public WebDriver driver;
	public Properties prop;
	public static Workbook book;
	public static Sheet sheet;
	public Base base;
	public WebElement element;


	public final String Scenario_SheetPath="/Users/Menakas/eclipse-workspace/KeywordDrivenHubSpot/src/main/java/com/qa/keyword/scenarios/orangeHRM_scenarios.xlsx";

	public void startExecution(String sheetname) {
		String locatorname =null;
		String locatorvalue=null;
		//get the excel file , goto workbook , goto sheet
		FileInputStream file=null;
		try {
			file = new FileInputStream(Scenario_SheetPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book=WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet=book.getSheet("Sheet1");
		int k=0;// for getting column value
		for(int i=0;i<sheet.getLastRowNum();i++) {
			try {
		String locatorColVaue=	sheet.getRow(i+1).getCell(k+1).toString().trim(); //get the locator column value from Excel sheet id=username
		if(!locatorColVaue.equalsIgnoreCase("NA")) {
			locatorname=locatorColVaue.split("=")[0].trim();//here zero th value , will be id and 1 value will be username
			locatorvalue=locatorColVaue.split("=")[1].trim();
		}
		
		//get the action column value
		String action=sheet.getRow(i+1).getCell(k+2).toString().trim();
		String value=sheet.getRow(i+1).getCell(k+3).toString().trim();
		switch (action) {
		case "open browser":
			base= new Base();
			prop=base.init_properties();
			if(value.equalsIgnoreCase("NA") || value.isEmpty()) {
			driver=	base.init_driver(prop.getProperty("browser"));
			}else {
			driver=	base.init_driver(value);
			}
			break;
		case "enter url":
			if(value.equalsIgnoreCase("NA") || value.isEmpty()) {
				driver.get(prop.getProperty("url"));
			}
			else {
				driver.get(value);
			}
			break;
		case "quit":
			driver.quit();
			break;
		default:
			break;
		}
		switch (locatorname) {
		case "id":
			 element=driver.findElement(By.id(locatorvalue));
			if(action.equalsIgnoreCase("sendkeys")) {
				element.clear();
				element.sendKeys(value);
			}else if(action.equalsIgnoreCase("click")) {
				element.click();
			}
			locatorname=null;
			break;
		case "linkText" :
			element=driver.findElement(By.linkText(locatorvalue));
			element.click();
			locatorname=null;
			break;
		
		default:
			break;
		}
			
		}
			catch(Exception e) {
				e.printStackTrace();
			}
	}



	}
}
