package com.webapp.util;

public class Constants {

	private Constants() {

	}
	
	public static final String TIME_ZONE = "TIME_ZONE";
	public static final String LOG_CONFIG_FILE_PATH = System.getProperty("catalina.base").concat("/ApplicationConfiguration/WebApp/logConfig.xml");
	public static final String APP_CONFIG_FILE_PATH = System.getProperty("catalina.base").concat("/ApplicationConfiguration/WebApp/application.properties");
	public static final String KEYSTORE_FILE_PATH = System.getProperty("catalina.base").concat("/ApplicationConfiguration/WebApp/keystore.jks");

}