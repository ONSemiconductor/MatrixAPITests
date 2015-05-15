package com.onsemi.matrix.api;

public class Settings {
	private static final int defaultTimeout = 5000;
    private static final String IP = "192.168.1.168";
	public static int getDefaultTimeout() {
		return defaultTimeout;
	}
	
    public static String getHostname(){
        return "http://" + getIP();
    }

    private static String getIP(){
        return IP;
    }
}
