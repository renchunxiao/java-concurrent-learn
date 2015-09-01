package com.rcx.concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 一个可重入的互斥锁，它具有与使用 synchronized 方法和语句所访问的隐式监视器锁相同的一些基本行为和语义。
 * 
 * 
 * @author renchunxiao
 *
 */
public class ReentrantLockDemo {
	private Lock lock = new ReentrantLock();

	public static void main(String[] args) {
		final ReentrantLockDemo demo = new ReentrantLockDemo();
		ExecutorService service = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			service.execute(new Runnable() {
				@Override
				public void run() {
					demo.lock();
				}
			});
		}
	}

	public void lock() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " begin");
			// do something
			Thread.sleep(200);
			System.out.println(Thread.currentThread().getName() + " eng");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * tryLock 方法
	 * 
	 * 如果该锁没有被另一个线程保持，并且立即返回 true 值。
	 * 
	 * 如果锁被另一个线程保持，则此方法将立即返回 false 值。
	 * 
	 */
	public void tryLock() {
		while (true) {
			if (lock.tryLock()) {
				try {
					System.out.println(Thread.currentThread().getName() + " begin");
					// do something
					Thread.sleep(200);
					System.out.println(Thread.currentThread().getName() + " eng");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}
	}

}