package com.rcx.concurrent.semaphore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class BounderHashSet<T> {
	private final Set<T> set;
	private final Semaphore semaphore;

	public BounderHashSet(int bound) {
		this.set = Collections.synchronizedSet(new HashSet<T>());
		this.semaphore = new Semaphore(bound);
	}

	public boolean add(T t) throws Exception {
		semaphore.acquire();
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
			semaphore.release();
		}
		return wasRemoved;
	}
}
