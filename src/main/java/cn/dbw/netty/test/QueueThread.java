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
                //֮����ÿ�ε�ǰ�̶߳�Ҫsleep(10)��Ϊ�˱�֤�̵߳�ִ��˳��
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
		thread4.join();//��Ϊ�߳�0-4ֹͣ������ִ�еģ����������֤���߳����߳�4��ֹͣ����ô���ܱ�֤���߳������رյ�
		System.out.println("�����ʱ��"+ (System.currentTimeMillis()-startTime ));
		
	}
}
