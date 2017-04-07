package com.tooro.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.tooro.ScraperModel;

public class ScraperUtil {
	public static Document getDocument(ScraperModel scraperModel) {
		String content = ScraperUtil.getContent(scraperModel.toString());
		Document doc = Jsoup.parse(content);
		return doc;
	}
	
	public static String getContent(String url){
		StringBuffer content = new StringBuffer();
		
		URL urlObj = null;
		URLConnection urlCon = null;
		BufferedReader in = null;
		try{
			urlObj = new URL(url);
			urlCon = urlObj.openConnection();
			in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
			String line = "";
			while((line=in.readLine()) != null){
				content.append(line);
			}
			in.close();
		}catch(MalformedURLException me){
			System.out.println("The url was malformed!");
			return "";
		}catch(IOException e){
			System.out.println("There was an error connecting to the URL");
			return "";
		}
		
		return content.toString();
	}
}
