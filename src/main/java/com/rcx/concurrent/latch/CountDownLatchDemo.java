package com.rcx.concurrent.latch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

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
						System.out.println("-----invoked-----");
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

		System.out.println(System.currentTimeMillis() - startTime);
		System.out.println("-----end-----");
	}
}
