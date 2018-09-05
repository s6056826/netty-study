package cn.dbw.netty.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo7 {
	
	public static void main(String[] args) throws InterruptedException {
		MyThread myThread1 = new MyThread("A");
		MyThread myThread2 = new MyThread("B");
		MyThread myThread3 = new MyThread("C");
		ExecutorService executor = Executors.newSingleThreadExecutor();
		ArrayBlockingQueue<MyThread> blockingQueue=new ArrayBlockingQueue<MyThread>(3);
		do {
			   if(blockingQueue.isEmpty()){
				    blockingQueue.put(myThread1);
					blockingQueue.put(myThread2);
					blockingQueue.put(myThread3);
			   }
			  
			   executor.execute(blockingQueue.take());
			
		} while (true);
	
	}

}

class MyThread implements Runnable{
	
	private String threadName=null;
    
	public MyThread(String threadName) {
		this.threadName=threadName;
	}
	
	public void run() {
		while(true){
			System.out.println(threadName);
			break;
		}
	}
	
}
