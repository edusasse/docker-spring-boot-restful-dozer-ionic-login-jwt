package com.edusasse.app.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext context;

	private ApplicationContextProvider() {
	}

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	public static <T> T getBean(String name, Class<T> aClass) {
		return context.getBean(name, aClass);
	}
	
	public static <T> T getBean(Class<T> aClass) {
		return context.getBean(aClass);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		context = ctx;
	}
}