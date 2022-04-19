package com.qa.keyword.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver init_driver(String browsername) {
		if(browsername.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			if(prop.getProperty("headless").equals("yes")) {
				//for headless code
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--headless--");
				driver = new ChromeDriver(option);
			}
			else {
				driver = new ChromeDriver();
			}
		}
		return driver;
	}
	
	public Properties init_properties() {
		prop= new Properties();
		try {
			FileInputStream ip = new FileInputStream("C://Users//Menakas//eclipse-workspace//KeywordDrivenHubSpot//src//main//java//com//qa//keyword//config//config.properties");
			try {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return prop;
	}

}
