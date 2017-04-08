package com.tooro.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Server {
	public static void main(String[] args) {
		try{
			ScraperImp localObject=new ScraperImp();
			Naming.rebind("rmi://localhost:1099/Scraper", localObject);
			System.out.println("Successfully bound Remote Implementation!");
		}catch(RemoteException re){
			System.out.println("RemoteException: " + re);
		}catch(MalformedURLException mfe){
			System.out.println("MalformedURLException: " + mfe);
		}
	}
}
