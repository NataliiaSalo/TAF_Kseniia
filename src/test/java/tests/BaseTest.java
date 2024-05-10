package tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.*;
import org.testng.annotations.*;

import io.qameta.allure.Attachment;
import utils.DriverFactory;

public class BaseTest {
	protected WebDriver driver;

	@BeforeTest
	protected String getBrowserName()  { return "chrome"; }

	@BeforeTest
	public void preCondition() {
		driver = new DriverFactory().createInstance(getBrowserName());
	}

	@AfterClass
	public void cleanup() {
		driver.close();
	}
	public WebDriver getDriver() {
		return driver;
	}

	@Attachment(value = "{name}", type = "image/png")
	private byte[] takeScreenShot(String name) throws IOException {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	private void takeScreenShot1(String name) throws IOException {
		String path = getRelativePath(name);
		File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenShot, new File(path));
		String filename = makeScreenShotFileName(name);
		System.out.println("Taking Screenshot! " + filename);
		Reporter.log("<a href=" + path + " target='_blank' >" + filename + "</a>");
	}

	private void takeScreenShot(ITestNGMethod testMethod) throws IOException {
		String nameScreenShot =
				testMethod.getTestClass().getRealClass().getSimpleName() + "_" + testMethod.getMethodName();
		takeScreenShot(nameScreenShot);
	}

	private String makeScreenShotFileName(String name) {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss");
		Date date = new Date();
		return dateFormat.format(date) + "_" + name + ".png";
	}

	private String getRelativePath(String name) throws IOException {
		Path path = Paths.get(".", "target", "surefire-reports", "screenShots", makeScreenShotFileName(name));
		File directory = new File(path.toString());
		return directory.getCanonicalPath();
	}

	@AfterMethod
	protected void screenShotIfFail(ITestResult result) throws IOException {
		if (!result.isSuccess()) {
			takeScreenShot(result.getMethod());
		}
	}
}
