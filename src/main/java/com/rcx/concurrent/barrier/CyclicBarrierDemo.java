package com.rcx.concurrent.barrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 的简单 Demo
 * 
 * Demo 描述了需要对一个二维数组 data 进行处理，分解成数组长度的线程来处理 data 的每一行
 * 等所有行都处理完成之后再进行 data 部分的总处理
 * 
 * 
 * @author renchunxiao
 *
 */
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
				//to do data in myRow
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
						// to do all data
						System.out.println("全部子工作完成，我开始进行计算");
					}
				});
		for (int i = 0; i < N; ++i)
			// 创建 N 个线程
			new Thread(new Worker(i)).start();

	}

	public static void main(String[] args) {
		CyclicBarrierDemo solver = new CyclicBarrierDemo(new float[][] { { 1.0f, 2.2f }, { 2.2f, 3.3f } });
	}
}