package com.tooro.tools;

public class InvalidLanguage extends Exception{
	private static final long serialVersionUID = 1L;
	
	public InvalidLanguage(String msg){
		System.out.println(msg);
	}
}
