package tests;


import java.util.*;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;

import io.qameta.allure.Allure;
import pages.GooglePage;
import org.testng.*;
import org.testng.annotations.*;


public class GoogleSearchTest extends BaseTest {
	List<String> epamLinksListExpected = Arrays.asList("Вакансії", "Навчання з ЕРАМ", "Університетські програми",
			"EPAM Україна", "Контакти");
	private static final Logger logger = Logger.getLogger(GooglePage.class);

	@Override
	protected String getBrowserName() {
		return "firefox";
	}

	@Test
	public void searchGoogle() {
		GooglePage googlePage = new GooglePage(getDriver());
		googlePage.openGoogle();
		googlePage.passValueIntoSearchField("Epam");
		googlePage.clickOnSearch();
		Assert.assertTrue(googlePage.verifyThatEpamSiteIsPresent());
	}

	@Test
	public void searchInputFieldContainsSearchWord() {
		GooglePage googlePage = new GooglePage(getDriver());
		googlePage.openGoogle();
		googlePage.passValueIntoSearchField("Epam");
		googlePage.clickOnSearch();
		String searchWord = googlePage.getValueFromSearchField();
		Assert.assertEquals(searchWord, "Epam");
	}

	@Test
	public void countListInEpam() {
		GooglePage googlePage = new GooglePage(getDriver());
		googlePage.openGoogle();
		googlePage.passValueIntoSearchField("Epam");
		googlePage.clickOnSearch();
		int sizeOfListOnPageActual = googlePage.countListValues();
		Assert.assertEquals(sizeOfListOnPageActual, epamLinksListExpected.size());
	}

	@Test
	public void verifyListInEpam() {
		GooglePage googlePage = new GooglePage(getDriver());
		googlePage.openGoogle();
		googlePage.passValueIntoSearchField("Epam");
		googlePage.clickOnSearch();
		List<WebElement> epamLinksList = googlePage.getEpamLinksList();
		List<String> epamLinksListTextActual = epamLinksList.stream().map(WebElement::getText).collect(
				Collectors.toList());
		logger.info("Verify values in the EPAM menu");
		Assert.assertEquals(epamLinksListTextActual, epamLinksListExpected, "Lists are not equal");
	}

	@Test
	public void searchGoogleWithEmptyText() {
		GooglePage googlePage = new GooglePage(getDriver());
		googlePage.openGoogle();
		googlePage.passValueIntoSearchField("");
		googlePage.clickOnSearch();
		String url = googlePage.getCurrentUrl();
		Assert.assertEquals(url, "https://www.google.com/");
		logger.info("Verify that Google homepage is still opened");
	}
}