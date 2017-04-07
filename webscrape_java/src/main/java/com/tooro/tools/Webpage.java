package com.tooro.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Webpage {
	public String url;
	public URL urlObj;
	
	public Webpage(String declaredUrl){
		url = declaredUrl;
		urlObj = null;
		try{
			urlObj = new URL(url);
		}catch(MalformedURLException e){
			System.out.println("The url was malformed!");
		}
	}
	
	public String getRedirect() throws MalformedURLException, IOException{
		try{
			return getRedirect(false);
		}catch(MalformedURLException e){
			System.out.println("The url was malformed!");
		}catch(IOException e){
			System.out.println("There was an error connecting to the website");
		}
		return "";
	}
	
	public String getRedirect(Boolean secondRedirect) throws MalformedURLException, IOException{
		HttpURLConnection URLCon = (HttpURLConnection)urlObj.openConnection();
		URLCon.setInstanceFollowRedirects(false);
		URLCon.connect();
		String header = URLCon.getHeaderField("Location");
		System.out.println("First header is: " + URLCon.getHeaderField(7));
		System.out.println("Redirect is: " + header);
		
		if(secondRedirect){
			URL newURL = new URL(header);
			HttpURLConnection newCon = (HttpURLConnection)newURL.openConnection();
			newCon.setInstanceFollowRedirects(false);
			newCon.connect();
			String finalHeader = newCon.getHeaderField("Location");
			return finalHeader;
		}else{
			return header;
		}
	}
	
	public String getString(){
		URLConnection urlCon = null;
		BufferedReader in = null;
		String outputText = "";
		try{
			urlCon = urlObj.openConnection();
			in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
			String line = "";
			while((line = in.readLine()) != null){
				outputText += line;
			}
			in.close();
		}catch(IOException ioe){
			System.out.println("There was an error connecting to the URL");
			return null;
		}
		return outputText;
	}
	
	public Document getJsoup(){
		URLConnection urlCon = null;
		BufferedReader in = null;
		String outputText = "";
		try{
			urlCon = urlObj.openConnection();
			in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
			String line = "";
			while((line = in.readLine()) != null){
				outputText += line;
			}
			in.close();
		}catch(IOException e){
			System.out.println("There was an error connecting to the URL");
			return null;
		}
		return Jsoup.parse(outputText);
	}
}
