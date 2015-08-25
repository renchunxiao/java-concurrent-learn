package com.rcx.concurrent.barrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class CyclicBarrierDemo {
	final int N;
	final float[][] data;
	final CyclicBarrier barrier;

	class Worker implements Runnable {
		int myRow;

		Worker(int row) {
			myRow = row;
		}

		public void run() {
			try {
				Thread.sleep(new Random().nextInt(5000));
				System.out.println(Thread.currentThread().getName() + " is work!");
				barrier.await();// 当 N 个线程都到达了关卡点后，所有线程都会被释放
			} catch (InterruptedException ex) {
				return;
			} catch (BrokenBarrierException ex) {
				return;
			}
		}
	}

	public CyclicBarrierDemo(float[][] matrix) {
		data = matrix;
		N = matrix.length;
		barrier = new CyclicBarrier(N, new Runnable() {// 创建 N 个线程的关卡
					public void run() {
						System.out.println("全部子工作完成，我开始进行计算");
					}
				});
		for (int i = 0; i < N; ++i)
			// 创建 N 个线程
			new Thread(new Worker(i)).start();

		System.out.println("---end---");
	}

	public static void main(String[] args) {
		CyclicBarrierDemo solver = new CyclicBarrierDemo(new float[][] { { 1.0f, 2.2f }, { 2.2f, 3.3f } });
	}
}