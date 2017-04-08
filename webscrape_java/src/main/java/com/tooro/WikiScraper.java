package com.tooro;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tooro.tools.IPResolver;
import com.tooro.tools.Webpage;
import com.tooro.utils.DBUtil;
import com.tooro.utils.ScraperUtil;

public class WikiScraper {
	public static ScraperModel scraperModel = new ScraperModel(Protocol.https, "www.wikipedia.org", "",
			"/wiki/Java");
	private static Map<String, String> foundLinks = new HashMap<String, String>();
	
	public static void main(String[] args) {
		//crawlEnglishAndGerman();
		try{
			//scrapeContent(scraperModel);
			scrapeLinks(scraperModel);
			//showFoundLinks();
			//fetchWiki();
		}catch(LinkNotFoundException e){
			System.out.println("Trying again");
		}
	}
	
	public String scrapePythonTopic(){
		scraperModel.setPath("/wiki/Python");
		String condition="#mw-content-text li:has(a[title=Pythonidae])";
		Document doc = ScraperUtil.getDocument(scraperModel);
		return doc.select(condition).first().text();
	}

	public static String scrapeContent(ScraperModel scraperModel) throws LinkNotFoundException{
		Document doc = ScraperUtil.getDocument(scraperModel);
		String condition = "#mw-content-text p";
		String text = doc.select(condition).first().text();
		return text;
	}

	public static void scrapeLinks(ScraperModel scraperModel) throws LinkNotFoundException{
		Document doc = ScraperUtil.getDocument(scraperModel);
		String condition = "#mw-content-text [href~=^/wiki/((?!:).)*$]";
		Elements links = doc.select(condition);
		
		if (links.size() == 0) {
			throw new LinkNotFoundException("No links on page, or page malformed");
		}

		for (Element element : links) {
			if(!foundLinks.containsKey(element.attr("href"))){
				foundLinks.put(element.attr("href"), element.text());
				System.out.printf("%s==>%s\n", element.attr("href"), element.text());
				DBUtil.storeData(element.text(), element.attr("href"));
				scraperModel.setPath(element.attr("href"));
				scrapeLinks(scraperModel);
			}
		}
	}
	
	private static void showFoundLinks(){
		for(Map.Entry<String, String> entry : foundLinks.entrySet()){
			System.out.printf("%s\t\t\t%s\n", entry.getKey(), entry.getValue());
		}
	}
	
	/**
	 * Crawl both English and German wikipedias.
	 * Find 10 random German wikipedias by following redirects throught the "special:random" page,
	 * and returns the country of origin for the first IP address (if any) that it finds on the page.
	 * After this, does the same for English Wikipedia edits.
	 */
	private static void crawlEnglishAndGerman(){
		for(int i=0; i<10; i++){
			String randomPage = getRandomHist("de");
			System.out.println("This page been edited in " + getEditCountry(randomPage));
		}
		
		for(int i=0; i<10; i++){
			String randomPage = getRandomHist("en");
			System.out.println("This page has been edited in " + getEditCountry(randomPage));
		}
	}
	
	private static String getRandomHist(String language){
		Webpage randomPage=new Webpage("https://" + language + ".wikipedia.org/wiki/Special:Random");
		
		String randomUrl="";
		try{
			if(language=="en"){
				randomUrl=randomPage.getRedirect();
			}else{
				randomUrl=randomPage.getRedirect(true);
			}
		}catch(MalformedURLException mue){
			mue.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		
		if(randomUrl==""){
			return "";
		}
		
		String randomHist = randomUrl.replace("https://" + language + ".wikipedia.org/wiki/", "");
		randomHist = "https://" + language + ".wikipedia.org/w/index.php?title=" + randomHist + "&action=history";
		return randomHist;
	}
	
	private static String getEditCountry(String histPageURL){
		Webpage histPage=new Webpage(histPageURL);
		Document histDoc=histPage.getJsoup();
		Elements rows=histDoc.select("li");
		for(Element row : rows){
			System.out.println(row.select(".mw-userlink").text());
			if(row.select(".mw-userlink").text().matches("^(?:[0-9]{1,3}.){3}[0-9]{1,3}$")){
				IPResolver IPAddress=new IPResolver(row.select(".mw-userlink").text());
				return IPAddress.getCountry();
			}
		}
		return "No IP Address found in edit history";
	}
}
