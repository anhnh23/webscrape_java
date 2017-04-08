package com.tooro.webscrape_java;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import junit.framework.TestCase;
/*
public class Intelius extends TestCase{
	@Test
	public void test() throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		WebClient webClient=new WebClient(BrowserVersion.FIREFOX_10);
		HtmlPage page=null;
		
		webClient.setThrowExceptionOnFailingStatusCode(false);
		webClient.setThrowExceptionOnScriptError(false);
		
		try{
			//page=webClient.getPage("http://www.intelius.com/results.php?ReportType=1&formname=name&qf=Ryan&qmi=E&qn=Mitchell&qcs=Needham%2C+MA&focusfirst=1");
			page=webClient.getPage("https://www.intelius.com/people-search/Ryan-E-Mitchell/Needham%20heights-MA");
		}catch(FailingHttpStatusCodeException e){
			e.printStackTrace();
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		Document doc=null;
		String name="";
		for(int i=0; i<20; i++){
			Boolean mblueExists=true;
			try{
				doc=Jsoup.parse(page.asXml());
				name=doc.select("div.small-8").first().getElementsByClass("searchCriteria-header").first().text();
			}catch(ElementNotFoundException e){
				mblueExists=false;
			}
			
			if(mblueExists){
				break;
			}
			
			synchronized(page){
				try{
					page.wait(500);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		webClient.closeAllWindows();
		
		try{
			assertEquals("Ryan F Mitchell age 45", name);
		}catch(NullPointerException e){
			fail("name not found");
		}
	}
}
*/
