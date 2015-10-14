package com.rcx.concurrent.consumer.productor.type3;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	private final BlockingQueue queue;

	Producer(BlockingQueue q) {
		queue = q;
	}

	public void run() {
		try {
			while (true) {
				queue.put(produce());
			}
		} catch (InterruptedException ex) {

		}
	}

	Object produce() {
		System.out.println("生产一个产品");
		return new Object();
	}
}
