package com.tooro.tools;

public class PageDoesNotExist extends Exception{
	public PageDoesNotExist(String msg){
		System.out.println(msg);
	}
}
