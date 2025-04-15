package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class PatientDetailsPage extends BasePage {

	public PatientDetailsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(className = "PersonName-givenName")
	WebElement givenNameElement;

	@FindBy(className = "PersonName-familyName")
	WebElement familyNameElement;

	@FindBy(xpath = "//em[contains(text(),'Patient ID')]//following-sibling::span")
	WebElement patientIdElement;

	@FindBy(xpath = "//div[contains(text(),'Start Visit')]//ancestor::a")
	WebElement startVisitsLink;

	@FindBy(xpath = "//div[@id='quick-visit-creation-dialog']//descendant::button[contains(text(),'Confirm')]")
	WebElement startVisitCoonfirmButton;

	@FindBy(xpath = "//div[@class='action-section']//h3[contains(text(),'Current Visit Actions')]//following-sibling::li//div[contains(text(),'End Visit')]//ancestor::a")
	WebElement endVisitLink;

	@FindBy(xpath = "//div[@class='action-section']//h3[contains(text(),'Current Visit Actions')]//following-sibling::li//div[contains(text(),'Attachments')]//ancestor::a")
	WebElement attachmentsButton;

	@FindBy(xpath = "//a[contains(@id,'attachments')]")
	WebElement attachmentsButtonInStartVisitPage;

	@FindBy(xpath = "//a[contains(@href,'EndVisit')]")
	WebElement endVisitButtonInStartVisitPage;

	@FindBy(xpath = "//div[contains(text(),'Delete Patient')]//ancestor::a")
	WebElement deletePatientLink;

	@FindBy(id = "delete-reason")
	WebElement deletReasonField;

	@FindBy(xpath = "//div[@id='delete-patient-creation-dialog']//button[contains(text(),'Confirm')]")
	WebElement deleteConfirmButton;

	public WebElement getAttachmentsButtonInStartVisitPage() {
		return attachmentsButtonInStartVisitPage;
	}

	public WebElement getEndVisitButtonInStartVisitPage() {
		return endVisitButtonInStartVisitPage;
	}

	public WebElement getDeletePatientLink() {
		return deletePatientLink;
	}

	public WebElement getDeletReasonField() {
		return deletReasonField;
	}

	public WebElement getDeleteConfirmButton() {
		return deleteConfirmButton;
	}

	public WebElement getAttachmentsButton() {
		return attachmentsButton;
	}

	public WebElement getEndVisitLink() {
		return endVisitLink;
	}

	public WebElement getStartVisitCoonfirmButton() {
		return startVisitCoonfirmButton;
	}

	public WebElement getStartVisitsLink() {
		return startVisitsLink;
	}

	public WebElement getGivenNameElement() {
		return givenNameElement;
	}

	public WebElement getFamilyNameElement() {
		return familyNameElement;
	}

	public WebElement getPatientIdElement() {
		return patientIdElement;
	}

//	public void verifyRegisteredPatientName(String name) {
//		String nameArr[] = name.split(",");
//		String givenName = driver.findElement(By.className("PersonName-givenName")).getText();
//		String familyName = driver.findElement(By.className("PersonName-familyName")).getText();
//
//		Assert.assertTrue(givenName.contains(nameArr[0].trim()), "Given Name is not matching");
//		Assert.assertTrue(familyName.contains(nameArr[1].trim()), "Family Name is not matching");
//	}
//
//	public String getPatientId() {
//		return driver.findElement(By.xpath("//em[contains(text(),'Patient ID')]//following-sibling::span")).getText();
//	}

	public void verifyRegisteredPatientName(String name) {
		String nameArr[] = name.split(",");
		Assert.assertTrue(getElementText(getGivenNameElement()).contains(nameArr[0].trim()),
				"Given Name is not matching");
		Assert.assertTrue(getElementText(getFamilyNameElement()).contains(nameArr[1].trim()),
				"Family Name is not matching");
	}

	public String getPatientId() {
		return getElementText(getPatientIdElement());
	}

	public void verifyPatientId() {
		String patientId = getPatientId();
		System.out.println(patientId);
		Assert.assertNotNull(patientId);
	}

	public void clickStartVisitLink() {
		getStartVisitsLink().click();
	}

	public void clickStartVisitConfirmButton() {
		getStartVisitCoonfirmButton().click();
	}

	public void startVisit() {
		clickStartVisitLink();
		clickStartVisitConfirmButton();
		Assert.assertTrue(isElementDisplayed(getEndVisitLink()), "End Visit button not displayed ");
	}

	public void startTheVisit() {
		clickStartVisitLink();
		clickStartVisitConfirmButton();
		Assert.assertTrue(isElementDisplayed(getEndVisitButtonInStartVisitPage()), "End Visit button not displayed ");
	}

	public void clickAttachmentsButton() {
		getAttachmentsButton().click();
	}

	public void clickAttachmentsButtonInStartVisitPage() {
		getAttachmentsButtonInStartVisitPage().click();
	}

	public void clickDeletePatientLink() {
		getDeletePatientLink().click();
	}

	public void setDeleteReason(String deleteReason) {
		getDeletReasonField().sendKeys(deleteReason);
	}

	public void clickDeleteConfirmButton() {
		getDeleteConfirmButton().click();
	}

	public void deletePatient(String reason) {
		clickDeletePatientLink();
		setDeleteReason(reason);
		clickDeleteConfirmButton();
	}
}
