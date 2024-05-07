package pages;

import utils.Driver;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;


public class GooglePage {
	private WebDriver driver = Driver.getDriver();
	private static final Logger logger = Logger.getLogger(GooglePage.class);

	@FindBy(xpath = "//textarea[@name='q']")
	private WebElement searchInputField;

	@FindBy(xpath = "//a[@href='https://careers.epam.ua/']")
	private WebElement epamLink;

	@FindBy(xpath = "//a[@class='l']")
	private List<WebElement> epamLinksList;

	public void openGoogle() {
		driver.get("https://www.google.com/");
		PageFactory.initElements(driver, this);
		logger.info("Open Google page");
	}

	public void passValueIntoSearchField(String word) {
		searchInputField.click();
		searchInputField.sendKeys(word);
		logger.info("Search word '" + word + "' in Google");
	}

	public void clickOnSearch() {
		searchInputField.sendKeys(Keys.ENTER);
		logger.info("Click on Search button");
	}

	public boolean verifyThatEpamSiteIsPresent() {
		boolean res = epamLink.isDisplayed();
		logger.info("Verify that EPAM Site is found by search");
		return res;
	}

	public String getValueFromSearchField() {
		String res = searchInputField.getAttribute("value");
		logger.info("Verify that searched word is displayed in the search field after search");
		return res;
	}

	public void printListValues() {
		for (WebElement link : epamLinksList) {
			System.out.println(link.getText());
		}
		logger.info("Print values from the EPAM menu");
	}

	public int countListValues() {
		int size = epamLinksList.size();
		logger.info("Count number of values in the EPAM menu");
		return size;
	}

	public List<WebElement> getEpamLinksList() {
		return epamLinksList;
	}

	public String getCurrentUrl() {
		logger.info("Get current url");
		return driver.getCurrentUrl();
	}
}