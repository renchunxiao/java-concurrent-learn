package com.rcx.concurrent.semaphore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 
 * 使用信号量来实现一个有界的阻塞容器
 * 
 * @author renchunxiao
 */
public class BounderHashSet<T> {
	private final Set<T> set;
	private final Semaphore semaphore;

	public BounderHashSet(int bound) {
		this.set = Collections.synchronizedSet(new HashSet<T>());
		this.semaphore = new Semaphore(bound);
	}

	public boolean add(T t) throws Exception {
		semaphore.acquire();// 添加元素前获取一个信号量
		boolean wasAdded = false;
		try {
			wasAdded = set.add(t);
			return wasAdded;
		} finally {
			if (!wasAdded) {// 添加元素失败，释放信号量
				semaphore.release();
			}
		}
	}

	public boolean remove(Object o) {
		boolean wasRemoved = set.remove(o);
		if (wasRemoved) {
			semaphore.release();// 删除一个元素，释放一个信号量
		}
		return wasRemoved;
	}
}
