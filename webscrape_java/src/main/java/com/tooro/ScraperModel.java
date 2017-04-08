package com.tooro;

public class ScraperModel {
	private Protocol protocol;
	private String domainName;
	private String port;
	private String path;

	public ScraperModel(Protocol protocol, String domainName, String port, String path) {
		this.protocol = protocol;
		this.domainName = domainName;
		this.port = port;
		this.path = path;
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String toString(){
		return protocol + "://" + domainName + path;
	}
}
