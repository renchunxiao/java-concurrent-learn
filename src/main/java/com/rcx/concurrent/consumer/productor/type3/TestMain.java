package com.rcx.concurrent.consumer.productor.type3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestMain {
	public static void main(String[] args) {
		BlockingQueue q = new ArrayBlockingQueue(5);
		Producer p = new Producer(q);
		Consumer c1 = new Consumer(q);
		new Thread(p).start();
		new Thread(c1).start();
	}
}
