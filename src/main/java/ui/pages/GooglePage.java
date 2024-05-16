package ui.pages;

import io.qameta.allure.Step;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.*;


public class GooglePage {
	private String BaseLink = "https://www.google.com/";

	private WebDriver driver;
	private static final Logger logger = Logger.getLogger(GooglePage.class);

	@FindBy(xpath = "//textarea[@name='q']")
	private WebElement searchInputField;

	@FindBy(xpath = "//a[@href='https://careers.epam.ua/']")
	private WebElement epamLink;

	@FindBy(xpath = "//a[@class='l']")
	private List<WebElement> epamLinksList;

	public GooglePage(WebDriver driver){
		this.driver = driver;
	}
	@Step("Open Google")
	public void openGoogle() {
		driver.get(BaseLink);
		PageFactory.initElements(driver, this);
		logger.info("Open Google page");
	}
	@Step("Pass Value into Search Field")
	public void passValueIntoSearchField(String word) {
		searchInputField.click();
		searchInputField.sendKeys(word);
		logger.info("Search word '" + word + "' in Google");
	}
	@Step("Click on Search")
	public void clickOnSearch() {
		searchInputField.sendKeys(Keys.ENTER);
		logger.info("Click on Search button");
	}
	@Step("Verify that EPAM site is present in the found results")
	public boolean verifyThatEpamSiteIsPresent() {
		boolean res = epamLink.isDisplayed();
		logger.info("Verify that EPAM Site is found by search");
		return res;
	}
	@Step("Get Value From Search Field")
	public String getValueFromSearchField() {
		String res = searchInputField.getAttribute("value");
		logger.info("Verify that searched word is displayed in the search field after search");
		return res;
	}
	@Step("Print list values")
	public void printListValues() {
		for (WebElement link : epamLinksList) {
			System.out.println(link.getText());
		}
		logger.info("Print values from the EPAM menu");
	}
	@Step("Count list values")
	public int countListValues() {
		int size = epamLinksList.size();
		logger.info("Count number of values in the EPAM menu");
		return size;
	}
	@Step("Get epam list menu")
	public List<WebElement> getEpamLinksList() {
		logger.info("Get epam list menu");
		return epamLinksList;
	}
	@Step("Get current url")
	public String getCurrentUrl() {
		logger.info("Get current url");
		return driver.getCurrentUrl();
	}
}