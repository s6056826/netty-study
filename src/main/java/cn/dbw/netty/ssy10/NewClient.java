package cn.dbw.netty.ssy10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 零拷贝的文件数据传输方法
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
	       //零copy的核心方法 该channel会直接将内核缓冲区的数据直接转移到目标缓冲区通道中，无需实际的拷贝
	       channel.transferTo(0, channel.size(),socketChannel);
	       System.out.println("传输完毕："+(System.currentTimeMillis()-currentTimeMillis));
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
