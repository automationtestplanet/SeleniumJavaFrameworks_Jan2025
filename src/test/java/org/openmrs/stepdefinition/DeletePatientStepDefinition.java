package org.openmrs.stepdefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FindPatientPage;
import pages.PatientDetailsPage;

public class DeletePatientStepDefinition extends BaseClass {

	public FindPatientPage findPatientPage;
	public PatientDetailsPage patientDetailsPage;

	@When("the user searches Patient record with Patien Name {string}")
	public void theUserSearchesPatientRecordWithPatienName(String patientName) {
		findPatientPage = new FindPatientPage(driver);
		findPatientPage.setPatientNameOrId(patientName);
	}

	@When("the user clicks search results first record")
	public void theUserClicksSearchResultsFirstRecord() {
		findPatientPage.clickFindPatientTableFirstRecord();
	}

	@When("the user delete the Patient record with reason {string}")
	public void theUserDeleteThePatientRecordWithReason(String reason) {
		patientDetailsPage = new PatientDetailsPage(driver);
		patientDetailsPage.deletePatient(reason);
	}

	@Then("the No matching records messgae must be displayed")
	public void theNoMatchingRecordsMessgaeMustBeDisplayed() {
		findPatientPage.verifyNoMatchingRecordsFoundMessage();
	}

}
