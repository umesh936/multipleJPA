package com.umesh.learning.multipleJPA.configuration;



import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
///@ImportResource({ "classpath:log4j.xml" })
public class LoggingConfig {
	
	@Bean
	public ConsoleAppender consoleAppender() {
	ConsoleAppender consoleAppender = new ConsoleAppender();
	consoleAppender.setThreshold(Level.TRACE);
	PatternLayout patternLayout = new PatternLayout();
	patternLayout.setConversionPattern("%d %-5p [%c{1}] %m %n");
	consoleAppender.setLayout(patternLayout);
	return consoleAppender;
	}
	
	@Bean
	public Logger springLogger() {
	Logger springlog = Logger.getLogger("org.springframework");
	springlog.setLevel(Level.DEBUG);
	springlog.getAppender("consoleAppender");
	return springlog;
	}
	
	@Bean
	public Logger localLogger() {
	Logger locallog = Logger.getLogger("com.umesh");
	locallog.setLevel(Level.DEBUG);
	locallog.getAppender("consoleAppender");
	return locallog;
	}

}
