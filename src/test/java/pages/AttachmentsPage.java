package pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AttachmentsPage extends BasePage {

	public AttachmentsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h3[contains(text(),'File')]//following-sibling::form[@id='visit-documents-dropzone']")
	WebElement dropFileButton;

	@FindBy(xpath = "//h3[contains(text(),'Caption')]//following-sibling::textarea[@placeholder='Enter a caption']")
	WebElement captionField;

	@FindBy(xpath = "//button[contains(text(),'Upload file')]")
	WebElement uploadFileButton;

	public WebElement getDropFileButton() {
		return dropFileButton;
	}

	public WebElement getCaptionField() {
		return captionField;
	}

	public WebElement getUploadFileButton() {
		return uploadFileButton;
	}

	public void clickDropFileButton() {
		getDropFileButton().click();
	}

	public void setCaption(String caption) {
		getCaptionField().sendKeys(caption);
	}

	public void clickUploadFileButton() {
		Assert.assertTrue(isElementEnabled(getUploadFileButton()), "Upload FIle button Not enabled");
		getUploadFileButton().click();
	}

	public void uploadFile(String filePath, String caption) {
		try {
			clickDropFileButton();
			Thread.sleep(5000);
			StringSelection strSelection = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strSelection, null);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(5000);
			setCaption(caption);
			clickUploadFileButton();
			Thread.sleep(10000);
			Assert.assertTrue(isElementDisplayed(getUploadeFileCaptionElement(caption)), "File Uploaded Failed");
		} catch (Exception e) {
			System.out.println("Exception occured while uploading the file: " + e.getMessage());
		}
	}

	public WebElement getUploadeFileCaptionElement(String text) {
		return driver.findElement(By.xpath("//p[text()='" + text + "']"));
	}

}
