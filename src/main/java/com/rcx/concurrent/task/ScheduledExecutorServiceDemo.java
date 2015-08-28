package com.rcx.concurrent.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 使用 ScheduledExecutorService 来实现调度
 * 
 * @author renchunxiao
 */
public class ScheduledExecutorServiceDemo {
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		service.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				// to do something
				System.out.println("invoked");
			}
		}, 0, 2, TimeUnit.SECONDS);
	}
}
