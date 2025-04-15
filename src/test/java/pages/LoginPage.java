package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "username")
	private WebElement userNameFiled;

	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(id = "loginButton")
	private WebElement loginButton;

	public WebElement getUserNameFiled() {
		return userNameFiled;
	}

	public WebElement getPasswordField() {
		return passwordField;
	}

	public WebElement getModule(String moduleName) {
		return driver.findElement(By.id(moduleName));
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public void setUserName(String userName) {
		getUserNameFiled().sendKeys(userName);
	}

	public void setPassword(String password) {
		getPasswordField().sendKeys(password);
	}

	public void selectModule(String moduleName) {
		getModule(moduleName).click();
	}

	public void clickLoginButton() {
		getLoginButton().click();
	}

	public void loginToApplication(String userName, String password, String moduleName) {

//		userNameFiled = driver.findElement(By.id("username"));
//		userNameFiled.sendKeys(userName);
		setUserName(userName);

//		driver.findElement(By.id("password")).sendKeys(password);
//		passwordField.sendKeys(password);
		setPassword(password);

//		driver.findElement(By.id(moduleName)).click(); // if we have any parameter in locator value, we can not use
		// @FnddBy annotation to initialize the WebElement
		selectModule(moduleName);

//		driver.findElement(By.id("loginButton")).click();
//		loginButton.click();
		clickLoginButton();
	}
}
