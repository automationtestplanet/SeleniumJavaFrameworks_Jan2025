package org.openmrs.frameworks;

import java.util.Map;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pages.Utils;

public class OpenMrsHybridFramework extends BaseTest {

	@Test(dataProvider = "TestData")
	public void registerPatientTest(Map<String, String> testData) {
		utils.captureScreenshot();
		Reporter.log("<img src=\"" + Utils.screenshotsPath + "\" />");
		homePage.ClickModule("Register a patient");
		utils.captureScreenshot();
		Reporter.log("<img src=\"" + Utils.screenshotsPath + "\" />");
		registrationPage.registerPatientDetails(testData.get("Name"), testData.get("Gender"),
				testData.get("DateOfBirth"), testData.get("Address"), testData.get("PhoneNumber"));
		utils.captureScreenshot();
		Reporter.log("<img src=\"" + Utils.screenshotsPath + "\" />");
		registrationPage.verifyPatientDetails(testData.get("Name"), testData.get("Gender"), testData.get("DateOfBirth"),
				testData.get("PhoneNumber"));
		registrationPage.clickConfirmButton();
		utils.captureScreenshot();
		Reporter.log("<img src=\"" + Utils.screenshotsPath + "\" />");
		patientDetailsPage.verifyRegisteredPatientName(testData.get("Name"));
		patientDetailsPage.verifyPatientId();
	}

	@Test(dataProvider = "TestData")
	public void findPatientTest(Map<String, String> testData) {
		homePage.ClickModule("Find Patient Record");
		findPatientPage.setPatientNameOrId(testData.get("Name"));
		findPatientPage.verifyTableData("Name", testData.get("Name"));
		findPatientPage.clickFindPatientTableFirstRecord();
		patientDetailsPage.verifyRegisteredPatientName(testData.get("Name"));
		patientDetailsPage.verifyPatientId();
	}

	@Test(dataProvider = "TestData")
	public void activateVisitsAndAddAttachmentsTest(Map<String, String> testData) {
		homePage.ClickModule("Find Patient Record");
		findPatientPage.setPatientNameOrId(testData.get("Name"));
		findPatientPage.clickFindPatientTableFirstRecord();
		patientDetailsPage.startTheVisit();
		patientDetailsPage.clickAttachmentsButtonInStartVisitPage();
		String uploadFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\"
				+ testData.get("UploadFilePath");
		attachmentsPage.uploadFile(uploadFilePath, testData.get("Caption"));
	}

	@Test(dataProvider = "TestData")
	public void deletePatientTest(Map<String, String> testData) {
		utils.captureScreenshot();
		homePage.ClickModule("Find Patient Record");
		findPatientPage.setPatientNameOrId(testData.get("Name"));
		utils.captureScreenshot();
		findPatientPage.clickFindPatientTableFirstRecord();
		utils.captureScreenshot();
		patientDetailsPage.deletePatient(testData.get("Reason"));
		findPatientPage.setPatientNameOrId(testData.get("Name"));
		utils.captureScreenshot();
		findPatientPage.verifyNoMatchingRecordsFoundMessage();
	}
}
