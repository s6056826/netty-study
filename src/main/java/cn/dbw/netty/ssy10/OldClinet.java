package cn.dbw.netty.ssy10;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class OldClinet {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		SocketChannel channel=SocketChannel.open();
		channel.connect(new InetSocketAddress("localhost", 9999));
		channel.configureBlocking(true);
		
		FileInputStream fileInputStream = new FileInputStream("C:\\Users\\wangxk\\Desktop\\eclipse-jee-helios-SR2-win32.zip");
		FileChannel filechannel = fileInputStream.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(4096);
		long currentTimeMillis = System.currentTimeMillis();
		int readCount=0;
		while(-1!=readCount){
			//将数据读入到buffer中
			readCount = filechannel.read(buffer);
		    channel.write(buffer);
		    buffer.rewind();
		}
		System.out.println("Old传输完毕:"+(System.currentTimeMillis()-currentTimeMillis));
		channel.close();
		filechannel.close();
	}
}
