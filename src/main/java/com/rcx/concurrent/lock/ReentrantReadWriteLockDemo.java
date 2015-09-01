package com.rcx.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用 ReentrantReadWriteLock 来包装 Map，来实现对读可多线程，读写和写写互斥。
 * 
 * @author renchunxiao
 *
 */
public class ReentrantReadWriteLockDemo {
	public static void main(String[] args) {
		ReadWriteMap<String, String> map = new ReadWriteMap<>(new HashMap<String, String>());
		map.put("key", "value");
	}
}

class ReadWriteMap<K, V> {
	private Map<K, V> map;
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private Lock readLock = readWriteLock.readLock();
	private Lock writeLock = readWriteLock.writeLock();

	public ReadWriteMap(Map<K, V> map) {
		this.map = map;
	}

	public V put(K key, V value) {
		writeLock.lock();
		try {
			return map.put(key, value);
		} finally {
			writeLock.unlock();
		}
	}

	public V get(K key) {
		readLock.lock();
		try {
			return map.get(key);
		} finally {
			readLock.unlock();
		}
	}

	// 其他方法省略
}