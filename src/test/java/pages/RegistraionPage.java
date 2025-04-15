package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class RegistraionPage extends BasePage {

	public RegistraionPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(name = "givenName")
	WebElement givenNameField;

	@FindBy(name = "familyName")
	WebElement familyNameField;

	@FindBy(id = "next-button")
	WebElement nextButton;

	@FindBy(id = "gender-field")
	WebElement genderDropdownElement;

	@FindBy(id = "birthdateDay-field")
	WebElement birthDayField;

	@FindBy(id = "birthdateMonth-field")
	WebElement birthMonthDropdwonElement;

	@FindBy(id = "birthdateYear-field")
	WebElement birthYearField;

	@FindBy(id = "address1")
	WebElement address1Field;

	@FindBy(id = "cityVillage")
	WebElement cityField;

	@FindBy(id = "stateProvince")
	WebElement stateField;

	@FindBy(id = "country")
	WebElement countryField;

	@FindBy(id = "postalCode")
	WebElement postalCodeField;

	@FindBy(name = "phoneNumber")
	WebElement phoneNumberField;

	@FindBy(xpath = "//p/span[contains(text(),'Name')]//parent::p")
	WebElement actualNameElement;

	@FindBy(xpath = "//p/span[contains(text(),'Gender:')]//parent::p")
	WebElement actualGenderElement;

	@FindBy(xpath = "//p/span[contains(text(),'Birthdate:')]//parent::p")
	WebElement actualDateOfBirthElement;

	@FindBy(xpath = "//p/span[contains(text(),'Phone Number:')]//parent::p")
	WebElement actualPhoneNumberElement;

	@FindBy(css = "input[value='Confirm']")
	WebElement confirmButton;

	public WebElement getGivenNameField() {
		return givenNameField;
	}

	public WebElement getFamilyNameField() {
		return familyNameField;
	}

	public WebElement getNextButton() {
		return nextButton;
	}

	public WebElement getGenderDropdownElement() {
		return genderDropdownElement;
	}

	public WebElement getBirthDayField() {
		return birthDayField;
	}

	public WebElement getBirthMonthDropdwonElement() {
		return birthMonthDropdwonElement;
	}

	public WebElement getBirthYearField() {
		return birthYearField;
	}

	public WebElement getAddress1Field() {
		return address1Field;
	}

	public WebElement getCityField() {
		return cityField;
	}

	public WebElement getStateField() {
		return stateField;
	}

	public WebElement getCountryField() {
		return countryField;
	}

	public WebElement getPostalCodeField() {
		return postalCodeField;
	}

	public WebElement getPhoneNumberField() {
		return phoneNumberField;
	}

	public WebElement getActualNameElement() {
		return actualNameElement;
	}

	public WebElement getActualGenderElement() {
		return actualGenderElement;
	}

	public WebElement getActualDateOfBirthElement() {
		return actualDateOfBirthElement;
	}

	public WebElement getActualPhoneNumberElement() {
		return actualPhoneNumberElement;
	}

	public WebElement getConfirmButton() {
		return confirmButton;
	}

//	public void enterPatientName(String name) {
//		String nameArr[] = name.split(",");
//		driver.findElement(By.name("givenName")).sendKeys(nameArr[0].trim());
//		driver.findElement(By.name("familyName")).sendKeys(nameArr[1].trim());
//	}
//
//	public void clickNextButton() {
//		driver.findElement(By.id("next-button")).click();
//	}
//
//	public void selectGender(String gender) {
//		WebElement genderDropdownElement = driver.findElement(By.id("gender-field"));
//		Select genderDropDown = new Select(genderDropdownElement);
//		genderDropDown.selectByVisibleText(gender);
//	}
//
//	public void enterDateOfBirth(String dateOfBirth) {
//		String[] dateOfBirthArr = dateOfBirth.split(",");
//		driver.findElement(By.id("birthdateDay-field")).sendKeys(dateOfBirthArr[0].trim());
//		WebElement birthMonthElement = driver.findElement(By.id("birthdateMonth-field"));
//		Select birthMonthDropdwon = new Select(birthMonthElement);
//		birthMonthDropdwon.selectByVisibleText(dateOfBirthArr[1].trim());
//		driver.findElement(By.id("birthdateYear-field")).sendKeys(dateOfBirthArr[2].trim());
//	}
//
//	public void enterAddress(String address) {
//		String[] addressArr = address.split(",");
//		driver.findElement(By.id("address1")).sendKeys(addressArr[0].trim());
//		driver.findElement(By.id("cityVillage")).sendKeys(addressArr[1].trim());
//		driver.findElement(By.id("stateProvince")).sendKeys(addressArr[2].trim());
//		driver.findElement(By.id("country")).sendKeys(addressArr[3].trim());
//		driver.findElement(By.id("postalCode")).sendKeys(addressArr[4].trim());
//	}
//
//	public void enterPhoneNumber(String phoneNumber) {
//		driver.findElement(By.name("phoneNumber")).sendKeys(phoneNumber);
//	}
//
//	public void verifyPatientDetails(String name, String gender, String dateOfBirth, String phoneNumber) {
//		String actualName = driver.findElement(By.xpath("//p/span[contains(text(),'Name')]//parent::p")).getText();
//		String actualGender = driver.findElement(By.xpath("//p/span[contains(text(),'Gender:')]//parent::p")).getText();
//		String actualBirthDate = driver.findElement(By.xpath("//p/span[contains(text(),'Birthdate:')]//parent::p"))
//				.getText();
//		String actualPhoneNumber = driver.findElement(By.xpath("//p/span[contains(text(),'Phone Number:')]//parent::p"))
//				.getText();
//		Assert.assertTrue(actualName.contains(name), "Name is not matching");
//		Assert.assertTrue(actualGender.contains(gender), "Gerder is not matching");
//		Assert.assertTrue(actualBirthDate.contains(dateOfBirth), "BirthDate is not matching");
//		Assert.assertTrue(actualPhoneNumber.contains(phoneNumber), "Phone Number is not matching");
//	}
//
//	public void clickConfirmButton() {
//		driver.findElement(By.cssSelector("input[value='Confirm']")).click();
//	}

	public void setGivenName(String givenName) {
		getGivenNameField().sendKeys(givenName);
	}

	public void setFamilyName(String familyName) {
		getFamilyNameField().sendKeys(familyName);
	}

	public void enterPatientName(String name) {
		String nameArr[] = name.split(",");
		setGivenName(nameArr[0].trim());
		setFamilyName(nameArr[1].trim());
	}

	public void clickNextButton() {
		getNextButton().click();
	}

	public void selectGender(String gender) {
		selectDropdownValueByVisibleText(getGenderDropdownElement(), gender);
	}

	public void setBirthDay(String bithDay) {
		getBirthDayField().sendKeys(bithDay);
	}

	public void setBirthYear(String birthYear) {
		getBirthYearField().sendKeys(birthYear);
	}

	public void enterDateOfBirth(String dateOfBirth) {
		String[] dateOfBirthArr = dateOfBirth.split(",");
		setBirthDay(dateOfBirthArr[0].trim());
		selectDropdownValueByVisibleText(getBirthMonthDropdwonElement(), dateOfBirthArr[1].trim());
		setBirthYear(dateOfBirthArr[2].trim());
	}

	public void setAddress1(String streetName) {
		getAddress1Field().sendKeys(streetName);
	}

	public void setCity(String cityName) {
		getCityField().sendKeys(cityName);
	}

	public void setState(String stateName) {
		getStateField().sendKeys(stateName);
	}

	public void setCountry(String countryName) {
		getCountryField().sendKeys(countryName);
	}

	public void setPostalCode(String postalCode) {
		getPostalCodeField().sendKeys(postalCode);
	}

	public void enterAddress(String address) {
		try {
			String[] addressArr = address.split(",");
			setAddress1(addressArr[0].trim());
			Thread.sleep(1000);
			setCity(addressArr[1].trim());
			Thread.sleep(1000);
			setState(addressArr[2].trim());
			Thread.sleep(1000);
			setCountry(addressArr[3].trim());
			Thread.sleep(1000);
			setPostalCode(addressArr[4].trim());
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Exception Occued whle entering the address: " + e.getMessage());
		}
	}

	public void setPhoneNumber(String phoneNumber) {
		getPhoneNumberField().sendKeys(phoneNumber);
	}

	public void enterPhoneNumber(String phoneNumber) {
		setPhoneNumber(phoneNumber);
	}

	public void verifyPatientDetails(String name, String gender, String dateOfBirth, String phoneNumber) {
		Assert.assertTrue(getElementText(getActualNameElement()).contains(name), "Name is not matching");
		Assert.assertTrue(getElementText(getActualGenderElement()).contains(gender), "Gerder is not matching");
		Assert.assertTrue(getElementText(getActualDateOfBirthElement()).contains(dateOfBirth),
				"BirthDate is not matching");
		Assert.assertTrue(getElementText(getActualPhoneNumberElement()).contains(phoneNumber),
				"Phone Number is not matching");
	}

	public void clickConfirmButton() {
		try {
			getConfirmButton().click();
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println("Exception Occured while clicking on Confirm Button: " + e.getMessage());
		}
	}

	public void registerPatientDetails(String name, String gender, String dateOfBirth, String address,
			String phoneNumber) {
		this.enterPatientName(name);
		this.clickNextButton();
		this.selectGender(gender);
		this.clickNextButton();
		this.enterDateOfBirth(dateOfBirth);
		this.clickNextButton();
		this.enterAddress(address);
		this.clickNextButton();
		this.enterPhoneNumber(phoneNumber);
		this.clickNextButton();
		this.clickNextButton();
	}
}
