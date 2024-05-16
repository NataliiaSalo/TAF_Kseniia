package ui.utils;

import java.util.concurrent.TimeUnit;

import ui.utils.manager.ChromeDriverManager;
import ui.utils.manager.FirefoxDriverManager;
import ui.utils.manager.EdgeDriverManager;

import org.openqa.selenium.WebDriver;

	public class DriverFactory
	{
		public enum BrowserList {
			CHROME, FIREFOX, EDGE
		}

		public static WebDriver createInstance(String browser)
		{
			WebDriver driver = null;
			BrowserList browserType = BrowserList.valueOf(browser.toUpperCase());

			switch (browserType) {
				case CHROME:
					driver = new ChromeDriverManager().createDriver();
					break;
				case FIREFOX:
					driver = new FirefoxDriverManager().createDriver();
					break;
				case EDGE:
					driver = new EdgeDriverManager().createDriver();
					break;
			};

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			return driver;
		}
	}