package org.openmrs.frameworks;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pages.Utils;

public class OpenMrsDataDrivenFramework extends BaseTest {

	@Test(dataProvider = "RegisterPatientData")
	public void registerPatientTest(String name, String geder, String dateOfBirth, String address, String phoneNumber) {
		utils.captureScreenshot();
		Reporter.log("<img src=\"" + Utils.screenshotsPath + "\" />");
		homePage.ClickModule("Register a patient");
		utils.captureScreenshot();
		Reporter.log("<img src=\"" + Utils.screenshotsPath + "\" />");
		registrationPage.registerPatientDetails(name, geder, dateOfBirth, address, phoneNumber);
		utils.captureScreenshot();
		Reporter.log("<img src=\"" + Utils.screenshotsPath + "\" />");
		registrationPage.verifyPatientDetails(name, geder, dateOfBirth, phoneNumber);
		registrationPage.clickConfirmButton();
		utils.captureScreenshot();
		Reporter.log("<img src=\"" + Utils.screenshotsPath + "\" />");
		patientDetailsPage.verifyRegisteredPatientName(name);
		patientDetailsPage.verifyPatientId();
	}

	@Test(enabled = false)
	public void findPatientTest() {
		homePage.ClickModule("Find Patient Record");
		findPatientPage.setPatientNameOrId("Ram Kumar");
		findPatientPage.verifyTableData("Name", "Ram Kumar");
		findPatientPage.clickFindPatientTableFirstRecord();
		patientDetailsPage.verifyRegisteredPatientName("Ram, Kumar");
		patientDetailsPage.verifyPatientId();
	}

	@Test(enabled = false)
	public void activateVisitsAndAddAttachmentsTest() {
		homePage.ClickModule("Find Patient Record");
		findPatientPage.setPatientNameOrId("Ram Kumar");
		findPatientPage.clickFindPatientTableFirstRecord();
		patientDetailsPage.startTheVisit();
		patientDetailsPage.clickAttachmentsButtonInStartVisitPage();
		String uploadFilePath = "C:\\Users\\RAJU CHELLE\\Desktop\\UploadFile.pdf";
		attachmentsPage.uploadFile(uploadFilePath, "Test");
	}

	@Test
	public void deletePatientTest() {
		utils.captureScreenshot();
		homePage.ClickModule("Find Patient Record");
		findPatientPage.setPatientNameOrId("Ram Kumar");
		utils.captureScreenshot();
		findPatientPage.clickFindPatientTableFirstRecord();
		utils.captureScreenshot();
		patientDetailsPage.deletePatient("Other");
		findPatientPage.setPatientNameOrId("Ram Kumar");
		utils.captureScreenshot();
		findPatientPage.verifyNoMatchingRecordsFoundMessage();
	}
}
