package com.tooro.tools;

/**
 * Using the free service provided by freegeoip.net to resolve IP addresses into a geographic location.
 */
public class IPResolver {
	String ipAddress;
	String ipInfo;
	
	public IPResolver(String declareAddress){
		ipAddress = declareAddress;
		Webpage ipInfoObj = new Webpage("http://freegeoip.net/xml/" + ipAddress);
		ipInfo = ipInfoObj.getString();
	}
	
	public String getCountry(){
		int startCtry = ipInfo.indexOf("<CountryName>") + 13;
		int endCtry = ipInfo.indexOf("</CountryName>");
		return ipInfo.substring(startCtry, endCtry);
	}
}
