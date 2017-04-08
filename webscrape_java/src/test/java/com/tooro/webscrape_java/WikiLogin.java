package com.tooro.webscrape_java;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import junit.framework.TestCase;

public class WikiLogin extends TestCase{
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("SubmitForm.WikiLogin");
	}
	/*
	@Test
	public void test() throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_10);
		HtmlPage page1=webClient.getPage("https://en.wikipedia.org/w/index.php?title=Special:UserLogin");
		HtmlForm form=page1.getFormByName("userlogin");
		HtmlButton button=form.getButtonByName("wploginattempt");
		
		HtmlTextInput userField=form.getInputByName("wpName");
		HtmlPasswordInput passField=form.getInputByName("wpPassword");
		
		userField.setValueAttribute("anhnh23");
		passField.setValueAttribute("12111985");
		
		HtmlPage page2=button.click();
		System.out.println(page2.asText());
		Document doc=Jsoup.parse(page2.asXml());
		String userTitle=doc.select("#pt-userpage").text();
		webClient.closeAllWindows();
		assertNotNull(userTitle);
		
		System.out.println("Title: " + userTitle);
	}
	*/
}
