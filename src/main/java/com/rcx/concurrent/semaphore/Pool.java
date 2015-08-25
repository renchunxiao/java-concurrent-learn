package com.rcx.concurrent.semaphore;

import java.util.concurrent.Semaphore;

/**
 * 通过 Semaphore 来实现对 Pool 的访问进行限制，当到达最大值的时候，然后继续获取 Item 会进行阻塞
 * 
 * @author renchunxiao
 */
class Pool {
	private static final int MAX_AVAILABLE = 100;
	private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

	public Object getItem() throws InterruptedException {
		available.acquire();
		return getNextAvailableItem();
	}

	public void putItem(Object x) {
		if (markAsUnused(x))
			available.release();
	}

	protected Object[] items = new Object[MAX_AVAILABLE];//这里需要，初始化一些对象到 items 当中，这里省略
	protected boolean[] used = new boolean[MAX_AVAILABLE];

	protected synchronized Object getNextAvailableItem() {
		for (int i = 0; i < MAX_AVAILABLE; ++i) {
			if (!used[i]) {
				used[i] = true;
				return items[i];
			}
		}
		return null; // not reached
	}

	protected synchronized boolean markAsUnused(Object item) {
		for (int i = 0; i < MAX_AVAILABLE; ++i) {
			if (item == items[i]) {
				if (used[i]) {
					used[i] = false;
					return true;
				} else
					return false;
			}
		}
		return false;
	}
}