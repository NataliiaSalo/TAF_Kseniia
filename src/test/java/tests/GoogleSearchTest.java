package tests;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;

import pages.GooglePage;
import utils.Driver;

import org.testng.Assert;
import org.testng.annotations.*;


public class GoogleSearchTest {
	private static final Logger logger = Logger.getLogger(GooglePage.class);

	@Test(priority = 1)
	public void searchGoogle() {
		GooglePage googlePage = new GooglePage();
		googlePage.openGoogle();
		googlePage.passValueIntoSearchField("Epam");
		googlePage.clickOnSearch();
		Assert.assertTrue(googlePage.verifyThatEpamSiteIsPresent());
	}

	@Test(priority = 2)
	public void searchInputFieldContainsSearchWord() {
		GooglePage googlePage = new GooglePage();
		googlePage.openGoogle();
		googlePage.passValueIntoSearchField("Epam");
		googlePage.clickOnSearch();
		String searchWord = googlePage.getValueFromSearchField();
		Assert.assertEquals(searchWord, "Epam");
	}

	@Test(priority = 3)
	public void countListInEpam() {
		GooglePage googlePage = new GooglePage();
		googlePage.openGoogle();
		googlePage.passValueIntoSearchField("Epam");
		googlePage.clickOnSearch();
		int sizeOfListOnPage = googlePage.countListValues();
		Assert.assertEquals(sizeOfListOnPage, 5);
	}

	@Test(dependsOnMethods = {"countListInEpam"},priority = 4)
	public void verifyListInEpam() {
		GooglePage googlePage = new GooglePage();
		List<String> epamExpectedLinksList = Arrays.asList("Вакансії", "Навчання з ЕРАМ", "Університетські програми",
				"EPAM Україна", "Контакти");
		googlePage.openGoogle();
		googlePage.passValueIntoSearchField("Epam");
		googlePage.clickOnSearch();
		List<WebElement> epamList = googlePage.getEpamLinksList();
		googlePage.printListValues();
		List<String> webElementTextEpamList = epamList.stream().map(WebElement::getText).collect(Collectors.toList());
		Assert.assertEquals(webElementTextEpamList, epamExpectedLinksList, "Lists are not equal");
		logger.info("Verify values in the EPAM menu");
	}

	@Test(priority = 5)
	public void searchGoogleWithEmptyText() {
		GooglePage googlePage = new GooglePage();
		googlePage.openGoogle();
		googlePage.passValueIntoSearchField("");
		googlePage.clickOnSearch();
		String url = googlePage.getCurrentUrl();
		Assert.assertEquals(url, "https://www.google.com/");
		logger.info("Verify that Google homepage is still opened");
	}

	@AfterClass
	public void cleanup() {
		Driver.close();
	}
}