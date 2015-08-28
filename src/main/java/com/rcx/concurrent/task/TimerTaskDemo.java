package com.rcx.concurrent.task;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * java5 之后推荐使用 ScheduledExecutorService 
 * 
 * 
 * @author renchunxiao
 *
 */
public class TimerTaskDemo {

	public static void main(String[] args) {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// do something
				try {
					System.out.println("begin");
					Thread.sleep(1000);
					System.out.println("invoked");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Timer timer = new Timer();
		timer.schedule(task, 1000, 5000);
	}

}