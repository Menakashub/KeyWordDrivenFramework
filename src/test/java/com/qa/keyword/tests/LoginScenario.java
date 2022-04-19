package com.qa.keyword.tests;

import org.testng.annotations.Test;

import com.qa.keyword.engine.KeywordEngine;

public class LoginScenario {
	public KeywordEngine keywordengine;
	
	@Test
	public void loginTest() {
		keywordengine= new KeywordEngine();
		keywordengine.startExecution("Sheet1");
	}
	
	

}
