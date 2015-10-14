package com.rcx.concurrent.consumer.productor.type2;

public class Consumer extends Thread {

	private Storage storage;

	private int num;

	public Consumer(Storage storage, int num) {
		this.storage = storage;
		this.num = num;
	}

	@Override
	public void run() {
		try {
			storage.consumer(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
