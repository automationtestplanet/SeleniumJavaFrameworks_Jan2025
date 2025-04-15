package org.openmrs.stepdefinition;

import java.io.File;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BaseClass {

	@Before
	public void setUp() {
		loginPage.loginToApplication("Admin", "Admin123", "Registration Desk");
	}

	@After
	public void tearDown() {
		try {
			Thread.sleep(5000);
			homePage.clickLogout();
			Thread.sleep(5000);
			loginPage.verifyPageTitle("Login");
		} catch (Exception e) {
			System.out.println("Exception Occured while logout the application: " + e.getMessage());
		}
	}

	@AfterStep
	public void afterStep(Scenario scenario) {
		try {
//			scenario.isFailed() // to check scenario pass or fail
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			byte[] fileByteArray = FileUtils.readFileToByteArray(screenshot);
			scenario.attach(fileByteArray, "image/png", "screenshot");
		} catch (Exception e) {
			System.out.println("Exception Occured while attahing the screenshot to Scenario: " + e.getMessage());
		}
	}

	@AfterAll
	public static void before_or_after_all() {
		driver.quit();
	}

}
