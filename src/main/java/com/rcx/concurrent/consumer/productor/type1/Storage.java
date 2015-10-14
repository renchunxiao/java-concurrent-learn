package com.rcx.concurrent.consumer.productor.type1;

import java.util.LinkedList;

public class Storage {

	private static final int SIZE = 20;

	private LinkedList<Object> list = new LinkedList<Object>();

	public void productor(int num) throws Exception {
		synchronized (list) {
			while (list.size() + num > SIZE) {
				System.out.println("【要生产的产品数量】:" + num + " \t【库存量】:" + list.size()
						+ " \t暂时不能执行生产任务!");
				list.wait();
			}

			for (int i = 0; i < num; i++) {
				list.add(new Object());
			}

			System.out.println("【已经生产产品数】:" + num + " \t【现仓储量为】:" + list.size());
			list.notifyAll();
		}
	}

	public void consumer(int num) throws Exception {
		synchronized (list) {
			while (num > list.size()) {
				System.out.println("【要消费的产品数量】:" + num + " \t【库存量】:" + list.size()
						+ " \t暂时不能执行生产任务!");
				list.wait();
			}
			for (int i = 0; i < num; i++) {
				list.remove();
			}

			System.out.println("【已经消费产品数】:" + num + " \t【现仓储量为】:" + list.size());

			list.notifyAll();
		}
	}
}