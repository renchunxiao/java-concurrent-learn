package com.rcx.concurrent.executor;

import java.util.concurrent.Executor;

/**
 * 执行已提交的 Runnable 任务的对象。
 * 此接口提供一种将任务提交与每个任务将如何运行的机制（包括线程使用的细节、调度等）分离开来的方法。
 * 通常使用 Executor 而不是显式地创建线程。
 * 
 * 
 * @author renchunxiao
 *
 */
public class ExecutorDemo {
	public static void main(String[] args) {
		Executor executor = new ThreadExecutor();
		executor.execute(new Runnable() {
			@Override
			public void run() {
				// do something
			}
		});
		
		Executor executor2 = new SerialExecutor();
		executor2.execute(new Runnable() {
			@Override
			public void run() {
				// do something
			}
		});
		
	}
}

/**
 * 创建一个线程来执行 command
 * 
 * @author renchunxiao
 *
 */
class ThreadExecutor implements Executor {
	@Override
	public void execute(Runnable command) {
		new Thread(command).start();
	}
}

/**
 * 串行执行 command
 * 
 * @author renchunxiao
 *
 */
class SerialExecutor implements Executor {
	@Override
	public void execute(Runnable command) {
		command.run();
	}
}