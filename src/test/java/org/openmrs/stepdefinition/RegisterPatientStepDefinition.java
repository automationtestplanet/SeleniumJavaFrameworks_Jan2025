package org.openmrs.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PatientDetailsPage;
import pages.RegistraionPage;

public class RegisterPatientStepDefinition extends BaseClass {

	public RegistraionPage registrationPage;
	public PatientDetailsPage patientDetailsPage;

	@Given("the user Open Mrs Home Page")
	public void theUserOpenMrsHomePage() {
		homePage.verifyPageTitle("Home");
	}

	@When("the user cliks on {string} tile")
	public void theUserCliksOnTile(String tileName) {
		homePage.ClickModule(tileName);
	}

	@When("the user enters the Patient details with Name {string}, Gender {string}, DateOfBirth {string}, Address {string} and PhoneNumber {string}")
	public void theUserEntersThePatientDetailsWithNameGenderDateOfBirthAddressAndPhoneNumber(String name, String gender,
			String dateOfBirth, String address, String phoneNumber) {
		registrationPage = new RegistraionPage(driver);
		registrationPage.registerPatientDetails(name, gender, dateOfBirth, address, phoneNumber);

	}

	@Then("patient details Name {string}, Gender {string}, DateOfBirth {string} and PhoneNumber {string} must be entered properly")
	public void patientDetailsNameGenderDateOfBirthAndPhoneNumberMustBeEnteredProperly(String name, String gender,
			String dateOfBirth, String phoneNumber) {
		registrationPage.verifyPatientDetails(name, gender, dateOfBirth, phoneNumber);

	}

	@When("the user clicks confirm button")
	public void theUserClicksConfirmButton() {
		registrationPage.clickConfirmButton();
	}

	@Then("the Patient details must be registered and Patient Name {string} must be diplayed properly")
	public void thePatientDetailsMustBeRegisteredAndPatientNameMustBeDiplayedProperly(String name) {
		patientDetailsPage = new PatientDetailsPage(driver);
		patientDetailsPage.verifyRegisteredPatientName(name);

	}

	@Then("PatientId created successfully")
	public void patientIdCreatedSuccessfully() {
		patientDetailsPage.verifyPatientId();
	}

}
