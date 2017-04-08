package com.tooro;

public class ThreadStarter {
	public static void main(String[] args) {
		WikiThreadCrawler javaIsland=new WikiThreadCrawler("/wiki/Java");
		WikiThreadCrawler javaLanguage=new WikiThreadCrawler("/wiki/Java_(programming_language)");
		javaLanguage.start();
		javaIsland.start();
	}
}
