package com.cdc.pcp.api.interceptors;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InterceptorSpringContextLoader {

	private static InterceptorSpringContextLoader INSTANCE;

	private ClassPathXmlApplicationContext springContext;

	private InterceptorSpringContextLoader() {
		springContext = new ClassPathXmlApplicationContext("classpath:spring/rmi-context.xml");
	}

	public static InterceptorSpringContextLoader factory() {
		if (INSTANCE == null) {
			Lock lock = new ReentrantLock();
			lock.lock();
			INSTANCE = new InterceptorSpringContextLoader();
			lock.unlock();
		}
		return INSTANCE;
	}

	public ClassPathXmlApplicationContext getSpringContext() {
		return springContext;
	}
}
