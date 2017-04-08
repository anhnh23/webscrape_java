package com.tooro.freelancer.oddsportal;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableDataCell;

public class Main {
	private String url = "http://www.oddsportal.com/matches/soccer/20170408/";
	private static Map<String, String> matchNames = new HashMap<String, String>();
	
	public static void main(String[] args) {
		Main main=new Main();
		try {
			main.getListPaths();
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Map.Entry<String, String> match : matchNames.entrySet()){
			System.out.printf("%s\t%s\n", match.getKey(), match.getValue());
		}
	}
	
	private void getListPaths() throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		try(final WebClient webClient=new WebClient()){
			final HtmlPage page=webClient.getPage(url);
			final HtmlDivision div=page.getHtmlElementById("table-matches");
			final List<HtmlTableDataCell> tds=div.getByXPath("//td[@class='name table-participant']");
			for(HtmlTableDataCell td : tds){
				final HtmlAnchor a=(HtmlAnchor) td.getByXPath("a").get(0);
				matchNames.put(a.asText(), a.getHrefAttribute());
			}
		}
	}
}
