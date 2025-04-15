package pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class FindPatientPage extends BasePage {

	public FindPatientPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "patient-search")
	WebElement patientSearchField;

	@FindBy(xpath = "//table[@id='patient-search-results-table']/thead/tr/th/div")
	List<WebElement> allHeaderElements;

	@FindBy(xpath = "//table[@id='patient-search-results-table']/tbody/tr/td")
	List<WebElement> allDataElements;

	@FindBy(xpath = "//table[@id='patient-search-results-table']/tbody/tr[1]")
	WebElement findPatientTableFirstRecord;

	@FindBy(xpath = "//td[contains(text(),'No matching records found')]")
	WebElement noRecordsFoundElement;

	public WebElement getNoRecordsFoundElement() {
		return noRecordsFoundElement;
	}

	public WebElement getFindPatientTableFirstRecord() {
		return findPatientTableFirstRecord;
	}

	public List<WebElement> getAllDataElements() {
		try {
//		waitForStalenessOfElements(allDataElements);
			Thread.sleep(5000);
			waitForVisibilityOfElements(allDataElements);
			return allDataElements;
		} catch (Exception e) {
			System.out.println("Exception occured while getting the fid patient Table Data");
			return null;
		}
	}

	public List<WebElement> getAllHeaderElements() {
		return allHeaderElements;
	}

	public WebElement getPatientSearchField() {
		return patientSearchField;
	}

	public void setPatientNameOrId(String patientIdOrName) {
		getPatientSearchField().sendKeys(patientIdOrName);
	}

	public Map<String, String> getfindPatientTableData() {
		Map<String, String> findPatientTableData = new LinkedHashMap<String, String>();
		for (int i = 0; i < getAllHeaderElements().size(); i++) {
			findPatientTableData.put(getAllHeaderElements().get(i).getText().trim(),
					getAllDataElements().get(i).getText().trim());
		}
		return findPatientTableData;
	}

	public void verifyTableData(String headerName, String expectedValue) {
		Map<String, String> data = getfindPatientTableData();
		Assert.assertTrue(data.get(headerName).equals(expectedValue), expectedValue + " is not matching");
	}

	public void clickFindPatientTableFirstRecord() {
		try {
			Thread.sleep(5000);
			getFindPatientTableFirstRecord().click();
		} catch (Exception e) {
			System.out
					.println("Exception Occured while clicking the find patient able first record: " + e.getMessage());
		}
	}

	public void verifyNoMatchingRecordsFoundMessage() {
		try {
			Thread.sleep(5000);
			Assert.assertTrue(isElementDisplayed(getNoRecordsFoundElement()), "Patient Record not deleted");
		} catch (Exception e) {
			System.out.println("Exception Occured while verifyng Patient Not found message");
		}
	}

}
