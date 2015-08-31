package com.rcx.concurrent.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
	public static void main(String[] args) {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);
		try {
			String string = queue.poll(3, TimeUnit.SECONDS);
			if (string == null) {
				System.err.println("null");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
