package com.example.material.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

public class SpringUtil implements ApplicationContextAware {
	private static final Logger log = LoggerFactory.getLogger(SpringUtil.class);
	private static ApplicationContext context;

	public SpringUtil() {
	}

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		context = context;
	}

	public static <T> T getBean(Class<T> clazz) {
		return  null;
		//return clazz == null ? null : context.getBean(clazz);
	}

	public static <T> T getBean(String beanId) {
		return beanId == null ? null : (T) context.getBean(beanId);
	}

	public static <T> T getBean(String beanName, Class<T> clazz) {
		if (null != beanName && !"".equals(beanName.trim())) {
			return clazz == null ? null : context.getBean(beanName, clazz);
		} else {
			return null;
		}
	}

	public static ApplicationContext getContext() {
		return context == null ? null : context;
	}

	public static void publishEvent(ApplicationEvent event) {
		if (context != null) {
			try {
				context.publishEvent(event);
			} catch (Exception var2) {
				log.error(var2.getMessage());
			}

		}
	}
}
