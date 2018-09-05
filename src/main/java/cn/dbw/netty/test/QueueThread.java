package cn.dbw.netty.test;

public class QueueThread implements Runnable{
	
	private Object current;
	private Object next;
	private int max=100;
	private String word;
		
	public QueueThread(Object current, Object next, String word) {
		this.current = current;
		this.next = next;
		this.word = word;
	}
 
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			synchronized (current) {
				synchronized (next) {
					System.out.println(word);
					next.notify();
				}
				try {
					current.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		
	}
	
	public static void main(String[] args) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		Object d = new Object();
		Object e = new Object();
                //之所以每次当前线程都要sleep(10)是为了保证线程的执行顺序
		new Thread(new QueueThread(a,b,"A")).start();
		Thread.sleep(10);
		new Thread(new QueueThread(b,c,"B")).start(); 
		Thread.sleep(10);
		new Thread(new QueueThread(c,d,"C")).start(); 
		Thread.sleep(10);
		new Thread(new QueueThread(d,e,"d")).start();
		Thread.sleep(10);
		Thread thread4 = new Thread(new QueueThread(e,a,"e")); 
		thread4.start();
		thread4.join();//因为线程0-4停止是依次执行的，所以如果保证主线程在线程4后停止，那么就能保证主线程是最后关闭的
		System.out.println("程序耗时："+ (System.currentTimeMillis()-startTime ));
		
	}
}
