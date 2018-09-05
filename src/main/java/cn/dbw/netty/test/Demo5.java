package cn.dbw.netty.test;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo5 extends Thread {
	private String threadName=null;
	private static volatile int flag=1;
	private static Object object=new Object();
	/**
	 * 编写一个程序，启动三个线程，三个线程的ID分别是A，B，C；，每个线程将自己的ID值在屏幕上打印3遍，打印顺序是ABCABC...
	 */
	public static void main(String[] args) {
		
		new Demo5("A").start();
		new Demo5("B").start();
		new Demo5("C").start();
		
	}
    
	
	public Demo5(String name)
	 {

		this.threadName=name;
	 }
	@Override
	public void run() {
		
		while (true) {
			synchronized (object) {
			if(flag==1&&threadName.equals("A")){
				System.out.println(threadName);
			}else if(flag==2&&threadName.equals("B")){
				System.out.println(threadName);
			}else if(flag==3&&threadName.equals("C")){
				System.out.println(threadName);
			}
			
				switch (flag) {
				case 1:
					if(threadName.equals("A"))
					flag=2;
					break;
				case 2:
					if(threadName.equals("B"))
					flag=3;
                    break;
                case 3:
                	if(threadName.equals("C"))
                	flag=1;
				}
			}	
		}
	}
}

