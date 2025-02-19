package com.webapp.util;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

@WebListener
public class Bootstrap implements ServletContextListener {

	private static final Logger logger = LogManager.getLogger(Bootstrap.class);

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			LoggerContext context = (LoggerContext) LogManager.getContext(false);
			File logConfig = new File(Constants.LOG_CONFIG_FILE_PATH);
			if (logConfig.exists() && logConfig.isFile()) {
				context.setConfigLocation(logConfig.toURI());
			} else {
				System.err.println(String.format("Log configuration files not exists in the location : %s", Constants.LOG_CONFIG_FILE_PATH));
			}
			if (!new ApplicationConfiguration().loadApplicationProperties()) {
				System.err.println("Unable to load the application properties. Exiting the application");
				System.exit(0);
			}
			logger.info("Application properties loaded successfully");
		} catch (Exception ex) {
			System.err.println(String.format("Exception while initializing the context %s", StackTrace.getMessage(ex)));
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("Context Destroyed {}", new DateTimeHelper().getCurrentDateAndTime());
	}
}