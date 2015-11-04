package com.rcx.concurrent.executor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskExecutorWebServer {
	private static final int NTHREADS = 100;
	private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);
	
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(80);
		while (true) {
			final Socket conn = serverSocket.accept();
			Runnable task = new Runnable() {
				@Override
				public void run() {
					//do something
				}
			};
			exec.execute(task);
		}
	}
}	
