package com.tooro.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.tooro.LinkNotFoundException;
import com.tooro.WikiScraper;

public class ScraperImp extends UnicastRemoteObject implements ScraperInterface{
	
	public ScraperImp() throws RemoteException{}
	
	public static void main(String[] args) {
		System.out.println("Hello, world!");
	}
	
	public String getMessage() throws RemoteException {
		WikiScraper.scraperModel.setPath("wiki/Java");
		try {
			return(WikiScraper.scrapeContent(WikiScraper.scraperModel));
		} catch (LinkNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
