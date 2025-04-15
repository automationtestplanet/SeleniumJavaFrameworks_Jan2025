package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor jse;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		jse = (JavascriptExecutor) driver;
	}

	public void navigateToApplication(String url) {
		driver.get(url);
	}

	public String getPageTitle() {
		return driver.getTitle().trim();
	}

	public boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	public boolean isElementEnabled(WebElement element) {
		return element.isEnabled();
	}

	public WebElement getPageHeader(String pageHeader) {
		return driver.findElement(By.xpath("//h2[contains(text(),'" + pageHeader + "')]"));
	}

	public void verifyPageTitle(String pageName) {
		Assert.assertEquals(getPageTitle(), pageName, pageName + " Page title is not matching");
	}

	public void verifyPageHeader(String pageHeader) {
		Assert.assertTrue(isElementDisplayed(getPageHeader(pageHeader)), "Regiser Patinet page is not displayed");
	}

	public void selectDropdownValueByVisibleText(WebElement dropdownElement, String optionValue) {
		Select birthMonthDropdwon = new Select(dropdownElement);
		birthMonthDropdwon.selectByVisibleText(optionValue);
	}

	public String getElementText(WebElement element) {
		return element.getText().trim();
	}

	public void waitForVisibilityOfElements(List<WebElement> element) {
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public void waitForStalenessOfElements(List<WebElement> element) {
		wait.until(ExpectedConditions.stalenessOf(element.get(0)));
	}
	
	public void scrollElementIntoView(WebElement element) {
		jse.executeScript("arguments[0].scrollIntoView(true)", element);
	}

}
