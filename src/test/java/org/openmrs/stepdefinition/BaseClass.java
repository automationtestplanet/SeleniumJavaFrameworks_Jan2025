package org.openmrs.stepdefinition;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;
import pages.Utils;

public class BaseClass {
	public static WebDriver driver;
	public static BasePage basePage;
	public static LoginPage loginPage;
	public static HomePage homePage;
	public static Utils utils;

	static {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		basePage = new BasePage(driver);
		basePage.navigateToApplication("https://demo.openmrs.org/openmrs/login.htm");
		basePage.verifyPageTitle("Login");
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		utils = new Utils(driver);
	}
}
