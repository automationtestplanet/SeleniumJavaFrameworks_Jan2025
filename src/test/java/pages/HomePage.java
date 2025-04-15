package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = ".icon-home.small")
	WebElement homeIconElement;

	@FindBy(partialLinkText = "Logout")
	WebElement logoutElement;
	
	@FindBy(xpath = "//span[@class='navbar-toggler-icon']//parent::button")
	WebElement logoutMenuButton;

	public WebElement getLogoutMenuButton() {
		return logoutMenuButton;
	}

	public WebElement getLogoutElement() {
		return logoutElement;
	}

	public WebElement getHomeIconElement() {
		return homeIconElement;
	}

	public WebElement getTileByTileName(String tileName) {
		return driver.findElement(By.partialLinkText(tileName));
	}

	public void verifyTile(String tileName) {
		Assert.assertTrue(isElementDisplayed(getTileByTileName(tileName)), tileName + " Tile is not displayed");
	}

	public void clickTile(String tileName) {
		getTileByTileName(tileName).click();
	}

	public void ClickModule(String modueName) {
		verifyTile(modueName);
		clickTile(modueName);
		verifyPageHeader(modueName);
	}

	public void clickHomeIcon() {
		getHomeIconElement().click();
	}

	public void clickLogout() {
		getLogoutElement().click();
	}
	
	public void clickLogoutMenuButton() {
		getLogoutMenuButton().click();
	}
}
