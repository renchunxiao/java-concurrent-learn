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
		
		service.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("invoked");
			}
		}, 8000, TimeUnit.MILLISECONDS);
	
		
		service.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("invoked222");
			}
		}, 10000, TimeUnit.MILLISECONDS);
	}
}
