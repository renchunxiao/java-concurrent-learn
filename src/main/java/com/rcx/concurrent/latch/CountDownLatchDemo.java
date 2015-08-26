package com.rcx.concurrent.latch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 简单使用 Demo
 * 
 * Demo 描述的是想要记录传入进来的 threadCount 个线程执行完总共需要多少时间
 * 
 * @author renchunxiao
 *
 */
public class CountDownLatchDemo {
	public static void main(String[] args) {
		CountDownLatchDemo demo = new CountDownLatchDemo();
		demo.printTime(5);
	}

	public void printTime(int threadCount) {
		final CountDownLatch start = new CountDownLatch(1);
		final CountDownLatch end = new CountDownLatch(threadCount);

		System.out.println("-----begin-----");
		long startTime = System.currentTimeMillis();

		for (int i = 0; i < threadCount; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						start.await();
						Thread.sleep(new Random().nextInt(500));
						System.out.println(Thread.currentThread().getName() + "-----invoked-----");
						end.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

		start.countDown();

		try {
			end.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("一共执行时间 " + (System.currentTimeMillis() - startTime) + "ms");
		System.out.println("-----end-----");
	}
}
