package com.tooro;

public class WikiThreadCrawler extends Thread {
	public WikiThreadCrawler(String str) {
		super(str);
	}

	public void run() {
		String newUrl = getName();
		for (int i = 0; i < 10; i++) {
			WikiScraper.scraperModel.setPath(getName());

			try {
				WikiScraper.scrapeLinks(WikiScraper.scraperModel);
				sleep((int) (Math.random() * 1000));
			} catch (InterruptedException | LinkNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
