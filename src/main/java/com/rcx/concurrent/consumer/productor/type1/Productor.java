package com.rcx.concurrent.consumer.productor.type1;

public class Productor extends Thread {

	private Storage storage;
	
	private int num;

	public Productor(Storage storage, int num) {
		this.storage = storage;
		this.num  = num;
	}

	@Override
	public void run() {
		try {
			storage.productor(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
