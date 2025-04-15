package org.openmrs.frameworks;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import pages.AttachmentsPage;
import pages.BasePage;
import pages.ExcelUtils;
import pages.FindPatientPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PatientDetailsPage;
import pages.RegistraionPage;
import pages.Utils;

public class BaseTest {

	public WebDriver driver;
	public BasePage basePage;
	public LoginPage loginPage;
	public HomePage homePage;
	public RegistraionPage registrationPage;
	public PatientDetailsPage patientDetailsPage;
	public FindPatientPage findPatientPage;
	public AttachmentsPage attachmentsPage;
	public Utils utils;

	@BeforeTest
	public void openBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		basePage = new BasePage(driver);
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		registrationPage = new RegistraionPage(driver);
		patientDetailsPage = new PatientDetailsPage(driver);
		findPatientPage = new FindPatientPage(driver);
		attachmentsPage = new AttachmentsPage(driver);
		utils = new Utils(driver);
	}

	@BeforeClass
	public void openOpenMrsApplication() {
		basePage.navigateToApplication("https://demo.openmrs.org/openmrs/login.htm");
		basePage.verifyPageTitle("Login");
	}

	@BeforeMethod
	public void login() {
		loginPage.loginToApplication("Admin", "Admin123", "Registration Desk");
		loginPage.verifyPageTitle("Home");
	}

	@AfterMethod
	public void logout() {
		try {
			Thread.sleep(5000);
			homePage.clickLogout();
			Thread.sleep(5000);
			loginPage.verifyPageTitle("Login");
		} catch (Exception e) {
			System.out.println("Exception Occured while logout the application: " + e.getMessage());
		}
	}

	@AfterTest
	public void closeBrowser() {
//		driver.close();  // it can close single browser window opened by driver
		driver.quit(); // it can close all browser windows opened by driver
	}

	@DataProvider(name = "RegisterPatientData")
	public Iterator<String[]> testDataProvider() {

		List<String[]> testData = ExcelUtils.readDataFromExcelForDataDriver(
				System.getProperty("user.dir") + "//src//test//resources//TestData//TestData.xlsx",
				"RegisterPatientDetails");
		return testData.iterator();
	}

	@DataProvider(name = "TestData")
	public Iterator<Object[]> hybridTestDataProvider(Method testMethodName) {
		ExcelUtils excelUtils = new ExcelUtils();
		return excelUtils.readDataFromExcelForHybridDriven(
				System.getProperty("user.dir") + "//src//test//resources//TestData//TestData.xlsx", "TestData",
				testMethodName.getName());

	}
}
