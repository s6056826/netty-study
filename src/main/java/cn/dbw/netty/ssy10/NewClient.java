package cn.dbw.netty.ssy10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * �㿽�����ļ����ݴ��䷽��
 * @author wangxk
 *
 */
public class NewClient {
    
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		    
	       FileInputStream fileInputStream = new FileInputStream("C:\\Users\\wangxk\\Desktop\\eclipse-jee-helios-SR2-win32.zip");
	       FileChannel channel = fileInputStream.getChannel();
	       SocketChannel socketChannel = SocketChannel.open();
	       socketChannel.connect(new InetSocketAddress("localhost", 9999));
	       socketChannel.configureBlocking(true);
	       long currentTimeMillis = System.currentTimeMillis();
	       //��copy�ĺ��ķ��� ��channel��ֱ�ӽ��ں˻�����������ֱ��ת�Ƶ�Ŀ�껺����ͨ���У�����ʵ�ʵĿ���
	       channel.transferTo(0, channel.size(),socketChannel);
	       System.out.println("������ϣ�"+(System.currentTimeMillis()-currentTimeMillis));
	       socketChannel.close();
	       channel.close();
	       /*
	        * This method is potentially much more efficient than a simple loop
     * that reads from this channel and writes to the target channel.  Many
     * operating systems can transfer bytes directly from the filesystem cache
     * to the target channel without actually copying them. 
	        */
	       
	}
}
