package com.webapp.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationConfiguration {

	private static final Logger logger = LogManager.getLogger(ApplicationConfiguration.class);
	private static final Properties properties = new Properties();
	
	public boolean loadApplicationProperties() {
		try (InputStream configFile = new FileInputStream(Constants.APP_CONFIG_FILE_PATH)) {
			logger.debug("Loading application configuration from : {}", Constants.APP_CONFIG_FILE_PATH);
			properties.load(configFile);
			logger.debug("Application configuration loading completed at: {}", new DateTimeHelper().getCurrentDateAndTime());
			return true;
		} catch (IOException ex) {
			logger.error("Exception in loading application configuration : {}", StackTrace.getMessage(ex));
			return false;
		}
	}

	public static String getTag(String key) {
		String value = properties.getProperty(key);
		return (value != null) ? value.trim() : null;
	}
}