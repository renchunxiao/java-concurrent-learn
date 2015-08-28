package com.rcx.concurrent.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 死锁
 * 
 * 单一线程池执行多个任务，有可能产生死锁
 * 
 * RenderPageTask 使用 service 实现任务，RenderPageTask 内部又使用了 service 执行了 2 次 LoadTask,
 * 从而产生了死锁
 * 
 * @author renchunxiao
 *
 */
public class DeadLock {
	ExecutorService service = Executors.newSingleThreadExecutor();

	public static void main(String[] args) throws Exception {
		new DeadLock().test();
	}

	public void test() throws Exception {
		Future<String> future = service.submit(new RenderPageTask());
		System.out.println(future.get());
	}

	class RenderPageTask implements Callable<String> {
		@Override
		public String call() throws Exception {
			Future<String> header, footer;
			header = service.submit(new LoadTask());
			footer = service.submit(new LoadTask());
			return header.get() + footer.get();
		}
	}

	class LoadTask implements Callable<String> {
		@Override
		public String call() throws Exception {
			Thread.sleep(2000);
			return "rcx";
		}
	}

}