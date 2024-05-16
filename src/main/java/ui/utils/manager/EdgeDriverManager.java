package ui.utils.manager;

import ui.utils.Factory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

public class EdgeDriverManager implements Factory {

	@Override
	public WebDriver createDriver() {
		WebDriverManager.getInstance(DriverManagerType.EDGE).setup();
		return new EdgeDriver();
	}
}
