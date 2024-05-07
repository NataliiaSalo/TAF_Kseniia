package utils;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


public class Driver {
	public static final String DRIVER = "webdriver.gecko.driver";
	public static final String DRIVER_PATH = "src/main/resources/geckodriver.exe";

	private static WebDriver driver;

	public static void initDriver() {
		System.setProperty(DRIVER, DRIVER_PATH);
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public static WebDriver getDriver() {
		if (driver == null) {
			initDriver();
		}
		return driver;
	}

	public static void close() {
		driver.quit();
	}
}