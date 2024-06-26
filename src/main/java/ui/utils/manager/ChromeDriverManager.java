package ui.utils.manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import ui.utils.Factory;


public class ChromeDriverManager implements Factory {

	@Override
	public WebDriver createDriver() {
		WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
		return new ChromeDriver();
	}
}
