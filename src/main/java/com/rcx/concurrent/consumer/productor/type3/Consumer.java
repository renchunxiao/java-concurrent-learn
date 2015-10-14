package com.rcx.concurrent.consumer.productor.type3;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	private final BlockingQueue queue;

	Consumer(BlockingQueue q) {
		queue = q;
	}

	public void run() {
		try {
			while (true) {
				consume(queue.take());
			}
		} catch (InterruptedException ex) {

		}
	}

	void consume(Object x) {
		System.out.println("消费一个产品");
	}
}