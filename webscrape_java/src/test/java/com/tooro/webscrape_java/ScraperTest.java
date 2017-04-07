package com.tooro.webscrape_java;

import org.junit.Test;

import com.tooro.WikiScraper;

import junit.framework.TestCase;

public class ScraperTest extends TestCase{
	
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("tests.ScraperTest");
	}

	@Test
	public void test() {
		WikiScraper scraper = new WikiScraper();
		String scrapedText=scraper.scrapePythonTopic();
		System.out.println(scrapedText);
		String openingText="Pythonidae, a family of nonvenomous snakes found in Africa, Asia, and"
				+ " Australia known as pythons Python (genus), of nonvenomous Pythonidae found in Africa and Asia";
		assertEquals("Nothing has changed!", scrapedText, openingText);
	}
}
