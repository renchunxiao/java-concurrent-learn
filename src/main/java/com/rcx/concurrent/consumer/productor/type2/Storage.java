package com.rcx.concurrent.consumer.productor.type2;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
	private static final int SIZE = 20;
	private Lock lock = new ReentrantLock();
	private Condition notEmpty = lock.newCondition();
	private Condition notFull = lock.newCondition();
	private LinkedList<Object> list = new LinkedList<Object>();

	public void productor(int num) throws Exception {
		lock.lock();
		while (list.size() + num > SIZE) {
			System.out.println("【要生产的产品数量】:" + num + " \t【库存量】:" + list.size() + " \t暂时不能执行生产任务!");
			notFull.await();// 不满新号等待，表示当前不能生产，容器满了
		}
		for (int i = 0; i < num; i++) {
			list.add(new Object());
		}
		notEmpty.signalAll();// 通知所有不空的新号，代表消费者可以进行消费
		System.out.println("【已经生产产品数】:" + num + " \t【现仓储量为】:" + list.size());
		lock.unlock();
	}

	public void consumer(int num) throws Exception {
		lock.lock();
		while (list.size() < num) {
			System.out.println("【要消费的产品数量】:" + num + " \t【库存量】:" + list.size() + " \t暂时不能执行生产任务!");
			notEmpty.await();
		}
		for (int i = 0; i < num; i++) {
			list.remove();
		}
		System.out.println("【已经消费产品数】:" + num + " \t【现仓储量为】:" + list.size());
		notFull.signalAll();
		lock.unlock();
	}

}
