package com.tooro.rmi;

import java.rmi.*;

public interface ScraperInterface extends Remote{
	public String getMessage() throws RemoteException;
}
