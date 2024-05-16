package ui.utils.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import ui.utils.Factory;

public class FirefoxDriverManager implements Factory {

	@Override
	public WebDriver createDriver() {
		WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();

		return new FirefoxDriver();
	}
}