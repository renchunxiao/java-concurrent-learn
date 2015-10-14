package com.rcx.concurrent.consumer.productor.type1;

import java.util.Random;

public class TestMain {
	public static void main(String[] args) {
		Storage storage = new Storage();
		for(int i = 0; i < 10; i++) {
			new Productor(storage, new Random().nextInt(4) + 4).start();
			new Consumer(storage, new Random().nextInt(4) + 1).start();
		}
	}
}
