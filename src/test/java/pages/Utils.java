package pages;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utils extends BasePage {

	public Utils(WebDriver driver) {
		super(driver);
	}

	public static String screenshotsPath;
	public static String destinationPath = System.getProperty("user.dir") + "//src//test//resources//Screenshots//";
	public static String screenshotsFolderPath = System.getProperty("user.dir") + "//target//screenshots//";

	public void captureScreenshot() {

		try {
			TakesScreenshot ts = (TakesScreenshot) driver; // down-casting
			File screenshot = ts.getScreenshotAs(OutputType.FILE);
			String screenshotName = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date()).replaceAll("[^0-9]",
					"") + ".jpg";
			screenshotsPath = destinationPath + screenshotName;
			FileUtils.copyFile(screenshot, new File(screenshotsPath));
		} catch (Exception e) {
			System.out.println("Exception Occured while capturin the screenshot: " + e.getMessage());
		}

	}

	public String getScreenshotAsFile() {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver; // down-casting
			File screenshot = ts.getScreenshotAs(OutputType.FILE);
			String screenshotName = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date()).replaceAll("[^0-9]",
					"") + ".jpg";
			screenshotsPath = screenshotsFolderPath + screenshotName;
			FileUtils.copyFile(screenshot, new File(screenshotsPath));
			return screenshotsPath;
		} catch (Exception e) {
			System.out.println("Exception Occured while capturin the screenshot: " + e.getMessage());
		}
		return null;
	}
}
